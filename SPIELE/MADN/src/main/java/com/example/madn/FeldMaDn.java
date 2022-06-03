package com.example.madn;
import java.util.Random;
import java.util.Scanner;

public class FeldMaDn extends Feld {
    
    //Standard-Chars für das gesamte Feld
    private char markMain;
    private char markHome;
    private char markEnd;

    char[][] feld = new char[11][11];
    Scanner scanner = new Scanner(System.in);
    int zz = 0;
    Random random = new Random();

    //Zwei Spieler werden eingefügt, sie starten gegenüber von einander (Start bei 0 und 20)
    Spieler spieler1 = new Spieler('A', 0);
    Spieler spieler2 = new Spieler('B', 20);

    //Es wird mitgezählt, wie weit man schon gegangen ist
    protected static int zeahlerA;

    boolean win = false;
    private int grenzeA = 43;
    private int grenzeB = 63;

    protected int x;
    protected int y;
    boolean needtomove = false;


    //Feld wird erstellt
    public FeldMaDn(char markM, char markH, char markE) {
        markMain = markM;
        markHome = markH;
        markEnd = markE;
    }


    //____Vorbereitung______________________________________________________

    //Grundfeld wird erstellt
    public void fillField() {

        spieler1.setStoneHome(4);
        spieler1.setStoneIn(0);
        spieler2.setStoneHome(4);
        spieler2.setStoneIn(0);

            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 11; j++) {
                    if (i == 0 || i == 1 || i == 2 || i == 3 || i == 7 || i == 8 || i == 9 || i == 10) {
                        if (j == 0 || j == 1 || j == 2 || j == 3 || j == 7 || j == 8 || j == 9 || j == 10) {
                            feld[i][j] = ' ';
                        } else {
                            feld[i][j] = markMain;
                        }
                    } else if (i == 5 && j == 5) {
                        feld[i][j] = ' ';
                    } else {
                        feld[i][j] = markMain;
                    }

                }
            }
    }

    //Die 4 Zuhause-Felder je Spieler bekommen die Namen des jeweiligen Spielers
    public void fillHome() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 1 || i == 2) {
                    if (j == 1 || j == 2) {
                        markHome = 'A';
                        feld[i][j] = markHome;
                    }
                }

                if (i == 8 || i == 9) {
                    if (j == 8 || j == 9) {
                        markHome = 'B';
                        feld[i][j] = markHome;
                    }
                }
            }
        }
    }

    //Die Enden für beide Spieler bekommen den Namen als Kleinbuchstaben
    public void fillEnd() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 5) {
                    if (j == 1 || j == 2 || j == 3 || j == 4) {
                        markEnd = 'a';
                        feld[i][j] = markEnd;
                    }
                }
                if (i == 5) {
                    if (j == 6 || j == 7 || j == 8 || j == 9) {
                        markEnd = 'b';
                        feld[i][j] = markEnd;
                    }
                }
                if (j == 5) {
                    if (i == 1 || i == 2 || i == 3 || i == 4) {
                        feld[i][j] = ' ';
                    }
                }
                if (j == 5) {
                    if (i == 6 || i == 7 || i == 8 || i == 9) {
                        feld[i][j] = ' ';
                    }
                }
            }
        }
    }

    //Platziert einen "Stein B" am Weg von Spieler A um Schmeißen zu testen
    public void testThrow(){
        feld[4][4]='B';
        spieler2.setStoneIn(1);
        spieler2.setStoneHome(3);
        feld[8][8]=markMain;
        spieler2.setZeahlB(4);
    }


    //Ausgabe des ganzen Felds
    public void outField() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(feld[i][j] + "  ");
            }
            System.out.println(" ");
        }
    }

    //Es wird nach einem Gewinner geschaut
    public void lookWin() {
        if(grenzeA==39){
            System.out.println("Spieler1 hat gewonnen - Glueckwunsch!");
            win=true;
        }
        else if(grenzeB==59){
            System.out.println("Spieler2 hat gewonnen - Glueckwunsch!");
            win=true;
        }
    }


    //____Spielablauf_____________________________________________________________________________________________--


    //Spiel wird begonnen - Abwechselndes Ziehen passiert hier; beendet wird bei Gewinn
    public void begin() {
        int beginningPlayer = random.nextInt(100);

        for(;win!=true; beginningPlayer++){
            System.out.println("Spieler A: ");
            draw(spieler1);
        /*        if(beginningPlayer%2==0){
                    System.out.println("Spieler A: ");
                    draw(spieler1);
                   }
                  else if(beginningPlayer%2==1){
                    System.out.println("Spieler B: ");
                    draw(spieler2);
                  }
        */
        }

    }

    //Allgemeine Funktion um zu Würfeln
    public void rollDice() {

        String s;
        System.out.println("Drücke irgendeine Taste zum Würfeln!");
        s = scanner.next();

            do {
                zz = random.nextInt(7);
            } while (zz == 0);

        System.out.println(zz);
    }


    //Der mitgegebene Spieler macht den Zug
    public void draw(Spieler s) {
        int a = 0;
        int Ahouse = s.getStoneHome();

        //Falls das Haus voll ist wird 3-mal gewürfelt, bei 6 wird ausgefahren sonst garnichts
        if (Ahouse == 4) {
            do {
                rollDice();
                a++;
            } while (zz != 6 && a < 3);

            if(zz==6) {
                switch (s.getStartPosition()) {
                    case 0:
                        feld[4][0] = s.getPlayerInitial();
                        feld[2][2] = markMain;
                        zeahlerA=0;
                        s.setZeahlA(0);
                    break;
                    case 20:
                        feld[6][10] = s.getPlayerInitial();
                        feld[8][8] = markMain;
                        zeahlerA=20;
                        s.setZeahlA(20);
                    break;

                }

                outField();
                a = 0;
                s.stoneIn++;
                s.stoneHome--;
            }

            else {
                System.out.println("KEIN 6");
                outField();
                a = 0;
            }
        }

        //Falls bereits ein Stein im Umlauf ist
        else{
            rollDice();

                if (zz == 6 && s.getZeahlA() != 0) {

                    //Es wird getestet ob schon ein Spieler in der Ausfahrt steht
                    switch (s.getPlayerInitial()){
                        case 'A':
                            if(feld[4][0]==s.getPlayerInitial()){
                                kill(s, s);
                                System.out.println("Spieler1 hat sich selbst gekillt :O");
                            }
                        break;

                        case 'B':
                            if(feld[6][10]==s.getPlayerInitial()){
                                kill(s, s);
                                System.out.println("Spieler2 hat sich selbst gekillt :O");
                            }
                            break;
                    }

                    //Der jeweilige Stein wird in die Ausfahrt gestellt
                    switch (s.getStartPosition()) {
                        case 0:
                            feld[4][0] = s.getPlayerInitial();
                                switch (s.getStoneHome()) {
                                    case 3:
                                        feld[2][1] = markMain;
                                    break;
                                    case 2:
                                        feld[1][2] = markMain;
                                    break;
                                    case 1:
                                        feld[1][1] = markMain;
                                    break;
                                }
                            s.stoneIn++;
                            s.stoneHome--;

                        break;

                        case 20:
                            feld[6][10] = s.getPlayerInitial();
                                switch (s.getStoneHome()) {
                                    case 3:
                                        feld[8][9] = markMain;
                                    break;
                                    case 2:
                                        feld[9][8] = markMain;
                                    break;
                                    case 1:
                                        feld[9][9] = markMain;
                                    break;
                                }

                            s.stoneIn++;
                            s.stoneHome--;
                            needtomove = true;
                    }
                }

                //Falls normal gewürfelt wurde
                else {

                    int moving = zz;

                    if (zeahlerA == 0) {
                        if (moving < 4) {
                            feld[4][moving] = s.getPlayerInitial();
                            feld[4][0] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else {
                            moving = moving - 4;
                            feld[4 - moving][4] = s.getPlayerInitial();
                            feld[4][0] = markMain;
                            zeahlerA = zeahlerA + moving + 4;
                        }
                    }
                    //____
                    else if (zeahlerA > 0 && zeahlerA <= 4) {

                        if (zeahlerA + moving <= 4) {
                            feld[4][zeahlerA + moving] = s.getPlayerInitial();
                            feld[4][zeahlerA] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > 4 && zeahlerA + moving <= 8) {
                            moving = moving - 4 + zeahlerA;
                            feld[4 - moving][4] = s.getPlayerInitial();
                            feld[4][zeahlerA] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > 8 && zeahlerA + moving <= 10) {
                            if (zeahlerA + moving == 9) {
                                feld[0][5] = s.getPlayerInitial();
                                feld[4][zeahlerA] = markMain;
                                zeahlerA = zeahlerA + moving;
                            }
                            else if (zeahlerA + moving == 10) {
                                feld[0][6] = s.getPlayerInitial();
                                feld[4][zeahlerA] = markMain;
                                zeahlerA = zeahlerA + moving;
                            }

                        }
                    }
                    //____
                    else if (zeahlerA > 4 && zeahlerA <= 8) {
                        if (zeahlerA + moving <= 8) {
                            feld[4 - moving - (zeahlerA - 4)][4] = s.getPlayerInitial();
                            feld[4 - (zeahlerA - 4)][4] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > 8 && zeahlerA + moving <= 10) {
                            if (zeahlerA + moving == 9) {
                                feld[0][5] = s.getPlayerInitial();
                                feld[4 - (zeahlerA - 4)][4] = markMain;
                                zeahlerA = zeahlerA + moving;
                            }
                            else if (zeahlerA + moving == 10) {
                                feld[0][6] = s.getPlayerInitial();
                                feld[4 - (zeahlerA - 4)][4] = markMain;
                                zeahlerA = zeahlerA + moving;
                            }

                        }
                        else if (zeahlerA + moving > 10 && zeahlerA + moving <= 14) {
                            feld[zeahlerA - 8 + (moving - 2)][6] = s.getPlayerInitial();
                            feld[4 - (zeahlerA - 4)][4] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }

                    }
                    //____
                    else if (zeahlerA == 9) {
                        if (zeahlerA + moving == 10) {
                            feld[0][6] = s.getPlayerInitial();
                            feld[0][5] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > 10 && zeahlerA + moving <= 14) {
                            feld[moving - 1][6] = s.getPlayerInitial();
                            feld[0][5] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving == 15) {
                            feld[4][7] = s.getPlayerInitial();
                            feld[0][5] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }

                    }
                    //____
                    else if (zeahlerA >= 10 && zeahlerA < 14) {
                        if (zeahlerA + moving <= 14) {
                            feld[zeahlerA - 10 + moving][6] = s.getPlayerInitial();
                            feld[zeahlerA - 10][6] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > 14 && zeahlerA + moving <= 18) {
                            feld[4][6 + zeahlerA - 14 + moving] = s.getPlayerInitial();
                            feld[zeahlerA - 10][6] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving == 19) {
                            feld[5][10] = s.getPlayerInitial();
                            feld[3][6] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                    }
                    //____
                    else if (zeahlerA >= 14 && zeahlerA <= 18) {
                        if (s.getPlayerInitial() == 'B' && zeahlerA + moving <= 20) {
                            int in = (zeahlerA + moving) - 20;
                            feld[5][9 - in] = s.getPlayerInitial();
                            feld[4][5 + (zeahlerA - 14)] = markMain;
                            zeahlerA = in + 60;
                        }
                        else if (zeahlerA + moving <= 18) {
                            feld[4][5 + zeahlerA - 13 + moving] = s.getPlayerInitial();
                            feld[4][5 + zeahlerA - 13] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving >= 18 && zeahlerA + moving <= 20) {
                            if (zeahlerA + moving == 18) {
                                feld[10][4] = s.getPlayerInitial();
                            } else if (zeahlerA + moving == 19) {
                                feld[5][10] = s.getPlayerInitial();
                            } else if (zeahlerA + moving == 20) {
                                feld[6][10] = s.getPlayerInitial();
                            }
                            feld[4][5 + zeahlerA - 13] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > 20 && zeahlerA + moving <= 24) {
                            feld[6][10 - (zeahlerA - 18 + moving - 2)] = s.getPlayerInitial();
                            feld[4][5 + zeahlerA - 13] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }

                    }
                    //____
                    else if (zeahlerA > 18 && zeahlerA <= 20) {
                        if (zeahlerA + moving == 20) {
                            feld[6][10] = s.getPlayerInitial();
                            feld[5][10] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > 20 && zeahlerA + moving <= 24) {
                            feld[6][10 - moving + (20 - zeahlerA)] = s.getPlayerInitial();
                            feld[4 + (zeahlerA - 18)][10] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > 24) {
                            feld[4 + (moving - 4)][6] = s.getPlayerInitial();
                            feld[4 + (zeahlerA - 18)][10] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                    }
                    //____
                    else if (zeahlerA > 20 && zeahlerA <= 24) {
                        if (zeahlerA + moving <= 24) {
                            feld[6][10 - (zeahlerA - 20 + moving)] = s.getPlayerInitial();
                            feld[6][10 - (zeahlerA - 20)] = markMain;
                            zeahlerA = zeahlerA + moving;

                        }
                        else if (zeahlerA + moving > 24 && zeahlerA + moving <= 28) {
                            feld[6 + (moving - (24 - zeahlerA))][6] = s.getPlayerInitial();
                            feld[6][10 - (zeahlerA - 20)] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > 28) {
                            if (zeahlerA + moving == 29) {
                                feld[10][5] = s.getPlayerInitial();
                                feld[6][10 - (zeahlerA - 20)] = markMain;
                                zeahlerA = zeahlerA + moving;
                            } else if (zeahlerA + moving == 30) {
                                feld[10][4] = s.getPlayerInitial();
                                feld[6][10 - (zeahlerA - 20)] = markMain;
                                zeahlerA = zeahlerA + moving;
                            }
                        }
                    }
                    //____
                    else if (zeahlerA > 24 && zeahlerA <= 28) {
                        if (zeahlerA + moving <= 28) {
                            feld[6 + (zeahlerA - 24 + moving)][6] = s.getPlayerInitial();
                            feld[6][6 + (zeahlerA - 24)] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving == 29) {
                            feld[10][5] = s.getPlayerInitial();
                            feld[6][6 + (zeahlerA - 24)] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving >= 30 && zeahlerA + moving <= 34) {
                            feld[10 - (moving - (30 - zeahlerA))][4] = s.getPlayerInitial();
                            feld[6 + (zeahlerA - 24)][6] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                    }
                    //____
                    else if (zeahlerA == 29 || zeahlerA == 30) {
                        if (zeahlerA + moving == 30) {
                            feld[10][4] = s.getPlayerInitial();
                        }
                        else if (zeahlerA + moving > 30 && zeahlerA + moving <= 34) {
                            if (zeahlerA == 29) {
                                feld[10 - (moving - (30 - zeahlerA))][4] = s.getPlayerInitial();
                                feld[10][5] = markMain;
                                zeahlerA = zeahlerA + moving;
                            } else if (zeahlerA == 30) {
                                feld[10 - (moving - (30 - zeahlerA))][4] = s.getPlayerInitial();
                                feld[10][4] = markMain;
                                zeahlerA = zeahlerA + moving;
                            }
                        }
                        else if (zeahlerA + moving == 35) {
                            if (zeahlerA == 29) {
                                feld[6][3] = s.getPlayerInitial();
                                feld[10][5] = markMain;
                                zeahlerA = zeahlerA + moving;
                            } else if (zeahlerA == 30) {
                                feld[6][3] = s.getPlayerInitial();
                                feld[10][4] = markMain;
                                zeahlerA = zeahlerA + moving;
                            }

                        }
                        else if (zeahlerA + moving == 36) {
                            if (zeahlerA == 29) {
                                feld[6][2] = s.getPlayerInitial();
                                feld[10][5] = markMain;
                                zeahlerA = zeahlerA + moving;
                            } else if (zeahlerA == 30) {
                                feld[6][2] = s.getPlayerInitial();
                                feld[10][4] = markMain;
                                zeahlerA = zeahlerA + moving;
                            }

                        }

                    }
                    //____
                    else if (zeahlerA > 30 && zeahlerA <= 34) {
                        if (zeahlerA + moving <= 34) {
                            feld[10 - (moving - (30 - zeahlerA))][4] = s.getPlayerInitial();
                            feld[6 + (34 - zeahlerA)][4] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > 34 && zeahlerA + moving <= 38) {
                            feld[6][4 - (moving - (34 - zeahlerA))] = s.getPlayerInitial();
                            feld[6 + (34 - zeahlerA)][4] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving == 39) {
                            feld[5][0] = s.getPlayerInitial();
                            feld[6 + (34 - zeahlerA)][4] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving >= 40 && zeahlerA + moving <= 43) {
                            if (s.getPlayerInitial() == 'A') {
                                feld[1 + (zeahlerA + moving - 40)][5] = s.getPlayerInitial();
                                feld[4][6] = markMain;
                                zeahlerA = zeahlerA + moving;
                            } else if (s.getPlayerInitial() == 'B') {
                                feld[0][4] = s.getPlayerInitial();
                                feld[4][6] = markMain;
                                zeahlerA = zeahlerA + moving;
                                zeahlerA = zeahlerA - 40;
                            }
                        }

                    }
                    //____
                    else if (zeahlerA > 34 && zeahlerA <= 38) {
                        if (zeahlerA + moving <= 38) {
                            feld[6][4 - (moving - (34 - zeahlerA))] = s.getPlayerInitial();
                            feld[6][38 - zeahlerA] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving == 39) {
                            feld[5][0] = s.getPlayerInitial();
                            feld[6][38 - zeahlerA] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving >= 40 && zeahlerA + moving <= 43) {
                            if (s.getPlayerInitial() == 'A') {
                                feld[5][moving - (39 - zeahlerA)] = s.getPlayerInitial();
                                feld[6][38 - zeahlerA] = markMain;
                                zeahlerA = zeahlerA + moving;
                            } else if (s.getPlayerInitial() == 'B') {
                                feld[6][38 - zeahlerA] = markMain;
                                zeahlerA = zeahlerA + moving;
                                zeahlerA = zeahlerA - 40;
                                feld[4][zeahlerA] = s.getPlayerInitial();

                            }
                        }

                    }
                    //____
                    else if (zeahlerA == 39) {
                        if (zeahlerA + moving >= 40 && zeahlerA + moving <= 43) {
                            if (s.getPlayerInitial() == 'A') {
                                feld[5][moving] = s.getPlayerInitial();
                                feld[5][0] = markMain;
                                zeahlerA = zeahlerA + moving;
                            } else if (s.getPlayerInitial() == 'B') {
                                feld[5][0] = markMain;
                                zeahlerA = zeahlerA + moving;
                                zeahlerA = zeahlerA - 40;
                                feld[4][zeahlerA] = s.getPlayerInitial();
                            }
                        }
                        else if (zeahlerA + moving >= 44 && s.getPlayerInitial() == 'B') {
                            feld[5][0] = markMain;
                            zeahlerA = zeahlerA + moving;
                            zeahlerA = zeahlerA - 40;
                            feld[4][zeahlerA] = s.getPlayerInitial();
                        }
                        else if (zeahlerA + moving == 45 && s.getPlayerInitial() == 'B') {
                            feld[5][0] = markMain;
                            zeahlerA = zeahlerA + moving;
                            zeahlerA = zeahlerA - 40;
                            feld[3][4] = s.getPlayerInitial();
                        }

                    }
                    //____
                    else if ((zeahlerA >= 40 && zeahlerA <= grenzeA) && s.getPlayerInitial() == 'A') {
                        if (zeahlerA + moving < grenzeA) {
                            feld[5][zeahlerA - 39 + moving] = s.getPlayerInitial();
                            feld[5][zeahlerA - 39] = markMain;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > grenzeA) {
                            System.out.println("Zahl zu gross");
                        }
                        else if (zeahlerA + moving == grenzeA) {

                            switch (grenzeA-40){
                                case 3: feld[5][4] = s.getPlayerInitial(); break;
                                case 2: feld[5][3] = s.getPlayerInitial(); break;
                                case 1: feld[5][2] = s.getPlayerInitial(); break;
                                case 0: feld[5][1] = s.getPlayerInitial(); break;
                            }

                            feld[5][zeahlerA - 39] = markMain;
                            zeahlerA=0;
                            grenzeA--;
                        }
                    }
                    //____
                    else if (zeahlerA >= 60 && s.getPlayerInitial() == 'B') {
                        int temp = zeahlerA - 60;

                        if (zeahlerA + moving < grenzeB) {
                            feld[5][9 - (temp + moving)] = s.getPlayerInitial();
                            feld[5][9 - temp] = markEnd;
                            zeahlerA = zeahlerA + moving;
                        }
                        else if (zeahlerA + moving > grenzeB) {
                            System.out.println("Zahl zu gross");
                        }
                        else if (zeahlerA + moving == grenzeB) {

                            switch (grenzeB-60){
                                case 3: feld[5][6] = s.getPlayerInitial(); break;
                                case 2: feld[5][7] = s.getPlayerInitial(); break;
                                case 1: feld[5][8] = s.getPlayerInitial(); break;
                                case 0: feld[5][9] = s.getPlayerInitial(); break;
                            }

                            feld[5][9 - temp] = markEnd;
                            zeahlerA=20;
                            grenzeB--;
                        }
                    }

                //Zähler wird für den jeweiligen Spieler erneuert
                if (s.getPlayerInitial() == 'A') {
                    s.setZeahlA(zeahlerA);
                }
                else if (s.getPlayerInitial() == 'B') {
                    s.setZeahlB(zeahlerA);
                }

                //Falls jemand geschmissen wurde, wird die dazugehörige Funktion aufgerufen
                switch (s.getPlayerInitial()) {
                    case 'A':
                        if (spieler1.getZeahlA() == spieler2.getZeahlB() && spieler1.getZeahlA() != 0) {
                            kill(spieler1, spieler2);
                            System.out.println("Spieler 1 hat Spieler 2 gekillt :O");
                        }
                    break;
                    case 'B':
                        if (spieler2.getZeahlA() == spieler1.getZeahlB() && spieler1.getZeahlB() != 0) {
                            kill(spieler2, spieler1);
                            System.out.println("Spieler 2 hat Spieler 1 gekillt :O");
                        }

                    break;
                }

                }

                //Feld wird ausgegeben und auf einen Win geprüft
                outField();
                lookWin();

           }
    }


    //Funktion zum Schmeißen
    public void kill(Spieler thrower, Spieler beingThrown){
        thrower.allThrows++;

        if(beingThrown.getPlayerInitial()=='B') {
            switch (beingThrown.getStoneIn()) {
                case 1:
                    feld[8][8] = beingThrown.getPlayerInitial();
                break;
                case 2:
                    feld[8][9] = beingThrown.getPlayerInitial();
                break;
                case 3:
                    feld[9][8] = beingThrown.getPlayerInitial();
                break;
                case 4:
                    feld[9][9] = beingThrown.getPlayerInitial();
                break;
            }
        }

        //Stein wird zurückgelegt
        if(beingThrown.getPlayerInitial()=='A') {
            switch (beingThrown.getStoneIn()) {
                case 1:
                    feld[2][2] = beingThrown.getPlayerInitial();
                    break;
                case 2:
                    feld[2][1] = beingThrown.getPlayerInitial();
                    break;
                case 3:
                    feld[1][2] = beingThrown.getPlayerInitial();
                    break;
                case 4:
                    feld[1][1] = beingThrown.getPlayerInitial();
                    break;
            }
        }

        beingThrown.stoneHome++;
        beingThrown.stoneIn--;


    }

}
