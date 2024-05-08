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
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public class Container extends JPanel {

    private Char c;
    private Label l;
    private LabelDays ld;
    private DateRange dr;

    private int totalCalorie;
    private int totalFat;
    private int totalCarbs;
    private int totalProtein;

    private int totalBF;
    private int totalLunch;
    private int totalDinner;
    private int totalSnacks;

    private Map<String, List<Map<String, String>>> stInfo = new HashMap<>();

    public Container(EnterCalories enterCal) {

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

        c = new Char();
        l = new Label();
        ld = new LabelDays();
        dr = new DateRange(this);

        top.add(dr);
        top.add(c);
        top.add(ld);
        bottom.add(l);
        add(top);
        add(bottom);
    }

    public void getInformation() {
        stInfo = MyJDBC.retrieveOneWeek(1);//UserProfile.getID()
//        for (Map.Entry<String, List<Map<String, String>>> entry : stInfo.entrySet()) {
//            System.out.println("key set " + entry.toString());
//        }
        calculateValues();
    }

    private void calculateValues() {
        String[] dates = dr.getDateLabelInfo();
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
        int temp=0;
        for(int i=0;i<cal.length;i++){
            if(cal[i]>temp){
                temp=cal[i];
            }
        }
        c.setTotalCalChar(temp);
        //assign values to array in char class
        c.setCal(cal);
        c.setFat(fat);
        c.setCarbs(carbs);
        c.setProt(prot);

        refreshStats();
    }

    private void refreshStats() {
        // dates working --------------------------------
        ld.getMonDate().setText(dr.getDateLabel()[0]);
        ld.getTuesDate().setText(dr.getDateLabel()[1]);
        ld.getWedDate().setText(dr.getDateLabel()[2]);
        ld.getThurDate().setText(dr.getDateLabel()[3]);
        ld.getFriDate().setText(dr.getDateLabel()[4]);
        ld.getSatDate().setText(dr.getDateLabel()[5]);
        ld.getSunDate().setText(dr.getDateLabel()[6]);
        // ------------------------------- values working 
        if (totalCalorie > 0) {
            Double fat = (totalFat / (double) totalCalorie) * 100;
            Double carbs = (totalCarbs / (double) totalCalorie) * 100;
            Double prot = (totalProtein / (double) totalCalorie) * 100;

            l.getCalPerc().setText("100%");
            l.getFatPerc().setText(String.format("%.0f", fat) + "%");
            l.getCarbPerc().setText(String.format("%.0f", carbs) + "%");
            l.getProPerc().setText(String.format("%.0f", prot) + "%");
        } else {
            l.getCalPerc().setText("0");
            l.getFatPerc().setText("0");
            l.getCarbPerc().setText("0");
            l.getProPerc().setText("0");
        }

        l.getBfValue().setText(String.valueOf(totalBF));
        l.getlValue().setText(String.valueOf(totalLunch));
        l.getdValue().setText(String.valueOf(totalDinner));
        l.getsValue().setText(String.valueOf(totalSnacks));

        l.getCalValue().setText(String.valueOf(totalCalorie));
        l.getFatValue().setText(String.valueOf(totalFat));
        l.getCarbValue().setText(String.valueOf(totalCarbs));
        l.getProValue().setText(String.valueOf(totalProtein));

        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
    }
}
