
import ru.mangeorge.awt.Point;
import ru.mangeorge.awt.service.JTableService;
import ru.mangeorge.swing.graphics.CustomXYDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;


public class MainFrame extends JFrame {

    private static final String FRAME_NAME = "TestGraphics";

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;


    public MainFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2 - HEIGHT / 2);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(FRAME_NAME);

        JPanel panel = new JPanel() {{
            setFocusable(true);
            setLayout(null);
        }};
        add(panel);

        Vector<String> headerModel1 = new Vector<>();
        headerModel1.add("lol");

        DefaultTableModel modelLevel1 = new DefaultTableModel(headerModel1, 0);
        JTable tableLevel1 = new JTable(modelLevel1);
        tableLevel1.getColumn("lol").setCellEditor(JTableService.getScatterPlotCellEditor(new Dimension(400, 400), "lol", "bu", "gaga"));



        List<List<Point>> data = new ArrayList<>();
        List<List<Point>> data2 = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> names2 = new ArrayList<>();

        List<Point> list = new ArrayList<>();
        for (double i = 0; i <= 10; i = i + 1) {
            list.add(new Point(new Random().nextDouble() * 10, i));
        }
        data.add(list);
        names.add("ololo pishpish ");
        List<Point> val2 = new ArrayList<>();
        for (double i = 0; i < 10; i = i + 0.1) {
            val2.add(new Point(0, i));
            val2.add(new Point(2, i));
            val2.add(new Point(8, i));
            val2.add(new Point(10, i));
        }
        names2.add("сигма ограничение");
        data2.add(val2);


        modelLevel1.addRow(new Object[]{new CustomXYDataset(data, names)});
        modelLevel1.addRow(new Object[]{new CustomXYDataset(data2, names2)});
        JScrollPane scrollTableLevel1 = new JScrollPane(tableLevel1) {{
            setLocation(25, 25);
            setSize(100, 100);
        }};
        panel.add(scrollTableLevel1);
    }

}
