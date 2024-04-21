/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author chg
 */
public class Form extends JFrame {
    ImageIcon icon = new ImageIcon("img/logo.png");
    public Form(String title){
        super(title);
        setIconImage(icon.getImage());
        setSize(Constants.Constants.WIDTH,Constants.Constants.HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultLookAndFeelDecorated(true);
        getContentPane().setBackground(Constants.Constants.COLOR_BACK);
    }
    
}
