package com.company;

import org.jzy3d.chart.ChartLauncher;
import org.mariuszgromada.math.mxparser.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        int wybor = 0;
        boolean start = false;
        Punkt poprzedniPunkt = new Punkt();
        double rk;
        int iteracja = 0;
        List<Punkt> kolejnePunkty = new ArrayList<>();
        Carroll carroll = new Carroll();
        Punkt punktStartowy = new Punkt();

        while (!start)
        switch (wybor){
            case 0: {

                System.out.println("Dostępne opcje: \n"+
                        "1.\tf(x0,x1) = (x0-2)^2 + (x1-1)^2\n\tg0(x0,x1) = x0^2 - x1\n\tg1(x0,x1) = x0 + x1 - 2\n\tPunkt startowy [0.1 0.1]\n\n"+
                        "2.\tf(x0,x1) = x0^4 + x1^4 - 2*(x0^2)*x1 - 4*x0 + 3\n\tg0(x0,x1) = 4 - x0 - x1\n\tPunkt startowy [3 3]\n\n"+
                        "3.\tf(x0,x1) = (x0^2 + x2 - 11)^2 + (x0 + x1^2 - 7)^2\n\tg0(x0,x1) = (x0-0.05)^2 + (x1-2.5)^2 - 4.84\n\tg1(x0,x1) = 4.84 - x0^2 - (x1 - 2.5)^2\n\tg2(x0,x1) = x0 - 10\n\tg3(x0,x1) = -x0 - 10\n\tPunkt startowy [2.24 2.38]\n\n"+
                        "4.\tf(x0,x1) = x0^4 + x1^4 - x0^2 - x1^2\n\tg0(x0,x1) = x0 + x1 - 15\n\t\"g1(x0,x1) = 5 - x0 + x1^2\n\tPunkt startowy [6 0.5]\n\n"+
                        "5.\tf(x0,x1) = 5*(x0^2 + x1^2)^(1/2)\n\tg0(x0,x1) = x0 + 1.5\n\tg1(x0,x1) = x1 + 0.5\n\tPunkt startowy [-2 -3]\n\n"+
                        "6.\tf(x0,x1) = jakies 5 zmiennych sobie tutaj byc powinno\n\tg0(x0,x1) = x0^2 - x1\n\tPunkt startowy [-2 -3]\n\n");
                System.out.println("Wybierz opcję od 1 do 6: ");
                Scanner in = new Scanner(System.in);
                wybor = in.nextInt();
                break;
            }
            case 1: {
                //Funkcja kwadratowa
                carroll = new Carroll(2, "f(x0,x1) = (x0-2)^2 + (x1-1)^2", "g0(x0,x1) = x0^2 - x1","g1(x0,x1) = x0 + x1 - 2");
                punktStartowy = new Punkt(0.1, 0.1);
                start = true;
                break;
            }
            case 2: {
                //Funkcja Engwalla
                carroll = new Carroll(2, "f(x0,x1) = x0^4 + x1^4 - 2*(x0^2)*x1 - 4*x0 + 3", "g0(x0,x1) = 4 - x0 - x1");
                punktStartowy = new Punkt(3, 3);
                start = true;
                break;
            }
            case 3: {
                carroll = new Carroll(2, "f(x0,x1) = (x0^2 + x2 - 11)^2 + (x0 + x1^2 - 7)^2", "g0(x0,x1) = (x0-0.05)^2 + (x1-2.5)^2 - 4.84","g1(x0,x1) = 4.84 - x0^2 - (x1 - 2.5)^2","g2(x0,x1) = x0 - 10","g3(x0,x1) = -x0 - 10");
                punktStartowy = new Punkt(2.24, 2.38);
                //ChartLauncher.openChart(Jzy3D.zOgraniczeniami(carroll,punktStartowy,10,50));
                //ChartLauncher.openChart(Jzy3D.bezOgraniczen(carroll,punktStartowy,10,50));
                start = true;
                break;
            }
            case 4: {
                carroll = new Carroll(2, "f(x0,x1) = x0^4 + x1^4 - x0^2 - x1^2", "g0(x0,x1) = x0 + x1 - 15","g1(x0,x1) = 5 - x0 + x1^2");
                punktStartowy = new Punkt(6, 0.5);
                start = true;
                break;
            }
            case 5: {
                carroll = new Carroll(2, "f(x0,x1) = 5*(x0^2 + x1^2)^(1/2)", "g0(x0,x1) = x0 + 1.5","g1(x0,x1) = x1 + 0.5");
                punktStartowy = new Punkt(-2, -3);
                start = true;
                break;
            }
            case 6: {
                carroll = new Carroll(5, "f(x0,x1,x2,x3,x4) = (x0 - 10)^2 + (x1 - 11)^2 + (x2 - 13)^2 + (x3 - 12)^2 - (x4 - 13)^2");

                //carroll = new Carroll(5, "f(x0,x1,x2,x3,x4) = (x0 - 1)^2 + (x1 - 2)^2 + (x2 + 3)^2 + (x3 + 2)^2 - (x4 - 4)^2", "g0(x0,x1,x2,x3,x4) = 3 - x0","g1(x0,x1,x2,x3,x4) = 7 - x4 - x1");
                punktStartowy = new Punkt(2, 3, 4, 5, 1);
                start = true;
                break;
            }
            default: {
                carroll = new Carroll(2, "f(x0,x1) = (x0-1)^2 + (x1-1)^2", "g0(x0,x1) = x0^2 - x1","g1(x0,x1) = x0 + x1 - 2");
                punktStartowy = new Punkt(0.1, 0.1);
                start = true;
                break;
            }

        }

        //carroll = new Carroll(2, "f(x0,x1) = 5*(x0^2 + x1^2)^(1/2)", "g0(x0,x1) = x0 + 1.5","g1(x0,x1) = x1 + 0.5");
        //punktStartowy = new Punkt(-2, -3);
        //Carroll carroll = new Carroll(2, "f(x0,x1) = 5*(x0^2 + x1^2)^(1/2)", "g0(x0,x1) = x0 + 1.5","g1(x0,x1) = x1 + 0.5"); Punkt punktStartowy = new Punkt(-2, -3);//TO JEST OK
        //Carroll carroll = new Carroll(2, "f(x0,x1) = 2*x0^2 + 2*x1^2 - 2*x0*x1 - 4*x0 - 6*x1", "g0(x0,x1) = x0 + 5*x1 - 5" , "g1(x0,x1) = 2*x0^2" , "g2(x0,x1) = -x0" , "g3(x0,x1) = -x1");
        //Carroll carroll = new Carroll(2, "f(x0,x1) = 2*x0^2 + 2*x1^2 - 2*x0*x1 - 4*x0 - 6*x1", "g0(x0,x1) = x0 + 5*x1 - 5" , "g1(x0,x1) = 2*x0^2" , "g2(x0,x1) = -x0" , "g3(x0,x1) = -x1");
        //Carroll carroll = new Carroll(2, "f(x0,x1) = (x1-1)^2 + (x1-x0^2)^2", "g1(x0,x1) = x1^2 - x0 + 1" );
        //Carroll carroll = new Carroll(2, "f(x0,x1) = (x0-2)^2 + (x1-1)^2", "g0(x0,x1) = x0", "g1(x0,x1) = x1" );
        //Carroll carroll = new Carroll(2, "f(x0,x1) = (x0 - 1)^2 + *(x1 - 1)^2"); //Funkcja Himmelblaua
        Function badanaFunkcja = carroll.odczytajBadanaFunkcja();

        // Punkt 1 algorytmu - dobranie punktu startowego

        kolejnePunkty.add(punktStartowy); //na potrzeby wykresu
        if(!carroll.sprawdzOgraniczenia(punktStartowy, true)){
            return;
        }


        if(carroll.n_zmiennych == 2){
            //ChartLauncher.openChart(Jzy3D.bezOgraniczen(carroll,punktStartowy,4,50));
            //ChartLauncher.openChart(Jzy3D.zOgraniczeniami(carroll,punktStartowy,4,100));
            //ChartLauncher.openChart(Jzy3D.zOgraniczeniami2D(carroll,punktStartowy,4,100));
        }




        // Punkt 2 algorytmu - dobranie rk1
        rk = Math.abs(carroll.obliczR1(punktStartowy, 1));
        System.out.println("[Carroll] r1 = " + rk);
        double c = 0.5; //stały mnożnik zmniejszający rk, zalecane wartości: 0.1 0.2 0.5

        ScatterPlotExample example = new ScatterPlotExample("Carroll");
        if(carroll.n_zmiennych == 2) example.dodajPunktDoWykresu(punktStartowy, 0);

        do {
            iteracja++;
            carroll.ustawRk(rk);

            Function f_carroll = new Function(carroll.funkcjaCarroll());

            // Punkt 3 algorytmu - Gauss-Seidel
            GaussSeidel gaussSeidel = new GaussSeidel();
            poprzedniPunkt.ustaw(punktStartowy);
            punktStartowy.ustaw(gaussSeidel.wykonaj(punktStartowy, f_carroll, iteracja, carroll));
            if(carroll.n_zmiennych == 2) kolejnePunkty.add(punktStartowy); //na potrzeby wykresu

            System.out.println("\n[Carroll] Skończyłem iterację nr: [" + iteracja + "]");
            System.out.println("[Carroll] rk: " + rk);
            System.out.println("[Carroll] ||x[i] - x[i-1]|| = " + punktStartowy.odlegloscAbsOd(poprzedniPunkt));
            System.out.println("[Carroll] |f(x[i]) - (fx[i-1])| = " + Math.abs(punktStartowy.oblicz(badanaFunkcja) - poprzedniPunkt.oblicz(badanaFunkcja)));
            System.out.println("[Carroll] Nowy punkt: " + punktStartowy);
            System.out.println("[Carroll] Poprzedni punkt: " + poprzedniPunkt);
            rk = rk * c;//zmniejsz rk o pół
            if (rk <= 0) rk = 0;
            if(carroll.n_zmiennych == 2)example.dodajPunktDoWykresu(punktStartowy, iteracja);

        } while (punktStartowy.odlegloscAbsOd(poprzedniPunkt) > 0.001 || Math.abs(punktStartowy.oblicz(badanaFunkcja) - poprzedniPunkt.oblicz(badanaFunkcja)) > 0.001); //Kryteria stopu


        System.out.println("[Carroll] Otrzymany punkt: [" + punktStartowy + "]");
        System.out.println("[Carroll] Wartosc funkcji w tym punkcie: " + punktStartowy.oblicz(badanaFunkcja));
        carroll.sprawdzOgraniczenia(punktStartowy, true);

        if(carroll.n_zmiennych == 2) {
            example.dodajSerie(iteracja);
            example.setSize(400, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        }





    }
}

