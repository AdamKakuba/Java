package com.company;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapGrayscale;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.plot3d.rendering.view.modes.ViewPositionMode;
import org.mariuszgromada.math.mxparser.Function;

public class Jzy3D {




    static Chart bezOgraniczen(Carroll carroll,Punkt punkt,int zakres, int steps){

        // Define range and precision for the function to plot
        Range range = new Range((int)punkt.odczytajZmiennaNr(0)-zakres,(int)punkt.odczytajZmiennaNr(0) + zakres);

        Function badanaFunkcja = carroll.odczytajBadanaFunkcja();
        Mapper mapper3 = new Mapper() {
            public double f(double x, double y) {
                Punkt punkt = new Punkt(x, y);
                double wynik;
                wynik = punkt.oblicz(badanaFunkcja);
                return wynik;
            }
        };

        Chart chart33 = new Chart(new AWTChartComponentFactory(), Quality.Advanced, "awt");
        Shape surface3 = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper3);
        surface3.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface3.getBounds().getZmin(), surface3.getBounds().getZmax(), new Color(1, 1, 1, 0.5f)));
        surface3.setFaceDisplayed(true);
        surface3.setWireframeDisplayed(true);
        surface3.setWireframeColor(Color.BLACK);
        surface3.setLegendDisplayed(true); // opens a colorbar on the right part of the display

        chart33.getScene().getGraph().add(surface3);

        return chart33;
    }

    static Chart zOgraniczeniami(Carroll carroll,Punkt punkt,int zakres, int steps){

        // Define range and precision for the function to plot
        Range range = new Range((int)punkt.odczytajZmiennaNr(0)-zakres,(int)punkt.odczytajZmiennaNr(0) + zakres);

        Function badanaFunkcja = carroll.odczytajBadanaFunkcja();

        // Define a function to plot
        Mapper mapper = new Mapper() {
            public double f(double x, double y) {
                Punkt punkt = new Punkt(x, y);
                double wynik;
                if(carroll.sprawdzOgraniczenia(punkt,false)) wynik = punkt.oblicz(badanaFunkcja);
                else wynik = 0;
                return wynik;
            }
        };

        // Define a function to plot
        Mapper mapper2 = new Mapper() {
            public double f(double x, double y) {
                Punkt punkt = new Punkt(x, y);
                double wynik;
                if(carroll.sprawdzOgraniczenia(punkt,false)) wynik = 0;
                else wynik = 1;
                return wynik;
            }
        };


        // Create a surface drawing that function
        Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
        Shape surface2 = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper2);
        surface2.setColorMapper(new ColorMapper(new ColorMapGrayscale(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, 1f)));
        surface2.setFaceDisplayed(true);
        surface2.setWireframeDisplayed(false);
        surface2.setWireframeColor(Color.BLACK);
        surface2.setLegendDisplayed(true); // opens a colorbar on the right part of the display


        ColorMapRainbow colorScale = new ColorMapRainbow();

        surface.setColorMapper(new ColorMapper(colorScale, surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(true);
        surface.setWireframeColor(Color.BLACK);
        surface.setLegendDisplayed(true); // opens a colorbar on the right part of the display

// Create a chart and add the surface

        Chart chart = new Chart(new AWTChartComponentFactory(), Quality.Advanced, "awt");


        chart.getScene().getGraph().add(surface);
        chart.getScene().getGraph().add(surface2);

        return chart;
    }

    static Chart zOgraniczeniami2D(Carroll carroll,Punkt punkt,int zakres, int steps){


        // Define range and precision for the function to plot
        Range range = new Range((int)punkt.odczytajZmiennaNr(0)-zakres,(int)punkt.odczytajZmiennaNr(0) + zakres);


        Function badanaFunkcja = carroll.odczytajBadanaFunkcja();

        // Define a function to plot
        Mapper mapper = new Mapper() {
            public double f(double x, double y) {
                Punkt punkt = new Punkt(x, y);
                double wynik;
                if(carroll.sprawdzOgraniczenia(punkt,false)) wynik = punkt.oblicz(badanaFunkcja);
                else wynik = 0;
                return wynik;
            }
        };

        // Define a function to plot
        Mapper mapper2 = new Mapper() {
            public double f(double x, double y) {
                Punkt punkt = new Punkt(x, y);
                double wynik;
                if(carroll.sprawdzOgraniczenia(punkt,false)) wynik = 0;
                else wynik = 1;
                return wynik;
            }
        };


        // Create a surface drawing that function
        Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
        Shape surface2 = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper2);
        surface2.setColorMapper(new ColorMapper(new ColorMapGrayscale(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, 1f)));
        surface2.setFaceDisplayed(true);
        surface2.setWireframeDisplayed(true);
        surface2.setWireframeColor(Color.BLACK);
        surface2.setLegendDisplayed(true); // opens a colorbar on the right part of the display


        ColorMapRainbow colorScale = new ColorMapRainbow();

        surface.setColorMapper(new ColorMapper(colorScale, surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(false);
        surface.setWireframeColor(Color.BLACK);
        surface.setLegendDisplayed(true); // opens a colorbar on the right part of the display

// Create a chart and add the surface

        Chart chart = new Chart(new AWTChartComponentFactory(), Quality.Advanced, "awt");


        chart.getScene().getGraph().add(surface);
        chart.getScene().getGraph().add(surface2);
        chart.getView().setViewPositionMode(ViewPositionMode.TOP);
        return chart;

    }

}
