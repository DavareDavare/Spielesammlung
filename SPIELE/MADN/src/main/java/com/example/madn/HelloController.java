package com.example.madn;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.util.Random;
import java.util.Scanner;


public class HelloController {

    @FXML
    Pane outerPane;


    public void initialize() {

        FeldMaDn spielfeld = new FeldMaDn('+', ' ', ' ');
        spielfeld.fillField();
        spielfeld.fillHome();
        spielfeld.fillEnd();
   //     spielfeld.testThrow();
        spielfeld.outField();
   //     spielfeld.begin();

    }

}

//  Es werden nur die Funktionen getestet
//  Alles GUI-mäßige würde noch kommen, hier nur testweise zum Fahren