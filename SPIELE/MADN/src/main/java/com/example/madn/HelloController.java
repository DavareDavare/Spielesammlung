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
        fill();
        house();
        out();
    }

    public void fill(){
        for(int i=0; i<11; i++) {
            for (int j = 0; j < 11; j++) {

                if (i == 0 || i == 1 || i == 2 || i == 3 || i == 7 || i == 8 || i == 9 || i == 10) {
                    if (j == 0 || j == 1 || j == 2 || j == 3 || j == 7 || j == 8 || j == 9 || j == 10) {
                        feld[i][j] = ' ';

                    } else {
                        feld[i][j] = '+';

                    }
                }

                else if (i == 5 && j == 5) {
                    feld[i][j] = ' ';

                } else {
                    feld[i][j] = '+';
                }

            }


        }

    }

    public void house(){
        for(int i=0; i<11; i++) {
            for (int j = 0; j < 11; j++) {
                if(i==1||i==2){
                    if(j==1||j==2){
                        feld[i][j] = 'a';

                    }
                }
                if(i==8||i==9){
                    if(j==1||j==2){
                        feld[i][j] = 'b';

                    }
                }
                if(i==1||i==2||i==8||i==9){
                    if(j==8||j==9){
                        feld[i][j] = '+';

                    }
                }
            }
        }
    }

    public void out(){
        for(int i=0; i<11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(feld[i][j] + "  ");
            }
            System.out.println(" ");
        }
    }

}