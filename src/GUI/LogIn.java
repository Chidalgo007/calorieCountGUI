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
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.*;

/**
 *
 * @author chg
 */
public class LogIn extends Form implements ActionListener, MouseListener {

    public LogIn() {
        super("Log In");
        addGUIComponents();
    }

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton btnRegister;
    private JTextPane textRegister;

    private void addGUIComponents() {

        // log in label ------------------------------------
        JLabel logIn = new JLabel("Log In");
        logIn.setBounds(0, 15, 287, 50);
        logIn.setHorizontalAlignment(SwingConstants.CENTER);
        logIn.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 30));
        logIn.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        //   welcome.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(logIn);

        // username label ------------------------------------
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(10, 170, 100, 50);
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        emailLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        emailLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        // name.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(emailLabel);

        // username text Field ------------------------------------
        emailField = new JTextField();
        emailField.setBounds(110, 174, 140, 30);
        emailField.setBackground(Constants.Constants.COLOR_BACK);
        emailField.setForeground(Color.WHITE);
        emailField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        this.add(emailField);

        // passwordField label ------------------------------------
        JLabel pass = new JLabel("Password: ");
        pass.setBounds(10, 250, 100, 50);
        pass.setHorizontalAlignment(SwingConstants.RIGHT);
        pass.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        pass.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        // pass.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(pass);

        // passwordField textField ------------------------------------
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 254, 140, 30);
        passwordField.setBackground(Constants.Constants.COLOR_BACK);
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        this.add(passwordField);

        // log in button ------------------------------------
        int btnWith = Constants.Constants.btnWidth;
        int btnCenter = (this.getWidth() - btnWith) / 2;
        btnRegister = new JButton("Log In");
        btnRegister.setBounds(btnCenter, 340, btnWith, 50);
        btnRegister.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 20));
        btnRegister.setForeground(Constants.Constants.COLOR_Light_Grey);
        btnRegister.setFocusable(false);
        btnRegister.setContentAreaFilled(false);
        btnRegister.setBorder(new RoundedBorder(Constants.Constants.btnRadius));
        btnRegister.addActionListener(this);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(btnRegister);

        // textRegister label ------------------------------------
        int registerWith = 210;
        int registerCenter = (this.getWidth() - registerWith) / 2;
        textRegister = new JTextPane();
        textRegister.setText("Don't have an account yet, register by clicking Here.");
        textRegister.setEditable(false);
        textRegister.setFocusable(false);
        textRegister.setBackground(Constants.Constants.COLOR_BACK);
        textRegister.setBounds(registerCenter, 420, registerWith, 40);
        textRegister.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        textRegister.setForeground(Constants.Constants.COLOR_4); // change color font
        textRegister.addMouseListener(this);
        textRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //   textRegister.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        // to center the text
        StyledDocument doc = textRegister.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        this.add(textRegister);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String password = new String(this.passwordField.getPassword());
        if(e.getSource()==btnRegister){
            System.out.println("email: "+email+" password: "+password);
            // login check with database
            if(MyJBDC.MyJDBC.validLogin(email, password)){
                LogIn.this.dispose();
                new OptionMenu().setVisible(true);
            }
        }

    }

    // -------------------- mouse listener -------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        LogIn.this.dispose();
        new Register().setVisible(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        textRegister.setText("REGISTER.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        textRegister.setText("Don't have an account yet, register by clicking Here.");
    }
}
