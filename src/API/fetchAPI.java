/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Main.Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author chg
 */
public class fetchAPI {

//----------------------------------------------------------------------------------------
    // fetch a single ingredient for calories information
    public static Map<String, String> fetchAPISingleCalories(String ing) {
        Map<String, String> calories = new HashMap<>();
        try {
            // Set the API endpoint URL
            String apiUrl = "https://api.edamam.com/api/nutrition-data";
            String appId = "9de20323";
            String appKey = "d6641e573606aa0604bef1b575fe0c12";
            String ingr = ing;//"500gr%20fried%20chicken"; spaces with %20
            String wrongIng = ing.replaceAll("%20", " ");

            // Construct the request URL with query parameters
            String requestUrl = apiUrl + "?app_id=" + appId + "&app_key=" + appKey + "&nutrition-type=logging&ingr=" + ingr;
            // Send the POST request
            URL url = new URL(requestUrl);
            // Open connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the reuest method to GET
            connection.setRequestMethod("GET");
            // Set the accepted header
            connection.setRequestProperty("accept", "application/json");
            // Get the response code
            int responseCode = connection.getResponseCode();
            // Check if the response code is HTTP OK (200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response;
                try ( // read the response from the connection input stream
                         BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    response = new StringBuilder();
                    while ((inputLine = read.readLine()) != null) {
                        response.append(inputLine);
                    }            
                }
                //---------------------getting food itemame ------------------------------------------
                // Parse the JSON response
                JSONParser parser = new JSONParser();
                JSONObject resultJsonObj;
                try {
                    resultJsonObj = (JSONObject) parser.parse(response.toString());
                    // Get the total nutrients object
                     JSONArray ingArray = (JSONArray) resultJsonObj.get("ingredients");

                    if (ingArray != null&&!ingArray.isEmpty()) {
                        JSONObject firstArrayItem = (JSONObject) ingArray.get(0);
                        JSONArray parsedArray = (JSONArray) firstArrayItem.get("parsed");
                        
                        if(parsedArray!=null&&!parsedArray.isEmpty()){
                            JSONObject parsedFirstArrayItem = (JSONObject) parsedArray.get(0);
                            String food = (String) parsedFirstArrayItem.get("food");
                            
                            calories.put("food", food);

                        }else{
                            System.out.println("error getting food name");
                        }
                    }else{
                        System.out.println("error getting ingrdients array");
                    }
                  // ------------------ calories and Macros--------------------------------------------  
                    JSONObject totalNutrientsKCal = (JSONObject) resultJsonObj.get("totalNutrientsKCal");
                    if (totalNutrientsKCal != null) {
                        // Get the calo, fat, carb, protein objects
                        JSONObject caloriesObj = (JSONObject) totalNutrientsKCal.get("ENERC_KCAL");
                        JSONObject proteObj = (JSONObject) totalNutrientsKCal.get("PROCNT_KCAL");
                        JSONObject fatObj = (JSONObject) totalNutrientsKCal.get("FAT_KCAL");
                        JSONObject carbObj = (JSONObject) totalNutrientsKCal.get("CHOCDF_KCAL");

                        if (caloriesObj != null) {
                            // Extract the calories value
                            Object calObj = caloriesObj.get("quantity");
                            String cal = calObj.toString();
                            calories.put("calories", cal);
                        } else {
                            System.out.println("wrong items ... " + wrongIng + ", refrase");
                            calories.put("calories", "-1");
                            return calories;
                        }
                        if (proteObj != null) {
                            Object pObj = proteObj.get("quantity");
                            String prot = pObj.toString();
                            calories.put("protein", prot);
                        } else {
                            System.out.println("protien error");
                        }
                        if (fatObj != null) {
                            Object fObj = fatObj.get("quantity");
                            String fat = fObj.toString();
                            calories.put("fat", fat);
                        } else {
                            System.out.println("fat error");
                        }
                        if (carbObj != null) {
                            Object cObj = carbObj.get("quantity");
                            String carbs = cObj.toString();
                            calories.put("carbs", carbs);
                        } else {
                            System.out.println("Carbs error");
                        }
                    } else {
                        System.out.println("Sorry I can compute " + wrongIng + " :(   try with another one");
                        calories.put("calories", "-1");
                        return calories;
                    }

                    //-------------------------------------------
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // if response code is not HTTP OK, print an error message
                System.out.println("Error; " + responseCode);
            }
            // close the connection
            connection.disconnect();

        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        return calories;
    }
}
