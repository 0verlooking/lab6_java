package com.chmnu_ki_123.c3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.plot.PlotOrientation;

import javax.swing.*;
import java.awt.*;
import static java.lang.Math.*;

public class MathCalcGraph extends JFrame {

    public MathCalcGraph(String title) {
        super(title);
        XYSeries series = new XYSeries("f(x)");

        double a = 7.12, b = 8.12, c = 2.0;
        double x = 0.5;

        double expAx = exp(a * x);
        double tanBxC = tan(b * x + c);
        double log2AxC = log((a * x) - c) / log(2);
        double expBx2_1 = exp(b * pow(x, 2) - 1);

        double result = expAx * tanBxC + log2AxC / expBx2_1;

        System.out.printf("Результат обчислення: %.3f\n", result);

        double minX = Double.NaN, minF = Double.NaN, maxX = Double.NaN, maxF = Double.NaN;
        boolean first = true;

        System.out.printf("%-10s %-10s\n", "x", "f(x)");
        System.out.println("--------------------------");

        for (double xVal = -10; xVal <= 10; xVal += 0.1) {
            double f;
            try {
                f = exp(a * cos(xVal + 2)) - (exp(-sin(b * xVal))) / (c - cbrt(xVal));

                if (Double.isNaN(f) || Double.isInfinite(f)) {
                    continue;
                }

                series.add(xVal, f);

                System.out.printf("%-10.2f %-10.2f\n", xVal, f);

                if (first || f < minF) {
                    minX = xVal;
                    minF = f;
                }
                if (first || f > maxF) {
                    maxX = xVal;
                    maxF = f;
                }

                first = false;
            } catch (Exception e) {
                System.err.println("Помилка обчислення значення функції при x = " + xVal + ": " + e.getMessage());
            }
        }

        System.out.println("--------------------------");
        System.out.printf("Знайдені екстремуми:%nМінімум: x = %.2f, f(x) = %.2f%nМаксимум: x = %.2f, f(x) = %.2f%n", minX, minF, maxX, maxF);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Графік функції з екстремумами",
                "x", "f(x)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MathCalcGraph chart = new MathCalcGraph("Побудова графіку та екстремуми");
        });
    }
}
