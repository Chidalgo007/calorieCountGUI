/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chg
 */
public class UserProfile {

    private static int ID;
    private static Map<String, String> profile = new HashMap();

    public UserProfile() {
    }

    /**
     * @return the ID
     */
    public static int getID() {
        return ID;
    }

    /**
     * @param aID the ID to set
     */
    public static void setID(int aID) {
        ID = aID;
    }

    /**
     * @return the profile
     */
    public static Map<String, String> getProfile() {
        return profile;
    }

    /**
     * @param aProfile the profile to set
     */
    public static void setProfile(Map<String, String> aProfile) {
        profile = aProfile;
        titleStyle();
    }

    private static void titleStyle() {
        String[] names = {"name", "lastName"};
        for (String st : names) {
            if (profile.containsKey(st)) {
                String toTitle = profile.get(st);
                if (!toTitle.isEmpty()) {
                    String newTitleStyle = toTitle.substring(0, 1).toUpperCase() + toTitle.substring(1).toLowerCase();
                    profile.put(st, newTitleStyle);
                }
            }
        }
    }

}
