/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author chg
 */
public class OptionMenu extends Form {
    private JPanel container;
    private CardLayout cardLayout;
    
    public OptionMenu(){
        super("Calorie Tracker");
        
        container = new JPanel();
        cardLayout = new CardLayout();
        container.setLayout(cardLayout);
        
        EnterProfile enterProfile = new EnterProfile();
        EnterCalories enterCalories = new EnterCalories();
        CaloriesStadistics stadistics = new CaloriesStadistics();
        
        container.add(enterProfile, "profile");
        container.add(enterCalories, "calories");
        container.add(stadistics, "stadistics");
        
        JPanel master = new JPanel();
        JButton profileBtn = new JButton("Profile");
        JButton caloriesBtn = new JButton("Calories");
        JButton stadisticsBtn = new JButton("Stadistics");
        master.add(profileBtn);
        master.add(caloriesBtn);
        master.add(stadisticsBtn);
        
    }
    
}
