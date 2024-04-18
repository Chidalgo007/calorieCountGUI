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

    private JTextField userName;
    private JPasswordField password;
    private JButton submit;
    private JTextPane register;

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
        JLabel name = new JLabel("User Name: ");
        name.setBounds(10, 170, 100, 50);
        name.setHorizontalAlignment(SwingConstants.RIGHT);
        name.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        name.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        // name.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(name);

        // username text Field ------------------------------------
        userName = new JTextField();
        userName.setBounds(110, 174, 140, 30);
        userName.setBackground(Color.BLACK);
        userName.setForeground(Color.WHITE);
        userName.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        userName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        this.add(userName);

        // password label ------------------------------------
        JLabel pass = new JLabel("Password: ");
        pass.setBounds(10, 250, 100, 50);
        pass.setHorizontalAlignment(SwingConstants.RIGHT);
        pass.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        pass.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        // pass.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(pass);

        // password textField ------------------------------------
        password = new JPasswordField();
        password.setBounds(110, 254, 140, 30);
        password.setBackground(Color.BLACK);
        password.setForeground(Color.WHITE);
        password.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));
        password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        this.add(password);

        // log in button ------------------------------------
        int btnWith = Constants.Constants.btnWidth;
        int btnCenter = (this.getWidth() - btnWith) / 2;
        submit = new JButton("Log In");
        submit.setBounds(btnCenter, 340, btnWith, 50);
        submit.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 20));
        submit.setForeground(Constants.Constants.COLOR_Light_Grey);
        submit.setFocusable(false);
        submit.setContentAreaFilled(false);
        submit.setBorder(new RoundedBorder(Constants.Constants.btnRadius));
        submit.addActionListener(this);
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(submit);

        // register label ------------------------------------
        int registerWith = 210;
        int registerCenter = (this.getWidth() - registerWith) / 2;
        register = new JTextPane();
        register.setText("Don't have an account yet, register by clicking Here.");
        register.setEditable(false);
        register.setFocusable(false);
        register.setBackground(Color.BLACK);
        register.setBounds(registerCenter, 420, registerWith, 40);
        register.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        register.setForeground(Constants.Constants.COLOR_4); // change color font
        register.addMouseListener(this);
        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //   register.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        // to center the text
        StyledDocument doc = register.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        this.add(register);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            String username = userName.getText();
            String password = new String(this.password.getPassword());
            System.out.println("username: "+username+" password: "+password);
            // login check with database
            // then open OptionMenu...
        }

    }

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
        register.setText("REGISTER.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        register.setText("Don't have an account yet, register by clicking Here.");
    }
}
