package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author imssbora
 */
public class ScatterPlotExample extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;

    List<XYSeries> serie = new ArrayList<>();


    XYSeriesCollection dataset = new XYSeriesCollection();
    public ScatterPlotExample(String title) {
        super(title);

        /*for(int i = 0;i<40;i++){
            serie.add(new XYSeries("x"+i));

        }*/

        // Create dataset
        XYDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                title,
                "x0", "x1", dataset);


        //Changes background color
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 255, 255));


        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset() {

        return dataset;
    }

    public void dodajSerie(int iteracje){
        for(int i = 0; i<iteracje;i++){
            dataset.addSeries(serie.get(i));
        }
    }

    public void dodajPunktDoWykresu(Punkt punkt,int index){

        serie.add(new XYSeries("x"+index));
        serie.get(index).add(punkt.odczytajZmiennaNr(0),punkt.odczytajZmiennaNr(1));

    }
}
