/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.GraphicChar;

import Constants.Constants;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author chg
 */
public class Char extends JPanel {

    private final int numberOfLines;
    private int totalCal;
    private int[] cal;
    private int[] fat;
    private int[] carbs;
    private int[] prot;

    public Char() {
        putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        setPreferredSize(new Dimension(Constants.WIDTH - 10, Constants.HEIGHT / 3));

        numberOfLines = 6;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
// --------------------------- lines -------------------------------------------    

        g.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN, 9));
        int y = ((getHeight() - 20) / numberOfLines);
        for (int i = 0; i <= numberOfLines; i++) {
            g2d.setColor(Constants.COLOR_Light_Grey);
// -------------------------- Y Axis -------------------------------------------
            int value = 0;
            if (totalCal > 0) {
                int valueIncre = totalCal / numberOfLines;
                value = totalCal - valueIncre * i;
            }
            int yPos = (y * i) + 19;

            String text = String.valueOf(value);
            g2d.drawLine(30, yPos, getWidth() - 10, yPos);
            g2d.drawString(text, 0, yPos);
        }

//-------------------------- BAR PLOT ------------------------------------------  
        int thickness = 3;
        int space1 = 36;
        int space2 = 41;
        int space3 = 46;
        int space4 = 51;
        
        for (int i = 0; i < cal.length; i++) {
            
            //values of each macro
            int s1 = cal[i]/10;
            int s2 = fat[i]/10;
            int s3 = carbs[i]/10;
            int s4 = prot[i]/10;
            // possitioning in char
            int y1 = (int) (getHeight() - s1);
            int y2 = (int) (getHeight() - s2);
            int y3 = (int) (getHeight() - s3);
            int y4 = (int) (getHeight() - s4);

            Rectangle2D.Double r1 = new Rectangle2D.Double(space1+space1*i, y1, thickness, s1);
            g2d.setColor(Constants.COLOR_SMK_WHITE);
            g2d.fill(r1);
            Rectangle2D.Double r2 = new Rectangle2D.Double(space2+space1*i, y2, thickness, s2);
            g2d.setColor(Constants.COLOR_redFat);
            g2d.fill(r2);
            Rectangle2D.Double r3 = new Rectangle2D.Double(space3+space1*i, y3, thickness, s3);
            g2d.setColor(Constants.COLOR_yellowCarb);
            g2d.fill(r3);
            Rectangle2D.Double r4 = new Rectangle2D.Double(space4+space1*i, y4, thickness, s4);
            g2d.setColor(Constants.COLOR_greenPro);
            g2d.fill(r4);
            }
        

    }

    public void setTotalCalChar(int x) {
        totalCal = x;
    }

    public void setCal(int[] Cal) {
        this.cal = Cal;
    }

    public void setFat(int[] fat) {
        this.fat = fat;
    }

    public void setCarbs(int[] carbs) {
        this.carbs = carbs;
    }

    public void setProt(int[] prot) {
        this.prot = prot;
    }
}
