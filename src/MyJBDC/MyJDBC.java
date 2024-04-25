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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author chg
 */
public class MyJDBC {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/ct";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "@.MySQL";
    private static final String DB_USER_TABLE = "users";
    private static final String DB_USER_PROFILE = "user_profile";
    private static final String DB_ITEMS_TABLE = "items";

    // register new users
    public static boolean register(String email, String password) {
        try {
            // check if user exist in the DB
            if (!checkUser(email)) {
                try ( Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); //NOSONAR
                          PreparedStatement insertUser = connection.prepareStatement(
                                "INSERT INTO " + DB_USER_TABLE + "(EMAIL, PASSWORD) VALUES(?,?)"
                        )) {

                    insertUser.setString(1, email);
                    insertUser.setString(2, password);
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
    private static boolean checkUser(String email) {
        try ( Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); //NOSONAR
                  PreparedStatement checkUserExist = connection.prepareStatement( //NOSONAR
                        " SELECT * FROM " + DB_USER_TABLE + " WHERE BINARY email = ? ")) {

            checkUserExist.setString(1, email);

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

    public static void insertUserProfile(String name, String lastName, String gender, LocalDate date, String weight, String height, int ID) {
        Date DOB = Date.valueOf(date);
        String checkUser = "SELECT COUNT(*) FROM " + DB_USER_PROFILE + " WHERE USERID = ?";
        String inserInfo = "INSERT INTO " + DB_USER_PROFILE + " VALUES(?,?,?,?,?,?,?)";
        String updateInfo = "UPDATE " + DB_USER_PROFILE + " SET NAME=?,LASTNAME=?,GENDER=?,DOB=?,WEIGHT=?,HEIGHT=? WHERE USERID = ?";
        try ( Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);//NOSONAR
                  PreparedStatement checkUserProfile = connection.prepareStatement(checkUser);//NOSONAR
                  PreparedStatement insertIntoProfile = connection.prepareStatement(inserInfo);//NOSONAR
                  PreparedStatement updateIntoProfile = connection.prepareStatement(updateInfo)) {

            checkUserProfile.setInt(1, ID);
            ResultSet resultSet = checkUserProfile.executeQuery();
            resultSet.next();
            int rowCount = resultSet.getInt(1);

            if (rowCount == 0) {

                insertIntoProfile.setInt(1, ID);
                insertIntoProfile.setString(2, name);
                insertIntoProfile.setString(3, lastName);
                insertIntoProfile.setString(4, gender);
                insertIntoProfile.setDate(5, DOB);
                insertIntoProfile.setString(6, weight);
                insertIntoProfile.setString(7, height);

                insertIntoProfile.executeUpdate();
            } else {
                updateIntoProfile.setString(1, name);
                updateIntoProfile.setString(2, lastName);
                updateIntoProfile.setString(3, gender);
                updateIntoProfile.setDate(4, DOB);
                updateIntoProfile.setString(5, weight);
                updateIntoProfile.setString(6, height);
                updateIntoProfile.setInt(7, ID);

                updateIntoProfile.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Map<String, String> getUserProfile(int ID) {
        Map<String, String> profile = new HashMap();
        try ( Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);//NOSONAR
                  PreparedStatement getProfileInfo = connection.prepareStatement(
                        " SELECT * FROM " + DB_USER_PROFILE + " WHERE USERID = ?")) {

            getProfileInfo.setInt(1, ID);

            try ( ResultSet resultSet = getProfileInfo.executeQuery()) {
                String[] st = {"name", "lastName", "gender", "DOB", "weight", "height"};
                if (resultSet.next()) {
                    for (String string : st) {
                        String s = resultSet.getString(string);
                        if (s != null) {
                            if (string.equalsIgnoreCase("DOB")) {
                                Date dob = resultSet.getDate(string);
                                if (dob != null) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                    s = dateFormat.format(dob);
                                    profile.put(string, s);
                                }
                            }
                            profile.put(string, s);
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profile;
    }

    public static void insertIntoItems(int itemsID, int userID, LocalDate dates, String meal, String items, String quantity,
            int calorie, int carbs, int fat, int protein) {
        Date date = Date.valueOf(dates);

        String inserCal = "INSERT INTO " + DB_ITEMS_TABLE + " VALUES(?,?,?,?,?,?,?,?)";
        String updateCal = "UPDATE " + DB_ITEMS_TABLE + " "
                + "SET DATE=?,MEAL=?, ITEMS=?, QUANTITY=?, CALORIE=?,CARBS=?,FAT=?,PROTEIN=?"
                + " WHERE ITEMSID=? AND USERID = ?";

        try ( Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);//NOSONAR
                  PreparedStatement inserCalo = connection.prepareStatement(inserCal);//NOSONAR
                  PreparedStatement updateCalo = connection.prepareStatement(updateCal)) {

            if (itemsID > -1) {
                inserCalo.setDate(1, date);
                inserCalo.setString(2, meal);
                inserCalo.setString(3, quantity);
                inserCalo.setInt(4, calorie);
                inserCalo.setInt(5, carbs);
                inserCalo.setInt(6, fat);
                inserCalo.setInt(7, protein);

                inserCalo.executeUpdate();
            } else {
                inserCalo.setInt(1, userID);
                inserCalo.setDate(2, date);
                inserCalo.setString(3, meal);
                inserCalo.setString(4, quantity);
                inserCalo.setInt(5, calorie);
                inserCalo.setInt(6, carbs);
                inserCalo.setInt(7, fat);
                inserCalo.setInt(8, protein);

                updateCalo.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
