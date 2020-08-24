package pl.kawka.appobrona.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MainWindowController { //kontroler glownego pustego okna

    @FXML
    private AnchorPane mainStackPane; //musi byc przypisany fx:id="mainStackPane"

    @FXML
    public void initialize() { //metoda wywolywana zaraz po kontruktorze
        loadMenuScreen();
        // to mozna zakomentowac i wtedy aplikacja startuje z glownym widokiem zadeklarowym w AppObronaClient
    }

    public void loadMenuScreen() { //ladowanie ekranu login
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/LoginWindow.fxml"));

        System.out.println("nic");

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginWindowController loginWindowController = loader.getController(); //wyciagamy referencje
        loginWindowController.setMainWindowController(this);//przekaznie instancji klasy menu kontroller do inyych (beda sie mogly
        // odnosic do niego)

        //teraz do stackpane glownego wrzucamy Pane z opcjami
        setScreen(pane);

    }

    public void setScreen(Pane pane) {
        //wyrzucalo Null exception poniewaz nie mialem przypisanego: fx:id="mainStackPane" w MainWindow.fxml
        mainStackPane.getChildren().clear(); //usuwanie elemntow by kolejne elementy nie naklay sie na siebie po
        // przejsciu na kolejne ekrany
        mainStackPane.getChildren().add(pane); //pobieramy dzieci czyli liste elementow co w sobie zawiera
    }

}
