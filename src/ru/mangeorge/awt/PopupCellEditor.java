package ru.mangeorge.awt;


import ru.mangeorge.swing.graphics.PopupDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

/**
 * Добавляет для ячейки таблицы при изминении всплывающее окно
 */
public class PopupCellEditor extends DefaultCellEditor {

    private PopupShowFunction clickFunction;
    private PopupSubmitFunction submitFunction;
    private JTextField textField;
    private Object currentValue;

    /**
     * @param textField      - поле ячейки
     * @param components     - массив компонентов, которые будут добавлены в всплывающее окно
     *                         (Необходимо указывать местоположение и размер элементов ориентируясь на размер всплывающего окна).
     * @param dimension      - размер всплывающего окна.
     * @param clickFunction  - функция, которая будет выполнена при загрузки всплывающего окна
     * @param submitFunction - функция, которая будет выполнена при закрытии всплывающего окна
     */
    public PopupCellEditor(JTextField textField, Component[] components, Dimension dimension,
                           PopupShowFunction clickFunction, PopupSubmitFunction submitFunction) {
        super(textField);
        this.textField = textField;
        this.clickFunction = clickFunction;
        this.submitFunction = submitFunction;
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
        currentValue = value;
        return textField;
    }

    public Object getCellEditorValue() {
        if (submitFunction != null) {
            return submitFunction.submit();
        }
        return currentValue;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        if (clickFunction != null)
            clickFunction.show(currentValue);
        return true;
    }


    public interface PopupShowFunction {
        void show(Object value);
    }

    public interface PopupSubmitFunction {
        Object submit();
    }
}
