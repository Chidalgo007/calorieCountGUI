/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import MyJBDC.MyJDBC;
import UserInfo.UserProfile;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import raven.datetime.component.date.DatePicker;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public class EnterProfile extends JPanel implements ActionListener {

    JTextField nameField;
    JTextField lastNameField;
    JComboBox<String> genderField;
    DatePicker datePicker;
    JTextField weightField;
    JTextField heightField;
    JButton saveBtn;
    JButton clearBtn;

    int labelFontSize = 13;
    int fieldFontSize = 13;
    int fieldSpaceFontSize = 15;
    private static final String LETTER_PATTERN = "^[A-Za-z]+$";
    //--------------------------------------------------------------------------
    String name = "";
    String lastname = "";
    String gender;
    LocalDate DOB;
    String weight;
    String height;

    public EnterProfile() {
        //  super("Profile");

        addGUIComponent();
        addUserInformation();
        this.setLayout(null);
        this.setBackground(Constants.Constants.COLOR_BACK);

    }

    private void addGUIComponent() {

        int pageNameWith = 200;
        int pageNameCenter = (Constants.Constants.WIDTH - pageNameWith) / 2;
        // Register label
        JLabel pageName = new JLabel("Profile");
        pageName.setBounds(pageNameCenter, 10, pageNameWith, 50);
        pageName.setHorizontalAlignment(SwingConstants.CENTER);
        pageName.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, 20));
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
        nameField.setBounds(120, 87, 130, fieldSpaceFontSize);
        nameField.setBackground(Constants.Constants.COLOR_BACK);
        nameField.setForeground(Constants.Constants.COLOR_WHITE);
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
        lastNameLabel.setBounds(10, 120, 100, 50);
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lastNameLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        lastNameLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(lastNameLabel);

        // lastName Field ------------------------------------
        lastNameField = new JTextField();
        lastNameField.setBounds(120, 137, 130, fieldSpaceFontSize);
        lastNameField.setBackground(Constants.Constants.COLOR_BACK);
        lastNameField.setForeground(Constants.Constants.COLOR_WHITE);
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

        // gender ---------------------------------------
        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setBounds(10, 170, 100, 50);
        genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        genderLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        genderLabel.setForeground(Constants.Constants.COLOR_Light_Grey);
        this.add(genderLabel);

        // gender field ------------------------------------
        genderField = new JComboBox();
        genderField.addItem("");
        genderField.addItem("Male");
        genderField.addItem("Female");
        genderField.setBounds(120, 182, 120, 30);
        genderField.setBackground(Constants.Constants.COLOR_Trans); // dark grey
        genderField.setForeground(Constants.Constants.COLOR_WHITE);
        genderField.setFocusable(false);
        genderField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, fieldFontSize));
        this.add(genderField);

        // DOB label ------------------------------------
        JLabel DOBLabel = new JLabel("Date of Birth: ");
        DOBLabel.setBounds(10, 220, 100, 50);
        DOBLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        DOBLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        DOBLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(DOBLabel);

        // DOB Field ------------------------------------
        JPanel date = new JPanel();
        date.setOpaque(false);
        date.setBounds(120, 230, 120, 30);
        date.setLayout(new MigLayout("fill, insets 0"));
        datePicker = new DatePicker();
        datePicker.setOpaque(false);
        JFormattedTextField editor = new JFormattedTextField();
        editor.setFocusable(false);
        editor.setBackground(Constants.Constants.COLOR_BACK);
        datePicker.setCloseAfterSelected(true);
        datePicker.setEditor(editor);
        datePicker.setDateSelectionAble((LocalDate LocalDate) -> !LocalDate.isAfter(LocalDate.now()));

        date.add(editor, "width 100%");
        this.add(date);

        // weightField label ------------------------------------
        JLabel weightLabel = new JLabel("Weight: ");
        weightLabel.setBounds(10, 270, 100, 50);
        weightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        weightLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        weightLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(weightLabel);

        // weightField textField ------------------------------------
        weightField = new JTextField();
        weightField.setBounds(120, 287, 80, fieldSpaceFontSize);
        weightField.setBackground(Constants.Constants.COLOR_BACK);
        weightField.setForeground(Constants.Constants.COLOR_WHITE);
        weightField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, fieldFontSize));
        weightField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        weightField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                weightValidation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                weightValidation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.add(weightField);
        
        // weightField label ------------------------------------
        JLabel weightLabelKg = new JLabel("Kg");
        weightLabelKg.setBounds(190, 275, 50, 50);
        weightLabelKg.setHorizontalAlignment(SwingConstants.RIGHT);
        weightLabelKg.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        weightLabelKg.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(weightLabelKg);

        // heightLabel label--------------------------------------
        JLabel heightLabel = new JLabel("Height: ");
        heightLabel.setBounds(10, 320, 100, 50);
        heightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        heightLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        heightLabel.setForeground(Constants.Constants.COLOR_Light_Grey);
        this.add(heightLabel);

        // heightLabel field ------------------------------------
        heightField = new JTextField();
        heightField.setBounds(120, 337, 80, fieldSpaceFontSize);
        heightField.setBackground(Constants.Constants.COLOR_BACK);
        heightField.setForeground(Constants.Constants.COLOR_WHITE);
        heightField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, fieldFontSize));
        heightField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Constants.Constants.COLOR_Light_Grey));
        heightField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                heightValidation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                heightValidation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.add(heightField);
        
        // heightLabel label--------------------------------------
        JLabel heightLabelCm = new JLabel("cm");
        heightLabelCm.setBounds(190, 325, 50, 50);
        heightLabelCm.setHorizontalAlignment(SwingConstants.RIGHT);
        heightLabelCm.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        heightLabelCm.setForeground(Constants.Constants.COLOR_Light_Grey);
        this.add(heightLabelCm);

        // save button ---------------------------------------
        ImageIcon green = new ImageIcon(getClass().getClassLoader().getResource("green.png"));
        Image greenScale = green.getImage().getScaledInstance((int) (green.getIconWidth() * 0.4),
                (int) (green.getIconHeight() * 0.4), Image.SCALE_SMOOTH);
        ImageIcon greenIcon = new ImageIcon(greenScale);

        int btnWith = greenIcon.getIconWidth();
        int btnHeight = greenIcon.getIconHeight();
        int greenPosition = (Constants.Constants.WIDTH / 2 + btnWith);
        saveBtn = new JButton();
        saveBtn.setIcon(greenIcon);
        saveBtn.setBounds(greenPosition, 380, btnWith, btnHeight);
        saveBtn.setBackground(Constants.Constants.COLOR_BACK);
        saveBtn.setFocusable(false);
        saveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveBtn.addActionListener(this);
        this.add(saveBtn);

        // clear button ---------------------------------------
        ImageIcon clear = new ImageIcon(getClass().getClassLoader().getResource("x.png"));
        Image clearScale = clear.getImage().getScaledInstance((int) (clear.getIconWidth() * 0.3),
                (int) (clear.getIconHeight() * 0.3), Image.SCALE_SMOOTH);
        ImageIcon clearIcon = new ImageIcon(clearScale);

        int clearBtnWith = clearIcon.getIconWidth();
        int clearBtnHeight = clearIcon.getIconHeight();
        int clearPosition = (Constants.Constants.WIDTH / 2 - clearBtnWith * 2);
        clearBtn = new JButton();
        clearBtn.setIcon(clearIcon);
        clearBtn.setBounds(clearPosition, 380, clearBtnWith, clearBtnHeight);
        clearBtn.setBackground(Constants.Constants.COLOR_BACK);
        clearBtn.setFocusable(false);
        clearBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearBtn.addActionListener(this);
        this.add(clearBtn);

    }

        // this method check if the user already update the information on his profile and update the GUI
    private void addUserInformation() {
        String[] fields = {"name", "lastName", "gender", "DOB", "weight", "height"};

        for (String field : fields) {
            if (UserProfile.getProfile().containsKey(field)) {
                String st = UserInfo.UserProfile.getProfile().get(field);
                switch (field) {
                    case "name":
                        nameField.setText(st);
                        break;
                    case "lastName":
                        lastNameField.setText(st);
                        break;
                    case "gender":
                        genderField.setSelectedItem(st);
                        break;
                    case "DOB":
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        try {
                            LocalDate date = LocalDate.parse(st, formatter);

                            datePicker.setSelectedDate(date);
                        } catch (DateTimeParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "weight":
                        weightField.setText(st);
                        break;
                    case "height":
                        heightField.setText(st);
                        break;
                }
            }

        }
    }
    
    //------------------- Action Performed -------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveBtn) {
            if (UserProfile.getID() != -1) {
                gender = (String) this.genderField.getSelectedItem();
                DOB = datePicker.getSelectedDate();
                MyJDBC.insertUserProfile(name, lastname, gender, DOB, weight, height, UserProfile.getID());
                UserProfile.setProfile(MyJDBC.getUserProfile(UserProfile.getID()));
            } else {
                System.out.println("user not exist = -1 ?..." + UserProfile.getID());
            }
        }
        if (e.getSource() == clearBtn) {
            nameField.setText("");
            lastNameField.setText("");
            genderField.setSelectedIndex(0);
            datePicker.clearSelectedDate();
            weightField.setText("");
            heightField.setText("");
        }

    }

    
// --------------------------------Validations ---------------------------------
    private boolean nameValidation() {
        String nameST = nameField.getText();
        if (!nameST.isEmpty()) {
            if (!nameST.matches(LETTER_PATTERN)) {
                nameField.setBackground(Constants.Constants.COLOR_Error);
                return false;
            } else {
                name = nameST;
            }
        }
        nameField.setBackground(Constants.Constants.COLOR_BACK);
        return true;
    }

    private boolean lastNameValidation() {
        String lastnameST = lastNameField.getText();
        if (!lastnameST.isEmpty()) {
            if (!lastnameST.matches(LETTER_PATTERN)) {
                lastNameField.setBackground(Constants.Constants.COLOR_Error);
                return false;
            } else {
                lastname = lastnameST;
            }
        }
        lastNameField.setBackground(Constants.Constants.COLOR_BACK);
        return true;
    }

    private boolean weightValidation() {
        String weightST = weightField.getText();
        if (!weightST.isEmpty()) {
            if (!weightST.matches("\\d{0,3}(\\.\\d{1})?")) {
                weightField.setBackground(Constants.Constants.COLOR_Error);
                return false;
            } else {
                weight = weightST;
            }
        }
        weightField.setBackground(Constants.Constants.COLOR_BACK);
        return true;
    }

    private boolean heightValidation() {
        String heightST = heightField.getText();
        if (!heightST.isEmpty()) {
            if (!heightST.matches("^$|^(1\\d{2}|20\\d|220)$")) {
                heightField.setBackground(Constants.Constants.COLOR_Error);
                return false;
            } else {
                height = heightST;
            }
        }
        heightField.setBackground(Constants.Constants.COLOR_BACK);
        return true;
    }

}
