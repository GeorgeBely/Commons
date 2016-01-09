
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import ru.mangeorge.awt.Point;
import ru.mangeorge.swing.graphics.CustomXYDataset;
import ru.mangeorge.swing.service.ScatterPlotService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

        List<List<Point>> data = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (double i = 0; i <= 10; i = i + 1) {
            List<Point> list = new ArrayList<>();
            Double d = new Random().nextDouble() * 10;
            list.add(new Point(d, i));
            names.add("ololo pishpish ");
            data.add(list);
        }
        List<Point> val2 = new ArrayList<>();
        for (double i = 0; i < 10; i = i + 0.1) {
            val2.add(new Point(0, i));
            val2.add(new Point(2, i));
            val2.add(new Point(8, i));
            val2.add(new Point(10, i));
        }
        names.add("сигма ограничение");
        data.add(val2);

        JFreeChart sca = ScatterPlotService.createChart("lolka", "xz", "0_O", new CustomXYDataset(data, names));


        ChartPanel cp = new ChartPanel(sca) {{
            setLocation(5, 5);
            setSize(470, 450);
            setVisible(true);
        }};
        panel.add(cp);
    }

}
