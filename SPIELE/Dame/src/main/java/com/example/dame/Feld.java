package com.example.dame;

import java.util.Scanner;

public class Feld {
    char[][] field = new char[8][8];
    int x;
    char y;
    int x2;
    char y2;
    char feldhintergrund;
    int spieler = 0;
    char currPlayer;
    char[] steine;
    int anz1 = 0;
    int anz2 = 0;

    Feld(char hintergrund, char[] spielsteine)
    {
        feldhintergrund = hintergrund;
        steine = spielsteine;
    }

    //Die ganze Fläche wird mit Hintergrund gefüllt
    public void fill()
    {
        int z=0;
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                field[i][j] = feldhintergrund;
                z++;
            }
        }
    }

    //gibt Feld aus
    public void output()
    {
        System.out.println();
        System.out.println("    0   1   2   3   4   5   6   7");
        System.out.println("  ---------------------------------");
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                String zeichen = Character.toString((char) i+65);
                if(j==0)
                {
                    System.out.print(zeichen + " | " + field[i][j] + "   ");
                }
                else if (j == 7)
                {
                    System.out.print(field[i][j] + " ");
                }
                else
                {
                    System.out.print(field[i][j] + "   ");
                }

            }
            if(i==7)
            {
                System.out.println("|");
                System.out.println("  ---------------------------------");
            }
            else
            {
                System.out.println("| \n  |                               |");
            }
        }
        System.out.println("Aktueller Spieler: " + currPlayer);
    }

    //Ersetzt Hintergrund mit den Playerstones
    public void placePlayerStones()
    {
        for(int j=0; j<8; j++)
        {
            for(int h=0; h<8; h++)
            {
                if(j<3)
                {
                    //Abwechselnd paltzieren
                    if(j%2 == 1)
                    {
                        if(h%2 == 0)
                        {
                            field[j][h] = steine[0];
                        }
                    } else {
                        if(h%2 == 1)
                        {
                            field[j][h] = steine[0];
                        }
                    }
                    //Für Spieler 2
                } else if ( j>4)
                {
                    if(j%2 == 1)
                    {
                        if(h%2 == 0)
                        {
                            field[j][h] = steine[1];
                        }
                    } else {
                        if(h%2 == 1)
                        {
                            field[j][h] = steine[1];
                        }
                    }
                }

            }
        }
    }

    public void firstMove()
    {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Für einen Wert '9' eingeben zum Neuanfang.");
            System.out.println("\n--------------------------");
            System.out.println("Geben sie den X-Wert eines Steines zum Bewegen ein (0-7): ");
            x = Integer.parseInt(scanner.next());
            if(x!=9)
            {
                System.out.println("Geben sie den Y-Wert eines Steines zum Bewegen ein (A-H): ");
                y = scanner.next().charAt(0);
                System.out.println("--------------------------");

                if(y!=9) {
                    if (!IsViable()) {
                        System.out.println(field[(int) y - 'A'][x]);
                        System.out.println(((int) y - 'A') + " " + x);
                        System.out.print("Ups... Da ist etwas schiefgelaufen...");
                    }
                    if(!IsBackground())
                    {
                        System.out.println("background");
                    }
                    if( !IsPlayer())
                    {
                        System.out.println("Player");
                    }
                }
            }
        } while (IfStop() || !IsViable() || !IsBackground() || !IsPlayer());
        System.out.println(x + " " + ((int)y-'A'));
    }

    public boolean fm()
    {
            if (!IsViable()) {
                System.out.println(field[(int) y - 'A'][x]);
                System.out.println(((int) y - 'A') + " " + x);
                System.out.print("Ups... Da ist etwas schiefgelaufen...");
                return false;
            }
            else if(!IsBackground())
            {
                System.out.println("background");
                return false;
            }
            else if( !IsPlayer())
            {
                System.out.println("Player");
                return false;
            }
            else
            {
                return true;
            }
    }

    public boolean smn()
    {
        if (x2 != x + 1 && x2 != x - 1 || spieler == 0 && y2 != y + 1 || spieler == 1 && y2 != y - 1) {
            System.out.println(x2 + " " + ((int) y2 - 'A'));
            System.out.print("Ups... Da ist etwas schiefgelaufen...");
            return false;
        } else
        {
            return true;
        }
    }

    public boolean smo()
    {
        if (x2 != x + 1 && x2 != x - 1 || spieler == 0 && y2 != y + 1 || spieler == 1 && y2 != y - 1) {
            System.out.println(x2 + " " + ((int) y2 - 'A'));
            System.out.print("Ups... Da ist etwas schiefgelaufen...");
            return false;
        }
        else
        {
            return true;
        }
    }

    public void secondMove()
    {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Für einen Wert '9' eingeben zum Neuanfang.");
            System.out.println("\n--------------------------");
            if(x > 0 && x!=7)
            {
                System.out.println("Geben sie den X-Wert ein wohin der Stein bewegt werden soll (" + (x-1) + "/" + (x+1) + "): ");
            }
            else if(x==0)
            {
                System.out.println("Geben sie den X-Wert ein wohin der Stein bewegt werden soll (" + (x+1) + "): ");
            }
            else if(x==7)
            {
                System.out.println("Geben sie den X-Wert ein wohin der Stein bewegt werden soll (" + (x-1) + "): ");
            }

            x2 = Integer.parseInt(scanner.next());
            if(x2!=9) {
                if (currPlayer == steine[0]) {
                    System.out.println("Geben sie den Y-Wert ein wohin der Stein bewegt werden soll (" + (char) (y + 1) + "): ");
                } else if (currPlayer == steine[1]) {
                    System.out.println("Geben sie den Y-Wert ein wohin der Stein bewegt werden soll (" + (char) (y - 1) + "): ");
                }

                y2 = scanner.next().charAt(0);
                System.out.println("--------------------------");
                if(y2!='9')
                {
                    if (x2 != x + 1 && x2 != x - 1 || spieler == 0 && y2 != y + 1 || spieler == 1 && y2 != y - 1) {
                        System.out.println(x2 + " " + ((int) y2 - 'A'));
                        System.out.print("Ups... Da ist etwas schiefgelaufen...");
                    }
                }

            }
        } while(x2!=x+1 && x2!=x-1 || spieler==0 && y2!=y+1 || spieler==1 && y2!=y-1);
    }


    public void checkOP()
    {
        for(int i=0; i<8; i++)
        {
            if(field[0][i] == steine[1])
            {
                field[0][i] = (char)(steine[1]-32);
            }

            if(field[7][i] == steine[0])
            {
                field[7][i] = (char)(steine[0]-32);
            }
        }
    }

    public boolean IsViable()
    {
        return x > 0 || x < 8 || y < 'H' || y > 'A';
    }

    public boolean IsBackground()
    {
        return field[(int) y - 'A'][x] != feldhintergrund;
    }

    public boolean IsPlayer()
    {
        System.out.println(currPlayer);
        return field[(int) y - 'A'][x] == currPlayer || field[(int) y - 'A'][x] == (char) (currPlayer - 32);
    }

    public boolean IfStop()
    {
        return x == 9 || y == '9' || x2 == 9 || y2 == '9';
    }

    public char[][] getField() {
        return field;
    }

    public int getX() {
        return x;
    }

    public char getY() {
        return y;
    }

    public char getCurrPlayer() {
        return currPlayer;
    }

    public char getFeldhintergrund() {
        return feldhintergrund;
    }

    public char getY2() {
        return y2;
    }

    public char getSteine(int i) {
        return steine[i];
    }

    public int getX2() {
        return x2;
    }

    public int getAnz1() {
        return anz1;
    }

    public int getAnz2() {
        return anz2;
    }

    public int getSpieler() {
        return spieler;
    }

    public char getAt(int x, int y) {return field[y][x];}

    public void setAt(int x, int y, char character) {field[y][x] = character;}

    public void setCurrPlayer()
    {
        if(spieler==0)
        {
            currPlayer = steine[0];

        }
        else if(spieler==1)
        {
            currPlayer = steine[1];
        }
    }

    public void setSpieler()
    {
        if(spieler==0)
        {
            spieler++;
        }
        else if(spieler==1)
        {
            spieler--;
        }
    }
}

