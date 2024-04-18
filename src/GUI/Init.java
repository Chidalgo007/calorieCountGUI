/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Helper.RoundedBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author chg
 */
public class Init extends Form implements ActionListener {
    
    JButton register;
    JButton logIn;

    public Init() {
        super("Welcome");
        addGUIComponents();
    }

    private void addGUIComponents() {
        setLayout(null);
        
        ImageIcon icon = new ImageIcon("img/logo.png");
        int width = (int) (icon.getIconWidth()*0.2);
        int height = (int) (icon.getIconHeight()*0.2);
        Image resizedImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
        int iconLabelCenter = (this.getWidth()-resizedIcon.getIconWidth())/2;
        JLabel iconLabel = new JLabel(resizedIcon);
        iconLabel.setBounds(iconLabelCenter, 100, width, height);
        this.add(iconLabel);
        
        JLabel welcome = new JLabel("Calorie Tracker");
     //   welcome.setIcon(resizedIcon);
        welcome.setBounds(0, 15, 287, 50);
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 20));
        welcome.setForeground(Constants.Constants.COLOR_3); // change color font
        //   welcome.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(welcome);


        int btnFontSize = 20;
        int btnRadius = Constants.Constants.btnRadius;
        int btnWith = Constants.Constants.btnWidth;
        int btnHeight = 50;
        int centerWith = (this.getWidth() - btnWith) / 2;

        register = new JButton("Sign Up");
        register.setBounds(centerWith, 260, btnWith, btnHeight);
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

        logIn = new JButton("Log In");
        logIn.setBounds(centerWith, 330, btnWith, btnHeight);
        logIn.setHorizontalAlignment(SwingConstants.CENTER);
        logIn.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, btnFontSize));
        logIn.setForeground(Constants.Constants.COLOR_3); // change color font
        logIn.setBackground(Constants.Constants.COLOR_2);
        logIn.setFocusable(false);
        logIn.setContentAreaFilled(false);
        logIn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        logIn.setBorder(new RoundedBorder(btnRadius));
        logIn.addActionListener(this);
        this.add(logIn);

        JLabel bottom = new JLabel();
        bottom.setText("Your free Calorie Tracker...");
        bottom.setBounds(0, 400, 287, 50);
        bottom.setHorizontalAlignment(SwingConstants.CENTER);
        bottom.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 10));
        bottom.setForeground(Constants.Constants.COLOR_4); // change color font
        //   welcome.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(bottom);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== register){
            Init.this.dispose();
            new Register().setVisible(true);
            System.out.println("register pressed");
        }
        if(e.getSource()== logIn){
                        Init.this.dispose();
            new LogIn().setVisible(true);
            System.out.println("Log In pressed");
        }
    }

}
