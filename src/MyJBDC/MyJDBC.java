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
                                "INSERT INTO " + DB_USER_TABLE + "(EMAIL, PASSWORD)" + " VALUES(?,?)"
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

}
