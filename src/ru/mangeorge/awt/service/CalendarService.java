package ru.mangeorge.awt.service;

import com.javaswingcomponents.calendar.JSCCalendar;
import com.javaswingcomponents.calendar.listeners.CalendarSelectionEventType;
import com.javaswingcomponents.calendar.model.DayOfWeek;
import com.javaswingcomponents.calendar.plaf.darksteel.*;
import ru.mangeorge.awt.DarkCalendarCellRenderer;
import ru.mangeorge.swing.graphics.PopupDialog;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Сервис для работы с компонентом календарь.
 */
public class CalendarService {

    /** Формат даты для поля ввода даты по умолчанию */
    public static final DateFormat FORMAT_DATE = DateFormat.getDateInstance(DateFormat.SHORT);


    /**
     * Создаёт компонент календарь.
     *
     * @return календарь.
     */
    public static JSCCalendar getCalendarComponent(Rectangle bound) {
        return new JSCCalendar() {{
            setBounds(bound);
            setUI(DarkSteelCalendarUI.createUI(this));
            getCalendarModel().setFirstDayOfWeek(DayOfWeek.MONDAY);
            setCalendarCellRenderer(new DarkCalendarCellRenderer());
        }};
    }

    /**
     * Создаёт всплывающее окно с календарём над полем ${textField}.
     *
     * @param textField        - поле над которым необходимо отобразить календарь
     * @param format           - формат вывода даты
     * @param dateFunction - функция, которую нужно выполнить после установки даты. Не обязательно.
     * @throws ParseException
     */
    public static void addPopupCalendarDialog(JTextField textField, DateFormat format, DateFunction dateFunction) throws ParseException {
        final DateFormat dateFormat;
        if (format == null)
            dateFormat = FORMAT_DATE;
        else
            dateFormat = format;

        JSCCalendar calendar = CalendarService.getCalendarComponent(new Rectangle(10, 5, 200, 200));
        Date date = dateFormat.parse(textField.getText());
        calendar.getCalendarModel().setDisplayDate(date);
        calendar.getCalendarModel().setSelectedDate(date);
        PopupDialog popupDialog = new PopupDialog(textField, new Dimension(220, 225), new Component[]{calendar}, false, true);
        calendar.addCalendarSelectionListener(e -> {
            if (CalendarSelectionEventType.DATE_SELECTED.equals(e.getCalendarSelectionEventType())) {
                Date selectDate = e.getSelectedDates().get(0);
                if (textField instanceof JFormattedTextField)
                    ((JFormattedTextField) textField).setValue(selectDate);
                else
                    textField.setText(dateFormat.format(selectDate));
                if (dateFunction != null)
                    dateFunction.dateCellClick(selectDate);
                popupDialog.closeDialog();
            }
        });
    }

    public interface DateFunction {
        void dateCellClick(Date date);
    }
}

