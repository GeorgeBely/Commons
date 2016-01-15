package ru.mangeorge.awt;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;


/**
 * Реализует ячейку таблицы для кнопки
 */
public class JButtonCellRenderer extends JButton implements TableCellRenderer {

    /**
     * @param icon иконка кнопки. Отображается, когда кнопка не нажата.
     */
    public JButtonCellRenderer(ImageIcon icon) {
        setOpaque(true);
        setIcon(icon);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}