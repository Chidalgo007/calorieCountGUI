/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import MyJBDC.MyJDBC;
import com.formdev.flatlaf.FlatLaf;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author chg
 */
public final class AddLineManager implements ActionListener {
    
    public AddLine getLine(){
        return line;
    }
    public AddMacros getMacros(){
        return addMacros;
    }

    private MacrosCalculations macroCal;
    private AddMacros addMacros;
    private AddLine line;
    private API_DB_InfoManager api;
    private final String meal;

    public AddLineManager(String mealType) {
        meal = mealType;
        macroCal = new MacrosCalculations();
        addMacros = new AddMacros();
        line = new AddLine();
        api = new API_DB_InfoManager();

    }

// ---------------------- ACTION LISTENER --------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {

        // get the index of the button clicked
        int index = -1;
        for (int i = 0; i < line.getDeleteBtnArray().size(); i++) {
            if (e.getSource() == line.getDeleteBtnArray().get(i)) {
                index = i;
                break;
            }
        }
        //check if items come from the DB to delete directly or are only local stored
        if (index != -1) {

            if (!line.getItemArray().isEmpty()) {
                String item_ID = line.getItemArray().get(index).getText();
                if (!item_ID.equals("-1")) {
                    MyJDBC.deleteRow(item_ID);
                }
            }

            // remvoe btnDelete from itself
            line.getDeleteBtnArray().remove(index);
            // remove line box from conteiner
            JPanel lineToRemove = line.getLineArray().get(index);
            line.getScrollablemiddleContent().remove(lineToRemove);

            List<List<?>> allArrays = Arrays.asList(line.getLineArray(), line.getItemIDArray(), line.getQArray(), line.getQnArray(), line.getItemArray(),
                    macroCal.getCalorieArray(), macroCal.getFatArray(), macroCal.getCarbsArray(), macroCal.getProteinArray());

            for (List<?> array : allArrays) {
                array.remove(index);
            }

            // refresh container after removing the lone box to reflect the changes
            FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
            macroCal.calculateMacro();
            refresh();
        }

        if (e.getSource() == line.getSave()) {

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
                insertMyJSBC();
                SwingUtilities.invokeLater(() -> line.createLine());
            }
        }
    }

    private void refresh() {
        String stCalMacro = macroCal.getStCalMacro();
        String stFatMacro = macroCal.getStFatMacro();
        String stCarbsMacro = macroCal.getStCarbsMacro();
        String stProteinMacro = macroCal.getStProteinMacro();
        addMacros.setInfoValues(stFatMacro, stCarbsMacro, stProteinMacro, stCalMacro);
    }

    private void insertMyJSBC() {
        String date = "30-04-2024";
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
