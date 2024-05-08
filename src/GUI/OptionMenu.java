/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import UserInfo.UserProfile;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public class OptionMenu extends Form implements ActionListener {

    private final JPanel container = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    EnterProfile enterProfile;
    EnterCalories enterCalories;
    CaloriesStadistics stadistics;

    JButton profileBtn;
    JButton caloriesBtn;
    JButton stadisticsBtn;

    public OptionMenu() {
        super("Calorie Tracker");

        this.setLayout(new BorderLayout());
        container.setLayout(cardLayout);

        enterProfile = new EnterProfile();
        enterCalories = new EnterCalories();
        stadistics = new CaloriesStadistics(enterCalories);

        container.add(enterProfile, "profile");
        container.add(enterCalories, "calories");
        container.add(stadistics, "stadistics");

        // buttons ------------------------------------------
        int btnWid = 90;
//        JPanel master = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel master = new JPanel(new MigLayout("fillx, insets 0"));
        master.setBackground(Constants.Constants.COLOR_BACK);
        master.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        profileBtn = new JButton("Profile");
        profileBtn.setPreferredSize(new Dimension(btnWid, 20));
        profileBtn.setText("Profile");
        profileBtn.setBackground(Constants.Constants.COLOR_BACK);
        profileBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        profileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profileBtn.setBorder(null);
//        profileBtn.addFocusListener(focus);
        profileBtn.setFocusPainted(false);
        profileBtn.addActionListener(this);

        caloriesBtn = new JButton("Calories");
        caloriesBtn.setPreferredSize(new Dimension(btnWid, 20));
        caloriesBtn.setText("Calories");
        caloriesBtn.setBackground(Constants.Constants.COLOR_BACK);
        caloriesBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        caloriesBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        caloriesBtn.setBorder(null);
//        caloriesBtn.setFocusPainted(false);
        caloriesBtn.addActionListener(this);

        stadisticsBtn = new JButton("Stadistics");
        stadisticsBtn.setPreferredSize(new Dimension(btnWid, 20));
        stadisticsBtn.setText("Stadistics");
        stadisticsBtn.setBackground(Constants.Constants.COLOR_BACK);
        stadisticsBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        stadisticsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        stadisticsBtn.setBorder(null);
//        stadisticsBtn.setFocusPainted(false);
        stadisticsBtn.addActionListener(this);

        master.add(profileBtn);
        master.add(caloriesBtn);
        master.add(stadisticsBtn);

        this.add(master, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
        cardLayout.show(container, "calories");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == profileBtn) {
            cardLayout.show(container, "profile");
            profileBtn.setBackground(Constants.Constants.COLOR_ORANGE);
            profileBtn.setFont(Constants.Constants.FONT_SemiBold.deriveFont(Font.BOLD, 10));

            caloriesBtn.setBackground(Constants.Constants.COLOR_BACK);
            caloriesBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
            stadisticsBtn.setBackground(Constants.Constants.COLOR_BACK);
            stadisticsBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        }
        if (e.getSource() == caloriesBtn) {
            cardLayout.show(container, "calories");
            caloriesBtn.setBackground(Constants.Constants.COLOR_ORANGE);
            caloriesBtn.setFont(Constants.Constants.FONT_SemiBold.deriveFont(Font.BOLD, 10));
            enterCalories.getNameF().setText(UserProfile.getProfile().get("name"));

            profileBtn.setBackground(Constants.Constants.COLOR_BACK);
            profileBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
            stadisticsBtn.setBackground(Constants.Constants.COLOR_BACK);
            stadisticsBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        }
        if (e.getSource() == stadisticsBtn) {
            cardLayout.show(container, "stadistics");
            stadisticsBtn.setBackground(Constants.Constants.COLOR_ORANGE);
            stadisticsBtn.setFont(Constants.Constants.FONT_SemiBold.deriveFont(Font.BOLD, 10));

            caloriesBtn.setBackground(Constants.Constants.COLOR_BACK);
            caloriesBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
            profileBtn.setBackground(Constants.Constants.COLOR_BACK);
            profileBtn.setFont(Constants.Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        }
    }

}
