/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MealGUI;

import Constants.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
public class Breakfast extends JPanel implements ActionListener{

    public Breakfast() {
        addGUIComponents();
        setLayout(new MigLayout("wrap, fillx","[]","[]"));
        this.setBackground(Constants.COLOR_BACK);
    }
    
    

    private void addGUIComponents() {

        JLabel breakfast = new JLabel("Breakfast");
        breakfast.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN,15));
        
        JLabel caloLabel = new JLabel("Calories: ");
        caloLabel.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN,15));
        
        JLabel caloBF = new JLabel();
        caloBF.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN,15));
        
        JPanel header = new JPanel(new MigLayout("fillx, insets 0"));
        header.setOpaque(false);
        JPanel calorie = new JPanel(new FlowLayout(FlowLayout.LEFT));
        calorie.setPreferredSize(new Dimension((int)(Constants.WIDTH*0.5),20));
        calorie.setBorder(BorderFactory.createLineBorder(Color.yellow));
        calorie.setOpaque(false);
        
        calorie.add(caloLabel);
        calorie.add(caloBF);
        header.add(breakfast);
        header.add(calorie, "gap 40");
        this.add(header);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
