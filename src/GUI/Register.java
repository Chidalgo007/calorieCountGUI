/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Helper.RoundedBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author chg
 */
public class Register extends Form implements ActionListener, MouseListener {

    JTextField emailField;
    JPasswordField passwordField;
    JTextPane passwordLabelCondition;
    JPasswordField rePasswordField;
    JLabel rePasswordLabelCondition;
    JButton registerbtn;
    JTextPane logIn;
    JTextPane errorMessage;
    int btnFontSize = 20;
    int labelFontSize = 13;
    int fieldFontSize = 13;
    int fieldSpaceFontSize = 15;
    SimpleAttributeSet center = new SimpleAttributeSet();

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$";
    //--------------------------------------------------------------------------

    String email;
    String password;
    String rePassword;

    public Register() {
        super("Register");
        addGUIComponent();
    }

    private void addGUIComponent() {

        int pageNameWith = 200;
        int pageNameCenter = (this.getWidth() - pageNameWith) / 2;
        // Register label
        JLabel pageName = new JLabel("Register");
        pageName.setBounds(pageNameCenter, 10, pageNameWith, 50);
        pageName.setHorizontalAlignment(SwingConstants.CENTER);
        pageName.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 30));
        pageName.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        // pageName.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.add(pageName);

        // Error message----------------------------------------------
        errorMessage = new JTextPane();
        errorMessage.setBounds(25, 90, 250, 50);
        errorMessage.setEditable(false);
        errorMessage.setFocusable(false);
        errorMessage.setBackground(Constants.Constants.COLOR_BACK);
        errorMessage.setMargin(new Insets(0, 0, 0, 0));
        errorMessage.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 13));
        errorMessage.setForeground(Constants.Constants.COLOR_Error);

        // to center the text
        StyledDocument error = errorMessage.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        error.setParagraphAttributes(0, error.getLength(), center, false);

        this.add(errorMessage);

        // email label ------------------------------------
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(10, 150, 100, 50);
        emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        emailLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        emailLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(emailLabel);

        // email text Field ------------------------------------
        emailField = new JTextField();
        emailField.setBounds(110, 167, 140, fieldSpaceFontSize);
        emailField.setBackground(Constants.Constants.COLOR_BACK);
        emailField.setForeground(Color.WHITE);
        emailField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, fieldFontSize));
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                emailValidation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                emailValidation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.add(emailField);

        // passwordField label ------------------------------------
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 210, 100, 50);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        passwordLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(passwordLabel);

        // passwordField textField ------------------------------------
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 227, 140, fieldSpaceFontSize);
        passwordField.setBackground(Constants.Constants.COLOR_BACK);
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, fieldFontSize));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                passwordValidation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                passwordValidation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.add(passwordField);

        // passwords label condition characteristic ------------------
        passwordLabelCondition = new JTextPane();
        passwordLabelCondition.setBounds(37, 263, 250, 20);
        passwordLabelCondition.setEditable(false);
        passwordLabelCondition.setFocusable(false);
        passwordLabelCondition.setBackground(Constants.Constants.COLOR_BACK);
        passwordLabelCondition.setMargin(new Insets(0, 0, 0, 0));
        passwordLabelCondition.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 8));
        passwordLabelCondition.setForeground(Constants.Constants.COLOR_Error);
        this.add(passwordLabelCondition);

        // rePasswordField label--------------------------------------
        JLabel rePasswordLabel = new JLabel("Re-Password: ");
        rePasswordLabel.setBounds(10, 270, 100, 50);
        rePasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rePasswordLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        rePasswordLabel.setForeground(Constants.Constants.COLOR_Light_Grey);
        this.add(rePasswordLabel);

        // rePasswordField field ------------------------------------
        rePasswordField = new JPasswordField();
        rePasswordField.setBounds(110, 287, 140, fieldSpaceFontSize);
        rePasswordField.setBackground(Constants.Constants.COLOR_BACK);
        rePasswordField.setForeground(Color.WHITE);
        rePasswordField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, fieldFontSize));
        rePasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        rePasswordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                rePasswordValidation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                rePasswordValidation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.add(rePasswordField);

        // rePasswords label condition characteristic ------------------
        rePasswordLabelCondition = new JLabel();
        rePasswordLabelCondition.setBounds(17, 305, 287, 10);
        rePasswordLabelCondition.setHorizontalAlignment(SwingConstants.LEFT);
        rePasswordLabelCondition.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 10));
        rePasswordLabelCondition.setForeground(Constants.Constants.COLOR_Error); // change color font
        this.add(rePasswordLabelCondition);

        // register button ---------------------------------------
        int btnRadius = Constants.Constants.btnRadius;
        int btnWith = Constants.Constants.btnWidth;
        int btnHeight = 50;
        int centerWith = (this.getWidth() - btnWith) / 2;

        registerbtn = new JButton("Sign In");
        registerbtn.setBounds(centerWith, 340, btnWith, btnHeight);
        registerbtn.setHorizontalAlignment(SwingConstants.CENTER);
        registerbtn.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, btnFontSize));
        registerbtn.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        registerbtn.setBackground(Constants.Constants.COLOR_Light_Grey);
        registerbtn.setFocusable(false);
        registerbtn.setContentAreaFilled(false);
        registerbtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        registerbtn.setBorder(new RoundedBorder(btnRadius));
        registerbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerbtn.addActionListener(this);
        this.add(registerbtn);

        // already have a log in----------------------------------------
        int logInWith = 210;
        int logInCenter = (this.getWidth() - logInWith) / 2;
        logIn = new JTextPane();
        logIn.setText("Already have an account?, Log In by clicking Here.");
        logIn.setEditable(false);
        logIn.setFocusable(false);
        logIn.setBackground(Constants.Constants.COLOR_BACK);
        logIn.setBounds(logInCenter, 420, logInWith, 40);
        logIn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        logIn.setForeground(Constants.Constants.COLOR_4); // change color font
        logIn.addMouseListener(this);
        logIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // to center the text
        StyledDocument doc = logIn.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        this.add(logIn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerbtn) {

            if (fieldValidation()) {
                if(MyJBDC.MyJDBC.register(email, password)){
                Register.this.dispose();
                new LogIn().setVisible(true);    
                }else {
                    errorMessage.setText("Email already register, please use another email or Log In with the Link below !!!");
                } 
            }
        }

    }

    private boolean fieldValidation() {
        return !(!emailValidation()
                || !passwordValidation()
                || !rePasswordValidation());
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
        logIn.setText("Already have an account?, Log In by clicking Here.");
    }

// --------------------------------------Validations ---------------------------
    private boolean emailValidation() {
        email = emailField.getText();
        if (email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
            emailField.setBackground(Constants.Constants.COLOR_Error);
            return false;
        }
        emailField.setBackground(Constants.Constants.COLOR_BACK);

        return true;
    }

    private boolean passwordValidation() {
        password = new String(this.passwordField.getPassword());
        if (password.isEmpty() || !password.matches(PASSWORD_PATTERN)) {
            passwordField.setBackground(Constants.Constants.COLOR_Error);
            passwordLabelCondition.setText("Password MUST contain at least 6 letter, 1 Uppercase, 1 Lowercase & 1 number");
            return false;
        }
        passwordField.setBackground(Constants.Constants.COLOR_BACK);
        passwordLabelCondition.setText("");

        return true;
    }

    private boolean rePasswordValidation() {
        rePassword = new String(this.rePasswordField.getPassword());
        if (!rePassword.equals(password)) {
            rePasswordField.setBackground(Constants.Constants.COLOR_Error);
            rePasswordLabelCondition.setText("Password's DON'T match");
            return false;
        }
        rePasswordField.setBackground(Constants.Constants.COLOR_BACK);
        rePasswordLabelCondition.setText("");

        return true;
    }

}
