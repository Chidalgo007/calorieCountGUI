/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Helper.RoundedBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author chg
 */
public class Register extends Form implements ActionListener, MouseListener {

    JTextField nameField;
    JTextField lastNameField;
    JTextField emailField;
    JTextField userNameField;
    JPasswordField password;
    JPasswordField rePassword;
    JButton register;
    JTextPane logIn;

    public Register() {
        super("Register");
        addGUIComponent();
    }

    private void addGUIComponent() {

        // Register label


        // name label ------------------------------------
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(10, 150, 100, 50);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        nameLabel.setForeground(Constants.Constants.COLOR_3); // change color font
        // name.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(nameLabel);

        // name Field ------------------------------------
        nameField = new JTextField();
        nameField.setBounds(110, 154, 140, 30);
        nameField.setBackground(Color.BLACK);
        nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_3));
        this.add(nameField);

        // lastName label ------------------------------------
        JLabel lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setBounds(10, 150, 100, 50);
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lastNameLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        lastNameLabel.setForeground(Constants.Constants.COLOR_3); // change color font
        // name.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(lastNameLabel);

        // lastName text Field ------------------------------------
        lastNameField = new JTextField();
        lastNameField.setBounds(110, 154, 140, 30);
        lastNameField.setBackground(Color.BLACK);
        lastNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_3));
        this.add(lastNameField);


        // email label ------------------------------------
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(10, 150, 100, 50);
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        emailLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        emailLabel.setForeground(Constants.Constants.COLOR_3); // change color font
        // name.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(emailLabel);

        // email text Field ------------------------------------
        emailField = new JTextField();
        emailField.setBounds(110, 154, 140, 30);
        emailField.setBackground(Color.BLACK);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_3));
        this.add(emailField);

        
        // username label ------------------------------------
        JLabel userNameLabel = new JLabel("User Name: ");
        userNameLabel.setBounds(10, 150, 100, 50);
        userNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        userNameLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        userNameLabel.setForeground(Constants.Constants.COLOR_3); // change color font
        // name.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(userNameLabel);

        // username text Field ------------------------------------
        userNameField = new JTextField();
        userNameField.setBounds(110, 154, 140, 30);
        userNameField.setBackground(Color.BLACK);
        userNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_3));
        this.add(userNameField);
    

        // password label ------------------------------------
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 250, 100, 50);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        passwordLabel.setForeground(Constants.Constants.COLOR_3); // change color font
        // pass.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(passwordLabel);

        // password textField ------------------------------------
        password = new JPasswordField();
        password.setBounds(110, 254, 140, 30);
        password.setBackground(Color.BLACK);
        password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_3));
        this.add(password);

        // rePassword label--------------------------------------
        JLabel rePasswordLabel = new JLabel("Re-Password: ");
        rePasswordLabel.setBounds(10, 250, 100, 50);
        rePasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rePasswordLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        rePasswordLabel.setForeground(Constants.Constants.COLOR_3); // change color font
        // pass.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(passwordLabel);

        // rePassword field ------------------------------------
        rePassword = new JPasswordField();
        rePassword.setBounds(110, 254, 140, 30);
        rePassword.setBackground(Color.BLACK);
        rePassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_3));
        this.add(rePassword);
        
        // register button ---------------------------------------
        int btnFontSize = 20;
        int btnRadius = Constants.Constants.btnRadius;
        int btnWith = Constants.Constants.btnWidth;
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
        
        // already have a log in----------------------------------------
        int logInWith = 210;
        int logInCenter = (this.getWidth() - logInWith) / 2;
        logIn = new JTextPane();
        logIn.setText("Already have an account yet, Log In by clicking Here.");
        logIn.setEditable(false);
        logIn.setFocusable(false);
        logIn.setBackground(Color.BLACK);
        logIn.setBounds(logInCenter, 420, logInWith, 40);
        logIn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        logIn.setForeground(Constants.Constants.COLOR_4); // change color font
        logIn.addMouseListener(this);
        logIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //   logIn.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        // to center the text
        StyledDocument doc = logIn.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        this.add(logIn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
               Register.this.dispose();
        new LogIn().setVisible(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        logIn.setText("Log In.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        logIn.setText("Already have an account yet, Log In by clicking Here.");
    }

}
