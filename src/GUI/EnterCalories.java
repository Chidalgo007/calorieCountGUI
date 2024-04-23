/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Constants.Constants;
import GUI.MealGUI.Breakfast;
import GUI.MealGUI.Dinner;
import GUI.MealGUI.Lunch;
import GUI.MealGUI.Snaks;
import UserInfo.UserProfile;
import com.raven.datechooser.DateChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DatePicker;
import java.awt.Dimension;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author chg
 */
public class EnterCalories extends JPanel implements ActionListener {

    DatePicker datePicker;
    DateChooser date;
    JTextField text;
    JPanel header;
    JLabel name = new JLabel();

    // meal multi panel container ---------------------------
    private final JPanel MEAL_CONTAINER = new JPanel();
    private final CardLayout CARDLAYOUT = new CardLayout();

    Breakfast bfast = new Breakfast();
    Lunch lunch = new Lunch();
    Dinner dinner = new Dinner();
    Snaks snack = new Snaks();

    JButton bFastBtn;
    JButton lunchBtn;
    JButton dinnerBtn;
    JButton snaksBtn;

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
        date.setForeground(Constants.COLOR_BLUE);

        text = new JTextField();
        date.setTextRefernce(text);// this set the date in the textfield
        text.setPreferredSize(new Dimension(80, 20));
        text.setBackground(Constants.COLOR_BACK);
        text.setBorder(null);
        text.setFocusable(false);
        text.addActionListener(this);
        text.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String dateSelected = text.getText();
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
        header.add(text);
        header.add(welcome);
        header.add(name);
        // -------- middle components panel ---------------------
        MEAL_CONTAINER.setLayout(CARDLAYOUT);
        MEAL_CONTAINER.add(bfast, "breakfast");
        MEAL_CONTAINER.add(lunch, "lunch");
        MEAL_CONTAINER.add(dinner, "dinner");
        MEAL_CONTAINER.add(snack, "snaks");

        //buttons for middle components--------------------------
        JPanel mealBtn = new JPanel(new MigLayout("fillx, insets 0", "[center]", "[bottom]"));
        mealBtn.setOpaque(false);
        FocusListener focus = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ((JButton) e.getSource()).setBackground(Constants.COLOR_Light_BAKG);
            }

            @Override
            public void focusLost(FocusEvent e) {
                ((JButton) e.getSource()).setBackground(Constants.COLOR_BACK);
            }
        };
        bFastBtn = new JButton("bfast");
        bFastBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        bFastBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bFastBtn.setBorder(null);
        bFastBtn.addFocusListener(focus);
        bFastBtn.setFocusPainted(false);
        bFastBtn.addActionListener(this);

        lunchBtn = new JButton("lunch");
        lunchBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        lunchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lunchBtn.setBorder(null);
        lunchBtn.addFocusListener(focus);
        lunchBtn.setFocusPainted(false);
        lunchBtn.addActionListener(this);

        dinnerBtn = new JButton("dinner");
        dinnerBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        dinnerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dinnerBtn.setBorder(null);
        dinnerBtn.addFocusListener(focus);
        dinnerBtn.setFocusPainted(false);
        dinnerBtn.addActionListener(this);

        snaksBtn = new JButton("snaks");
        snaksBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        snaksBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        snaksBtn.setBorder(null);
        snaksBtn.addFocusListener(focus);
        snaksBtn.setFocusPainted(false);
        snaksBtn.addActionListener(this);

        mealBtn.add(bFastBtn);
        mealBtn.add(lunchBtn);
        mealBtn.add(dinnerBtn);
        mealBtn.add(snaksBtn);

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
        }
        if (e.getSource() == lunchBtn) {
            CARDLAYOUT.show(MEAL_CONTAINER, "lunch");
        }
        if (e.getSource() == dinnerBtn) {
            CARDLAYOUT.show(MEAL_CONTAINER, "dinner");
        }
        if (e.getSource() == snaksBtn) {
            CARDLAYOUT.show(MEAL_CONTAINER, "snaks");
        }
        if (e.getSource() == text) {
            date.showPopup();
        }
    }

}
