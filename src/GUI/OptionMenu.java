/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Constants.Constants;
import UserInfo.UserProfile;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    JButton logOutBtn;

    public OptionMenu() {
        super("Calorie Tracker");

        this.setLayout(new BorderLayout());
        container.setLayout(cardLayout);

        enterProfile = new EnterProfile();
        enterCalories = new EnterCalories();
        stadistics = new CaloriesStadistics();

        container.add(enterProfile, "profile");
        container.add(enterCalories, "calories");
        container.add(stadistics, "stadistics");

        // buttons ------------------------------------------
        int btnWid = 90;
        JPanel master = new JPanel(new MigLayout("wrap,fillx, insets 0","5[]0[]0[]20[20]5","[]"));
        master.setBackground(Constants.COLOR_BACK);
        master.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        profileBtn = new JButton("Profile");
        profileBtn.setPreferredSize(new Dimension(btnWid, 20));
        profileBtn.setText("Profile");
        profileBtn.setBackground(Constants.COLOR_BACK);
        profileBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        profileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profileBtn.putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        profileBtn.setFocusPainted(false);
        profileBtn.addActionListener(this);

        caloriesBtn = new JButton("Calories");
        caloriesBtn.setPreferredSize(new Dimension(btnWid, 20));
        caloriesBtn.setText("Calories");
        caloriesBtn.setBackground(Constants.COLOR_BACK);
        caloriesBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        caloriesBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        caloriesBtn.putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        caloriesBtn.setFocusPainted(false);
        caloriesBtn.addActionListener(this);

        stadisticsBtn = new JButton("Stadistics");
        stadisticsBtn.setPreferredSize(new Dimension(btnWid, 20));
        stadisticsBtn.setText("Stadistics");
        stadisticsBtn.setBackground(Constants.COLOR_BACK);
        stadisticsBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        stadisticsBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        stadisticsBtn.putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        stadisticsBtn.setFocusPainted(false);
        stadisticsBtn.addActionListener(this);

// Logout
        ImageIcon logOutIcon = new ImageIcon(getClass().getClassLoader().getResource("logOut.png"));
        logOutBtn = new JButton();
        logOutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logOutBtn.putClientProperty(FlatClientProperties.STYLE, "" + "arc:40");
        logOutBtn.setBackground(Constants.COLOR_BACK);
        logOutBtn.setFocusable(false);
        logOutBtn.setBorder(null);
        logOutBtn.addActionListener(this);
//        logOutBtn.setPreferredSize(new Dimension(20, 20));
        JLabel iconLogOut = new JLabel(logOutIcon);
        logOutBtn.add(iconLogOut);

        master.add(profileBtn);
        master.add(caloriesBtn);
        master.add(stadisticsBtn);
        master.add(logOutBtn,"height 30!");

        this.add(master, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
        cardLayout.show(container, "calories");
    }

    // ----------------------- Action Performed -------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == profileBtn) {
            cardLayout.show(container, "profile");
            profileBtn.setBackground(Constants.COLOR_ORANGE);
            profileBtn.setFont(Constants.FONT_SemiBold.deriveFont(Font.BOLD, 10));

            caloriesBtn.setBackground(Constants.COLOR_BACK);
            caloriesBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
            stadisticsBtn.setBackground(Constants.COLOR_BACK);
            stadisticsBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        }
        if (e.getSource() == caloriesBtn) {
            cardLayout.show(container, "calories");
            caloriesBtn.setBackground(Constants.COLOR_ORANGE);
            caloriesBtn.setFont(Constants.FONT_SemiBold.deriveFont(Font.BOLD, 10));
            enterCalories.getNameF().setText(UserProfile.getProfile().get("name"));

            profileBtn.setBackground(Constants.COLOR_BACK);
            profileBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
            stadisticsBtn.setBackground(Constants.COLOR_BACK);
            stadisticsBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
        }
        if (e.getSource() == stadisticsBtn) {
            cardLayout.show(container, "stadistics");
            stadisticsBtn.setBackground(Constants.COLOR_ORANGE);
            stadisticsBtn.setFont(Constants.FONT_SemiBold.deriveFont(Font.BOLD, 10));

            caloriesBtn.setBackground(Constants.COLOR_BACK);
            caloriesBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));
            profileBtn.setBackground(Constants.COLOR_BACK);
            profileBtn.setFont(Constants.FONT_Light.deriveFont(Font.PLAIN, 10));

            stadistics.container.getInformation();// called to update info
        }
        if (e.getSource()==logOutBtn){
            OptionMenu.this.dispose();
            new Init().setVisible(true);
        }
    }

}
