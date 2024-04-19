/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chg
 */
public class MyJDBC {
    
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/guicalorietracker";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "@.MySQL";
    private static final String DB_USER_TABLE = "users";
    private static final String DB_ITEMS_TABLE = "items";
    
      // register new users
    public static boolean register(String name,String lastname, String email,String username, String password) {
        try {
            // check if user exist in the DB
            if (!checkUser(username, email)) {
                try ( Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); //NOSONAR
                          PreparedStatement insertUser = connection.prepareStatement(
                                "INSERT INTO " + DB_USER_TABLE + "(NAME, LASTNAME, EMAIL, PASSWORD)" + " VALUES(?,?,?,?,?)"
                        )) {
                    insertUser.setString(1, name);
                    insertUser.setString(2, lastname);
                    insertUser.setString(3, email);
                    insertUser.setString(4, password);
                    // update DB
                    insertUser.executeUpdate();
                }
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    // check if the username or email exist in the user table before creating a new user
    private static boolean checkUser(String username, String email) {
        try ( Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); //NOSONAR
                  PreparedStatement checkUserExist = connection.prepareStatement( //NOSONAR
                        " SELECT * FROM " + DB_USER_TABLE + " WHERE BINARY USERNAME = ? OR EMAIL = ?")) {

            checkUserExist.setString(1, username);
            checkUserExist.setString(2, email);

            try ( ResultSet resultSet = checkUserExist.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    return false;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    // get the user id
    public static int getUserId(String email, String password) {
        int userId = -1; // default to indicate user not found
        try ( Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); //NOSONAR
                  PreparedStatement checkUserExist = connection.prepareStatement(
                        " SELECT * FROM " + DB_USER_TABLE + " WHERE BINARY EMAIL = ? AND BINARY PASSWORD = ?")) {

            checkUserExist.setString(1, email);
            checkUserExist.setString(2, password);

            ResultSet resultSet = checkUserExist.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("userID");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userId;
    }

    // check for valid user
    public static boolean validLogin(String email, String password) {
        int userId = getUserId(email, password);
        return userId != -1;
    }

}
