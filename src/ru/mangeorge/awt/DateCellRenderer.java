package ru.mangeorge.awt;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;


/**
 * Реализует ячейку таблицы для ввода даты
 */
public class DateCellRenderer extends DefaultTableCellRenderer {

    private DateFormat dateFormat;

    public DateCellRenderer(DateFormat dateFormat) {
        super();
        this.dateFormat = dateFormat;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value instanceof Date)
            ((DateCellRenderer) component).setText(dateFormat.format((Date) value));
        return component;
    }
}