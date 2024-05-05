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
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 * this initiate the lunch field items.
 */
public class Lunch extends JPanel {

    private final AddLineManager addLine;
    private final EnterCalories EC; // to pass the date

    public Lunch(EnterCalories enterCalorie) {
        EC = enterCalorie;
        addLine = new AddLineManager("Lunch", EC);
        addGUIComponents();
        setLayout(new MigLayout("wrap, fillx", "[]", "[]"));
        this.setBackground(Constants.COLOR_BACK);
    }

    private void addGUIComponents() {
        //  header constainer-------------------------------------
        JPanel header = new JPanel(new MigLayout("fillx, insets 0", "[grow, fill]push[]", "[]"));
        header.setOpaque(false);

        JLabel lunch = new JLabel("Lunch");
        lunch.setFont(Constants.FONT_SemiBold.deriveFont(Font.PLAIN, 15));
        
        header.add(lunch, "growx");
        header.add(addLine.getMacros().getCaloContainer());// macros calculation 
        this.add(header);
        this.add(addLine.getLine().getMiddleContent());// add panel calories 
    }
        public void updateContentForNewDate(String date){
        addLine.updateLinesAndItemsForNewDate(date);
    }
}
