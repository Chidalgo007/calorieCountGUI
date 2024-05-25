/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyJBDC;

import com.mysql.cj.jdbc.DatabaseMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author chg
 */
public class MyJDBC {

    private static final String DB_URL = "jdbc:derby:CalorieTracker;create=true";
    private static final String DB_USERNAME = "CHG";
    private static final String DB_PASSWORD = "CHG";
    private static final String DB_USER_TABLE = "users";
    private static final String DB_USER_PROFILE = "user_profile";
    private static final String DB_ITEMS_TABLE = "items";
    private static Connection connection = null;

    // start server connection
    public static void connection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // register new users
    public static boolean register(String email, String password) {
        try {
            // check if user exist in the DB
            if (!checkUser(email)) {
                try ( PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + DB_USER_TABLE + "(EMAIL, PASSWORD) VALUES(?,?)")) {

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
        try ( PreparedStatement checkUserExist = connection.prepareStatement(
                " SELECT * FROM " + DB_USER_TABLE + " WHERE EMAIL = ? ")) {

            checkUserExist.setString(1, email);

            try ( ResultSet resultSet = checkUserExist.executeQuery()) {
                if (!resultSet.next()) {
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
        try ( PreparedStatement checkUserExist = connection.prepareStatement(
                " SELECT * FROM " + DB_USER_TABLE + " WHERE EMAIL = ? AND PASSWORD = ?")) {

            checkUserExist.setString(1, email);
            checkUserExist.setString(2, password);

            try ( ResultSet resultSet = checkUserExist.executeQuery()) {

                if (resultSet.next()) {
                    userId = resultSet.getInt("userID");
                }
            }
        } catch (SQLException ex) {
            System.out.println("user not exist");
        }

        return userId;
    }

    public static void insertUserProfile(String name, String lastName, String gender, LocalDate date, String weight, String height, int ID) {
        Date DOB = Date.valueOf(date);
        String checkUser = "SELECT COUNT(*) FROM " + DB_USER_PROFILE + " WHERE USERID = ?";
        String inserInfo = "INSERT INTO " + DB_USER_PROFILE + " VALUES(?,?,?,?,?,?,?)";
        String updateInfo = "UPDATE " + DB_USER_PROFILE + " SET NAME=?,LASTNAME=?,GENDER=?,DOB=?,WEIGHT=?,HEIGHT=? WHERE USERID = ?";
        try ( PreparedStatement checkUserProfile = connection.prepareStatement(checkUser);//NOSONAR
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
        try ( PreparedStatement getProfileInfo = connection.prepareStatement(
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

    public static void deleteRow(String itemsId) {
        int itemsID = Integer.parseInt(itemsId);
        try ( PreparedStatement deleteRow = connection.prepareStatement(
                "DELETE FROM " + DB_ITEMS_TABLE + " WHERE ITEMSID=?")) {

            deleteRow.setInt(1, itemsID);
            deleteRow.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int insertIntoItems(int userID, String dates, String meal, String items, String quantity,
            String qtype, String calorie, String carbs, String fat, String protein) {
        try {
//            int itemsID = Integer.parseInt(itemsId);
            SimpleDateFormat dateforamt = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = dateforamt.parse(dates);
            Date sqlDate = new Date(utilDate.getTime());
            int generatedId = -1; // Default value in case insertion fails

            String inserCal = "INSERT INTO " + DB_ITEMS_TABLE + ""
                    + " (userID, date, meal, items, quantity, qtype, calorie, fat, carbs, protein) "
                    + " VALUES(?,?,?,?,?,?,?,?,?,?)";

            try ( PreparedStatement inserCalo = connection.prepareStatement(inserCal, Statement.RETURN_GENERATED_KEYS)) {

                inserCalo.setInt(1, userID);
                inserCalo.setDate(2, sqlDate);
                inserCalo.setString(3, meal);
                inserCalo.setString(4, items);
                inserCalo.setString(5, quantity);
                inserCalo.setString(6, qtype);
                inserCalo.setString(7, calorie);
                inserCalo.setString(8, fat);
                inserCalo.setString(9, carbs);
                inserCalo.setString(10, protein);

                int rowsAffected = inserCalo.executeUpdate();

                if (rowsAffected > 0) {
                    try ( ResultSet generatedKeys = inserCalo.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedId = generatedKeys.getInt(1);
                        } else {
                            throw new SQLException("Failed to retrieve generated ID.");
                        }
                    }
                } else {
                    throw new SQLException("Insertion failed, no rows affected.");
                }

            } catch (SQLException ex) {
                Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }

            return generatedId;
        } catch (ParseException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static Map<String, List< Map<String, String>>> retrieveOneWeek(int userID) {

        Map<String, List<Map<String, String>>> dateItemsInfo = new LinkedHashMap<>();

        try ( PreparedStatement retriveInfo = connection.prepareStatement(
                "SELECT * FROM " + DB_ITEMS_TABLE + " WHERE USERID = ? ")) {

            retriveInfo.setInt(1, userID);

            String[] items = {"itemsID", "meal", "items", "quantity", "qtype", "calorie", "fat", "carbs", "protein"};
            try ( ResultSet result = retriveInfo.executeQuery()) {

                while (result.next()) {
                    // assign date for grouped Map (first map)
                    Date dbDate = result.getDate("date");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String date = dateFormat.format(dbDate);

                    // Get or create the list for the current date
                    List<Map<String, String>> listItems = dateItemsInfo.getOrDefault(date, new LinkedList<>());
                    // make map with information
                    Map<String, String> itemsInfo = new LinkedHashMap<>();

                    for (String st : items) {
                        String value = result.getString(st);
                        if (st.equals("itemsID")) {
                            value = String.valueOf(result.getInt(st));
                        }
                        itemsInfo.put(st, value);
                    }
                    listItems.add(itemsInfo);

                    dateItemsInfo.put(date, listItems);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dateItemsInfo;
    }

    public static void connectionClose() {
        System.out.println("connection close");
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MyJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// ====================== for testing purpose ==================================
    public static void setConnection(Connection conn) {
        connection = conn;
    }
}
