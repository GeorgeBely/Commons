package ru.mangeorge.awt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.EventObject;
import java.util.Vector;


/**
 * Класс реализующий изминение ячейки таблицы, для кнопки. Обрабатывает нажатие кнопки в таблице.
 */
public class JButtonCellEditor extends DefaultCellEditor {

    protected JButton button;
    private Object value;
    private JTable table;
    private int row;
    private int column;

    private ButtonClickFunction buttonClickFunction;

    /**
     * @param icon                - иконка кнопки. Отображается, когда кнопка нажата
     * @param buttonClickFunction - функция, которую нужно выполнить при нажатии кнопки
     */
    public JButtonCellEditor(ImageIcon icon, ButtonClickFunction buttonClickFunction) {
        super(new JCheckBox());
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
        button.setIcon(icon);
        this.buttonClickFunction = buttonClickFunction;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.value = value;
        this.table = table;
        this.row = row;
        this.column = column;
        return button;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        buttonClickFunction.buttonClick(value, table, row);
        return true;
    }

    public Object getCellEditorValue() {
        if (((DefaultTableModel) table.getModel()).getDataVector().size() > row) {
            return ((Vector) ((DefaultTableModel) table.getModel()).getDataVector().get(row)).get(column);
        }
        return null;
    }

    public interface ButtonClickFunction {
        void buttonClick(Object value, JTable table, int row);
    }
}
