package com.company;

import org.mariuszgromada.math.mxparser.*;

public class GaussSeidel {

    double dokladnosc = 0.001;
    int iteracja = 0;
    Punkt wykonaj(Punkt x_start, Function f_caroll, int iteracja, Carroll carroll) {

        Punkt wynik = new Punkt();
        Punkt poprzedniWynik = new Punkt();
        wynik.ustaw(x_start);
        int kierunek = 0;
        iteracja = 0;


        /**
         *
         * Wykonuj złoty podział, dopóki nie zostało spełnione kryterium zbieżności
         *
         */
        do {
            iteracja++;
            poprzedniWynik.ustaw(wynik);
            kierunek = 0;
            while (kierunek < wynik.wymiar()) {

                wynik.ustaw(ZlotyPodzial.wykonaj(wynik, f_caroll, kierunek, carroll));
                kierunek++; //zmiana kierunku

            }

        } while (!czyZbiezny(poprzedniWynik,wynik,f_caroll));


        return wynik;
    }

    boolean czyZbiezny(Punkt poprzedniWynik, Punkt wynik, Function f_caroll) {

        boolean zbiezny = true;

        //Sprawdzanie w każdym kierunku, czy przesunięcie jest większe niż dokładność, jeżeli jest, to nie jest zbiezny
/*        for (int i = 0; i<wynik.wymiar(); i++) {
            if(wynik.odczytajZmiennaNr(i) - poprzedniWynik.odczytajZmiennaNr(i) > dokladnosc){
                zbiezny = false;
            }*/


        if( Math.abs(wynik.oblicz(f_caroll) - poprzedniWynik.oblicz(f_caroll)) > dokladnosc && wynik.odlegloscAbsOd(poprzedniWynik) > dokladnosc) {

/*            System.out.println("[Gauss-Seidel] Iteracja nr ["+iteracja+"]");
            System.out.println("[Gauss-Seidel] Zbieżność w kierunku ["+i+"] = "+(wynik.odczytajZmiennaNr(i) - poprzedniWynik.odczytajZmiennaNr(i)));*/
            zbiezny = false;
        }

        return zbiezny;
    }

}
