/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Constants.Constants;
import com.formdev.flatlaf.FlatLaf;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public class AddMacros {
    public JPanel getCaloContainer(){
        return caloContainer;
    }

    private JLabel caloTotal;
    private JLabel fat;
    private JLabel carbs;
    private JLabel protein;
    private JPanel caloContainer;

    public AddMacros() {
        addGUIComponents();
    }

    private void addGUIComponents() {
        caloContainer = new JPanel(new MigLayout("fillx, insets 0", "30[]15[]5[]5[]5", "[]"));
        caloContainer.setOpaque(false);

        caloTotal = new JLabel();
        caloTotal.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 25));
        caloTotal.setText("0");
        caloContainer.add(caloTotal);

        // individual container
        JPanel fatContainer = new JPanel(new BorderLayout());
        fatContainer.setOpaque(false);
        JLabel fatLabel = new JLabel("Fat");
        fatLabel.setForeground(Constants.COLOR_redFat);

        fat = new JLabel();
        fat.setText("0%");
        fat.setForeground(Constants.COLOR_redFat);
        fat.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));

        fatContainer.add(fatLabel, BorderLayout.NORTH);
        fatContainer.add(fat, BorderLayout.SOUTH);
        caloContainer.add(fatContainer);

        // individual container
        JPanel carbsContainer = new JPanel(new BorderLayout());
        carbsContainer.setOpaque(false);
        JLabel carbsLabel = new JLabel("Carbs");
        carbsLabel.setForeground(Constants.COLOR_yellowCarb);

        carbs = new JLabel();
        carbs.setText("0%");
        carbs.setForeground(Constants.COLOR_yellowCarb);
        carbs.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));

        carbsContainer.add(carbsLabel, BorderLayout.NORTH);
        carbsContainer.add(carbs, BorderLayout.SOUTH);
        caloContainer.add(carbsContainer);

        // individual container
        JPanel proteinCont = new JPanel(new BorderLayout());
        proteinCont.setOpaque(false);
        JLabel proLabel = new JLabel("Protein");
        proLabel.setForeground(Constants.COLOR_greenPro);

        protein = new JLabel();
        protein.setText("0%");
        protein.setForeground(Constants.COLOR_greenPro);
        protein.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 15));

        proteinCont.add(proLabel, BorderLayout.NORTH);
        proteinCont.add(protein, BorderLayout.SOUTH);
        caloContainer.add(proteinCont);

        //commun properties for food label fat, carbs and protein
        JLabel[] label = {fatLabel, carbsLabel, proLabel};
        for (JLabel L : label) {
            L.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 10));
            L.setHorizontalTextPosition(JLabel.CENTER);
            L.setHorizontalAlignment(JLabel.CENTER);
            L.setOpaque(false);
        }
    }

    // need set values for this in the AddLineManager
    public void setInfoValues(String stFat, String stCarbs, String stProtein, String stCal) {
        fat.setText(stFat);
        carbs.setText(stCarbs);
        protein.setText(stProtein);
        caloTotal.setText(stCal);
        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
    }

}
