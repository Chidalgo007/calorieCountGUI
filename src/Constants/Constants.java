/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constants;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chg
 */
public class Constants {
    
    //With and Heigh
    public static final int WIDTH = 320;
    public static final int HEIGHT = 520;

    // Colors
    public static final Color COLOR_Error = Color.decode("#C06565");// light red
    public static final Color COLOR_Light_BAKG = Color.decode("#181818");// dark grey
    public static final Color COLOR_Light_Grey = Color.decode("#7F7F80");//ligh grey
    public static final Color COLOR_ORANGE = Color.decode("#B54C00");//orange
    public static final Color COLOR_Trans = new Color(0, 0, 0, 128);//black 50%
    public static final Color COLOR_BACK = Color.BLACK;
    public static final Color COLOR_WHITE = Color.WHITE;
    public static final Color COLOR_SMK_WHITE = new Color(194, 194, 194);
    // ------------ food color ------------------------------------------
    public static final Color COLOR_yellowCarb = Color.decode("#E0AD13");
    public static final Color COLOR_redFat = Color.decode("#930909");
    public static final Color COLOR_greenPro = Color.decode("#07CE4D");
    

    // Fonts
    public static Font FONT_Light = loadFont("fonts/MontserratAlternates-Light.ttf");
    public static Font FONT_Medium = loadFont("fonts/MontserratAlternates-Medium.ttf");
    public static Font FONT_Regular = loadFont("fonts/MontserratAlternates-Regular.ttf");
    public static Font FONT_SemiBold = loadFont("fonts/MontserratAlternates-SemiBold.ttf");
    public static Font FONT_Neon = loadFont("fonts/TiltNeon-Regular-VariableFont_XROT,YROT.ttf");
    // border
    public static int btnRadius = 15;
    public static int btnWidth = 200;
    
    private static Font loadFont(String path) {
        try {
            File font = new File(path);
            return Font.createFont(Font.TRUETYPE_FONT, font).deriveFont(12f);
        } catch (IOException | FontFormatException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}