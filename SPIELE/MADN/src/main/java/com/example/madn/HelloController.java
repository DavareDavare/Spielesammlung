package com.example.madn;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Random;

public class HelloController {

        @FXML
        Pane outerPane;
        @FXML
        GridPane fieldGridpane;

        char [][]feld=new char[11][11];
        int zz = 0;
        Random random = new Random();
        int Ahouse=4;
        int Bhouse=4;
        int Chouse=4;
        int Dhouse=4;


    public void initialize(){
        fill();
        house();
        end();
        out();
        draw();
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
                        feld[i][j] = 'A';

                    }
                }
                if(i==8||i==9){
                    if(j==1||j==2){
                        feld[i][j] = 'B';

                    }
                }
                if(i==1||i==2){
                    if(j==8||j==9){
                        feld[i][j] = 'D';

                    }
                }
                if(i==8||i==9){
                    if(j==8||j==9){
                        feld[i][j] = 'C';

                    }
                }
            }
        }
    }

    public void end(){
        for(int i=0; i<11; i++) {
            for (int j = 0; j < 11; j++) {
                if(i==5){
                    if(j==1||j==2||j==3||j==4){
                        feld[i][j] = 'a';

                    }
                }
                if(i==5){
                    if(j==6||j==7||j==8||j==9){
                        feld[i][j] = 'c';

                    }
                }
                if(j==5){
                    if(i==1||i==2||i==3||i==4){
                        feld[i][j] = 'd';

                    }
                }
                if(j==5){
                    if(i==6||i==7||i==8||i==9){
                        feld[i][j] = 'c';

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

    public void rollDice(){
        do {
            zz = random.nextInt(7);
        }while(zz==0);
        System.out.println(zz);
    }


    public void draw(){
        int a=0;

        if(Ahouse==4){
            do {
                rollDice();
                a++;
            }while (zz!=6&&a<3);

            if(zz==6){
                for(int i=0; i<11; i++) {
                    for (int j = 0; j < 11; j++) {
                        if(j==0&&i==4){
                            feld[i][j]='A';
                        }
                        if(j==2&&i==2){
                            feld[i][j]='+';
                        }
                    }
                }
                out();
                a=0;
            }
            else{
                System.out.println("KEIN 6");
                out();
                a=0;
            }
        }
    }

}