package com.example.madn;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class HelloController {

        @FXML
        Pane outerPane;
        @FXML
        GridPane fieldGridpane;

        char [][]feld=new char[11][11];


    public void initialize(){
        for(int i=0; i<11; i++) {
            for (int j = 0; j < 11; j++) {
                /*
                 if (i == 0 || i == 1 || i == 2 || i == 3 || i == 7 || i == 8 || i == 9 || i == 10) {
                    if (j == 0 || j == 1 || j == 2 || j == 3 || j == 7 || j == 8 || j == 9 || j == 10) {
                        feld[i][j] = ' ';
                        System.out.print(feld[i][j] + "  ");
                    } else {
                        feld[i][j] = 'X';
                        System.out.print(feld[i][j] + "  ");
                    }
                }

                else if (i == 5 && j == 5) {
                    feld[i][j] = ' ';
                    System.out.print(feld[i][j] + "  ");
                } else {
                    feld[i][j] = 'X';
                    System.out.print(feld[i][j] + "  ");
                }*/

                if(i==0||i==3||i==7||i==10){
                    if(j==4||j==5||j==6){
                        feld[i][j] = 'X';
                        System.out.print(feld[i][j] + "  ");
                    }
                    else {
                        feld[i][j] = ' ';
                        System.out.print(feld[i][j] + "  ");
                    }
                }
                if(i==1||i==2||i==8||i==9){
                    if(j==1||j==2||j==8||i==9){
                        feld[i][j] = 'X';
                        System.out.print(feld[i][j] + "  ");
                    }
                    else {
                        feld[i][j] = ' ';
                        System.out.print(feld[i][j] + "  ");
                    }
                }
                if(i==4||i==5||i==6){
                    feld[i][j] = 'X';
                    System.out.print(feld[i][j] + "  ");
                }
                if(i==5&&j==5){
                    feld[i][j] = ' ';
                    System.out.print(feld[i][j] + "  ");
                }




            }
            System.out.println(" ");
        }

    }

}