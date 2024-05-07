/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.GraphicChar;

import Constants.Constants;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author chg
 */
public class Char extends JComponent {

        int nL;
        int totalCal;
        int valueIncre;
    public Char() {
        setPreferredSize(new Dimension(Constants.WIDTH - 25, Constants.HEIGHT / 3));
//        setBorder(BorderFactory.createLineBorder(Constants.COLOR_Light_BAKG, 3));
        

        nL = 6;
        totalCal = 2500;
        valueIncre = totalCal / nL;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
// --------------------------- lines -------------------------------------------    

        g.setFont(Constants.FONT_Medium.deriveFont(Font.PLAIN,10));
            int y = ((getHeight()-20) / nL);
        for (int i = 0; i <= nL; i++) {
            g2d.setColor(Constants.COLOR_Light_Grey);
// -------------------------- Y Axis -------------------------------------------
            int value = totalCal - valueIncre * i;
            int yPos = (y*i)+15;
            System.out.println("value "+value);
            String text = String.valueOf(value);
            g2d.drawString(text, 5, yPos);
            g2d.drawLine(40, yPos, getWidth() - 10, yPos);
        }

//-------------------------- BAR PLOT ------------------------------------------  
int s1 = 250;
int s2 = (int)(250*0.2);
int s3 = (int)(250*0.5);
int s4 = (int)(250*0.3);
        
int y1 = (int)(getHeight()-s1);
int y2 = (int)(getHeight()-s2);
int y3 = (int)(getHeight()-s3);
int y4 = (int)(getHeight()-s4);

        Rectangle2D.Double r1 = new Rectangle2D.Double(46, y1,4 ,s1);
        g2d.setColor(Constants.COLOR_SMK_WHITE);
        g2d.fill(r1);
        Rectangle2D.Double r2 = new Rectangle2D.Double(52, y2, 4,s2);
        g2d.setColor(Constants.COLOR_redFat);
        g2d.fill(r2);
        Rectangle2D.Double r3 = new Rectangle2D.Double(58, y3, 4,s3);
        g2d.setColor(Constants.COLOR_yellowCarb);
        g2d.fill(r3);
        Rectangle2D.Double r4 = new Rectangle2D.Double(64, y4,4 ,s4);
        g2d.setColor(Constants.COLOR_greenPro);
        g2d.fill(r4);
    }

}
