/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import MyJBDC.MyJDBC;
import UserInfo.UserProfile;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chg
 */
public class RetrieveOneWeekInfo {

    public static Map<String,List<Map<String, String>>> getWeeklyInfo() {
        return weeklyInfo;
    }

    private static Map<String, List<Map<String, String>>> weeklyInfo = new LinkedHashMap<>();

    public RetrieveOneWeekInfo() {
    }

    public static void retrieveOneWeekInfo() {
        System.out.println("calling data");
        System.out.println("user id "+UserProfile.getID());
        weeklyInfo = MyJDBC.retrieveOneWeek(UserProfile.getID());
        System.out.println(weeklyInfo.size());
        for (Map.Entry<String, List<Map<String, String>>> st : weeklyInfo.entrySet()) {
            System.out.println(st);
        }
    }

}
