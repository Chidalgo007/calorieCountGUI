/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import GUI.EnterCalories;
import MyJBDC.MyJDBC;
import com.formdev.flatlaf.FlatLaf;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author chg
 */
public final class AddLineManager {

    public AddLine getLine() {
        return line;
    }

    public AddMacros getMacros() {
        return addMacros;
    }

    private MacrosCalculations macroCal; // calculate the macros
    private AddMacros addMacros; // update the calories en macros total
    private AddLine line; // create the lines to add food information
    private API_DB_InfoManager api; // communicate with API and insert food to DB
    private EnterCalories EC; // provide updated date
    private AddLineWithInfo previousDayInfo; // in charge to add privous lines with food information

    private final String meal;
    private String date;

    public AddLineManager(String mealType, EnterCalories enterCal) {
        meal = mealType; // mealType (breakfast, lunch...etc)
        EC = enterCal; // to retrive updated date.
        // panel managed
        macroCal = new MacrosCalculations();
        addMacros = new AddMacros();
        line = new AddLine(this);
        api = new API_DB_InfoManager();
        previousDayInfo = new AddLineWithInfo(line, macroCal, this, meal);

    }

// ---------------------- ACTION LISTENER --------------------------------------
    public void handleDeleteButtonAction(JPanel lineToRemove) {
        int index = line.getLineArray().indexOf(lineToRemove);
        System.out.println("line to remove: " + index);
        if (index != -1) {
            String item_ID = line.getItemIDArray().get(index).getText();
            if (!item_ID.equals("-1")) {
                MyJDBC.deleteRow(item_ID); // remove row from DB 
            }

            // remove line box from conteiner
            line.getScrollablemiddleContent().remove(lineToRemove);

            // remvoe btnDelete from itself
            line.getDeleteBtnArray().remove(index);
            line.getLineArray().remove(index);
            line.getItemIDArray().remove(index);
            line.getQArray().remove(index);
            line.getQnArray().remove(index);
            line.getItemArray().remove(index);
            macroCal.getCalorieArray().remove(index);
            macroCal.getFatArray().remove(index);
            macroCal.getCarbsArray().remove(index);
            macroCal.getProteinArray().remove(index);

            // refresh container after removing the lone box to reflect the changes
            FlatLaf.revalidateAndRepaintAllFramesAndDialogs();

            macroCal.calculateMacro();
            refresh();
        }

    }

    public void handleSaveButtonAction(ActionEvent e) {

        String items = line.getItem().getText();
        String Quan = line.getQ().getText();
        String QnType = line.getQn().getSelectedItem().toString();

        if (line.fieldValidationQ() && line.fieldValidationItem()) {
            api.getAPIinfo(Quan, QnType, items);
            String cal = api.getCaloriesInfo().get("calories");
            line.getCalorie().setText(cal);
            macroCal.getCalorieArray().add(cal);
        }

        if (line.fieldValidationCalorie()) {
            // get fat, prot, and carb from API Map
            String food = api.getCaloriesInfo().get("food");
            // add them to the arrays
            macroCal.getFatArray().add(api.getCaloriesInfo().get("fat"));
            macroCal.getCarbsArray().add(api.getCaloriesInfo().get("carbs"));
            macroCal.getProteinArray().add(api.getCaloriesInfo().get("protein"));
            line.getItem().setText(food.replaceAll("[-'.,/]", " "));

            line.getItem().setFocusable(false);
            line.getQ().setFocusable(false);
            line.getQn().setFocusable(false);

            macroCal.calculateMacro();
            refresh();
            insertMyJDBC();
            SwingUtilities.invokeLater(() -> line.createLine());
        }
    }

//================================ UPDATE MACRO VALUES ================================
    public void refresh() {
        String stCalMacro = macroCal.getStCalMacro();
        String stFatMacro = macroCal.getStFatMacro();
        String stCarbsMacro = macroCal.getStCarbsMacro();
        String stProteinMacro = macroCal.getStProteinMacro();
        addMacros.setInfoValues(stFatMacro, stCarbsMacro, stProteinMacro, stCalMacro);
    }
//================================= INSERT INTO DB ====================================

    private void insertMyJDBC() {
        date = EC.shareDate;
        System.out.println("insert date " + date);
        String itemid = line.getItemID().getText().isEmpty() ? "-1" : line.getItemID().getText();
        String item = line.getItem().getText();
        String Quan = line.getQ().getText();
        String QnType = line.getQn().getSelectedItem().toString();
        String calorie = line.getCalorie().getText();
        String carbs = macroCal.getCarbsArray().get(macroCal.getCarbsArray().size() - 1);
        String fat = macroCal.getFatArray().get(macroCal.getFatArray().size() - 1);
        String protein = macroCal.getProteinArray().get(macroCal.getProteinArray().size() - 1);

        // return id of line inserted and add it to itemID of line
        int id = api.constructInsertion(itemid, date, meal, item, Quan, QnType, calorie, carbs, fat, protein);
        if (id != -1) {
            line.getItemID().setText(String.valueOf(id));
            line.getItemIDArray().add(line.getItemID());
        }
    }

}
