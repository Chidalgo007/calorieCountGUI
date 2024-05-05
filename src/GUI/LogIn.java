/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Constants.Constants;
import Helper.RoundedBorder;
import MyJBDC.MyJDBC;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.*;

/**
 *
 * @author chg
 */
public class LogIn extends Form implements ActionListener, MouseListener {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton logInBtn;
    private JTextPane errorMessage;
    private JLabel signUp;
    private JLabel signUpLink;
    SimpleAttributeSet center = new SimpleAttributeSet();

    public LogIn() {
        super("Log In");
        addGUIComponents();
    }

    private void addGUIComponents() {

        // Error message----------------------------------------------
        errorMessage = new JTextPane();
        errorMessage.setBounds(25, 20, 250, 50);
        errorMessage.setEditable(false);
        errorMessage.setFocusable(false);
        errorMessage.setBackground(Constants.COLOR_BACK);
        errorMessage.setMargin(new Insets(0, 0, 0, 0));
        errorMessage.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 13));
        errorMessage.setForeground(Constants.COLOR_Error);

        // to center the text
        StyledDocument error = errorMessage.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        error.setParagraphAttributes(0, error.getLength(), center, false);

        this.add(errorMessage);
// ---  CONTAINER ------------------------------------------------
        JPanel container = new JPanel();
        container.setLayout(null);
        container.setBounds(20, 110, this.getWidth() - 50, 310);
        container.setBorder(new RoundedBorder(Constants.btnRadius));
//        container.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        container.setBackground(Constants.COLOR_Light_BAKG);

        // log in label ------------------------------------
        JLabel logIn = new JLabel("Welcome Back");
        logIn.setBounds(15, 20, Constants.WIDTH, 30);
        logIn.setHorizontalAlignment(SwingConstants.LEFT);
        logIn.setFont(Constants.FONT_SemiBold.deriveFont(Font.PLAIN, 20));
        logIn.setForeground(Constants.COLOR_Light_Grey); // change color font
        //   welcome.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        container.add(logIn);

        // Log in messase ------------------------------------
        JLabel logInmessage = new JLabel("Please sing in to access your account");
        logInmessage.setBounds(15, 45, Constants.WIDTH, 30);
        logInmessage.setHorizontalAlignment(SwingConstants.LEFT);
        logInmessage.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 10));
        logInmessage.setForeground(Color.WHITE); // change color font
        //   welcome.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        container.add(logInmessage);

        // username label ------------------------------------
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(15, 125, 100, 20);
        emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
        emailLabel.setFont(Constants.FONT_Neon.deriveFont(Font.PLAIN, 15));
        emailLabel.setForeground(Constants.COLOR_Light_Grey); // change color font
        // name.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        container.add(emailLabel);

        // username text Field ------------------------------------
        emailField = new JTextField();
        emailField.setBounds(70, 124, 160, 20);
        emailField.setOpaque(false);
        emailField.setForeground(Color.WHITE);
        emailField.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 12));
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.COLOR_Light_Grey));
        container.add(emailField);

        // passwordField label ------------------------------------
        JLabel pass = new JLabel("Password: ");
        pass.setBounds(15, 170, 100, 30);
        pass.setHorizontalAlignment(SwingConstants.LEFT);
        pass.setFont(Constants.FONT_Neon.deriveFont(Font.PLAIN, 15));
        pass.setForeground(Constants.COLOR_Light_Grey); // change color font
        // pass.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        container.add(pass);

        // passwordField textField ------------------------------------
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 170, 130, 20);
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.WHITE);
        passwordField.setFocusable(true);
        passwordField.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 12));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.COLOR_Light_Grey));
        passwordField.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true");
        container.add(passwordField);

        JCheckBox rememberMe = new JCheckBox("Remember Me");
        rememberMe.setBounds(14, 200, 200, 20);
        container.add(rememberMe, "grow 0");

        // log in button ------------------------------------
        int btnWith = Constants.btnWidth;
        int btnCenter = (container.getWidth() - btnWith) / 2;
        logInBtn = new JButton("Login");
        logInBtn.setBounds(btnCenter, 240, btnWith, 40);
        logInBtn.setFont(Constants.FONT_Neon.deriveFont(Font.PLAIN, 15));
        logInBtn.setForeground(Constants.COLOR_WHITE);
        logInBtn.setFocusable(false);
        logInBtn.setContentAreaFilled(false);
        logInBtn.setBackground(Constants.COLOR_Light_Grey);
        logInBtn.setBorder(new RoundedBorder(Constants.btnRadius));
        logInBtn.addActionListener(this);
        logInBtn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "loginAction");
        logInBtn.getActionMap().put("loginAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logIn();
            }
        });
        logInBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        container.add(logInBtn);

        //------- OUT OF CONTAINER --------------------------------
        // textRegister label ------------------------------------
        JPanel jSignUp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jSignUp.setOpaque(false);
        jSignUp.setBounds(0, 430, Constants.WIDTH, 20);
        signUp = new JLabel("Don't have and account?, ");
        signUp.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        signUp.setForeground(Constants.COLOR_ORANGE);
        jSignUp.add(signUp);
        signUpLink = new JLabel("<html>Sign Up.</html>");
        signUpLink.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        signUpLink.setForeground(Constants.COLOR_ORANGE);
        signUpLink.addMouseListener(this);
        signUpLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signUpLink.setFocusable(false);
        jSignUp.add(signUpLink);

        this.add(container);
        this.add(jSignUp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logInBtn) {
            logIn();
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
        signUpLink.setText("<html><i><b>Sign Up.<b></i></html>");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        signUpLink.setText("<html>Sign Up.</html>");
    }

    // ============= LOGIN =============================
    private void logIn() {
        String email = emailField.getText();
        String password = new String(this.passwordField.getPassword());
        errorMessage.setText("");
        // login check with database
        UserInfo.UserProfile.setID(MyJDBC.getUserId(email, password)); // setting the user ID

        if (UserInfo.UserProfile.getID() != -1) { // check the user exist
            UserInfo.UserProfile.setProfile(MyJDBC.getUserProfile(UserInfo.UserProfile.getID()));
            LogIn.this.dispose();
            new OptionMenu().setVisible(true);
        } else {
            errorMessage.setText("Email / Password credential not valid, please try again or Sign Up below !!!");
        }
    }
}
