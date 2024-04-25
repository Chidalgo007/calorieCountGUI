/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import Constants.Constants;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public final class AddLine implements ActionListener {

    public JPanel getMiddleContent() {
        return middleContent;
    }

    public JTextField getQ() {
        return Q;
    }

    public void setQ(JTextField Q) {
        this.Q = Q;
    }

    public JComboBox getQn() {
        return Qn;
    }

    public void setQn(JComboBox Qn) {
        this.Qn = Qn;
    }

    public JTextField getItem() {
        return item;
    }

    public void setItem(JTextField item) {
        this.item = item;
    }

    public JTextField getCalorie() {
        return calorie;
    }

    public void setCalorie(JTextField calorie) {
        this.calorie = calorie;
    }

    private JTextField Q;
    private JComboBox<String> Qn;
    private JTextField item;
    private JTextField calorie;
    private JButton more;
    private JButton save;
    private JButton edit;
    private JButton delete;
    private JPanel line;

    private final List<JTextField> QArray = new ArrayList<>();
    private final List<JComboBox<String>> QnArray = new ArrayList<>();
    private final List<JTextField> itemArray = new ArrayList<>();
    private final List<JTextField> calorieArray = new ArrayList<>();
    private final List<JButton> moreBtnArray = new ArrayList<>();
    private final List<JButton> saveBtnArray = new ArrayList<>();
    private final List<JButton> editBtnArray = new ArrayList<>();
    private final List<JButton> deleteBtnArray = new ArrayList<>();
    private final List<JPanel> lineArray = new ArrayList<>();

    private final JPanel middleContent = new JPanel(new MigLayout("wrap, fillx, insets 0"));

    public AddLine() {

        JScrollPane scroll = new JScrollPane(getMiddleContent());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        createLine();
    }

    public void createLine() {
        line = new JPanel(new MigLayout("wrap 6, insets 0", "[40!][50!][90!][8!][45!][40]", "[][center]"));

        setQ(new JTextField(3));
        getQ().setOpaque(false);

        setQn(new JComboBox(new String[]{"gr", "ml", "un"}));
        getQn().setOpaque(false);
        getQn().setPreferredSize(new Dimension(50, 20));
        getQn().setFocusable(false);

        setItem(new JTextField(20));
        getItem().setOpaque(false);

        JLabel separator = new JLabel(" | ");
        separator.setBorder(null);

        setCalorie(new JTextField(4));
        getCalorie().setEditable(false);
        getCalorie().setFocusable(false);

        JLabel kcal = new JLabel("Kcal");

        more = new JButton("(+)");
        more.addActionListener(this);

        delete = new JButton("(-)");
        delete.addActionListener(this);

        edit = new JButton("Edit");
        edit.addActionListener(this);

        save = new JButton("Save");
        save.addActionListener(this);

        JPanel btnPanel = new JPanel(new MigLayout("center, insets 0", "[center]"));
        btnPanel.add(more, "width 50!, height 20!");
        btnPanel.add(delete, "width 50!, height 20!");
        btnPanel.add(edit, "width 60!, height 20!");
        btnPanel.add(save, "width 60!, height 20!");


        line.add(getQ(), "width 40!, height 20!");
        line.add(getQn(), "width 50!, height 20!");
        line.add(getItem());
        line.add(separator);
        line.add(getCalorie());
        line.add(kcal);
        line.add(btnPanel, "center, spany 6");

        QArray.add(Q);
        QnArray.add(Qn);
        itemArray.add(item);
        calorieArray.add(calorie);
        moreBtnArray.add(more);
        deleteBtnArray.add(delete);
        editBtnArray.add(edit);
        saveBtnArray.add(save);
        lineArray.add(line);
        getMiddleContent().add(line);
        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == more) {
            createLine();
        }

        for (JButton delete : deleteBtnArray) {
            if (e.getSource() == delete) {
                int index = deleteBtnArray.indexOf((JButton) e.getSource());
                lineArray.remove(index);
                FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
            }
        }
        for (JButton edit : editBtnArray) {
            if (e.getSource() == edit) {
                int index = editBtnArray.indexOf((JButton) e.getSource());
                QArray.get(index).setEditable(true);
                QnArray.get(index).setEditable(true);
                itemArray.get(index).setEditable(true);
            }
        }
        for (JButton save : saveBtnArray) {
            if (e.getSource() == save) {
                int index = saveBtnArray.indexOf((JButton) e.getSource());
                String Quan = QArray.get(index).getText();
                QArray.get(index).setEditable(false);
                QArray.get(index).setFocusable(false);
                String QnType = (String) QnArray.get(index).getSelectedItem();
                QnArray.get(index).setEditable(false);
                QnArray.get(index).setFocusable(false);
                String item = itemArray.get(index).getText();
                itemArray.get(index).setEditable(false);
                itemArray.get(index).setFocusable(false);
//            String calorie = calorieArray.get(index).getText();
                System.out.println(Quan + QnType + " " + item);
            }
        }

    }

}
