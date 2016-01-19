package ru.mangeorge.awt;


import ru.mangeorge.swing.graphics.PopupDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Добавляет для ячейки таблицы при изминении всплывающее окно
 */
public class PopupCellEditor extends DefaultCellEditor {

    private PopupClickFunction clickFunction;
    private JTextField textField;
    private Object currentValue;

    private Map<Integer, Map<Integer, Object>> cellValues = new HashMap<>();

    /**
     * @param textField  - поле ячейки
     * @param components - массив компонентов, которые будут добавлены в всплывающее окно
     *                     (Необходимо указывать местоположение и размер элементов ориентируясь на размер всплывающего окна).
     * @param dimension  - размер всплывающего окна.
     */
    public PopupCellEditor(JTextField textField, Component[] components, Dimension dimension, PopupClickFunction clickFunction) {
        super(textField);
        this.textField = textField;
        this.clickFunction = clickFunction;
        textField.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) {
                new PopupDialog(textField, dimension, components, false, true);
            }
        });
        textField.setEditable(false);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (!cellValues.containsKey(row)) {
            Map<Integer, Object> val = new HashMap<>();
            val.put(column, value);
            cellValues.put(row, val);
        } else if (!cellValues.get(row).containsKey(column)) {
            cellValues.get(row).put(column, value);
        }
        currentValue = cellValues.get(row).get(column);
        return textField;
    }

    public Object getCellEditorValue() {
        return currentValue;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        if (clickFunction != null)
            clickFunction.click(currentValue);
        return true;
    }

    public interface PopupClickFunction {
        void click(Object value);
    }
}
