package com.example.madn;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Random;
import java.util.Scanner;

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
        Scanner scanner=new Scanner(System.in);


    public void initialize(){
        fill();
        house();
        end();
        out();
        draw();
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
        String s;
        System.out.println("Drücke irgendeine Taste zum Würfeln!");
        s=scanner.next();
        do {
            zz = random.nextInt(7);
        }while(zz==0);
        System.out.println(zz);
    }


    public void draw(){
        int a=0;
        int zeahlerA=0;

        if(Ahouse==4){
            do {
                rollDice();
                a++;
            }while (zz!=6&&a<10);

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
                Ahouse--;
                zeahlerA=0;
            }
            else{
                System.out.println("KEIN 6");
                out();
                a=0;
                zeahlerA=999;
            }
        }
        else{
            int test=0;
            do {
                rollDice();
                int moving = zz;

                if (zeahlerA == 0) {

                    if (moving < 4) {
                        feld[4][moving] = 'A';
                        feld[4][0] = '+';
                        zeahlerA = zeahlerA + moving;
                    } else {
                        moving = moving - 4;
                        feld[4-moving][4] = 'A';
                        feld[4][0] = '+';
                        zeahlerA = zeahlerA + moving+4;
                    }
                } else if (zeahlerA > 0 && zeahlerA <= 4) {

                    if (zeahlerA + moving <= 4) {
                        feld[4][zeahlerA + moving] = 'A';
                        feld[4][zeahlerA] = '+';
                        zeahlerA = zeahlerA + moving;
                    } else if (zeahlerA+moving > 4 &&zeahlerA+moving<=8) {
                        moving = moving - 4 + zeahlerA;
                        feld[4-moving][4] = 'A';
                        feld[4][zeahlerA] = '+';
                        zeahlerA = zeahlerA + moving;
                    }
                    else if(zeahlerA+moving>8&&zeahlerA+moving<=10){
                        if(zeahlerA+moving==9){
                            feld[0][5]='A';
                            feld[4][zeahlerA]='+';
                            zeahlerA = zeahlerA + moving;
                        }
                        else if(zeahlerA+moving==10){
                            feld[0][6]='A';
                            feld[4][zeahlerA]='+';
                            zeahlerA = zeahlerA + moving;
                        }

                    }
                }
                else if(zeahlerA>4&&zeahlerA<=8){
                    if (zeahlerA + moving <= 8) {
                        feld[4-moving-(zeahlerA-4)][4] = 'A';
                        feld[4-(zeahlerA-4)][4] = '+';
                        zeahlerA = zeahlerA + moving;
                    }
                    else if(zeahlerA+moving>8&&zeahlerA+moving<=10){
                        if(zeahlerA+moving==9){
                            feld[0][5]='A';
                            feld[4-(zeahlerA-4)][4]='+';
                            zeahlerA = zeahlerA + moving;
                        }
                        else if(zeahlerA+moving==10){
                            feld[0][6]='A';
                            feld[4-(zeahlerA-4)][4]='+';
                            zeahlerA = zeahlerA + moving;
                        }

                    }
                    else if(zeahlerA+moving>10&&zeahlerA+moving<=14){
                        feld[zeahlerA-8+(moving-2)][6]='A';
                        feld[4-(zeahlerA-4)][4]='+';
                        zeahlerA = zeahlerA + moving;
                    }

                }
                else if(zeahlerA==9){
                    if(zeahlerA+moving==10){
                        feld[0][6]='A';
                        feld[0][5]='+';
                        zeahlerA = zeahlerA + moving;
                    }
                    else if(zeahlerA+moving>10&&zeahlerA+moving<=14){
                        feld[moving-1][6]='A';
                        feld[0][5]='+';
                        zeahlerA = zeahlerA + moving;
                    }
                    else if(zeahlerA+moving==15){
                        feld[4][7]='A';
                        feld[0][5]='+';
                        zeahlerA = zeahlerA + moving;
                    }


                }
                else if(zeahlerA>=10&&zeahlerA<14){
                    if(zeahlerA+moving<=14){
                        feld[zeahlerA-10+moving][6]='A';
                        feld[zeahlerA-10][6]='+';
                        zeahlerA = zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>14&&zeahlerA+moving<=18){
                       feld[4][6+zeahlerA-14+moving]='A';
                        feld[zeahlerA-10][6]='+';
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving==19){
                        feld[5][10]='A';
                        feld[3][6]='+';
                        zeahlerA=zeahlerA+moving;
                    }
                }
                else if(zeahlerA>=14&&zeahlerA<=18){
                    if(zeahlerA+moving<=18){
                        feld[4][5+zeahlerA-13+moving]='A';
                        feld[4][5+zeahlerA-13]='+';
                        zeahlerA = zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>=18&&zeahlerA+moving<=20){
                        if(zeahlerA+moving==18){
                            feld[10][4]='A';
                        }
                        else if(zeahlerA+moving==19){
                            feld[5][10]='A';
                        }
                        else if(zeahlerA+moving==20){
                            feld[6][10]='A';
                        }
                        feld[4][5+zeahlerA-13]='+';
                        zeahlerA=zeahlerA+moving;
                    }
                    else if(zeahlerA+moving>20&&zeahlerA+moving<=24){
                        feld[6][10-(zeahlerA-18+moving-2)]='A';
                        feld[4][5+zeahlerA-13]='+';
                        zeahlerA=zeahlerA+moving;
                    }

                }
                else if(zeahlerA>18&&zeahlerA<=20){
                     if(zeahlerA+moving==20){
                        feld[6][10]='A';
                        feld[5][10]='+';
                         zeahlerA=zeahlerA+moving;
                    }
                     else if(zeahlerA+moving>20&&zeahlerA+moving<=24){
                         feld[6][10-moving+(20-zeahlerA)]='A';
                         feld[4+(zeahlerA-18)][10]='+';
                         zeahlerA=zeahlerA+moving;
                     }
                     else if(zeahlerA+moving>24){
                         feld[4+(moving-4)][6]='A';
                         feld[4+(zeahlerA-18)][10]='+';
                         zeahlerA=zeahlerA+moving;
                     }
                }
                else if(zeahlerA>20&&zeahlerA<=24){
                    if(zeahlerA+moving<=24){

                    }else if(zeahlerA+moving>24&&zeahlerA+moving<=28){
                        
                    }
                    else if(zeahlerA+moving>28){

                    }
                }








                out();

                test++;
            }while(test<10);
        }
    }

}