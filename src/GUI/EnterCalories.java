/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Constants.Constants;
import GUI.MealGUI.Breakfast;
import GUI.MealGUI.Dinner;
import GUI.MealGUI.Lunch;
import GUI.MealGUI.Snacks;
import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author chg
 */
public class EnterCalories extends JPanel implements ActionListener {

    //private DatePicker datePicker;
    private DateChooser date;
    private static JTextField textDate;
    private JPanel header;
    final JLabel name = new JLabel();

    // meal multi panel container ---------------------------
    private final JPanel MEAL_CONTAINER = new JPanel();
    private final CardLayout CARDLAYOUT = new CardLayout();

    private final Breakfast bfast = new Breakfast();
    private final Lunch lunch = new Lunch();
    private final Dinner dinner = new Dinner();
    private final Snacks snack = new Snacks();

    private JButton bFastBtn;
    private JButton lunchBtn;
    private JButton dinnerBtn;
    private JButton snacksBtn;

    JButton[] btnFieldsArray = {};

    private final ImageIcon bfastIcon = new ImageIcon("./img/bfast.png");
    private final ImageIcon lunchIcon = new ImageIcon("./img/lunch.png");
    private final ImageIcon dinnerIcon = new ImageIcon("./img/dinner.png");
    private final ImageIcon snackIcon = new ImageIcon("./img/snack.png");

    public EnterCalories() {
        addGUIComponents();
    }

    private void addGUIComponents() {
        setLayout(new BorderLayout());
        setBackground(Constants.COLOR_BACK);

        header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(Constants.COLOR_BACK);
        JLabel welcome = new JLabel("Welcome ");
        welcome.setFont(Constants.FONT_Medium.deriveFont(15));

        name.setFont(Constants.FONT_SemiBold.deriveFont(17));

        // datechooser -------------------
        date = new DateChooser();
        date.setForeground(Constants.COLOR_ORANGE);

        textDate = new JTextField();
        date.setTextRefernce(getTextDate());// this set the date in the textfield
        getTextDate().setPreferredSize(new Dimension(80, 20));
        getTextDate().setBackground(Constants.COLOR_BACK);
        getTextDate().setBorder(null);
        getTextDate().setFocusable(false);
        getTextDate().addActionListener(this);
        getTextDate().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            // check date is not after today date
            public void insertUpdate(DocumentEvent e) {
                String dateSelected = getTextDate().getText();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate todayDate = LocalDate.parse(dateSelected, df);
                if (todayDate.isAfter(LocalDate.now())) {

                    LocalDate localDate = LocalDate.now();
                    // Convert the LocalDate object to a Date object
                    Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                    Date today = Date.from(instant);

                    SwingUtilities.invokeLater(() -> {
                        date.setSelectedDate(today);
                    });
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

//        datePicker = new DatePicker();
//        JFormattedTextField editor = new JFormattedTextField();
//        editor.setBorder(null);
//        editor.setOpaque(false);
//        datePicker.setEditor(editor);
//        datePicker.setSelectedDate(LocalDate.now());
//        datePicker.doLayout();
//        datePicker.setDateSelectionAble((LocalDate LocalDate) -> !LocalDate.isAfter(LocalDate.now()));
        header.add(getTextDate());
        header.add(welcome);
        header.add(name);
        // -------- middle components panel ---------------------
        MEAL_CONTAINER.setLayout(CARDLAYOUT);
        MEAL_CONTAINER.add(bfast, "breakfast");
        MEAL_CONTAINER.add(lunch, "lunch");
        MEAL_CONTAINER.add(dinner, "dinner");
        MEAL_CONTAINER.add(snack, "snacks");

        //buttons for middle components panel--------------------------
        JPanel mealBtn = new JPanel(new MigLayout("wrap,fillx, insets 0", "0[grow, fill]0[grow, fill]0[grow, fill]0[grow, fill]0", "[center]"));
        mealBtn.setOpaque(false);

        bFastBtn = new JButton();
        lunchBtn = new JButton();
        dinnerBtn = new JButton();
        snacksBtn = new JButton();
        btnFieldsArray = new JButton[]{bFastBtn, lunchBtn, dinnerBtn, snacksBtn};

        for (JButton btn : btnFieldsArray) {
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.setBackground(Constants.COLOR_BACK);
            btn.setLayout(new BorderLayout());
            btn.putClientProperty(FlatClientProperties.STYLE, ""+"arc:30");
            btn.setFocusable(false);
            btn.addActionListener(this);
//            btn.setBorder(null);
            mealBtn.add(btn,"growy");
        }
        bFastBtn.setBackground(Constants.COLOR_ORANGE);

        JLabel iconBF = new JLabel(bfastIcon);
        JLabel iconLCH = new JLabel(lunchIcon);
        JLabel iconDN = new JLabel(dinnerIcon);
        JLabel iconSK = new JLabel(snackIcon);

        JLabel labelBF = new JLabel("Breakfast");
        JLabel labelLCH = new JLabel("Lunch");
        JLabel labelDN = new JLabel("Dinner");
        JLabel labelSK = new JLabel("Snack");

        JLabel[] labelFieldsArray = {labelBF, labelLCH, labelDN, labelSK};

        for (JLabel label : labelFieldsArray) {
            label.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 10));
            label.setHorizontalTextPosition(JButton.CENTER);
            label.setHorizontalAlignment(JButton.CENTER);
        }

        bFastBtn.add(iconBF, BorderLayout.NORTH);
        bFastBtn.add(labelBF, BorderLayout.SOUTH);

        lunchBtn.add(iconLCH, BorderLayout.NORTH);
        lunchBtn.add(labelLCH, BorderLayout.SOUTH);

        dinnerBtn.add(iconDN, BorderLayout.NORTH);
        dinnerBtn.add(labelDN, BorderLayout.SOUTH);

        snacksBtn.add(iconSK, BorderLayout.NORTH);
        snacksBtn.add(labelSK, BorderLayout.SOUTH);

        this.add(header, BorderLayout.NORTH);
        this.add(MEAL_CONTAINER, BorderLayout.CENTER);
        this.add(mealBtn, BorderLayout.SOUTH);
        CARDLAYOUT.show(MEAL_CONTAINER, "breakfast");

    }
//------------------ Action Events ---------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bFastBtn) {
            CARDLAYOUT.show(MEAL_CONTAINER, "breakfast");
            bFastBtn.setBackground(Constants.COLOR_ORANGE);
            lunchBtn.setBackground(Constants.COLOR_BACK);
            dinnerBtn.setBackground(Constants.COLOR_BACK);
            snacksBtn.setBackground(Constants.COLOR_BACK);

            if (bfast.line.getLineArray().isEmpty()) {
                bfast.line.createLine();
            }
        }
        if (e.getSource() == lunchBtn) {
            CARDLAYOUT.show(MEAL_CONTAINER, "lunch");
            lunchBtn.setBackground(Constants.COLOR_ORANGE);
            bFastBtn.setBackground(Constants.COLOR_BACK);
            dinnerBtn.setBackground(Constants.COLOR_BACK);
            snacksBtn.setBackground(Constants.COLOR_BACK);
        }
        if (e.getSource() == dinnerBtn) {
            CARDLAYOUT.show(MEAL_CONTAINER, "dinner");
            dinnerBtn.setBackground(Constants.COLOR_ORANGE);
            lunchBtn.setBackground(Constants.COLOR_BACK);
            bFastBtn.setBackground(Constants.COLOR_BACK);
            snacksBtn.setBackground(Constants.COLOR_BACK);
        }
        if (e.getSource() == snacksBtn) {
            CARDLAYOUT.show(MEAL_CONTAINER, "snacks");
            snacksBtn.setBackground(Constants.COLOR_ORANGE);
            dinnerBtn.setBackground(Constants.COLOR_BACK);
            lunchBtn.setBackground(Constants.COLOR_BACK);
            bFastBtn.setBackground(Constants.COLOR_BACK);
        }
        if (e.getSource() == getTextDate()) {
            date.showPopup();
        }
    }

    /**
     * @return the textDate
     */
    public static JTextField getTextDate() {
        return textDate;
    }

}
