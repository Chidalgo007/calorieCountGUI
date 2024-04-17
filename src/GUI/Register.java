/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Helper.RoundedBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author chg
 */
public class Register extends Form implements ActionListener {

    JButton register;

    public Register() {
        super("Register");
        addGUIComponent();
    }


    private void addGUIComponent() {
        
        

        int btnFontSize = 20;
        int btnRadius = 15;
        int btnWith = 200;
        int btnHeight = 50;
        int centerWith = (this.getWidth() - btnWith) / 2;

        register = new JButton("Register");
        register.setBounds(centerWith, 250, btnWith, btnHeight);
        register.setHorizontalAlignment(SwingConstants.CENTER);
        register.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, btnFontSize));
        register.setForeground(Constants.Constants.COLOR_3); // change color font
        register.setBackground(Constants.Constants.COLOR_2);
        register.setFocusable(false);
        register.setContentAreaFilled(false);
        register.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        register.setBorder(new RoundedBorder(btnRadius));
        register.addActionListener(this);
        this.add(register);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
