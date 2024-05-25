/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package API;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chg
 */
public class fetchAPITest {

    String apiUrl = "https://api.edamam.com/api/nutrition-data";
    String appId = "9de20323";
    String appKey = "d6641e573606aa0604bef1b575fe0c12";

    public fetchAPITest() {
    }

    // Method to get the HTTP status code from the API
    public int getHttpStatus(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        return responseCode;
    }

    /**
     * Test of fetchAPISingleCalories method, of class fetchAPI.
     */
    @Test
    public void testFetchAPISingleCalories() throws IOException {
        System.out.println("fetchAPISingleCalories");
        String ing = "1+apple"; // Example ingredient
        String requestUrl = apiUrl + "?app_id=" + appId + "&app_key=" + appKey + "&nutrition-type=logging&ingr=" +ing;
        int statusCode = getHttpStatus(requestUrl);
        assertEquals(200, statusCode);
    }

}
