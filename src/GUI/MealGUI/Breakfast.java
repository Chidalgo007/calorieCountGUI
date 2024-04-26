/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MealGUI;

import Constants.Constants;
import Helper.AddLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public class Breakfast extends JPanel implements ActionListener {

    JLabel caloBF;

    public Breakfast() {
        addGUIComponents();
        setLayout(new MigLayout("wrap, fillx", "[]", "[]"));
        this.setBackground(Constants.COLOR_BACK);
    }

    private void addGUIComponents() {

        JLabel breakfast = new JLabel("Breakfast");
        breakfast.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));

        JLabel caloLabel = new JLabel("Calories: ");
        caloLabel.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));

        caloBF = new JLabel();
        caloBF.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        caloBF.setText("1230");
        
        String num= String.valueOf(Constants.WIDTH*0.55);
        JPanel header = new JPanel(new MigLayout("fillx, insets 0", "["+num+"!][][]", "[]"));
        header.setOpaque(false);

        header.add(breakfast);
        header.add(caloLabel);
        header.add(caloBF);

        this.add(header);

        // ---center panel calories -------------------------------------------
        AddLine line = new AddLine();
        this.add(line.getMiddleContent());

        // ----- macros ------------------------------------------------------
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
