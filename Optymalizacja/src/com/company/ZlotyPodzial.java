package com.company;

import org.mariuszgromada.math.mxparser.*;

public class ZlotyPodzial {

    static Punkt wykonaj(Punkt x_start, Function f_caroll, int kierunek, Carroll carroll) {

        //System.out.println("[Zloty podzial] Start");
        //System.out.println("[Złoty podział] Kierunek = " + kierunek);

        Punkt wynik = new Punkt(x_start);
        Punkt a = new Punkt(wynik);
        Punkt b = new Punkt(wynik);
        Punkt xL = new Punkt(wynik);
        Punkt xR = new Punkt(wynik);
        double t = 0.618;


/*        a.ustawZmiennaNr(kierunek, wynik.odczytajZmiennaNr(kierunek) - 2*Math.abs(wynik.odczytajZmiennaNr(kierunek))); //tak trochę nas sztywno
        b.ustawZmiennaNr(kierunek, wynik.odczytajZmiennaNr(kierunek) + 2*Math.abs(wynik.odczytajZmiennaNr(kierunek))); //tak trochę nas ztywno*/

        //System.out.println("[Złoty podzial] Zaraz ustawiam a i b");
        a.ustawZmiennaNr(kierunek, wynik.odczytajZmiennaNr(kierunek) - 0.01);
        while(!carroll.sprawdzOgraniczenia(a,false)){
            a.ustawZmiennaNr(kierunek,a.odczytajZmiennaNr(kierunek)+0.0001);
        }
        //System.out.println("[Złoty podzial] Ustawilem a");
        b.ustawZmiennaNr(kierunek, wynik.odczytajZmiennaNr(kierunek) + 0.01);
        while(!carroll.sprawdzOgraniczenia(b,false)){
            b.ustawZmiennaNr(kierunek,b.odczytajZmiennaNr(kierunek)-0.0001);
        }
        //System.out.println("[Złoty podzial] Ustawilem b");
        xL.ustawZmiennaNr(kierunek, b.odczytajZmiennaNr(kierunek) - t * Math.abs(b.odczytajZmiennaNr(kierunek) - a.odczytajZmiennaNr(kierunek)));
        xR.ustawZmiennaNr(kierunek, a.odczytajZmiennaNr(kierunek) + t * Math.abs(b.odczytajZmiennaNr(kierunek) - a.odczytajZmiennaNr(kierunek)));


        int krokNr = 0;
        while ((b.odczytajZmiennaNr(kierunek) - a.odczytajZmiennaNr(kierunek)) > 0.001) {

            krokNr++;

            /*
            System.out.println("[Zloty podzial] Krok: " + krokNr);
            System.out.println("[Zloty podzial] a = " + a);
            System.out.println("[Zloty podzial] xL = " + xL);
            System.out.println("[Zloty podzial] xR = " + xR);
            System.out.println("[Zloty podzial] b = " + b);*/

            if (xL.oblicz(f_caroll) < xR.oblicz(f_caroll)) {
                b.ustaw(xR);
                xR.ustaw(xL);
                xL.ustawZmiennaNr(kierunek, b.odczytajZmiennaNr(kierunek) - (t * (b.odczytajZmiennaNr(kierunek) - a.odczytajZmiennaNr(kierunek))));

            } else {
                a.ustaw(xL);
                xL.ustaw(xR);
                xR.ustawZmiennaNr(kierunek, a.odczytajZmiennaNr(kierunek) + (t * (b.odczytajZmiennaNr(kierunek) - a.odczytajZmiennaNr(kierunek))));
            }
            //System.out.println("[Zloty podzial] Dokladnosc = " + (b.odczytajZmiennaNr(kierunek) - a.odczytajZmiennaNr(kierunek)));

        }

        wynik.ustawZmiennaNr(kierunek, ((a.odczytajZmiennaNr(kierunek) + b.odczytajZmiennaNr(kierunek)) / 2));
        return wynik;
    }

}

