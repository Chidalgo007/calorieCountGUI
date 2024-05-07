/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.GraphicChar;
import GUI.GraphicChar.Char;
import Constants.Constants;
import Helper.RoundedBorder;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Dimension;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public class Container extends JPanel {

    Char c;
    Label l;
    LabelDays ld;

    public Container() {
        JPanel top = new JPanel(new MigLayout("wrap,fillx","[]","[][]"));
        top.putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        top.setBackground(Constants.COLOR_Light_BAKG);
        
        JPanel bottom = new JPanel(new MigLayout());
        bottom.putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        bottom.setBackground(Constants.COLOR_Light_BAKG);

        c = new Char();
        l = new Label();
        ld = new LabelDays();

        ld.getMonDate().setText("21-04");

        top.add(c);
        top.add(ld);
        bottom.add(l);
        add(top);
        add(bottom);

        setPreferredSize(new Dimension(Constants.WIDTH - 20, Constants.HEIGHT - 70));
        putClientProperty(FlatClientProperties.STYLE, "" + "arc:20");
        setLayout(new MigLayout("wrap,fillx","[]","20[]20[]"));
        setBackground(Constants.COLOR_BACK);
    }

}
