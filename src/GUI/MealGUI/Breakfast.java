/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MealGUI;

import Constants.Constants;
import static GUI.EnterCalories.getTextDate;
import Helper.AddLine;
import Helper.AddMacros;
import MyJBDC.MyJDBC;
import UserInfo.UserProfile;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public class Breakfast extends JPanel {

    private static String date;
    private static AddLine addLine;
    private static AddMacros macros;

    public Breakfast() {
        addGUIComponents();
        setLayout(new MigLayout("wrap, fillx", "[]", "[]"));
        this.setBackground(Constants.COLOR_BACK);
//        date = LocalDate.now().toString();
//        while (true) {
//            if (addLine.getReadyToInsert()) {
//                constructInsertion();
//                addLine.setReadyToInsert(false);
//            }
//        }
    }

    private void addGUIComponents() {
        //  header constainer-------------------------------------
        JPanel header = new JPanel(new MigLayout("fillx, insets 0", "[grow, fill]push[]", "[]"));
        header.setOpaque(false);

        JLabel breakfast = new JLabel("Breakfast");
        breakfast.setFont(Constants.FONT_SemiBold.deriveFont(Font.PLAIN, 15));

        header.add(breakfast, "growx");

        // macros constiner -------------------------------------
        macros = new AddMacros();
        header.add(macros.getCaloContainer());

        this.add(header);

        // ---center panel calories --------------------------------------------
        addLine = new AddLine();
        this.add(getLine().getMiddleContent());

    }

    public AddLine getLine() {
        return addLine;
    }

    // ----- macros --------------------------------------------------------
    public static void refreshValue() {

        float cal = addLine.getTotalCal();
        float f = addLine.getTotalFat();
        float c = addLine.getTotalCarb();
        float p = addLine.getTotalProtein();
        if (cal != 0) {
            float fatPercentage = (f / cal) * 100;
            float cabsPercentage = (c / cal) * 100;
            float proPercentage = (p / cal) * 100;

            macros.getFat().setText(String.format("%.1f%%", fatPercentage));
            macros.getCarbs().setText(String.format("%.1f%%", cabsPercentage));
            macros.getProtein().setText(String.format("%.1f%%", proPercentage));
            macros.getCaloTotal().setText(String.valueOf(addLine.getTotalCal()));
        }
        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
    }

    // ----------------- construct Array to insert into MyJDBC -----------------
    public static void constructInsertion() {
        date = getTextDate().getText();
        String itemid = addLine.getItemID().getText().isEmpty() ? "-1" : addLine.getItemID().getText();
        int userid = UserProfile.getID();
        // date
        String meal = "breakfast";
        String item = addLine.getItem().getText();
        String quantity = addLine.getQ().getText();
        String qtype = addLine.getQn().getSelectedItem().toString();
        String calorie = addLine.getCalorie().getText();
        String carbs = addLine.getCarbs();
        String fat = addLine.getFat();
        String protein = addLine.getProtein();

        int id = MyJDBC.insertIntoItems(itemid, userid, date, meal, item, quantity, qtype, calorie, carbs, fat, protein);
        if (id != -1) {
            addLine.getItemID().setText(String.valueOf(id));
        }

    }
}
