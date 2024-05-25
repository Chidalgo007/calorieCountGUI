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
import UserInfo.UserProfile;
import com.formdev.flatlaf.FlatClientProperties;
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
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import raven.datetime.component.date.DateEvent;
import raven.datetime.component.date.DatePicker;

/**
 *
 * @author chg
 */
public class EnterCalories extends JPanel implements ActionListener {

    /**
     * @return the shareDate
     */
    public String getShareDate() {
        return shareDate;
    }

    public JTextField getTextDate() {
        return textDate;
    }

    public JLabel getNameF() {
        return nameF;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    private DatePicker datePicker;
//    private DateChooser date;
    private final JTextField textDate;
    private String shareDate; // assigning dateText to here
    private JPanel header;
    private final JLabel nameF;

    // meal multi panel container ---------------------------
    private final JPanel MEAL_CONTAINER = new JPanel();
    private final CardLayout CARDLAYOUT = new CardLayout();

    private final Breakfast bfast;
    private final Lunch lunch;
    private final Dinner dinner;
    private final Snacks snack;

    private JButton bFastBtn;
    private JButton lunchBtn;
    private JButton dinnerBtn;
    private JButton snacksBtn;

    private JButton[] btnFieldsArray = {};
    
    private final ImageIcon bfastIcon = new ImageIcon(getClass().getClassLoader().getResource("bfast.png"));
    private final ImageIcon lunchIcon = new ImageIcon(getClass().getClassLoader().getResource("lunch.png"));
    private final ImageIcon dinnerIcon = new ImageIcon(getClass().getClassLoader().getResource("dinner.png"));
    private final ImageIcon snackIcon = new ImageIcon(getClass().getClassLoader().getResource("snack.png"));

    public EnterCalories() {
        bfast = new Breakfast(this);
        lunch = new Lunch(this);
        dinner = new Dinner(this);
        snack = new Snacks(this);
        textDate = new JTextField(); // this is where the date will be displayed
        nameF = new JLabel(); // name will be displayed here
        addGUIComponents();
    }

    private void addGUIComponents() {
        setLayout(new BorderLayout());
        setBackground(Constants.COLOR_BACK);

        header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(Constants.COLOR_BACK);
        JLabel welcome = new JLabel("Welcome ");
        welcome.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN,14));

        nameF.setText(UserProfile.getProfile().get("name"));
        nameF.setFont(Constants.FONT_SemiBold.deriveFont(Font.PLAIN,15));

// ---- datepicker ------------------
        JFormattedTextField date = new JFormattedTextField();
        date.setFocusable(false);
        date.setEditable(false);
        date.setOpaque(false);
        date.setBorder(null);
        datePicker = new DatePicker();
        datePicker.setEditor(date);
        datePicker.setCloseAfterSelected(true);
        datePicker.setSelectedDate(LocalDate.now());
        datePicker.setDateSelectionAble((LocalDate LocalDate) -> !LocalDate.isAfter(LocalDate.now()));
        datePicker.addDateSelectionListener((DateEvent e) -> {
            if (!datePicker.isDateSelected()) {
                datePicker.setSelectedDate(LocalDate.now());
            }
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate temp = datePicker.getSelectedDate();
            shareDate = temp.format(dateFormat);
            notifyDateChange();
        });

        header.add(date);
        header.add(welcome);
        header.add(nameF);
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
            btn.putClientProperty(FlatClientProperties.STYLE, "" + "arc:30");
            btn.setFocusable(false);
            btn.addActionListener(this);

            mealBtn.add(btn, "growy");
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
// -------------------- fin meal btns -----------------------------
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
    }
    
    private void notifyDateChange() {
    // call a method in each meal window to update its content acording to date selected
    bfast.updateContentForNewDate(shareDate);
    lunch.updateContentForNewDate(shareDate);
    dinner.updateContentForNewDate(shareDate);
    snack.updateContentForNewDate(shareDate);
}
}
