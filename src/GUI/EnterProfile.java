/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
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
public class EnterProfile extends Form implements ActionListener {

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
    Date DOB;
    float weight;
    int height;

    public EnterProfile() {
        super("Profile");
        FlatMacDarkLaf.setup();
        addGUIComponent();
        /*
        CREATE ACCES TO THE USER INFORMATION USER 
        GET USER EMAIL TO UPDATE INFOMRATION...
        addUserInformation();
        FILL THE FIELDS WITH THE USER INFORMATION IF AVAILABLE
        */
    }
    private void addUserInformation(){
        
        // ADD INFORMATION TO FIELDS....
        
    }

    private void addGUIComponent() {

        int pageNameWith = 200;
        int pageNameCenter = (this.getWidth() - pageNameWith) / 2;
        // Register label
        JLabel pageName = new JLabel("Profile");
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
        lastNameLabel.setBounds(10, 120, 100, 50);
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lastNameLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        lastNameLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(lastNameLabel);

        // lastName Field ------------------------------------
        lastNameField = new JTextField();
        lastNameField.setBounds(110, 137, 140, fieldSpaceFontSize);
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

        // gender ---------------------------------------
        JLabel genderLabel = new JLabel("Gender: ");
        genderLabel.setBounds(10, 170, 100, 50);
        genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        genderLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        genderLabel.setForeground(Constants.Constants.COLOR_Light_Grey);
        this.add(genderLabel);

        // gender field ------------------------------------
        genderField = new JComboBox();
        genderField.addItem("Male");
        genderField.addItem("Female");
        genderField.setBounds(110, 182, 120, 30);
        genderField.setBackground(Constants.Constants.COLOR_Trans); // dark grey
        genderField.setForeground(Color.WHITE);
        genderField.setFocusable(false);
        genderField.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, fieldFontSize));
        this.add(genderField);

        // age label ------------------------------------
        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setBounds(10, 220, 100, 50);
        ageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        ageLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        ageLabel.setForeground(Constants.Constants.COLOR_Light_Grey); // change color font
        this.add(ageLabel);

        // email text Field ------------------------------------
        JPanel date = new JPanel();
        date.setBounds(110, 230, 120, 30);
        date.setLayout(new MigLayout("fill, insets 0"));
        datePicker = new DatePicker();
        JFormattedTextField editor = new JFormattedTextField();
        editor.setFocusable(false);
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
        weightField.setBounds(110, 287, 140, fieldSpaceFontSize);
        weightField.setBackground(Color.BLACK);
        weightField.setForeground(Color.WHITE);
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

        // rePasswordField label--------------------------------------
        JLabel heightLabel = new JLabel("Height: ");
        heightLabel.setBounds(10, 320, 100, 50);
        heightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        heightLabel.setFont(Constants.Constants.FONT_Medium.deriveFont(Font.PLAIN, labelFontSize));
        heightLabel.setForeground(Constants.Constants.COLOR_Light_Grey);
        this.add(heightLabel);

        // rePasswordField field ------------------------------------
        heightField = new JTextField();
        heightField.setBounds(110, 337, 140, fieldSpaceFontSize);
        heightField.setBackground(Color.BLACK);
        heightField.setForeground(Color.WHITE);
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

        // save button ---------------------------------------
        ImageIcon green = new ImageIcon("img/green.png");
        Image greenScale = green.getImage().getScaledInstance((int) (green.getIconWidth() * 0.4),
                (int) (green.getIconHeight() * 0.4), Image.SCALE_SMOOTH);
        ImageIcon greenIcon = new ImageIcon(greenScale);

        int btnWith = greenIcon.getIconWidth();
        int btnHeight = greenIcon.getIconHeight();
        int greenPosition = (this.getWidth() / 2 + btnWith);
        saveBtn = new JButton();
        saveBtn.setIcon(greenIcon);
        saveBtn.setBounds(greenPosition, 390, btnWith, btnHeight);
        saveBtn.setBackground(Color.BLACK);
        saveBtn.setFocusable(false);
        saveBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveBtn.addActionListener(this);
        this.add(saveBtn);

        // clear button ---------------------------------------
        ImageIcon clear = new ImageIcon("img/x.png");
        Image clearScale = clear.getImage().getScaledInstance((int) (clear.getIconWidth() * 0.3),
                (int) (clear.getIconHeight() * 0.3), Image.SCALE_SMOOTH);
        ImageIcon clearIcon = new ImageIcon(clearScale);

        int clearBtnWith = clearIcon.getIconWidth();
        int clearBtnHeight = clearIcon.getIconHeight();
        int clearPosition = (this.getWidth() / 2 - clearBtnWith * 2);
        clearBtn = new JButton();
        clearBtn.setIcon(clearIcon);
        clearBtn.setBounds(clearPosition, 390, clearBtnWith, clearBtnHeight);
        clearBtn.setBackground(Color.BLACK);
        clearBtn.setFocusable(false);
        clearBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearBtn.addActionListener(this);
        this.add(clearBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveBtn) {

//            if (fieldValidation()) {
//                System.out.println(fieldValidation());
//            }
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

// --------------------------------------Validations ---------------------------
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
        nameField.setBackground(Color.BLACK);
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
        lastNameField.setBackground(Color.BLACK);
        return true;
    }

    private boolean weightValidation() {
        String weightST = weightField.getText();
        if (!weightST.isEmpty()) {
            if (!weightST.matches("\\d{0,3}(\\.\\d{1})?")) {
                weightField.setBackground(Constants.Constants.COLOR_Error);
                return false;
            } else {
                try {
                    weight = Float.parseFloat(weightST);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        weightField.setBackground(Color.BLACK);
        return true;
    }

    private boolean heightValidation() {
        String heightST = heightField.getText();
        if (!heightST.isEmpty()) {
            if (!heightST.matches("^$|^(1\\d{2}|20\\d|220)$")) {
                heightField.setBackground(Constants.Constants.COLOR_Error);
                return false;
            } else {
                try {
                    height = Integer.parseInt(heightST);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        heightField.setBackground(Color.BLACK);
        return true;
    }

}
