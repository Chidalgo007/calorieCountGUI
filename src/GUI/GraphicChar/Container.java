/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.GraphicChar;

import GUI.GraphicChar.Char;
import Constants.Constants;
import GUI.EnterCalories;
import MyJBDC.MyJDBC;
import UserInfo.UserProfile;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 *
 * this class manage the charplot, Y-labels , the labels ans the dates
 * displayed. retrive the information and calculate the values and pass them to
 * the correspondent classes
 *
 */
public class Container extends JPanel {

    private Char charPlot;
    private Label labelChar;
    private LabelDays labelDays;
    private DateRange dateRange;

    private int totalCalorie;
    private int totalFat;
    private int totalCarbs;
    private int totalProtein;

    private int totalBF;
    private int totalLunch;
    private int totalDinner;
    private int totalSnacks;

    private Map<String, List<Map<String, String>>> stInfo = new HashMap<>();

    public Container() {

        setPreferredSize(new Dimension(Constants.WIDTH - 10, Constants.HEIGHT - 70));
        putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        setLayout(new MigLayout("wrap,fillx", "[]", "[]15[]"));
        setBackground(Constants.COLOR_BACK);

        JPanel top = new JPanel(new MigLayout("wrap,fillx", "[]", "[]10[][]"));
        top.putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        top.setBackground(Constants.COLOR_Light_BAKG);

        JPanel bottom = new JPanel(new MigLayout());
        bottom.putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        bottom.setBackground(Constants.COLOR_Light_BAKG);

        charPlot = new Char();
        labelChar = new Label();
        labelDays = new LabelDays();
        dateRange = new DateRange(this);

        top.add(dateRange);
        top.add(charPlot);
        top.add(labelDays);
        bottom.add(labelChar);
        add(top);
        add(bottom);
    }

    public void getInformation() {
        stInfo = MyJDBC.retrieveOneWeek(UserProfile.getID());
        calculateValues();
    }

    private void calculateValues() {
        String[] dates = dateRange.getDateLabelInfo();
        totalCalorie = 0;
        totalFat = 0;
        totalCarbs = 0;
        totalProtein = 0;
        totalBF = 0;
        totalLunch = 0;
        totalDinner = 0;
        totalSnacks = 0;
        int[] cal = new int[7];
        int[] fat = new int[7];
        int[] carbs = new int[7];
        int[] prot = new int[7];

        for (int i = 0; i < dates.length; i++) {
            if (stInfo.get(dates[i]) != null) {
//            System.out.println("calculation stInfo 1er layer " + stInfo.get(dates[i]).toString());
                int tempCal = 0;
                int tempFat = 0;
                int tempCarb = 0;
                int tempProt = 0;
                for (Map<String, String> entry : stInfo.get(dates[i])) {
                    // values for labels 
                    totalCalorie += Integer.parseInt(entry.get("calorie"));
                    totalFat += Integer.parseInt(entry.get("fat"));
                    totalCarbs += Integer.parseInt(entry.get("carbs"));
                    totalProtein += Integer.parseInt(entry.get("protein"));

                    totalBF += entry.get("meal").equalsIgnoreCase("BREAKFAST") ? Integer.parseInt(entry.get("calorie")) : 0;
                    totalLunch += entry.get("meal").equalsIgnoreCase("LUNCH") ? Integer.parseInt(entry.get("calorie")) : 0;
                    totalDinner += entry.get("meal").equalsIgnoreCase("DINNER") ? Integer.parseInt(entry.get("calorie")) : 0;
                    totalSnacks += entry.get("meal").equalsIgnoreCase("SNACKS") ? Integer.parseInt(entry.get("calorie")) : 0;

                    // values for bar plot ------------------------------
                    tempCal += Integer.parseInt(entry.get("calorie"));
                    tempFat += Integer.parseInt(entry.get("fat"));
                    tempCarb += Integer.parseInt(entry.get("carbs"));
                    tempProt += Integer.parseInt(entry.get("protein"));
                }

                cal[i] = tempCal;
                fat[i] = tempFat;
                carbs[i] = tempCarb;
                prot[i] = tempProt;
            }
        }
        //assign value of y axis for calorie max
        int temp = 0;
        for (int i = 0; i < cal.length; i++) {
            if (cal[i] > temp) {
                temp = cal[i];
            }
        }
        charPlot.setTotalCalChar(temp);
        //assign values to array in char class
        charPlot.setCal(cal);
        charPlot.setFat(fat);
        charPlot.setCarbs(carbs);
        charPlot.setProt(prot);

        refreshStats();
    }

    private void refreshStats() {
        // dates working --------------------------------
        labelDays.getMonDate().setText(dateRange.getDateLabel()[0]);
        labelDays.getTuesDate().setText(dateRange.getDateLabel()[1]);
        labelDays.getWedDate().setText(dateRange.getDateLabel()[2]);
        labelDays.getThurDate().setText(dateRange.getDateLabel()[3]);
        labelDays.getFriDate().setText(dateRange.getDateLabel()[4]);
        labelDays.getSatDate().setText(dateRange.getDateLabel()[5]);
        labelDays.getSunDate().setText(dateRange.getDateLabel()[6]);
        // ------------------------------- values working 
        if (totalCalorie > 0) {
            Double fat = (totalFat / (double) totalCalorie) * 100;
            Double carbs = (totalCarbs / (double) totalCalorie) * 100;
            Double prot = (totalProtein / (double) totalCalorie) * 100;

            labelChar.getCalPerc().setText("100%");
            labelChar.getFatPerc().setText(String.format("%.0f", fat) + "%");
            labelChar.getCarbPerc().setText(String.format("%.0f", carbs) + "%");
            labelChar.getProPerc().setText(String.format("%.0f", prot) + "%");
        } else {
            labelChar.getCalPerc().setText("0");
            labelChar.getFatPerc().setText("0");
            labelChar.getCarbPerc().setText("0");
            labelChar.getProPerc().setText("0");
        }

        labelChar.getBfValue().setText(String.valueOf(totalBF));
        labelChar.getlValue().setText(String.valueOf(totalLunch));
        labelChar.getdValue().setText(String.valueOf(totalDinner));
        labelChar.getsValue().setText(String.valueOf(totalSnacks));

        labelChar.getCalValue().setText(String.valueOf(totalCalorie));
        labelChar.getFatValue().setText(String.valueOf(totalFat));
        labelChar.getCarbValue().setText(String.valueOf(totalCarbs));
        labelChar.getProValue().setText(String.valueOf(totalProtein));

        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
    }
}
