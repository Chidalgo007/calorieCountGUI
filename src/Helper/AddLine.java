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

    public List<JLabel> getItemIDArray() {
        return itemIDArray;
    }

    public List<JTextField> getItemArray() {
        return itemArray;
    }

    public List<JTextField> getQArray() {
        return QArray;
    }

    public List<JComboBox<String>> getQnArray() {
        return QnArray;
    }

    public List<String> getCalorieArray() {
        return calorieArray;
    }

    public List<String> getCarbsArray() {
        return carbsArray;
    }

    public List<String> getFatArray() {
        return fatArray;
    }

    public List<String> getProteinArray() {
        return proteinArray;
    }

    public JLabel getItemID() {
        return itemID;
    }

    public JButton getSave() {
        return save;
    }

    public JButton getDelete() {
        return delete;
    }

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

    public boolean getReadyToInsert() {
        return readyToInsert;
    }

    public void setReadyToInsert(boolean B) {
        readyToInsert = B;
    }

    private int totalCal;
    private int totalFat;
    private int totalCarb;
    private int totalProtein;

    private String fat;
    private String carbs;
    private String protein;

    private JLabel itemID;
    // user ID
    // date
    private JTextField item;
    private JTextField Q;
    private JComboBox<String> Qn;
    private JTextField calorie;

    private JButton save;
    private JButton delete;
    private JPanel line;

    private final List<JLabel> itemIDArray = new ArrayList<>();
    private final List<JTextField> itemArray = new ArrayList<>();
    private final List<JTextField> QArray = new ArrayList<>();
    private final List<JComboBox<String>> QnArray = new ArrayList<>();

    private List<String> calorieArray;// = new ArrayList<>();
    private List<String> carbsArray;// = new ArrayList<>();
    private List<String> fatArray;// = new ArrayList<>();
    private List<String> proteinArray;// = new ArrayList<>();

    private final List<JButton> saveBtnArray = new ArrayList<>();
    private final List<JButton> deleteBtnArray = new ArrayList<>();
    private final List<JPanel> lineArray = new ArrayList<>();

    private final JPanel middleContent = new JPanel(new MigLayout("wrap,insets 0", "[]"));
    private final JPanel scrollablemiddleContent = new JPanel(new MigLayout("wrap, insets 0", "[]"));

    private Map<String, String> caloriesInfo = new HashMap<>(); // come from API
    private boolean readyToInsert = false;

    public AddLine() {

        calorieArray = new ArrayList<>();
        carbsArray = new ArrayList<>();
        fatArray = new ArrayList<>();
        proteinArray = new ArrayList<>();

        JScrollPane scroll = new JScrollPane(getScrollablemiddleContent());

        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scroll.setBorder(BorderFactory.createEmptyBorder());

        getMiddleContent()
                .add(scroll);

        createLine();
    }

    public void createLine() {
        line = new JPanel(new MigLayout("wrap 6, insets 0", "[40!][50!][90!]3[5!]2[50!]2[40!]", "[][center]"));

        itemID = new JLabel();

        Q = new JTextField(3);
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
        btnPanel.add(getDelete(), "split 2, width 80!, height 20!");
        btnPanel.add(getSave(), "width 80!, height 20!");

        line.add(getQ(), "width 40!, height 20!");
        line.add(getQn(), "width 50!, height 20!");
        line.add(getItem());
        line.add(separator);
        line.add(getCalorie());
        line.add(kcal, "width 40!, height 20!");
        line.add(btnPanel);

        // need it for DB dont need to add to line box
        itemIDArray.add(getItemID());

        // need it for DB & API
        getQArray().add(getQ());
        getQnArray().add(getQn());
        getItemArray().add(getItem());
        getCalorieArray().add(calorie.getText());
        // buttons
        deleteBtnArray.add(getDelete());
        saveBtnArray.add(getSave());
        // line box
        getLineArray().add(line);
        // container w/ scroll
        getScrollablemiddleContent().add(line);
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
                // remove macros
                getFatArray().remove(index);
                getCarbsArray().remove(index);
                getProteinArray().remove(index);
                getCalorieArray().remove(index);
                // select line w/ index to be deleted
                JPanel deletedLine = getLineArray().remove(index);
                //check if items come from the DB to delete directly or are only local stored
                String item_ID = getItemIDArray().get(index).getText();
                if (!item_ID.equals("-1")) {
                    MyJDBC.deleteRow(item_ID);
                }
                // remove line box from conteiner
                getScrollablemiddleContent().remove(deletedLine);
                iterator.remove(); // Remove the current(it self) element using the iterator
                // refresh container after removing the lone box to reflect the changes
                FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
                calculateMacro();
                break; // Exit the loop after removing the line
            }
        }

        Iterator<JButton> saveIterator = saveBtnArray.iterator();
        while (saveIterator.hasNext()) {
            JButton save = saveIterator.next();
            if (e.getSource() == save) {
                int index = saveBtnArray.indexOf((JButton) e.getSource());
                String QnType = (String) getQnArray().get(index).getSelectedItem();
                String Quan = getQArray().get(index).getText();
                String item = getItemArray().get(index).getText();
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
                    calorie.setText(cal);

                    // wait for iterator to finish before create another line box
                    if (fieldValidationCalorie()) {
                        // get fat, prot, and carb from Map
                        fat = caloriesInfo.get("fat"); // getting from MYJDBC Map
                        fatArray.add(fat); // adding to Calculate percentage
                        carbs = caloriesInfo.get("carbs");
                        carbsArray.add(carbs);
                        protein = caloriesInfo.get("protein");
                        proteinArray.add(protein);
                        String food = caloriesInfo.get("food");
                        // add them to the arrays
                        fatArray.add(fat);
                        carbsArray.add(carbs);
                        proteinArray.add(protein);
//                        getItemArray().get(index).setText(food);
                        getItem().setText(food);

                        // set elements no editable nor fucusable
                        getItemArray().get(index).setEditable(false);
                        getItemArray().get(index).setFocusable(false);
                        getQArray().get(index).setEditable(false);
                        getQArray().get(index).setFocusable(false);
                        getQnArray().get(index).setEditable(false);
                        getQnArray().get(index).setFocusable(false);
                        readyToInsert = true;
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
        totalCal = 0;
        totalFat = 0;
        totalCarb = 0;
        totalProtein = 0;

//        calorieArray.removeIf(s -> s.isEmpty());
//        fatArray.removeIf(s -> s.isEmpty());
//        carbsArray.removeIf(s -> s.isEmpty());
//        proteinArray.removeIf(s -> s.isEmpty());
        for (String s : calorieArray) {
            if (!s.isEmpty()) {
                totalCal += Integer.parseInt(s);
            }
        }
        for (String s : fatArray) {
            if (!s.isEmpty()) {
                totalFat += Integer.parseInt(s);
            }
        }

        for (String s : carbsArray) {
            if (!s.isEmpty()) {
                totalCarb += Integer.parseInt(s);
            }
        }
        for (String s : proteinArray) {
            if (!s.isEmpty()) {
                totalProtein += Integer.parseInt(s);
            }
        }
        refreshValue(); // parent window, breakfast, lunch, dinner, snacks
    }

}
