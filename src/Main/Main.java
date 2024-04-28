/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import GUI.EnterProfile;
import GUI.Init;
import GUI.LogIn;
import GUI.MealGUI.Breakfast;
import GUI.OptionMenu;
import GUI.Register;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.EventQueue;

/**
 *
 * @author chg
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(()->
        new Init().setVisible(true)
//        new LogIn().setVisible(true)
//        new Register().setVisible(true)
//        new OptionMenu().setVisible(true)

            );
        Breakfast.constructInsertion();
    }
    
}
