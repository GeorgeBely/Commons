package ru.mangeorge.swing.graphics;

import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.xy.AbstractXYDataset;
import ru.mangeorge.awt.Point;

import java.util.List;


/**
 * Класс реализующий модель данных, для двумерных графиков
 */
public class CustomXYDataset extends AbstractXYDataset implements org.jfree.data.xy.XYDataset, DomainInfo, RangeInfo {

    private Double xValues[][];
    private Double yValues[][];
    private int seriesCount;
    private int itemCount;
    private Number domainMin;
    private Number domainMax;
    private Number rangeMin;
    private Number rangeMax;
    private Range domainRange;
    private Range range;
    private List<String> names;

    /**
     * @param values - список списков точек. Каждый отдельный список с точками будет отображаться как отдельная функция
     * @param names  - список имён функций
     */
    public CustomXYDataset(List<List<Point>> values, List<String> names) {
        if (values != null && !values.isEmpty()) {
            this.names = names;
            int maxValueLength = 0;
            for (List<Point> list : values) {
                if (maxValueLength < list.size())
                    maxValueLength = list.size();
            }
            xValues = new Double[values.size()][maxValueLength];
            yValues = new Double[values.size()][maxValueLength];
            seriesCount = values.size();
            itemCount = maxValueLength;

            int i = 0;
            for (List<Point> list : values) {
                int j = 0;
                for (Point point : list) {
                    xValues[i][j] = point.getX();
                    yValues[i][j] = point.getY();
                    j++;
                }
                i++;
            }
        }

        domainMin = Double.POSITIVE_INFINITY;
        domainMax = Double.NEGATIVE_INFINITY;
        domainRange = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        rangeMin = Double.POSITIVE_INFINITY;
        rangeMax = Double.NEGATIVE_INFINITY;
        range = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }



    public Number getX(int i, int j) {
        return xValues[i][j];
    }

    public Number getY(int i, int j) {
        return yValues[i][j];
    }

    public int getSeriesCount() {
        return seriesCount;
    }

    public Comparable getSeriesKey(int i) {
        String name = "Sample " + i;
        if (names != null && !names.isEmpty() && names.size() > i) {
            name = names.get(i);
        }
        return name;
    }

    public int getItemCount(int i) {
        return itemCount;
    }

    public double getDomainLowerBound(boolean flag) {
        return domainMin.doubleValue();
    }

    public double getDomainUpperBound(boolean flag) {
        return domainMax.doubleValue();
    }

    public Range getDomainBounds(boolean flag) {
        return domainRange;
    }

    public double getRangeLowerBound(boolean flag) {
        return rangeMin.doubleValue();
    }

    public double getRangeUpperBound(boolean flag) {
        return rangeMax.doubleValue();
    }

    public Range getRangeBounds(boolean flag) {
        return range;
    }

    public String toString() {
        return "данные";
    }
}

