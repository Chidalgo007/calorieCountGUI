/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MealGUI;

import Constants.Constants;
import GUI.EnterCalories;
import Helper.AddLineManager;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public class Breakfast extends JPanel {

    private AddLineManager addLine;
//    private final EnterCalories EC; // truing to pass the date

    public Breakfast(EnterCalories enterCalorie) {
//        EC = enterCalorie;
//        String dateST = EC.getTextDate().getText();
        addLine = new AddLineManager("Breakfast");//, dateST);
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

        // macros calculation -------------------------------------

        header.add(addLine.getMacros().getCaloContainer());
        this.add(header);

        // ---center panel calories --------------------------------------------
        
        this.add(addLine.getLine().getMiddleContent());

    }


    
}
