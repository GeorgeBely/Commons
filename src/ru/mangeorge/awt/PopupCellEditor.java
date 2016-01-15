package ru.mangeorge.awt;


import ru.mangeorge.swing.graphics.PopupDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Добавляет для ячейки таблицы при изминении всплывающее окно
 */
public class PopupCellEditor extends DefaultCellEditor {

    /**
     * @param textField  - поле ячейки
     * @param components - массив компонентов, которые будут добавлены в всплывающее окно
     *                     (Необходимо указывать местоположение и размер элементов ориентируясь на размер всплывающего окна).
     * @param dimension  - размер всплывающего окна.
     */
    public PopupCellEditor(JTextField textField, Component[] components, Dimension dimension) {
        super(textField);
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
}
