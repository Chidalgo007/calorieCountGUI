/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import MyJBDC.MyJDBC;
import UserInfo.UserProfile;
import com.formdev.flatlaf.FlatLaf;
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

    private Map<String, List<Map<String, String>>> previousDayInfo = new HashMap<>();
    private final AddLine line;
    private final MacrosCalculations macroCal;
    private final AddLineManager lineManager;
    private final String mealType;

    public AddLineWithInfo(AddLine newLine, MacrosCalculations macroCalo, AddLineManager manager, String meal) {
        line = newLine;
        macroCal = macroCalo;
        lineManager = manager;
        mealType = meal;
    }

    public void retrieveInfo() {
        previousDayInfo = MyJDBC.retrieveOneWeek(UserProfile.getID());
//     ------- to check information retrived by the API----------------------------        
//        for (Map.Entry<String, List<Map<String, String>>> st : previousDayInfo.entrySet()) {
//            System.out.println(st);
//        }
    }

    // create new line with information on it according to the day slelected and the meal
    public void allocateInfo(String date) {
        retrieveInfo();
        removeAll();
        line.createLine();

        List<Map<String, String>> dateStored = previousDayInfo.get(date);
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
                if (!line.getItemID().getText().isEmpty()||!line.getItemID().getText().equals("-1")){
                    line.addActionListenerToDeleteBtn();
                }
                if (!line.getCalorie().getText().isEmpty()) { // prevent to create lines with empty information
                    line.createLine();
                }
            }
            macroCal.calculateMacro();
            lineManager.refresh();
            FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
        }

    }

    // when click in a meal type, BF, Lunch...remove all the information 
    // displayed and display updated information
    private void removeAll() {

        line.getDeleteBtnArray().clear();
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

        // refresh container after removing the line box to reflect the changes
        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();

        macroCal.calculateMacro();
        lineManager.refresh();

    }

}
