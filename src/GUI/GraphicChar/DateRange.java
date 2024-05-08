/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.GraphicChar;

import Constants.Constants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DateEvent;
import raven.datetime.component.date.DatePicker;
import raven.datetime.component.date.DateSelectionListener;

/**
 *
 * @author chg
 */
public class DateRange extends JPanel {

    private JFormattedTextField pickDate; // date displayed
    private DatePicker datePicker; // date chooser

    private LocalDate temp; // temp var to manipulate date
    private JLabel dateStart; //
    private JLabel dateTo;
    private String[] dateLabel; // short date for char label dates
    private String[] dateTotalInfo; // date for char total info
    private Container c;

    public DateRange(Container con) {
        setLayout(new MigLayout("wrap, fillx", "[280]", "0[]0[]0"));
        setBorder(null);
        c = con;
        setBackground(Constants.COLOR_Light_BAKG);
        dateLabel = new String[7];
        dateTotalInfo = new String[7];

        addGUIComponents();
    }

    private void addGUIComponents() {
        pickDate = new JFormattedTextField();
        pickDate.setBorder(null);
        pickDate.setEditable(false);
        pickDate.setFocusable(false);
        pickDate.setFont(Constants.FONT_Regular.deriveFont(Font.PLAIN, 10));
        datePicker = new DatePicker();
        datePicker.setFocusable(false);
        datePicker.setOpaque(false);
        datePicker.setEditor(pickDate);
        datePicker.setCloseAfterSelected(true);
        datePicker.setSelectedDate(LocalDate.now());
        datePicker.setDateSelectionAble((LocalDate LocalDate) -> !LocalDate.isAfter(LocalDate.now()));
        datePicker.addDateSelectionListener(new DateSelectionListener() {
            @Override
            public void dateSelected(DateEvent e) {
                temp = datePicker.getSelectedDate();
                
                setRange();
                c.getInformation();
            }
        });

        dateStart = new JLabel();
        dateStart.setBackground(Constants.COLOR_Light_BAKG);
        dateStart.setFont(Constants.FONT_Regular.deriveFont(Font.PLAIN, 10));
        dateTo = new JLabel();
        dateTo.setBackground(Constants.COLOR_Light_BAKG);
        dateTo.setFont(Constants.FONT_Regular.deriveFont(Font.PLAIN, 10));

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        datePanel.setBackground(Constants.COLOR_Light_BAKG);
        JPanel rangeDate = new JPanel(new FlowLayout());
        rangeDate.setBackground(Constants.COLOR_Light_BAKG);

        datePanel.add(pickDate);
        rangeDate.add(new JLabel(" From "));
        rangeDate.add(dateStart);
        rangeDate.add(new JLabel(" to "));
        rangeDate.add(dateTo);

        add(datePanel, "growx");
        add(rangeDate, "growx");
    }

    private void setRange() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate monday = temp.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        dateStart.setText(monday.format(dateFormat));
        dateTo.setText(monday.plusDays(6).format(dateFormat));

        DateTimeFormatter dateFormatLabel = DateTimeFormatter.ofPattern("dd-MM");
        for (int i = 0; i <= 6; i++) {
            LocalDate tempLabel = monday.plusDays(i);
            dateLabel[i] = tempLabel.format(dateFormatLabel);
            dateTotalInfo[i] = tempLabel.format(dateFormat);
        }
    }

    public String[] getDateLabel() {
        return dateLabel;
    }

    public String[] getDateLabelInfo() {
        return dateTotalInfo;
    }

}
