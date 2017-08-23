package ru.mangeorge.swing.service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import ru.mangeorge.swing.graphics.CustomXYDataset;

import java.util.Locale;

/**
 * Сервис лля работы с двумерным графиком
 */
public class ScatterPlotService {

    /**
     * Создание двумерного графика
     *
     * @param title   наименование графика
     * @param titleX  наименование оси X
     * @param titleY  наименование оси Y
     * @param data    данные
     * @return компонент двумерного графика
     */
    public static JFreeChart createChart(String title, String titleX, String titleY, CustomXYDataset data) {
        JFreeChart jfreechart = ChartFactory.createScatterPlot(title, titleX, titleY, data, PlotOrientation.VERTICAL, true, true, false);

        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setDomainCrosshairVisible(true);
        xyplot.setDomainCrosshairLockedOnData(true);
        xyplot.setRangeCrosshairVisible(true);
        xyplot.setRangeCrosshairLockedOnData(true);
        xyplot.setDomainZeroBaselineVisible(true);
        xyplot.setRangeZeroBaselineVisible(true);
        xyplot.setDomainPannable(true);
        xyplot.setRangePannable(true);

        NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
        numberaxis.setAutoRangeIncludesZero(false);

        String noDataMessage = "No data available";
        if (Locale.getDefault().equals(new Locale("ru", "RU")))
            noDataMessage = "Нет данных";
        xyplot.setNoDataMessage(noDataMessage);

        return jfreechart;
    }

    /**
     * Обновляет данные на графике
     *
     * @param scatterPlot двумерный график
     * @param data        данные
     */
    public static void updateData(JFreeChart scatterPlot, CustomXYDataset data) {
        XYPlot plot = (XYPlot) scatterPlot.getPlot();

        plot.setDataset(data);
    }

}

