package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.*;
import java.util.Date;
import java.util.Map;

public class bazkadanych {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:bazkadanych.db";

    private Connection conn;
    private Statement stat;

    public bazkadanych() {
        try {
            Class.forName(bazkadanych.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        createTables();
    }
    public boolean createTables()  {
        String createTab = "CREATE TABLE IF NOT EXISTS waluta (id_waluty INTEGER PRIMARY KEY AUTOINCREMENT,dataw varchar(255),nazwa varchar(255), wartosc varchar(255))";
        try {
            stat.execute(createTab);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insertWaluty(Map<String,String> map) {
        Date date = new Date();
        String sdate = date.toString();
        for (Map.Entry< String,String> me:map.entrySet()) {
            try {
                PreparedStatement prepStmt = conn.prepareStatement(
                        "insert into waluta values (NULL,?,?,?);");
                prepStmt.setString(1, sdate);
                prepStmt.setString(2, me.getKey());
                prepStmt.setString(3, me.getValue());
                prepStmt.execute();
            } catch (SQLException e) {
                System.err.println("Blad przy wstawianiu waluty");
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    StringProperty selectWalutyOkienko() {
        StringProperty temp = new SimpleStringProperty();
        String name, value,data =null;
        String temp2 = "";
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM waluta");
            int id;
            while(result.next()) {
                id = result.getInt("id_waluty");
                data = result.getString("dataw");
                temp2 += data + " ";
                //System.out.print(data+" ");
                name = result.getString("nazwa");
                temp2 += name + "=";
                //System.out.print(name+"=");
                value = result.getString("wartosc");
                temp2 += value + "\n";
                //System.out.println(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        //System.out.println(data);
        temp.set(temp2);
        return temp;
    }

    StringProperty searchWalutyOkienko(String name) {
        StringProperty temp = new SimpleStringProperty();
        String value,data,temp2 = "";
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM `waluta` WHERE `nazwa` LIKE \"%"+name+"%\"\n");
            int id;

            while(result.next()) {
                id = result.getInt("id_waluty");
                data = result.getString("dataw");
                temp2 += data  +" ";
                //System.out.print(data+" ");
                name = result.getString("nazwa");
                temp2 += name+"=";
                //System.out.print(name+"=");
                value = result.getString("wartosc");
                temp2 += value +"\n";
                //System.out.println(value);
                //waluty.put(name,value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        temp.set(temp2);
        return temp;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
}

