/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import API.fetchAPI;
import static GUI.EnterCalories.getTextDate;
import MyJBDC.MyJDBC;
import UserInfo.UserProfile;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chg
 */
public class API_DB_InfoManager {

    public String getFat() {
        return fat;
    }

    public String getCarbs() {
        return carbs;
    }

    public String getProtein() {
        return protein;
    }
    
    public Map<String, String> getCaloriesInfo() {
        return caloriesInfo;
    }
// to receive info from API
    private String fat;
    private String carbs;
    private String protein;

    // to receive info from API
    private Map<String, String> caloriesInfo = new HashMap<>();

    public API_DB_InfoManager() {
    }

    public void getAPIinfo(String Quan, String QnType, String items) {
        
            // calling the API    
            String ing;
            String ingM;
            ing = Quan + QnType + " " + items;
            ingM = ing.replaceAll(" ", "%20");

            // storing the API info in the Map
            caloriesInfo.clear();
            caloriesInfo = fetchAPI.fetchAPISingleCalories(ingM);
            //calories - fat - carbs - protein

    }

    // ----------------- construct Array to insert into MyJDBC -----------------
    public int constructInsertion(String itemid,String date,String meal,String item,String quantity,String qtype,
            String calorie,String carbs,String fat,String protein) {
        
        int userid = UserProfile.getID();

        int id = MyJDBC.insertIntoItems(itemid, userid, date, meal, item, quantity, qtype, calorie, carbs, fat, protein);

        return id;
    }
}