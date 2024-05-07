/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.GraphicChar;

import Constants.Constants;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

/**
 *
 * @author chg
 */
public class Labels extends JComponent{
    
    public Labels(){
         setPreferredSize(new Dimension(Constants.WIDTH-30, 35));
    }
    
    protected void paintComponent(Graphics g){
        int w=15;
        int h=15;
        int centerY = (getHeight()-h)/2;
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        
        Ellipse2D.Double Calorie = new Ellipse2D.Double(5,centerY,w,h);
        g2d.setColor(Constants.COLOR_SMK_WHITE);
        g2d.fill(Calorie);
        Ellipse2D.Double Fat = new Ellipse2D.Double(90,centerY,w,h);
        g2d.setColor(Constants.COLOR_redFat);
        g2d.fill(Fat);
        Ellipse2D.Double Carbs = new Ellipse2D.Double(150,centerY,w,h);
        g2d.setColor(Constants.COLOR_yellowCarb);
        g2d.fill(Carbs);
        Ellipse2D.Double Protein = new Ellipse2D.Double(220,centerY,w,h);
        g2d.setColor(Constants.COLOR_greenPro);
        g2d.fill(Protein);

        int textY = 22;
        g2d.setColor(Constants.COLOR_SMK_WHITE);
        g2d.drawString("Calories", 25, textY);
        g2d.drawString("Fat", 110, textY);
        g2d.drawString("Carbs", 170, textY);
        g2d.drawString("Protein", 240, textY);
    }
}
