/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package MyJBDC;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chg
 */
public class MyJDBCTest {

    private Connection connection;

    @Before
    public void setUp() throws Exception {
        // Set up the H2 in-memory database connection
        connection = DriverManager.getConnection("jdbc:h2:mem:test");
        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement();

        // Create users table
        stmt.execute("CREATE TABLE users ("
                + "userID INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
                + "email VARCHAR(40) NOT NULL, "
                + "password VARCHAR(20) NOT NULL, "
                + "PRIMARY KEY (userID), "
                + "UNIQUE (email))");

        // Create user_profile table
        stmt.execute("CREATE TABLE user_profile ("
                + "userID INT NOT NULL, "
                + "name VARCHAR(20) DEFAULT NULL, "
                + "lastName VARCHAR(20) DEFAULT NULL, "
                + "gender VARCHAR(6) DEFAULT NULL, "
                + "DOB DATE DEFAULT NULL, "
                + "weight VARCHAR(5) DEFAULT NULL, "
                + "height VARCHAR(3) DEFAULT NULL, "
                + "PRIMARY KEY (userID), "
                + "CONSTRAINT user_profile_ibfk_1 FOREIGN KEY (userID) REFERENCES users (userID))");

        // Create items table
        stmt.execute("CREATE TABLE items ("
                + "itemsID INT NOT NULL GENERATED ALWAYS AS IDENTITY, "
                + "userID INT NOT NULL, "
                + "date DATE NOT NULL, "
                + "meal VARCHAR(40) NOT NULL, "
                + "items VARCHAR(40) NOT NULL, "
                + "quantity VARCHAR(5) DEFAULT NULL, "
                + "qtype VARCHAR(4) DEFAULT NULL, "
                + "calorie VARCHAR(4) NOT NULL, "
                + "fat VARCHAR(4) NOT NULL, "
                + "carbs VARCHAR(4) NOT NULL, "
                + "protein VARCHAR(4) NOT NULL, "
                + "PRIMARY KEY (itemsID), "
                + "CONSTRAINT items_ibfk_1 FOREIGN KEY (userID) REFERENCES users (userID))");

        stmt.execute("INSERT INTO users (EMAIL, PASSWORD) VALUES('123@123.cl','password')");
        // crete dataBase H2 in Memory connection and connect to test methods
        MyJDBC.setConnection(connection);
    }

    @Test
    public void testRegister() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("INSERT INTO users (email, password) VALUES ('test@example.com', 'password')");

        // Test the register method with an existing email
        assertFalse(MyJDBC.register("test@example.com", "newpassword"));
        // Test the register method with a new email
        assertTrue(MyJDBC.register("new@example.com", "newpassword"));
    }

    @Test
    public void testGetUserId() throws SQLException {
        // Insert a test user into the in-memory database
        Statement stmt = connection.createStatement();
        stmt.execute("INSERT INTO users (email, password) VALUES ('test@example.com', 'password')");

        // Test with a valid email and password
        int userId = MyJDBC.getUserId("test@example.com", "password");
        assertTrue(userId > 0);

        // Test with an invalid email
        userId = MyJDBC.getUserId("invalid@example.com", "password");
        assertEquals(-1, userId);

        // Test with an invalid password
        userId = MyJDBC.getUserId("test@example.com", "wrongpassword");
        assertEquals(-1, userId);
    }

    @Test
    public void testInsertUserProfile() throws SQLException {
        // Insert a test user into the in-memory database
        Statement stmt = connection.createStatement();
        stmt.execute("INSERT INTO users (email, password) VALUES ('test@example.com', 'password')");

        int userId = MyJDBC.getUserId("test@example.com", "password");
        LocalDate dob = LocalDate.of(1990, 5, 15);

        // Insert a new user profile
        MyJDBC.insertUserProfile("John", "Doe", "Male", dob, "75", "180", userId);

        // Verify the inserted user profile
        Map<String, String> profile = MyJDBC.getUserProfile(userId);
        assertEquals("John", profile.get("name"));
        assertEquals("Doe", profile.get("lastName"));
        assertEquals("Male", profile.get("gender"));
        assertEquals("15-05-1990", profile.get("DOB"));
        assertEquals("75", profile.get("weight"));
        assertEquals("180", profile.get("height"));

        // Update the user profile
        MyJDBC.insertUserProfile("Jane", "Smith", "Female", dob.plusYears(5), "65", "170", userId);

        // Verify the updated user profile
        profile = MyJDBC.getUserProfile(userId);
        assertEquals("Jane", profile.get("name"));
        assertEquals("Smith", profile.get("lastName"));
        assertEquals("Female", profile.get("gender"));
        assertEquals("15-05-1995", profile.get("DOB"));
        assertEquals("65", profile.get("weight"));
        assertEquals("170", profile.get("height"));
    }

    @Test
    public void testDeleteRow() throws SQLException {
        // Insert a test user into the in-memory database
        Statement stmt = connection.createStatement();
        stmt.execute("INSERT INTO users (email, password) VALUES ('test@example.com', 'password')");

        int userId = MyJDBC.getUserId("test@example.com", "password");

        // Insert a test item
        int itemId = MyJDBC.insertIntoItems(userId, "25-05-2023", "Breakfast", "Oatmeal", "1", "cup", "300", "5", "54", "5");

        // Verify that the item was inserted
        Map<String, List<Map<String, String>>> itemsInfo = MyJDBC.retrieveOneWeek(userId);
        assertFalse(itemsInfo.isEmpty());

        // Delete the item
        MyJDBC.deleteRow(String.valueOf(itemId));

        // Verify that the item was deleted
        itemsInfo = MyJDBC.retrieveOneWeek(userId);
        assertTrue(itemsInfo.isEmpty());
    }

    @After
    public void tearDown() throws Exception {
        // Rollback the transaction to undo any changes made during the test
        connection.rollback();
        connection.setAutoCommit(true);
        System.out.println("close call");
        MyJDBC.connectionClose();
    }
}
