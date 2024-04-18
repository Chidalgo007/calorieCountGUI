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

    // Colors
    public static final Color COLOR_Error = Color.decode("#C06565");// light red
    public static final Color COLOR_2 = Color.decode("#303030");// dark grey
    public static final Color COLOR_Light_Grey = Color.decode("#7F7F80");//ligh grey
    public static final Color COLOR_4 = Color.decode("#B54C00");//orange

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