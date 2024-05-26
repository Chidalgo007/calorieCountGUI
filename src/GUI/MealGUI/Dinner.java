/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MealGUI;

import Constants.Constants;
import GUI.EnterCalories;
import GUI_Interaction.AddLineManager;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 * this initiate the dinner field items.
 */
public class Dinner extends JPanel {

    private final AddLineManager addLine;
    private final EnterCalories EC; // to pass the date

    public Dinner(EnterCalories enterCalorie) {
        EC = enterCalorie;
        addLine = new AddLineManager("Dinner", EC);
        addGUIComponents();
        setLayout(new MigLayout("wrap, fillx", "[]", "[]"));
        this.setBackground(Constants.COLOR_BACK);
    }

    private void addGUIComponents() {
        //  header constainer-------------------------------------
        JPanel header = new JPanel(new MigLayout("fillx, insets 0", "[grow, fill]push[]", "[]"));
        header.setOpaque(false);

        JLabel dinner = new JLabel("Dinner");
        dinner.setFont(Constants.FONT_SemiBold.deriveFont(Font.PLAIN, 15));

        header.add(dinner, "growx");
        header.add(addLine.getMacros().getCaloContainer()); // add macros calculation
        this.add(header);
        this.add(addLine.getLine().getMiddleContent()); // add panel calories
    }
        public void updateContentForNewDate(String date){
        addLine.updateLinesAndItemsForNewDate(date);
    }
}
