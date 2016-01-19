package ru.mangeorge.awt;


import com.javaswingcomponents.calendar.JSCCalendar;
import com.javaswingcomponents.calendar.cellrenderers.CalendarCellRenderer;
import com.javaswingcomponents.calendar.cellrenderers.CellRendererComponentParameter;
import com.javaswingcomponents.calendar.plaf.darksteel.DarkSteelCalendarCellRenderer;

import javax.swing.*;
import java.awt.*;


/**
 * Реализует ячейку календаря для отображения даты
 */
public class DarkCalendarCellRenderer extends JLabel implements CalendarCellRenderer {

    @Override
    public JComponent getCellRendererComponent(CellRendererComponentParameter parameterObject) {
        DarkSteelCalendarCellRenderer defaultCell = (DarkSteelCalendarCellRenderer)
                new DarkSteelCalendarCellRenderer().getCellRendererComponent(parameterObject);

        setHorizontalAlignment(defaultCell.getHorizontalAlignment());
        setIcon(defaultCell.getIcon());
        setText(parameterObject.getText());
        setOpaque(defaultCell.isOpaque());
        setForeground(defaultCell.getForeground());
        setBorder(defaultCell.getBorder());
        setBackground(defaultCell.getBackground());

        if (parameterObject.isCurrentMonth) {
            if (parameterObject.isWeekend || parameterObject.isHoliday) {
                setForeground(new Color(51, 142, 237));
                setOpaque(true);
            }

            if (parameterObject.isToday) {
                setForeground(new Color(237, 142, 62));
                setOpaque(true);
            }
        }
        return this;
    }

    @Override
    public JComponent getHeadingCellRendererComponent(JSCCalendar calendar, String text) {
        return new DarkSteelCalendarCellRenderer().getHeadingCellRendererComponent(calendar, text);
    }
}