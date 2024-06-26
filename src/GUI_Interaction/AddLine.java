/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI_Interaction;

import Constants.Constants;
import com.formdev.flatlaf.FlatLaf;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author chg
 */
public final class AddLine implements ActionListener {

// components getters
    public JLabel getItemID() {
        return itemID;
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

// buttons getters
    public JButton getSave() {
        return save;
    }

    public JButton getDelete() {
        return delete;
    }

    public List<JButton> getDeleteBtnArray() {
        return deleteBtnArray;
    }

// panel getters
    public JPanel getLine() {
        return line;
    }

    public JPanel getMiddleContent() {
        return middleContent;
    }

    public JPanel getScrollablemiddleContent() {
        return scrollablemiddleContent;
    }

// array getters
    public List<JPanel> getLineArray() {
        return lineArray;
    }

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

    // to assign from DB
    private JLabel itemID;
// to input info
    private JTextField item;
    private JTextField Q;
    private JComboBox<String> Qn;
    private JTextField calorie;
// to handl actions
    private JButton save;
    private JButton delete;
    // container of all labels...etc 
    private JPanel line;

    // to store info and iterrate over on deletion
    private final List<JLabel> itemIDArray;
    private final List<JTextField> itemArray;
    private final List<JTextField> QArray;
    private final List<JComboBox<String>> QnArray;

    // to access indexed button
    private final List<JButton> deleteBtnArray;
    private final List<JPanel> lineArray;

    // create container for line and scroll
    private final JPanel middleContent;
    private final JPanel scrollablemiddleContent;

    private final AddLineManager manager;
    private final String mealType;

    public AddLine(AddLineManager managerLine, String meal) {

        mealType = meal;
        manager = managerLine;
        itemIDArray = new LinkedList<>();
        itemArray = new LinkedList<>();
        QArray = new LinkedList<>();
        QnArray = new LinkedList<>();
        deleteBtnArray = new LinkedList<>();
        lineArray = new LinkedList<>();

        middleContent = new JPanel(new MigLayout("wrap,insets 0", "[]"));
        scrollablemiddleContent = new JPanel(new MigLayout("wrap, insets 0", "[]"));

        JScrollPane scroll = new JScrollPane(scrollablemiddleContent);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        middleContent.add(scroll);

//        createLine();
    }

    public void createLine() {
        line = new JPanel(new MigLayout("wrap 6, insets 0", "[40!][55!][90!]3[5!]2[50!]2[40!]", "[][center]"));
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
        delete.putClientProperty("linePanel", line);
        delete.putClientProperty("mealType", mealType);

        save = new JButton("Save");
        save.addActionListener(this);

        // second row of line box
        String num = String.valueOf(Constants.WIDTH * 0.34);
        JPanel btnPanel = new JPanel(new MigLayout("fillx, insets 0", "[" + num + "!][]", "[]"));
        btnPanel.add(new JLabel(), "grow");
        btnPanel.add(delete, "split 2, width 80!, height 20!");
        btnPanel.add(save, "width 80!, height 20!");

        line.add(Q, "width 40!, height 20!");
        line.add(Qn, "width 55!, height 20!");
        line.add(item);
        line.add(separator);
        line.add(calorie);
        line.add(kcal, "width 40!, height 20!");
        line.add(btnPanel);

        // need it for DB & API
        QArray.add(Q);
        QnArray.add(Qn);
        itemArray.add(item);
        itemIDArray.add(itemID);
        // buttons
        deleteBtnArray.add(delete);
        // line box
        lineArray.add(line);
        // container w/ scroll
        scrollablemiddleContent.add(line);
        // refresh container
        FlatLaf.revalidateAndRepaintAllFramesAndDialogs();
    }
// ==================== validation ===================================

    public boolean fieldValidationQ() {
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

    public boolean fieldValidationItem() {
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

    public boolean fieldValidationCalorie() {
        String inputCalorie = getCalorie().getText().trim();
        if ( inputCalorie.isEmpty()) {
            calorie.setBackground(Constants.COLOR_Error);
            return false;
        } else {
            calorie.setOpaque(false);
            calorie.setBackground(null);
        }
        return true;
    }
//---------------------- Action Listener ---------------------------------------
    public void addActionListenerToDeleteBtn() {
        delete.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            manager.handleSaveButtonAction(e);
        } else if (e.getSource() instanceof JButton) {
            JButton deleteButton = (JButton) e.getSource();
//            String mealtype = (String) deleteButton.getClientProperty("mealType"); // in case is need it to identify the meal
            JPanel lineToRemove = (JPanel) deleteButton.getClientProperty("linePanel");
            manager.handleDeleteButtonAction(lineToRemove);

        }
    }

}
