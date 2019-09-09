package com.company;

import org.mariuszgromada.math.mxparser.*;

import java.util.ArrayList;
import java.util.List;

public class Punkt {

    private String nazwyZmiennych[] = {"x0","x1","x2","x3","x4"};

    private List<Double> zmienne = new ArrayList<>();

    Punkt(){

    }

    Punkt(Punkt dawca){

        for(int i = 0; i<dawca.wymiar();i++) zmienne.add(dawca.odczytajZmiennaNr(i));

    }

    Punkt(double...values){
        this.usunZmienne();
        for(int i = 0; i<values.length;i++) zmienne.add(values[i]);
    }

    Punkt(List<Double> wsp){
        this.zmienne = wsp;
    }

    /**
     *  Funkcja zwraca wartość zmiennej o zadanym indexie
     *
     * @param index
     * @return
     */
    double odczytajZmiennaNr(int index){
        return zmienne.get(index);
    }

    /**
     * Ustawia wartości zmiennych, ile zmiennych się poda, taki będzie wymiar punktu
     *
     * @param values wartości zmiennych typu double
     */
    void ustaw(double...values){
        this.usunZmienne();
        for(int i = 0; i<values.length;i++) zmienne.add(values[i]);
    }

    /**
     * Funkcja pobiera zmienne z innego punktu
     *
     * @param dawca
     */
    void ustaw(Punkt dawca){
        this.usunZmienne();
        for(int i = 0; i<dawca.wymiar();i++) zmienne.add(dawca.odczytajZmiennaNr(i));
    }

    /**
     * Funkcja ustawia wartość konkretnej zmiennej
     *
     * @param index numer zmiennej
     * @param value wartość jaka zostanie wpisana do zmiennej o zadanym indexie
     */
    void ustawZmiennaNr(int index, double value){
        zmienne.set(index,value);
    }

    /**
     * usuwa wszystkie zmienne z punktu
     */
    void usunZmienne(){
        zmienne.clear();
    }

    /**
     * podaje ile zmiennych zawiera punkt
     * @return
     */
    int wymiar(){
        return zmienne.size();
    }

    /**
     * Funkcja oblicza wartość funkcji w punkcie
     *
     * @param f funkcja f jest obliczana dla współrzędnych punktu
     * @return zwraca wynik jako double
     */
    double oblicz(Function f){
        List<Argument> argumenty = new ArrayList<>();
        for(int i = 0; i<wymiar();i++) argumenty.add(new Argument(nazwyZmiennych[i],this.odczytajZmiennaNr(i)));
        switch (wymiar()){
            case 1: {
                Expression expression = new Expression(f.getFunctionName()+"(x0)",f,argumenty.get(0));
                return expression.calculate();
            }

            case 2: {
                Expression expression = new Expression(f.getFunctionName()+"(x0,x1)",f,argumenty.get(0),argumenty.get(1));
                return expression.calculate();
            }

            case 3: {
                Expression expression = new Expression(f.getFunctionName()+"(x0,x1,x2)",f,argumenty.get(0),argumenty.get(1),argumenty.get(2));
                return expression.calculate();
            }

            case 4: {
                Expression expression = new Expression(f.getFunctionName()+"(x0,x1,x2,x3)",f,argumenty.get(0),argumenty.get(1),argumenty.get(2),argumenty.get(3));
                return expression.calculate();
            }

            case 5: {
                Expression expression = new Expression(f.getFunctionName()+"(x0,x1,x2,x3,x4)",f,argumenty.get(0),argumenty.get(1),argumenty.get(2),argumenty.get(3),argumenty.get(4));
                return expression.calculate();
            }

            default: {
                System.out.println("BŁąddddd");
                return -1;
            }

        }

    }

    /**
     * Sprawdza, czy punkt spełnia ograniczenie, zwraca bool
     *
     * @param ograniczenie ograniczenie w postaci funkcji z parsera
     * @return
     */
    boolean spelniaOgraniczenie(Function ograniczenie) {
        if(this.oblicz(ograniczenie) <= 0) return true;
        else return false;
    }

    double odlegloscAbsOd(Punkt punkt){
        double wynik = 0;

        for(int i=0; i<wymiar();i++) {
            wynik+= (this.odczytajZmiennaNr(i)-punkt.odczytajZmiennaNr(i))*(this.odczytajZmiennaNr(i)-punkt.odczytajZmiennaNr(i));
        }

        return Math.abs(Math.sqrt(wynik));
    }

    @Override
    public String toString() {

        switch (this.wymiar()){
            case 1: {
                return "x0 = "+ odczytajZmiennaNr(0);
            }
            case 2: {
                return "x0 = "+ odczytajZmiennaNr(0)+" x1 = "+ odczytajZmiennaNr(1);
            }
            case 3: {
                return  "x0 = "+ odczytajZmiennaNr(0)+" x1 = "+ odczytajZmiennaNr(1)+" x2 = "+ odczytajZmiennaNr(2);
            }
            case 4: {
                return  "x0 = "+ odczytajZmiennaNr(0)+" x1 = "+ odczytajZmiennaNr(1)+" x2 = "+ odczytajZmiennaNr(2)+" x3 = "+ odczytajZmiennaNr(3);
            }
            case 5: {
                return  "x0 = "+ odczytajZmiennaNr(0)+" x1 = "+ odczytajZmiennaNr(1)+" x2 = "+ odczytajZmiennaNr(2)+" x3 = "+ odczytajZmiennaNr(3)+" x4 = "+ odczytajZmiennaNr(4);
            }
            default: {
                return "błąd";
            }
        }

    }
}
