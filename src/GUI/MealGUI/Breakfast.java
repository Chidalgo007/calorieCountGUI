/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MealGUI;

import Constants.Constants;
import Helper.AddLine;
import com.formdev.flatlaf.FlatLaf;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public class Breakfast extends JPanel {

    private static JLabel caloTotal;
    private static JLabel fat;
    private static JLabel carbs;
    private static JLabel protein;

    private JPanel caloContainer;
    private static AddLine line;

    public Breakfast() {
        addGUIComponents();
        setLayout(new MigLayout("wrap, fillx", "[]", "[]"));
        this.setBackground(Constants.COLOR_BACK);
    }

    private void addGUIComponents() {
        //  header constainer-------------------------------------
        JPanel header = new JPanel(new MigLayout("fillx, insets 0", "[grow, fill]push[]", "[]"));
        header.setOpaque(false);

        JLabel breakfast = new JLabel("Breakfast");
        breakfast.setFont(Constants.FONT_SemiBold.deriveFont(Font.PLAIN, 15));
        // macros constiner -------------------------------------
        caloContainer = new JPanel(new MigLayout("fillx, insets 0", "30[]15[]5[]5[]5", "[]"));
        caloContainer.setOpaque(false);

        caloTotal = new JLabel();
        caloTotal.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 25));
        caloContainer.add(caloTotal);

        // individual container
        JPanel fatContainer = new JPanel(new BorderLayout());
        fatContainer.setOpaque(false);
        JLabel fatLabel = new JLabel("Fat");
        fatLabel.setForeground(Constants.COLOR_redFat);

        fat = new JLabel();
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

        header.add(breakfast, "growx");
        header.add(caloContainer);

        this.add(header);

        // ---center panel calories --------------------------------------------
        line = new AddLine();
        this.add(getLine().getMiddleContent());

        // ----- macros --------------------------------------------------------
    }

    public AddLine getLine() {
        return line;
    }

    public static void refreshValue() {

        float cal = line.getTotalCal();
        float f = line.getTotalFat();
        float c = line.getTotalCarb();
        float p = line.getTotalProtein();
        if (cal != 0) {
            float fatPercentage = (f / cal) * 100;
            float cabsPercentage = (c / cal) * 100;
            float proPercentage = (p / cal) * 100;

            fat.setText(String.format("%.1f%%", fatPercentage));
            carbs.setText(String.format("%.1f%%", cabsPercentage));
            protein.setText(String.format("%.1f%%", proPercentage));
            caloTotal.setText(String.valueOf(line.getTotalCal()));
        }
        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
    }
}
