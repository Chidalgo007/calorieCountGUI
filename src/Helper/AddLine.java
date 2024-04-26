/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import API.fetchAPI;
import Constants.Constants;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public final class AddLine implements ActionListener {

    /**
     * @return the itemID
     */
    public JLabel getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(JLabel itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the fat
     */
    public JLabel getFat() {
        return fat;
    }

    /**
     * @param fat the fat to set
     */
    public void setFat(JLabel fat) {
        this.fat = fat;
    }

    /**
     * @return the carbs
     */
    public JLabel getCarbs() {
        return carbs;
    }

    /**
     * @param carbs the carbs to set
     */
    public void setCarbs(JLabel carbs) {
        this.carbs = carbs;
    }

    /**
     * @return the protein
     */
    public JLabel getProtein() {
        return protein;
    }

    /**
     * @param protein the protein to set
     */
    public void setProtein(JLabel protein) {
        this.protein = protein;
    }

    /**
     * @return the save
     */
    public JButton getSave() {
        return save;
    }

    /**
     * @param save the save to set
     */
    public void setSave(JButton save) {
        this.save = save;
    }

    /**
     * @return the delete
     */
    public JButton getDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(JButton delete) {
        this.delete = delete;
    }

    /**
     * @return the line
     */
    public JPanel getLine() {
        return line;
    }

    /**
     * @return the totalCal
     */
    public String getTotalCal() {
        return totalCal;
    }

    /**
     * @param totalCal the totalCal to set
     */
    public void setTotalCal(String totalCal) {
        this.totalCal = totalCal;
    }

    /**
     * @return the totalFat
     */
    public String getTotalFat() {
        return totalFat;
    }

    /**
     * @param totalFat the totalFat to set
     */
    public void setTotalFat(String totalFat) {
        this.totalFat = totalFat;
    }

    /**
     * @return the totalCarb
     */
    public String getTotalCarb() {
        return totalCarb;
    }

    /**
     * @param totalCarb the totalCarb to set
     */
    public void setTotalCarb(String totalCarb) {
        this.totalCarb = totalCarb;
    }

    /**
     * @return the totalProtein
     */
    public String getTotalProtein() {
        return totalProtein;
    }

    public JPanel getMiddleContent() {
        return middleContent;
    }

    public JPanel getScrollablemiddleContent() {
        return scrollablemiddleContent;
    }

    public List<JPanel> getLineArray() {
        return lineArray;
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
    private String totalCal;
    private String totalFat;
    private String totalCarb;
    private String totalProtein;

    private JLabel itemID;
    private JLabel fat;
    private JLabel carbs;
    private JLabel protein;
    private JTextField Q;
    private JComboBox<String> Qn;
    private JTextField item;
    private JTextField calorie;
    private JButton save;
    private JButton delete;
    private JPanel line;

    private final List<JLabel> itemIDArray = new ArrayList<>();
    private final List<JLabel> fatArray = new ArrayList<>();
    private final List<JLabel> carbsArray = new ArrayList<>();
    private final List<JLabel> proteinArray = new ArrayList<>();
    private final List<JTextField> QArray = new ArrayList<>();
    private final List<JComboBox<String>> QnArray = new ArrayList<>();
    private final List<JTextField> itemArray = new ArrayList<>();
    private final List<JTextField> calorieArray = new ArrayList<>();
    private final List<JButton> saveBtnArray = new ArrayList<>();
    private final List<JButton> deleteBtnArray = new ArrayList<>();
    private final List<JPanel> lineArray = new ArrayList<>();

    private final JPanel middleContent = new JPanel(new MigLayout("wrap,insets 0", "[]"));
    private final JPanel scrollablemiddleContent = new JPanel(new MigLayout("wrap, insets 0", "[]"));

    private Map<String, String> caloriesInfo = new HashMap<>();

    public AddLine() {

        JScrollPane scroll = new JScrollPane(getScrollablemiddleContent());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        getMiddleContent().add(scroll);

        createLine();
    }

    public void createLine() {
        line = new JPanel(new MigLayout("wrap 6, insets 0", "[40!][50!][90!][8!][40!][40!]", "[][center]"));

        setItemID(new JLabel());
        setFat(new JLabel());
        setCarbs(new JLabel());
        setProtein(new JLabel());

        setQ(new JTextField(3));
        getQ().setOpaque(false);

        getQ().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fieldValidationQ();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fieldValidationQ();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        setQn(new JComboBox(new String[]{"gr", "ml", "unit"}));
        getQn().setOpaque(false);
        getQn().setPreferredSize(new Dimension(50, 20));
        getQn().setFocusable(false);

        setItem(new JTextField(20));
        getItem().setOpaque(false);
        getItem().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fieldValidationItem();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fieldValidationItem();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        JLabel separator = new JLabel(" | ");
        separator.setBorder(null);

        setCalorie(new JTextField(4));
        getCalorie().setEditable(false);
        getCalorie().setFocusable(false);

        JLabel kcal = new JLabel("Kcal");

        setDelete(new JButton("Delete (-)"));
        getDelete().addActionListener(this);

        setSave(new JButton("Save"));
        getSave().addActionListener(this);

        // second row of line box
        String num = String.valueOf(Constants.WIDTH * 0.34);
        JPanel btnPanel = new JPanel(new MigLayout("fillx, insets 0", "[" + num + "!][]", "[]"));
        btnPanel.add(new JLabel(), "grow");
        btnPanel.add(getDelete(), "split 2, width 80!, height 20!");
        btnPanel.add(getSave(), "width 80!, height 20!");

        getLine().add(getQ(), "width 40!, height 20!");
        getLine().add(getQn(), "width 50!, height 20!");
        getLine().add(getItem());
        getLine().add(separator);
        getLine().add(getCalorie());
        getLine().add(kcal, "width 40!, height 20!");
        getLine().add(btnPanel);

        // need it for DB dont need to add to line box
        itemIDArray.add(getItemID());
        fatArray.add(getFat());
        carbsArray.add(getCarbs());
        proteinArray.add(getProtein());
        // need it for DB & API
        QArray.add(getQ());
        QnArray.add(getQn());
        itemArray.add(getItem());
        calorieArray.add(getCalorie());
        // buttons
        deleteBtnArray.add(getDelete());
        saveBtnArray.add(getSave());
        // line box
        getLineArray().add(getLine());
        // container w/ scroll
        getScrollablemiddleContent().add(getLine());
        // refresh container
        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
    }

// ---------------------- ACTION LISTENER --------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {

        Iterator<JButton> iterator = deleteBtnArray.iterator();
        while (iterator.hasNext()) {
            JButton delete = iterator.next();
            if (e.getSource() == delete) {
                // select index of btn pressed
                int index = deleteBtnArray.indexOf(delete);
                // select line w/ index to be deleted
                JPanel deletedLine = getLineArray().remove(index);
                // remove line box from conteiner
                getScrollablemiddleContent().remove(deletedLine);
                iterator.remove(); // Remove the current(it self) element using the iterator
                // refresh container after removing the lone box to reflect the changes
                FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
                break; // Exit the loop after removing the line
            }
        }

        Iterator<JButton> saveIterator = saveBtnArray.iterator();
        while (saveIterator.hasNext()) {
            JButton save = saveIterator.next();
            if (e.getSource() == save) {
                int index = saveBtnArray.indexOf((JButton) e.getSource());
                String QnType = (String) QnArray.get(index).getSelectedItem();
                String Quan = QArray.get(index).getText();
                String item = itemArray.get(index).getText();
                if (fieldValidationQ() && fieldValidationItem()) {

                    String ing;
                    String ingM;
                    if (QnType.equalsIgnoreCase("un")) {
                        ing = Quan + " " + item;
                        ingM = ing.replaceAll(" ", "%20");
                    } else {
                        ing = Quan + QnType + " " + item;
                        ingM = ing.replaceAll(" ", "%20");
                    }
                    // storing the API info in the Map
                    caloriesInfo.clear();
                    caloriesInfo = fetchAPI.fetchAPISingleCalories(ingM);
                    //calories - fat - carbs - protein
                    String cal = caloriesInfo.get("calories");
                    calorieArray.get(index).setText(String.valueOf(cal));

                    // wait for iterator to finish before create another line box
                    String calorie = calorieArray.get(index).getText();

                    if (fieldValidationCalorie()) {
                        // get fat, prot, and carb from Map
                        String f = caloriesInfo.get("fat");
                        fatArray.get(index).setText(String.valueOf(f));
                        String c = caloriesInfo.get("carbs");
                        carbsArray.get(index).setText(String.valueOf(c));
                        String p = caloriesInfo.get("calories");
                        proteinArray.get(index).setText(String.valueOf(p));

                        // set elements no editable nor fucusable
                        itemArray.get(index).setEditable(false);
                        itemArray.get(index).setFocusable(false);
                        QArray.get(index).setEditable(false);
                        QArray.get(index).setFocusable(false);
                        QnArray.get(index).setEditable(false);
                        QnArray.get(index).setFocusable(false);
                        calculateMacro();
                        SwingUtilities.invokeLater(() -> createLine());
                    }
                }
                return;
            }
        }
    }
    // ==================== validation ===================================

    private boolean fieldValidationQ() {
        String inputQ = getQ().getText();
        if (inputQ.isEmpty() || !inputQ.matches("^\\d{1,3}$")) {
            getQ().setBackground(Constants.COLOR_Error);
            return false;
        } else {
            getQ().setOpaque(false);
            getQ().setBackground(null);
        }
        return true;
    }

    private boolean fieldValidationItem() {
        String inputItem = getItem().getText();
        if (inputItem.isEmpty() || !inputItem.matches("^[\\sA-Za-z]+$")) {
            getItem().setBackground(Constants.COLOR_Error);
            return false;
        } else {
            getItem().setOpaque(false);
            getItem().setBackground(null);
        }
        return true;
    }

    private boolean fieldValidationCalorie() {
        String inputCalorie = getCalorie().getText().trim();
        if (inputCalorie.equals("-1") || inputCalorie.equals("0")) {
            getCalorie().setBackground(Constants.COLOR_Error);
            return false;
        } else {
            getCalorie().setOpaque(false);
            getCalorie().setBackground(null);
        }
        return true;
    }

    //========================================================================
    private void calculateMacro() {
        for (JTextField C : calorieArray) {
            setTotalCal(getTotalCal() + C.getText());
        }
        for (JLabel C : fatArray) {
            setTotalFat(getTotalFat() + C.getText());
        }
        for (JLabel C : carbsArray) {
            setTotalCarb(getTotalCarb() + C.getText());
        }
        for (JLabel C : proteinArray) {
            totalProtein += C.getText();
        }
        
    }

}
