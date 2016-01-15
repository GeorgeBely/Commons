package ru.mangeorge.awt;

import ru.mangeorge.awt.service.CalendarService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;


/**
 * Класс реализующий изминение ячейки таблицы, для задания даты. Создаёт всплывающий диалог с календарём.
 */
public class DateCellEditor extends DefaultCellEditor {

    public JTextField textField;
    private DateFormat dateFormat;

    public DateCellEditor(JTextField textField, DateFormat dateFormat, CalendarService.DateCellFunction dateCellFunction) {
        super(textField);
        textField.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseClicked(MouseEvent e) { }
            public void mousePressed(MouseEvent e) {
                try {
                    CalendarService.addPopupCalendarDialog(textField, null, dateCellFunction);
                } catch (ParseException ignore) { }
            }
        });
        textField.setEditable(false);
        this.dateFormat = dateFormat;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        textField = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
        if (value instanceof Date)
            textField.setText(dateFormat.format((Date) value));
        return textField;
    }
}