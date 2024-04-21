/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author chg
 */
public class OptionMenu extends Form implements ActionListener {

    private final JPanel container = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    EnterProfile enterProfile = new EnterProfile();
    EnterCalories enterCalories = new EnterCalories();
    CaloriesStadistics stadistics = new CaloriesStadistics();

    JButton profileBtn;
    JButton caloriesBtn;
    JButton stadisticsBtn;

    public OptionMenu() {
        super("Calorie Tracker");

        this.setLayout(new BorderLayout());
        container.setLayout(cardLayout);

        container.add(enterProfile, "profile");
        container.add(enterCalories, "calories");
        container.add(stadistics, "stadistics");

        // buttons ------------------------------------------
//        int btnWid = Constants.Constants.WIDTH / 3;
        int btnWid = 90;
        JPanel master = new JPanel(new FlowLayout(FlowLayout.LEFT));
        master.setBackground(Constants.Constants.COLOR_BACK);
//        master.setBorder(null);
        master.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        FocusListener focus = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ((JButton) e.getSource()).setBackground(Constants.Constants.COLOR_4);
                ((JButton) e.getSource()).setFont(Constants.Constants.FONT_SemiBold.deriveFont(Font.BOLD, 10));
            }

            @Override
            public void focusLost(FocusEvent e) {
                ((JButton) e.getSource()).setBackground(Constants.Constants.COLOR_BACK);
                ((JButton) e.getSource()).setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
            }
        };

        profileBtn = new JButton("Profile");
        profileBtn.setPreferredSize(new Dimension(btnWid, 20));
        profileBtn.setText("Profile");
        profileBtn.setBackground(Constants.Constants.COLOR_BACK);
        profileBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        profileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profileBtn.setBorder(null);
        profileBtn.addFocusListener(focus);
        profileBtn.setFocusPainted(false);
        profileBtn.addActionListener(this);

        caloriesBtn = new JButton("Calories");
        caloriesBtn.setPreferredSize(new Dimension(btnWid, 20));
        caloriesBtn.setText("Calories");
        caloriesBtn.setBackground(Constants.Constants.COLOR_BACK);
        caloriesBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        caloriesBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        caloriesBtn.setBorder(null);
        caloriesBtn.addFocusListener(focus);
        caloriesBtn.setFocusPainted(false);
        caloriesBtn.addActionListener(this);

        stadisticsBtn = new JButton("Stadistics");
        stadisticsBtn.setPreferredSize(new Dimension(btnWid, 20));
        stadisticsBtn.setText("Stadistics");
        stadisticsBtn.setBackground(Constants.Constants.COLOR_BACK);
        stadisticsBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        stadisticsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        stadisticsBtn.setBorder(null);
        stadisticsBtn.addFocusListener(focus);
        stadisticsBtn.setFocusPainted(false);
        stadisticsBtn.addActionListener(this);

        master.add(profileBtn);
        master.add(caloriesBtn);
        master.add(stadisticsBtn);

        this.add(master, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
        cardLayout.show(container, "profile");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == profileBtn) {
            cardLayout.show(container, "profile");
        }
        if (e.getSource() == caloriesBtn) {
            cardLayout.show(container, "calories");
        }
        if (e.getSource() == stadisticsBtn) {
            cardLayout.show(container, "stadistics");
        }
    }

}
