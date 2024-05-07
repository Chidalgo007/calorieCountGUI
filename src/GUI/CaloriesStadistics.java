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

    Container c; 

    public CaloriesStadistics() {
//        this.setLayout(null);
        c = new Container();
        setBackground(Constants.COLOR_BACK);
        add(c);
    }

}
