/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import MyJBDC.MyJDBC;
import UserInfo.UserProfile;
import com.formdev.flatlaf.FlatLaf;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chg
 *
 * this class add a line for each day where there is food information for the
 * user
 *
 */
public class AddLineWithInfo {

    private static Map<String, List<Map<String, String>>> previousDayInfo = new HashMap<>();
    private static AddLine line;
    private static MacrosCalculations macroCal;
    private static AddLineManager lineManager;
    private static String mealType;

    public AddLineWithInfo(AddLine newLine, MacrosCalculations macroCalo, AddLineManager manager, String meal) {
        line = newLine;
        macroCal = macroCalo;
        lineManager = manager;
        mealType = meal;
    }

    public static void retrieveOneWeekInfo() {
        previousDayInfo = MyJDBC.retrieveOneWeek(UserProfile.getID());

//        for (Map.Entry<String, List<Map<String, String>>> st : previousDayInfo.entrySet()) {
//            System.out.println(st);
//        }
    }

    public static void allocateInfo(LocalDate date) {
        retrieveOneWeekInfo();
        removeAll();
        line.createLine();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateSelected = date.format(dateFormat);
        System.out.println("date in class AddLineWithInfo" + dateSelected);
        List<Map<String, String>> dateStored = previousDayInfo.get(dateSelected);
        if (dateStored != null) {
            for (int i = 0; i < dateStored.size(); i++) {
                if (dateStored.get(i).get("meal").equalsIgnoreCase(mealType)) {

                    line.getItemID().setText(dateStored.get(i).get("itemsID"));
                    line.getQ().setText(dateStored.get(i).get("quantity"));
                    line.getQn().setSelectedItem(dateStored.get(i).get("qtype"));
                    line.getItem().setText(dateStored.get(i).get("items"));
                    line.getCalorie().setText(dateStored.get(i).get("calorie"));

                    macroCal.getCalorieArray().add(dateStored.get(i).get("calorie"));
                    macroCal.getFatArray().add(dateStored.get(i).get("fat"));
                    macroCal.getCarbsArray().add(dateStored.get(i).get("carbs"));
                    macroCal.getProteinArray().add(dateStored.get(i).get("protein"));
                }
                line.createLine();
                System.out.println("delete btn size: " + line.getDeleteBtnArray().size());
            }
            macroCal.calculateMacro();
            lineManager.refresh();
            FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
        }

    }

    private static void removeAll() {

//        line.getDeleteBtnArray().clear();
        line.getScrollablemiddleContent().removeAll();
        line.getLineArray().clear();
        line.getItemIDArray().clear();
        line.getQArray().clear();
        line.getQnArray().clear();
        line.getItemArray().clear();
        macroCal.getCalorieArray().clear();
        macroCal.getFatArray().clear();
        macroCal.getCarbsArray().clear();
        macroCal.getProteinArray().clear();

        // refresh container after removing the lone box to reflect the changes
        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();

        macroCal.calculateMacro();
        lineManager.refresh();

    }

}
