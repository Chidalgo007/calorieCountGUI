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
import MyJBDC.MyJDBC;
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
        MyJDBC.connection();
        // shutdown hook to close the connection when the program ends
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            MyJDBC.connectionClose();
        }));
        EventQueue.invokeLater(() -> new Init().setVisible(true));
    }

}
