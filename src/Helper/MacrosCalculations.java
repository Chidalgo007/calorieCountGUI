/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author chg
 */
public class MacrosCalculations {

    public String getStCalMacro() {
        return stCalMacro;
    }

    public String getStFatMacro() {
        return stFatMacro;
    }

    public String getStCarbsMacro() {
        return stCarbsMacro;
    }

    public String getStProteinMacro() {
        return stProteinMacro;
    }

    public List<String> getCarbsArray() {
        return carbsArray;
    }

    public List<String> getFatArray() {
        return fatArray;
    }

    public List<String> getProteinArray() {
        return proteinArray;
    }

    public List<String> getCalorieArray() {
        return calorieArray;
    }

    // for cal of macros
    private int totalCal;
    private int totalFat;
    private int totalCarb;
    private int totalProtein;

    private String stCalMacro;
    private String stFatMacro;
    private String stCarbsMacro;
    private String stProteinMacro;

    private final List<String> carbsArray;
    private final List<String> fatArray;
    private final List<String> proteinArray;
    private final List<String> calorieArray;

    public MacrosCalculations() {
        carbsArray = new LinkedList<>();
        fatArray = new LinkedList<>();
        proteinArray = new LinkedList<>();
        calorieArray = new LinkedList<>();

    }

    //========================= Macros =========================================
    public void calculateMacro() {
        totalCal = 0;
        totalFat = 0;
        totalCarb = 0;
        totalProtein = 0;

        for (String s : calorieArray) {
            totalCal += Integer.parseInt(s);
        }
        for (String s : fatArray) {
            totalFat += Integer.parseInt(s);
        }

        for (String s : carbsArray) {
            totalCarb += Integer.parseInt(s);
        }
        for (String s : proteinArray) {
            totalProtein += Integer.parseInt(s);
        }
        refreshValue(); // parent window, breakfast, lunch, dinner, snacks

    }

    private void refreshValue() {
        float cal = totalCal;
        float fat = totalFat;
        float carbs = totalCarb;
        float protein = totalProtein;
        if (cal != 0) {
            float fatPercentage = (fat / cal) * 100;
            float cabsPercentage = (carbs / cal) * 100;
            float proPercentage = (protein / cal) * 100;

            stFatMacro = String.format("%.1f%%", fatPercentage);
            stCarbsMacro = String.format("%.1f%%", cabsPercentage);
            stProteinMacro = String.format("%.1f%%", proPercentage);
            stCalMacro = String.valueOf(totalCal);

        }else {
            stFatMacro = "0%";
            stCarbsMacro = "0%";
            stProteinMacro = "0%";
            stCalMacro = "0";
        }

    }

}
