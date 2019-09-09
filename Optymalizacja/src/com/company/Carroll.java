package com.company;

import org.mariuszgromada.math.mxparser.Function;

import java.util.ArrayList;
import java.util.List;

public class Carroll {

    private String nazwyParametrow[] = {"x0","x1","x2","x3","x4"};
    private String nazwyOgraniczen[] = {"g0","g1","g2","g3","g4"};
    private String nazwyOdwrotnosciOgraniczen[] = {"go0","go1","go2","go3","go4"};


    int n_zmiennych;
    double rk = 10;
    Function badanaFunkcja;
    private List<Function> ograniczenia = new ArrayList<>();

    Carroll(){

    }

    Carroll(int n_zmiennych, String funkcja, String...ograniczenia){
        this.n_zmiennych = n_zmiennych;
        badanaFunkcja = new Function(funkcja);
        for(int i = 0; i<ograniczenia.length;i++) this.ograniczenia.add(new Function(ograniczenia[i]));
    }

    /**
     *  Funkcja zwraca ograniczenie o zadanym indexie
     *
     * @param index
     * @return
     */
    Function odczytajOgraniczenieNr(int index){
        return ograniczenia.get(index);
    }

    Function odczytajBadanaFunkcja(){
        return badanaFunkcja;
    }

    String sumaOdwrotnosci(){

        String funkcjaStr ="";
        switch (ograniczenia.size()){

            case 1:{
                funkcjaStr = "("+ograniczenia.get(0).getFunctionExpressionString()+" )^(-1)";
                break;
            }
            case 2:{
                funkcjaStr = "("+ograniczenia.get(0).getFunctionExpressionString()+" )^(-1) +" +" ("+ograniczenia.get(1).getFunctionExpressionString()+" )^(-1)";
                break;
            }
            case 3:{
                funkcjaStr = "("+ograniczenia.get(0).getFunctionExpressionString()+" )^(-1) +" +" ("+ograniczenia.get(1).getFunctionExpressionString()+" )^(-1) +" +" ("+ograniczenia.get(2).getFunctionExpressionString()+" )^(-1)";
                break;
            }
            case 4:{
                funkcjaStr = "("+ograniczenia.get(0).getFunctionExpressionString()+" )^(-1) +" +" ("+ograniczenia.get(1).getFunctionExpressionString()+" )^(-1) +" +" ("+ograniczenia.get(2).getFunctionExpressionString()+" )^(-1) +" +" ("+ograniczenia.get(3).getFunctionExpressionString()+" )^(-1)";
                break;
            }
            case 5:{
                funkcjaStr = "("+ograniczenia.get(0).getFunctionExpressionString()+" )^(-1) +" +" ("+ograniczenia.get(1).getFunctionExpressionString()+" )^(-1) +" +" ("+ograniczenia.get(2).getFunctionExpressionString()+" )^(-1) +" +" ("+ograniczenia.get(3).getFunctionExpressionString()+" )^(-1) +" +" ("+ograniczenia.get(4).getFunctionExpressionString()+" )^(-1)";
                break;
            }
            default:{
                funkcjaStr = "e = 0";
            }

        }
        return funkcjaStr;
    }

    String funkcjaCarroll(){
        if(ileOgraniczen() > 0) return "f_carroll"+listaArgumentow()+" ="+badanaFunkcja.getFunctionExpressionString()+" - "+rk+"*("+sumaOdwrotnosci()+")";
        else return "f_carroll"+listaArgumentow()+" ="+badanaFunkcja.getFunctionExpressionString();
    }

    /**
     * podaje ile zmiennych zawiera punkt
     * @return
     */
    int ileOgraniczen(){
        return ograniczenia.size();
    }

    String listaArgumentow(){
        String lista = "(";
        for(int i = 0; i<n_zmiennych-1; i++){
            lista += nazwyParametrow[i] + ",";
        }
        lista += nazwyParametrow[n_zmiennych-1] + ")";
        return lista;
    }

    double odczytajRk(){
        return rk;
    }

    double obliczR1(Punkt punktStartowy, double wspolczynnik){

        String licznik;
        String mianownik;
        licznik = this.odczytajBadanaFunkcja().getFunctionExpressionString();
        mianownik = this.sumaOdwrotnosci();
        Function function = new Function("R(x0) =("+licznik+")/-("+mianownik+")");
        switch (punktStartowy.wymiar()){

            case 1: {
                function = new Function("R(x0) =("+licznik+")/-("+mianownik+")");
                break;

            }
            case 2: {
                function = new Function("R(x0,x1) =("+licznik+")/-("+mianownik+")");
                break;

            }
            case 3: {
                function = new Function("R(x0,x1,x2) =("+licznik+")/-("+mianownik+")");
                break;

            }
            case 4: {
                function = new Function("R(x0,x1,x2,x3) =("+licznik+")/-("+mianownik+")");
                break;

            }
            case 5: {
                function = new Function("R(x0,x1,x2,x3,x4) =("+licznik+")/-("+mianownik+")");
                break;

            }
            default: {
                return 0;
            }
        }

        if(ileOgraniczen() == 0) return 0;

        System.out.println(function.getFunctionName()+ " =" + function.getFunctionExpressionString());
        return punktStartowy.oblicz(function);

    }

    boolean sprawdzOgraniczenia(Punkt punkt, boolean wyswietl){

        boolean wynik = true;
        String niespelnioneOgraniczenia = "Punkt " + punkt + " nie spełnia ograniczen numer:";

        for(int i = 0; i<ileOgraniczen(); i++){
            if(!punkt.spelniaOgraniczenie(ograniczenia.get(i)))
            {
                wynik = false;
                niespelnioneOgraniczenia += " "+i+";";
            }
        }

        if(wyswietl == true){
            if (wynik == false) System.out.println(niespelnioneOgraniczenia);
            else  System.out.println("Punkt " + punkt + " spełnia ograniczenia");
        }


        return wynik;
    }

    void ustawRk(double rk){
        this.rk = rk;
    }

    @Override
    public String toString() {

        switch (this.ileOgraniczen()){
            case 1: {
                return badanaFunkcja.getFunctionName()+ " =" + badanaFunkcja.getFunctionExpressionString()+"\n"
                        +ograniczenia.get(0).getFunctionName() + " ="+ ograniczenia.get(0).getFunctionExpressionString() +"\n";
            }
            case 2: {
                return badanaFunkcja.getFunctionName()+ " =" + badanaFunkcja.getFunctionExpressionString()+"\n"
                        +ograniczenia.get(0).getFunctionName() + " ="+ ograniczenia.get(0).getFunctionExpressionString()+"\n"
                        +ograniczenia.get(1).getFunctionName() + " ="+ ograniczenia.get(1).getFunctionExpressionString() +"\n";
            }
            case 3: {
                return badanaFunkcja.getFunctionName()+ " =" + badanaFunkcja.getFunctionExpressionString()+"\n"
                        +ograniczenia.get(0).getFunctionName() + " ="+ ograniczenia.get(0).getFunctionExpressionString() +"\n"
                        +ograniczenia.get(1).getFunctionName() + " ="+ ograniczenia.get(1).getFunctionExpressionString() +"\n"
                        +ograniczenia.get(2).getFunctionName() + " ="+ ograniczenia.get(2).getFunctionExpressionString() +"\n";
            }
            case 4: {
                return badanaFunkcja.getFunctionName()+ " =" + badanaFunkcja.getFunctionExpressionString()+"\n"
                        +ograniczenia.get(0).getFunctionName() + " ="+ ograniczenia.get(0).getFunctionExpressionString() +"\n"
                        +ograniczenia.get(1).getFunctionName() + " ="+ ograniczenia.get(1).getFunctionExpressionString() +"\n"
                        +ograniczenia.get(2).getFunctionName() + " ="+ ograniczenia.get(2).getFunctionExpressionString() +"\n"
                        +ograniczenia.get(3).getFunctionName() + " ="+ ograniczenia.get(3).getFunctionExpressionString() +"\n";
            }
            case 5: {
                return badanaFunkcja.getFunctionName()+ " =" + badanaFunkcja.getFunctionExpressionString()+"\n"
                        +ograniczenia.get(0).getFunctionName() + " ="+ ograniczenia.get(0).getFunctionExpressionString() +"\n"
                        +ograniczenia.get(1).getFunctionName() + " ="+ ograniczenia.get(1).getFunctionExpressionString() +"\n"
                        +ograniczenia.get(2).getFunctionName() + " ="+ ograniczenia.get(2).getFunctionExpressionString() +"\n"
                        +ograniczenia.get(3).getFunctionName() + " ="+ ograniczenia.get(3).getFunctionExpressionString() +"\n"
                        +ograniczenia.get(4).getFunctionName() + " ="+ ograniczenia.get(4).getFunctionExpressionString() +"\n";
            }
            default: {
                return "błąd";
            }
        }

    }
}
