/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import API.fetchAPI;
import Constants.Constants;
import GUI.MealGUI.Breakfast;
import static GUI.MealGUI.Breakfast.refreshValue;
import MyJBDC.MyJDBC;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Dimension;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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

    public List<JLabel> getItemIDArray() {
        return itemIDArray;
    }
//
//    public List<JTextField> getItemArray() {
//        return itemArray;
//    }
//
//    public List<JTextField> getQArray() {
//        return QArray;
//    }
//
//    public List<JComboBox<String>> getQnArray() {
//        return QnArray;
//    }

//    public List<String> getCalorieArray() {
//        return calorieArray;
//    }
//
//    public List<String> getCarbsArray() {
//        return carbsArray;
//    }
//
//    public List<String> getFatArray() {
//        return fatArray;
//    }
//
//    public List<String> getProteinArray() {
//        return proteinArray;
//    }

    public JLabel getItemID() {
        return itemID;
    }

//    public JButton getSave() {
//        return save;
//    }
//
//    public JButton getDelete() {
//        return delete;
//    }

    public JPanel getLine() {
        return line;
    }

    public int getTotalCal() {
        return totalCal;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public int getTotalCarb() {
        return totalCarb;
    }

    public int getTotalProtein() {
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

    public JComboBox getQn() {
        return Qn;
    }

    public JTextField getItem() {
        return item;
    }

    public JTextField getCalorie() {
        return calorie;
    }

    public String getFat() {
        return fat;
    }

    public String getCarbs() {
        return carbs;
    }

    public String getProtein() {
        return protein;
    }
// for cal of macros
    private int totalCal;
    private int totalFat;
    private int totalCarb;
    private int totalProtein;
// to receive info from API
    private String fat;
    private String carbs;
    private String protein;
    private JTextField calorie;
// to assign from DB
    private JLabel itemID;
// to input info
    private JTextField item;
    private JTextField Q;
    private JComboBox<String> Qn;
// to handl actions
    private JButton save;
    private JButton delete;
    // container of all labels...etc 
    private JPanel line;

    // to store info and iterrate over on deletion
    private final List<JLabel> itemIDArray = new LinkedList<>();
    private final List<JTextField> itemArray = new LinkedList<>();
    private final List<JTextField> QArray = new LinkedList<>();
    private final List<JComboBox<String>> QnArray = new LinkedList<>();

    private final List<String> calorieArray;
    private final List<String> carbsArray;
    private final List<String> fatArray;
    private final List<String> proteinArray;

    // to access indexed button
    // save button doenst need itteration as stop working when new line is created
    private final List<JButton> deleteBtnArray = new LinkedList<>();
    private final List<JPanel> lineArray = new LinkedList<>();

    private final JPanel middleContent = new JPanel(new MigLayout("wrap,insets 0", "[]"));
    private final JPanel scrollablemiddleContent = new JPanel(new MigLayout("wrap, insets 0", "[]"));

    // to receive info from API
    private Map<String, String> caloriesInfo = new HashMap<>();

    public AddLine() {

        calorieArray = new LinkedList<>();
        carbsArray = new LinkedList<>();
        fatArray = new LinkedList<>();
        proteinArray = new LinkedList<>();

        JScrollPane scroll = new JScrollPane(getScrollablemiddleContent());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        getMiddleContent().add(scroll);

        createLine();
    }

    public void createLine() {
        line = new JPanel(new MigLayout("wrap 6, insets 0", "[40!][50!][90!]3[5!]2[50!]2[40!]", "[][center]"));

        itemID = new JLabel();

        Q = new JTextField(3);
        Q.setOpaque(false);

        Q.getDocument().addDocumentListener(new DocumentListener() {
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

        Qn = new JComboBox(new String[]{"gr", "ml", "unit"});
        Qn.setOpaque(false);
        Qn.setPreferredSize(new Dimension(50, 20));
        Qn.setFocusable(false);

        item = new JTextField(20);
        item.setOpaque(false);
        item.getDocument().addDocumentListener(new DocumentListener() {
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

        JLabel separator = new JLabel("|");
        separator.setBorder(null);

        calorie = new JTextField(4);
        calorie.setEditable(false);
        calorie.setFocusable(false);

        JLabel kcal = new JLabel("Kcal");

        delete = new JButton("Delete (-)");
        delete.addActionListener(this);

        save = new JButton("Save");
        save.addActionListener(this);

        // second row of line box
        String num = String.valueOf(Constants.WIDTH * 0.34);
        JPanel btnPanel = new JPanel(new MigLayout("fillx, insets 0", "[" + num + "!][]", "[]"));
        btnPanel.add(new JLabel(), "grow");
        btnPanel.add(delete, "split 2, width 80!, height 20!");
        btnPanel.add(save, "width 80!, height 20!");

        line.add(Q, "width 40!, height 20!");
        line.add(Qn, "width 50!, height 20!");
        line.add(item);
        line.add(separator);
        line.add(calorie);
        line.add(kcal, "width 40!, height 20!");
        line.add(btnPanel);

        // need it for DB & API
        QArray.add(Q);
        QnArray.add(Qn);
        itemArray.add(item);
        // buttons
        deleteBtnArray.add(delete);
        // line box
        lineArray.add(line);
        // container w/ scroll
        getScrollablemiddleContent().add(line);
        // refresh container
        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
    }

// ---------------------- ACTION LISTENER --------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {

        // get the index of the button clicked
        int index = -1;
        for (int i = 0; i < deleteBtnArray.size(); i++) {
            if (e.getSource() == deleteBtnArray.get(i)) {
                index = i;
                break;
            }
        }
        //check if items come from the DB to delete directly or are only local stored
        if (index != -1) {

            if (!itemIDArray.isEmpty()) {
                String item_ID = itemIDArray.get(index).getText();
                if (!item_ID.equals("-1")) {
                    MyJDBC.deleteRow(item_ID);
                }
            }

            // remove line box from conteiner
            JPanel lineToRemove = lineArray.get(index);
            getScrollablemiddleContent().remove(lineToRemove);

            List<List<?>> allArrays = Arrays.asList(lineArray, itemIDArray, QArray, QnArray, itemArray,
                    calorieArray, fatArray, carbsArray, proteinArray);

            for (List<?> array : allArrays) {
                array.remove(index);
            }

            // refresh container after removing the lone box to reflect the changes
            FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
            calculateMacro();
        }

        if (e.getSource() == save) {

            String items = item.getText();
            String Quan = Q.getText();
            String QnType = Qn.getSelectedItem().toString();
            if (fieldValidationQ() && fieldValidationItem()) {
                // calling the API    
                String ing;
                String ingM;
                ing = Quan + QnType + " " + items;
                ingM = ing.replaceAll(" ", "%20");

                // storing the API info in the Map
                caloriesInfo.clear();
                caloriesInfo = fetchAPI.fetchAPISingleCalories(ingM);
                //calories - fat - carbs - protein
                String cal = caloriesInfo.get("calories");
                calorie.setText(cal);
                calorieArray.add(cal);

                if (fieldValidationCalorie()) {
                    // get fat, prot, and carb from Map
                    fat = caloriesInfo.get("fat"); // getting from MYJDBC Map
                    carbs = caloriesInfo.get("carbs");
                    protein = caloriesInfo.get("protein");
                    String food = caloriesInfo.get("food");
                    // add them to the arrays
                    fatArray.add(fat);
                    carbsArray.add(carbs);
                    proteinArray.add(protein);
                    getItem().setText(food.replaceAll("[-'.,/]", " "));

                    item.setEditable(false);
                    item.setFocusable(false);
                    Q.setEditable(false);
                    Q.setFocusable(false);
                    Qn.setEditable(false);
                    Qn.setFocusable(false);

                    calculateMacro();
                    Breakfast.constructInsertion(); // find general way to action this
                    SwingUtilities.invokeLater(() -> createLine());
                }
            }
        }

    }
// ==================== validation ===================================

    private boolean fieldValidationQ() {
        String inputQ = Q.getText();
        if (inputQ.isEmpty() || !inputQ.matches("^\\d{1,3}$")) {
            Q.setBackground(Constants.COLOR_Error);
            return false;
        } else {
            Q.setOpaque(false);
            Q.setBackground(null);
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

    //========================= Macros =========================================
    private void calculateMacro() {
        totalCal = 0;
        totalFat = 0;
        totalCarb = 0;
        totalProtein = 0;

        for (String s : calorieArray) {
            totalCal += Integer.parseInt(s);
            System.out.println("Calories " + s);
        }
        for (String s : fatArray) {
            totalFat += Integer.parseInt(s);
        }

        for (String s : carbsArray) {
            totalCarb += Integer.parseInt(s);
        }
        for (String s : proteinArray) {
            totalProtein += Integer.parseInt(s);
        }
        Breakfast.refreshValue(); // parent window, breakfast, lunch, dinner, snacks
    }

}
