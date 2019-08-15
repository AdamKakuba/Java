package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    private Map< String,String> waluty = new HashMap<>();
    private JsoupTest jsoupTest = new JsoupTest();
    private bazkadanych baza = new bazkadanych();

    @Override
    public void start(Stage primaryStage) throws RuntimeException {

        jsoupTest.pobierz(waluty);

        Button b1 = new Button("Aktualizuj");
        Button b2 = new Button("Dodaj walute");
        Button b3 = new Button("Znajdz walute");
        Button b4 = new Button("Dodaj waluty do bazy danych");
        Button b5 = new Button("Wyswietl waluty z bazy danych");
        Button b6 = new Button("Znajdz walute w bazie danych");
        Button b7 = new Button("Wyjdz");

        TextArea text = new TextArea();
        TextField text1 = new TextField();
        TextField text2 = new TextField();
        TextField text3 = new TextField();
        TextField text7 = new TextField();
        Text text4 = new Text("Wpisz nazwe waluty do dodania:");
        Text text8 = new Text("Wpisz wartosc waluty do dodania:");
        Text text5 = new Text("Wpisz walute do znalezienia:");
        Text text6 = new Text("Wpisz walute do znalezienia:");
        text.setMinHeight(250);
        text.setMaxWidth(200);
        text1.setMaxWidth(100);
        text2.setMaxWidth(100);
        text3.setMaxWidth(100);
        text7.setMaxWidth(100);

        b1.setOnAction((event -> {
            jsoupTest.pobierz(waluty);
            text.textProperty().bind(jsoupTest.wyswietl(waluty));
        }));

        b2.setOnAction((event -> {
           jsoupTest.insert(text7.getText(),text1.getText(),waluty);
           text7.clear();
           text1.clear();
        }));

        b3.setOnAction((event -> {
            text.textProperty().bind(jsoupTest.search(text2.getText(),waluty));
            text2.clear();
        }));

        b4.setOnAction((event -> {
            baza.insertWaluty(waluty);
            StringProperty temp = new SimpleStringProperty();
            temp.set("Baza została zaaktualizowana!");
            text.textProperty().bind(temp);
            waluty.clear();
        }));

        b5.setOnAction((event -> text.textProperty().bind(baza.selectWalutyOkienko())));

        b6.setOnAction((event -> {
            text.textProperty().bind(baza.searchWalutyOkienko(text3.getText()));
            text3.clear();
        }));

        b7.setOnAction((event -> {
            baza.closeConnection();
            System.exit(0);
        }));
        FlowPane root = new FlowPane();
        root.setVgap(6);
        root.setOrientation(Orientation.VERTICAL);
        root.setColumnHalignment(HPos.CENTER);
        root.getChildren().addAll(b1,text4,text7,text8,text1,b2,text5,text2,b3,b4,b5,text6,text3,b6,b7,text);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(800);
        primaryStage.setMaxWidth(210);
        primaryStage.setTitle("");
        primaryStage.show();


    }

    public static void main(String[] args){
        launch(args);
    }
}