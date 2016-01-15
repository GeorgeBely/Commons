package ru.mangeorge.swing.service;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.awt.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;


/**
 * Сервис лля работы с графиком "Пирожок"
 */
public class PieService {

    /** Название цкуска пирога, в который собираются все остатки из переданного списка после 9 */
    private static final String ANOTHER_BLOCK_NAME = "Другие";

    /**
     * Максимальное колличество возможных самостоятельных частей пирога (по умолчанию).
     * Если частей более этого числа, то 10 кусок и все последующие будут объеденены в кусок ${ANOTHER_BLOCK_NAME}.
     */
    private static final Integer DEFAULT_MAX_SLICE_PIE = 10;


    /**
     * Создание компонента графика "Пирог"
     *
     * @param name       наименование графика
     * @param dataset    данные
     * @return компонент графика "Пирог".
     */
    public static JFreeChart createChart(String name, PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(name, null);
        updatePieData(chart, dataset);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}-{1}-({2})"));
        plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);

        String noDataMessage = "No data available";
        if (Locale.getDefault().equals(new Locale("ru", "RU")))
            noDataMessage = "Нет данных";
        plot.setNoDataMessage(noDataMessage);

        return chart;
    }

    /**
     * Обновляет данные на графике. Формирует равномерное распределение цветов.
     *
     * @param pie  - график "Пирожок"
     * @param data - данные
     */
    public static void updatePieData(JFreeChart pie, PieDataset data) {
        PiePlot plot = (PiePlot) pie.getPlot();

        plot.setExplodePercent((String) data.getKeys().get(0), 0.20);
        plot.setDataset(data);

        int greenCount = 255;
        int redCount = 0;
        int blueCount = 0;
        int step = 511/data.getKeys().size();
        for (Object keyObj : data.getKeys()) {
            plot.setSectionPaint(keyObj.toString(), new Color(redCount, greenCount, blueCount));
            if (greenCount > step) {
                greenCount -= step;
                redCount += step;
            } else if (blueCount < 255 - step) {
                blueCount += step;
                redCount -= step;
            }
        }
    }

    /**
     * Формирует объект даных. Устанавливает максимальное колличество кусков пирога по умолчанию
     *
     * @param data карта с данными
     * @return объект DefaultPieDataset, по заданным значениям из карты
     */
    public static DefaultPieDataset getCountMoneyPieData(Map<String, Number> data) {
        return getCountMoneyPieData(data, DEFAULT_MAX_SLICE_PIE);
    }

    /**
     * Формирует объект даных. Позволяет установить максимальное колличество кусков пирога
     *
     * @param data        - карта с данными
     * @param sliceCount  - максимальное количество кусков пирога. Если данных больше заданного числа, то данные
     *                      с наименьшими значениями будут собраны в кусок ${ANOTHER_BLOCK_NAME}
     * @return объект DefaultPieDataset, по заданным значениям из карты
     */
    public static DefaultPieDataset getCountMoneyPieData(Map<String, Number> data, Integer sliceCount) {
        DefaultPieDataset defaultPieDataset = new DefaultPieDataset();

        if (data == null || data.isEmpty()) {
            return defaultPieDataset;
        }

        Map<String, Number> sortedValueMap = new TreeMap<>((s1, s2) -> {
            if (ANOTHER_BLOCK_NAME.equals(s1))
                return 1;
            if (ANOTHER_BLOCK_NAME.equals(s2))
                return -1;
            if (data.get(s2) instanceof Long)
                return ((Long) data.get(s2)).compareTo((Long) data.get(s1));
            if (data.get(s2) instanceof Double)
                return ((Double) data.get(s2)).compareTo((Double) data.get(s1));
            if (data.get(s2) instanceof Float)
                return ((Float) data.get(s2)).compareTo((Float) data.get(s1));
            if (data.get(s2) instanceof Integer)
                return ((Integer) data.get(s2)).compareTo((Integer) data.get(s1));
            return data.get(s2).toString().compareTo(data.get(s1).toString());
        });
        sortedValueMap.putAll(data);

        if (data.size() > sliceCount) {
            Map<String, Number> shortValueMap = new HashMap<>();
            int count = 0;
            for (Map.Entry<String, Number> value : sortedValueMap.entrySet()) {
                if (count < sliceCount - 1) {
                    shortValueMap.put(value.getKey(), value.getValue());
                } else {
                    if (shortValueMap.get(ANOTHER_BLOCK_NAME) == null) {
                        shortValueMap.put(ANOTHER_BLOCK_NAME, value.getValue());
                    } else {
                        if (value.getValue() instanceof Long)
                            shortValueMap.put(ANOTHER_BLOCK_NAME, ((Long) shortValueMap.get(ANOTHER_BLOCK_NAME)) + ((Long) value.getValue()));
                        if (value.getValue() instanceof Double)
                            shortValueMap.put(ANOTHER_BLOCK_NAME, ((Double) shortValueMap.get(ANOTHER_BLOCK_NAME)) + ((Double) value.getValue()));
                        if (value.getValue() instanceof Float)
                            shortValueMap.put(ANOTHER_BLOCK_NAME, ((Float) shortValueMap.get(ANOTHER_BLOCK_NAME)) + ((Float) value.getValue()));
                        shortValueMap.put(ANOTHER_BLOCK_NAME, shortValueMap.get(ANOTHER_BLOCK_NAME).intValue() + value.getValue().intValue());
                    }
                }
                count++;
            }
            sortedValueMap.clear();
            sortedValueMap.putAll(shortValueMap);
        }

        for (Map.Entry<String, Number> value : sortedValueMap.entrySet()) {
            defaultPieDataset.setValue(value.getKey(), value.getValue());
        }
        return defaultPieDataset;
    }


}
