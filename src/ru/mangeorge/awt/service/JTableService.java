package ru.mangeorge.awt.service;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import ru.mangeorge.awt.*;
import ru.mangeorge.swing.graphics.CustomXYDataset;
import ru.mangeorge.swing.service.ScatterPlotService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;


/**
 * Сервис для работы с таблицей и её ячейками
 */
public class JTableService {

    public static PopupCellEditor getScatterPlotCellEditor(Dimension dimension, String title, String titleX, String titleY) {
        JFreeChart chart = ScatterPlotService.createChart(title, titleX, titleY, null);
        ChartPanel panel = new ChartPanel(chart) {{
            setLocation(10, 5);
            setSize((int) dimension.getWidth() - 21, (int) dimension.getHeight() - 20);
        }};

        return new PopupCellEditor(new JTextField(), new Component[]{panel}, dimension, val -> ScatterPlotService.updateData(chart, (CustomXYDataset) val));
    }

    public static SelectCellEditor getSelectCellEditor(Object[] values) {
        return new SelectCellEditor(new JComboBox<>(), values);
    }

    public static DateCellEditor getDateCellEditor(DateFormat dateFormat, CalendarService.DateFunction dateFunction) {
        return new DateCellEditor(new JTextField(), dateFormat, dateFunction);
    }

    public static PopupCellEditor getPopupCellEditor(Dimension popupDimension, Component[] components, PopupCellEditor.PopupClickFunction clickFunction) {
        return new PopupCellEditor(new JTextField(), components, popupDimension, clickFunction);
    }

    public static PopupCellEditor JTextAreaCellEditor(Dimension textDimension, Dimension popupDimension, PopupCellEditor.PopupClickFunction clickFunction) {
        JTextField textField = new JTextField();

        JTextArea textArea = new JTextArea() {{
            setLineWrap(true);
            setWrapStyleWord(true);
            setText(textField.getText());
        }};
        textArea.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) { }
            public void keyPressed(KeyEvent e) { }
            public void keyReleased(KeyEvent e) {
                textField.setText(textArea.getText());
            }
        });
        JScrollPane textScrollPane = new JScrollPane() {{
            setViewportView(textArea);
            setLocation(10, 5);
            setSize(textDimension);
        }};

        return new PopupCellEditor(textField, new Component[]{textScrollPane}, popupDimension, clickFunction);
    }

    public static JButtonCellRenderer getJButtonCellRenderer(ImageIcon icon) {
        return new JButtonCellRenderer(icon);
    }

    public static JButtonCellEditor getJButtonCellEditor(ImageIcon icon, JButtonCellEditor.ButtonClickFunction buttonClickFunction) {
        return new JButtonCellEditor(icon, buttonClickFunction);
    }
}



