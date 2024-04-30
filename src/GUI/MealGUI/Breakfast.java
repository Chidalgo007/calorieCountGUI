/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MealGUI;

import Constants.Constants;
import static GUI.EnterCalories.getTextDate;
import Helper.AddLineManager;
import Helper.AddMacros;
import MyJBDC.MyJDBC;
import UserInfo.UserProfile;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public class Breakfast extends JPanel {

    private static AddLineManager addLine;
    private static AddMacros macros;

    public Breakfast() {
        addGUIComponents();
        setLayout(new MigLayout("wrap, fillx", "[]", "[]"));
        this.setBackground(Constants.COLOR_BACK);
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
        addLine = new AddLineManager("Breakfast");
        this.add(addLine.getLine().getMiddleContent());

    }

//    public AddLineManager getLine() {
//        return addLine;
//    }

    
}
