package com.example.dame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Scanner;

public class HelloController{
    @FXML
    private GridPane grid;
    char[][] field = new char [8][8];
    int x;
    char y;
    int x2;
    char y2;
    int spieler = 0;
    char currPlayer;
    char currPlayer2;

    public void initialize()
    {
        fill();
        place();
        do{
            if(spieler==0)
            {
                currPlayer = 'x';
                spieler++;
            }
            else if(spieler==1)
            {
                currPlayer = 'o';
                spieler--;
            }
            play();
        }while(true);

    }

    public void fill()
    {
        int z=0;
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                field[i][j] = '*';
                z++;
                grid.add(createButton(z), j, i);
            }
        }
    }

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
        System.out.print("Aktueller Spieler: " + currPlayer);
    }

    public void place()
    {
        for(int j=0; j<8; j++)
        {
            for(int h=0; h<8; h++)
            {
                if(j<3)
                {
                if(j%2 == 1)
                {
                    if(h%2 == 0)
                    {
                        field[j][h] = 'x';
                    }
                } else {
                    if(h%2 == 1)
                    {
                        field[j][h] = 'x';
                    }
                }
                } else if ( j>4)
                {
                    if(j%2 == 1)
                    {
                        if(h%2 == 0)
                        {
                            field[j][h] = 'o';
                        }
                    } else {
                        if(h%2 == 1)
                        {
                            field[j][h] = 'o';
                        }
                    }
                }

            }
        }

    }


    public Button createButton(int z)
    {
        Button button = new Button(Integer.toString(z));
        button.setMaxSize(75, 75);
        button.setOpacity(0);

        button.setOnAction(e -> {
            System.out.println("Button: " + Integer.toString(z));
        });

        return button;
    }

    public void play()
    {
        output();
        inputStart();
        output();
        inputGoal();

        char zeichen = field[(int)y-'A'][x];
        field[(int)y-'A'][x] = '*';
        System.out.println(zeichen);
        field[(int)y2-'A'][x2] = zeichen;
        System.out.println(Integer.toString(x2) + ((int)y2-'A'));
    }


    public void inputStart()
    {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\n--------------------------");
            System.out.println("Geben sie den X-Wert eines Steines zum Bewegen ein (0-7): ");
            x = Integer.parseInt(scanner.next());
            System.out.println("Geben sie den Y-Wert eines Steines zum Bewegen ein (A-H): ");
            y = scanner.next().charAt(0);
            System.out.println("--------------------------");
            if(x <= 0 || x >= 7 || y > 'H' || y < 'A' || field[(int)y-'A'][x] == '*' || field[(int)y-'A'][x] != currPlayer)
            {
                System.out.println(field[x][(int)y-'A']);
                System.out.println(x + " " + ((int)y-'A'));
                System.out.print("Ups... Da ist etwas schiefgelaufen...");
            }

        } while (x < 0 || x > 8 || y > 'H' || y < 'A' || field[(int)y-'A'][x] == '*' || field[(int)y-'A'][x] != currPlayer);
        System.out.println(x + " " + ((int)y-'A'));
    }

    public void inputGoal()
    {
        Scanner scanner = new Scanner(System.in);
        do {
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
                System.out.println("Geben sie den X-Wert ein wohin der Stein bewegt werden soll (" + (x) + "): ");
            }

            x2 = Integer.parseInt(scanner.next());
            if(currPlayer=='x')
            {
                System.out.println("Geben sie den Y-Wert ein wohin der Stein bewegt werden soll (" + (char)(y+1) + "): ");
            }
            else if(currPlayer=='o')
            {
                System.out.println("Geben sie den Y-Wert ein wohin der Stein bewegt werden soll (" + (char)(y-1) + "): ");
            }

            y2 = scanner.next().charAt(0);
            System.out.println("--------------------------");
            if(x2!=x+1 && x2!=x-1 || y2!=y+1 || field[x2][(int)y2-'A'] != '*')
            {
                System.out.println(x2 + " " + ((int)y2-'A'));
                System.out.print("Ups... Da ist etwas schiefgelaufen...");
            }
        } while (x2 != x+1 && x2 != x-1 || y2 != y+1 );
    }

    public boolean possible(int x, int y)
    {
        if(y==0)
        {
            if(field[x-1][y+1] != currPlayer && field[x+1][y+1] != currPlayer)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(y==7)
        {
            if(field[x-1][y+1] != currPlayer && field[x+1][y+1] != currPlayer)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(field[x-1][y+1] != currPlayer && field[x+1][y+1] != currPlayer)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

    }

}