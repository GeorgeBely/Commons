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


/**
 * Сервис для работы с таблицей и её ячейками
 */
public class JTableService {

    /**
     * Создаёт ячейку со всплывающем окном, в котором график распределения растянут на всё всплывающее окно
     *
     * @param dimension размер всплывающего окна
     * @param title     наименование графика
     * @param titleX    наименование оси x
     * @param titleY    наименование оси y
     * @return Объект ячейки при редактировании которого отображается всплывающее окно
     */
    public static PopupCellEditor getScatterPlotCellEditor(Dimension dimension, String title, String titleX, String titleY) {
        JFreeChart chart = ScatterPlotService.createChart(title, titleX, titleY, null);
        ChartPanel panel = new ChartPanel(chart) {{
            setLocation(10, 5);
            setSize((int) dimension.getWidth() - 21, (int) dimension.getHeight() - 20);
        }};

        return new PopupCellEditor(new JTextField(), new Component[]{panel}, dimension, val -> ScatterPlotService.updateData(chart, (CustomXYDataset) val), null);
    }

    /**
     * Создаёт ячейку со всплывающем окном, в котором поле ввода растянуто на всё всплывающее окно
     *
     * @param dimension размер всплывающего окна
     * @return Объект ячейки при редактировании которого отображается всплывающее окно
     */
    public static PopupCellEditor getJTextAreaCellEditor(Dimension dimension) {
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
            setSize((int) dimension.getWidth() - 21, (int) dimension.getHeight() - 20);
        }};

        return new PopupCellEditor(textField, new Component[]{textScrollPane}, dimension, v -> textArea.setText(v.toString()), textField::getText);
    }

}



