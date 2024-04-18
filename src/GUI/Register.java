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
    
    JTextField nameField;
    JTextField lastNameField;
    JTextField emailField;
    JTextField userNameField;
    JPasswordField passwordField;
    JTextPane passwordLabelCondition;
    JPasswordField rePasswordField;
    JLabel rePasswordLabelCondition;
    JButton registerbtn;
    JTextPane logIn;
    int btnFontSize = 20;
    int labelFontSize = 13;
    int fieldFontSize = 13;
    int fieldSpaceFontSize = 15;
    private static final String LETTER_PATTERN = "^[A-Za-z]+$";
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$";
    //--------------------------------------------------------------------------
    String name;
    String lastname;
    String email;
    String userName;
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

        // name label ------------------------------------
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(10, 70, 100, 50);
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        nameLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(nameLabel);

        // name Field ------------------------------------
        nameField = new JTextField();
        nameField.setBounds(110, 87, 140, fieldSpaceFontSize);
        nameField.setBackground(Color.BLACK);
        nameField.setForeground(Color.WHITE);
        nameField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, fieldFontSize));
        nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                nameValidation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                nameValidation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.add(nameField);

        // lastName label ------------------------------------
        JLabel lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setBounds(10, 110, 100, 50);
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lastNameLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        lastNameLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(lastNameLabel);

        // lastName text Field ------------------------------------
        lastNameField = new JTextField();
        lastNameField.setBounds(110, 127, 140, fieldSpaceFontSize);
        lastNameField.setBackground(Color.BLACK);
        lastNameField.setForeground(Color.WHITE);
        lastNameField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, fieldFontSize));
        lastNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        lastNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                lastNameValidation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                lastNameValidation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.add(lastNameField);

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
        emailField.setBackground(Color.BLACK);
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

        // username label ------------------------------------
        JLabel userNameLabel = new JLabel("User Name: ");
        userNameLabel.setBounds(10, 190, 100, 50);
        userNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        userNameLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        userNameLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(userNameLabel);

        // username text Field ------------------------------------
        userNameField = new JTextField();
        userNameField.setBounds(110, 207, 140, fieldSpaceFontSize);
        userNameField.setBackground(Color.BLACK);
        userNameField.setForeground(Color.WHITE);
        userNameField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, fieldFontSize));
        userNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        userNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                userNameValidation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                userNameValidation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.add(userNameField);

        // passwordField label ------------------------------------
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 230, 100, 50);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        passwordLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(passwordLabel);

        // passwordField textField ------------------------------------
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 247, 140, fieldSpaceFontSize);
        passwordField.setBackground(Color.BLACK);
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
        passwordLabelCondition.setBackground(Color.BLACK);
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
        rePasswordField.setBackground(Color.BLACK);
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
        registerbtn.setBackground(Constants.Constants.COLOR_2);
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
        logIn.setBackground(Color.BLACK);
        logIn.setBounds(logInCenter, 420, logInWith, 40);
        logIn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        logIn.setForeground(Constants.Constants.COLOR_4); // change color font
        logIn.addMouseListener(this);
        logIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // to center the text
        StyledDocument doc = logIn.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        this.add(logIn);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerbtn) {
            
            if (fieldValidation()) {
                System.out.println(fieldValidation());
            }
        }
        
    }
    
    private boolean fieldValidation() {
        return !(!nameValidation()||
                !lastNameValidation()||
                !emailValidation()||
                !userNameValidation()||
                !passwordValidation()||
                !rePasswordValidation());
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
    private boolean nameValidation() {
        name = nameField.getText();
        if (name.isEmpty() || !name.matches(LETTER_PATTERN)) {
            nameField.setBackground(Constants.Constants.COLOR_Error);
            return false;
        }
        nameField.setBackground(Color.BLACK);
        
        return true;
    }
    
    private boolean lastNameValidation() {
        lastname = lastNameField.getText();
        if (lastname.isEmpty() || !lastname.matches(LETTER_PATTERN)) {
            lastNameField.setBackground(Constants.Constants.COLOR_Error);
            return false;
        }
        lastNameField.setBackground(Color.BLACK);
        
        return true;
    }
    
    private boolean emailValidation() {
        email = emailField.getText();
        if (email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
            emailField.setBackground(Constants.Constants.COLOR_Error);
            return false;
        }
        emailField.setBackground(Color.BLACK);
        
        return true;
    }
    
    private boolean userNameValidation() {
        userName = userNameField.getText();
        if (userName.isEmpty() || userName.isBlank()) {
            userNameField.setBackground(Constants.Constants.COLOR_Error);
            return false;
        }
        userNameField.setBackground(Color.BLACK);
        
        return true;
    }
    
    private boolean passwordValidation() {
        password = new String(this.passwordField.getPassword());
        if (password.isEmpty() || !password.matches(PASSWORD_PATTERN)) {
            passwordField.setBackground(Constants.Constants.COLOR_Error);
            passwordLabelCondition.setText("Password MUST contain at least 6 letter, 1 Uppercase, 1 Lowercase & 1 number");
            return false;
        }
        passwordField.setBackground(Color.BLACK);
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
        rePasswordField.setBackground(Color.BLACK);
        rePasswordLabelCondition.setText("");
        
        return true;
    }
    
}
