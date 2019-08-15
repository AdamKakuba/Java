package sample;
import java.io.IOException;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupTest {

    void pobierz(Map<String,String> map){
        Connection connect = Jsoup.connect("https://tvn24bis.pl/notowania/waluty.html");
        Document document = null;
        try{
            document = connect.get();}
        catch(IOException exception) {
            exception.printStackTrace();
        }
        Elements name = document.select("div.index-name");
        Elements value = document.select("div.value");

        for(int i = 1;i<name.size();i++){
            map.put(name.eq(i).text(),value.eq(i).text());
        }
    }

    StringProperty wyswietl(Map<String,String> map){
        StringProperty temp = new SimpleStringProperty();
        String temp2 = "";
        for (Map.Entry< String,String> me:map.entrySet())
        {
            //System.out.print(me.getKey()+" = ");
            //System.out.println(me.getValue());
            temp2 += me.getKey()+" = "+ me.getValue()+"\n";
            temp.set(temp2);

            //temp.setValue(me.getKey()+" = "+ me.getValue()+"\n");
        }
        return temp;
    }

    void insert(String name,String value,Map<String,String> map){
        //Scanner scanner = new Scanner(System.in);
        //String name,value;
        //System.out.print("Podaj waluty:\n");
        //name = scanner.next();
        //System.out.print("Podaj wartosc:\n");
        //value = scanner.next();
        map.put(name,value);

    }
    StringProperty search(String name,Map<String,String> map){
        //Scanner scanner = new Scanner(System.in);
        //String name;
        //System.out.print("Podaj waluty:\n");
        //name = scanner.next();
        //System.out.print("Wartosc szukanej waluty:\n");
        //System.out.println(map.get(name));
        StringProperty temp = new SimpleStringProperty();
        temp.set(map.get(name));
        return temp;
    }
}