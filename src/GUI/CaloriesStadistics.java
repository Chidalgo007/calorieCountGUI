/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Constants.Constants;
import GUI.GraphicChar.Container;

import javax.swing.JPanel;

/**
 *
 * @author chg
 */
public class CaloriesStadistics extends JPanel {

    EnterCalories EC;
    Container c; 

    public CaloriesStadistics(EnterCalories enterCal) {
//        this.setLayout(null);
        EC = enterCal;
        c = new Container(EC);
        setBackground(Constants.COLOR_BACK);
        add(c);
    }

}
