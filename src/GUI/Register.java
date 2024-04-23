/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Constants.Constants;
import Helper.RoundedBorder;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import net.miginfocom.swing.MigLayout;

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
    JLabel logIn;
    JLabel logInLink;
    JTextPane errorMessage;
    int fontSize = 13;
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
        setLayout(new MigLayout("wrap 1, insets 20 20 10 20", "[center]", "[bottom]"));

        // Error message----------------------------------------------
        errorMessage = new JTextPane();
        errorMessage.setPreferredSize(new Dimension(250, 50));
        errorMessage.setEditable(false);
        errorMessage.setFocusable(false);
        errorMessage.setOpaque(false);
        errorMessage.setMargin(new Insets(0, 0, 0, 0));
        errorMessage.setFont(Constants.FONT_Neon.deriveFont(Font.PLAIN, fontSize));
        errorMessage.setForeground(Constants.COLOR_Error);

        // to center the text
        StyledDocument error = errorMessage.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        error.setParagraphAttributes(0, error.getLength(), center, false);

        this.add(errorMessage);

        // passwords label condition characteristic ------------------
        passwordLabelCondition = new JTextPane();
        passwordLabelCondition.setPreferredSize(new Dimension(250, 25));
        passwordLabelCondition.setEditable(false);
        passwordLabelCondition.setFocusable(false);
        passwordLabelCondition.setOpaque(false);
        passwordLabelCondition.setMargin(new Insets(0, 0, 0, 0));
        passwordLabelCondition.setFont(Constants.FONT_Neon.deriveFont(Font.PLAIN, 10));
        passwordLabelCondition.setForeground(Constants.COLOR_Error);
        this.add(passwordLabelCondition);

        // rePasswords label condition characteristic ------------------
        rePasswordLabelCondition = new JLabel();
        rePasswordLabelCondition.setPreferredSize(new Dimension(250, 15));
        rePasswordLabelCondition.setFont(Constants.FONT_Neon.deriveFont(Font.PLAIN, 10));
        rePasswordLabelCondition.setForeground(Constants.COLOR_Error);
        this.add(rePasswordLabelCondition);

// ------------------------------- container --------------------------------------------        
        JPanel container = new JPanel(new MigLayout("wrap, fillx, insets 10","fill,270"));
//        container.putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        container.setBorder(new RoundedBorder(Constants.btnRadius));
        container.setBackground(Constants.COLOR_Light_BAKG);

        JPanel pageName = new JPanel(new MigLayout("wrap, fill, insets 0","[]","[]"));
        pageName.setOpaque(false);
        JLabel title = new JLabel("Register");
        title.setFont(Constants.FONT_SemiBold.deriveFont(Font.PLAIN, 20));
        title.setForeground(Constants.COLOR_Light_Grey);
        JLabel frase = new JLabel("Enter your detail to Sing Up ");
        frase.setFont(Constants.FONT_Neon.deriveFont(Font.PLAIN, 10));
        frase.setForeground(Constants.COLOR_WHITE);
        
        pageName.add(title);
        pageName.add(frase);

        JPanel pFields = new JPanel(new MigLayout("wrap, fillx, insets 1, gap 20", "grow"));
        pFields.setOpaque(false);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(Constants.FONT_Neon.deriveFont(Font.PLAIN, fontSize));
        emailLabel.setForeground(Constants.COLOR_Light_Grey);

        emailField = new JTextField();
        emailField.setOpaque(false);
        emailField.setForeground(Color.WHITE);
        emailField.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, fontSize));
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.COLOR_Light_Grey));
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

        pFields.add(emailLabel, "split 2");
        pFields.add(emailField, "grow");

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(Constants.FONT_Neon.deriveFont(Font.PLAIN, fontSize));
        passwordLabel.setForeground(Constants.COLOR_Light_Grey);

        passwordField = new JPasswordField();
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.WHITE);
        passwordField.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, fontSize));
        passwordField.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true");
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.COLOR_Light_Grey));
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

        pFields.add(passwordLabel, "split 2");
        pFields.add(passwordField, "grow");

        JLabel rePasswordLabel = new JLabel("Re-Password: ");
        rePasswordLabel.setFont(Constants.FONT_Neon.deriveFont(Font.PLAIN, fontSize));
        rePasswordLabel.setForeground(Constants.COLOR_Light_Grey);

        rePasswordField = new JPasswordField();
        rePasswordField.setOpaque(false);
        rePasswordField.setForeground(Constants.COLOR_WHITE);
        rePasswordField.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, fontSize));
        rePasswordField.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true");
        rePasswordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.COLOR_Light_Grey));
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

        pFields.add(rePasswordLabel, "split 2");
        pFields.add(rePasswordField, "grow");

        // register button ---------------------------------------
        registerbtn = new JButton("Sign Up");
//        registerbtn.setHorizontalAlignment(SwingConstants.CENTER);
        registerbtn.setPreferredSize(new Dimension(Constants.btnWidth, 30));
        registerbtn.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, fontSize));
        registerbtn.setForeground(Constants.COLOR_WHITE);
        registerbtn.setBackground(Constants.COLOR_Light_Grey);
        registerbtn.setFocusable(false);
        registerbtn.putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
//        registerbtn.setContentAreaFilled(false);
//        registerbtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        registerbtn.setBorder(new RoundedBorder(Constants.btnRadius));
        registerbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerbtn.addActionListener(this);

        container.add(pageName);
        container.add(pFields, "gapy 30");
        container.add(registerbtn, "gapy 30");

        this.add(container);

        // already have a log in----------------------------------------
        JPanel jLogin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jLogin.setOpaque(false);
        logIn = new JLabel("Already have an account?, ");
        logIn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        logIn.setForeground(Constants.COLOR_ORANGE);
        jLogin.add(logIn);
        logInLink = new JLabel("<html>Sing In.</html>");
        logInLink.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        logInLink.setForeground(Constants.COLOR_ORANGE);
        logInLink.addMouseListener(this);
        logInLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLogin.add(logInLink);

        this.add(jLogin);
    }
// =========================== Action Events =================================

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerbtn) {

            if (fieldValidation()) {
                if (MyJBDC.MyJDBC.register(email, password)) {
                    Register.this.dispose();
                    new LogIn().setVisible(true);
                } else {
                    errorMessage.setText("Email already register, please use another email or Login with the link at the bottom !!!");
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
        logInLink.setText("<html><i><b>Sign In.</b></i></html>");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        logInLink.setText("<html>Sign In.</html>");
    }

// --------------------------------------Validations ---------------------------
    private boolean emailValidation() {
        email = emailField.getText();
        if (email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
            emailField.setBackground(Constants.COLOR_Error);
            return false;
        }
        emailField.setBackground(Constants.COLOR_BACK);

        return true;
    }

    private boolean passwordValidation() {
        password = new String(this.passwordField.getPassword());
        if (password.isEmpty() || !password.matches(PASSWORD_PATTERN)) {
            passwordField.setBackground(Constants.COLOR_Error);
            passwordLabelCondition.setText("Password MUST contain at least 6 letter, 1 Uppercase, 1 Lowercase & 1 number");
            return false;
        }
        passwordField.setBackground(Constants.COLOR_BACK);
        passwordLabelCondition.setText("");

        return true;
    }

    private boolean rePasswordValidation() {
        rePassword = new String(this.rePasswordField.getPassword());
        if (!rePassword.equals(password)) {
            rePasswordField.setBackground(Constants.COLOR_Error);
            rePasswordLabelCondition.setText("Password's DON'T match");
            return false;
        }
        rePasswordField.setBackground(Constants.COLOR_BACK);
        rePasswordLabelCondition.setText("");

        return true;
    }

}
