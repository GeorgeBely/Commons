package ru.mangeorge.awt;

import javax.swing.*;

/**
 * Класс реализующий изминение ячейки таблицы, для выпадающего списка.
 */
public class SelectCellEditor extends DefaultCellEditor {

    public SelectCellEditor(JComboBox<Object> comboBox, Object[] values) {
        super(comboBox);
        comboBox.setModel(new DefaultComboBoxModel<>(values));
        comboBox.addActionListener(e -> fireEditingStopped());
    }

}