package com.example.dame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Scanner;

public class HelloController{
    @FXML
    private GridPane grid;
    char[][] field = new char [8][8];

    public void initialize()
    {
        fill();
        place();
        play();
        output();
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
        System.out.println("    1   2   3   4   5   6   7   8");
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
                System.out.print("  ---------------------------------");
            }
            else
            {
                System.out.println("| \n  |                               |");
            }
        }
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
                        field[j][h] = 'X';
                    }
                } else {
                    if(h%2 == 1)
                    {
                        field[j][h] = 'X';
                    }
                }
                } else if ( j>4)
                {
                    if(j%2 == 1)
                    {
                        if(h%2 == 0)
                        {
                            field[j][h] = 'O';
                        }
                    } else {
                        if(h%2 == 1)
                        {
                            field[j][h] = 'O';
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
        int x;
        char y;
        int x2;
        char y2;
        output();
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
                System.out.println("Geben sie den X-Wert ein wohin der Stein bewegt werden soll (" + (x-1) + "): ");
            }

            x2 = Integer.parseInt(scanner.next());
            System.out.println("Geben sie den Y-Wert ein wohin der Stein bewegt werden soll (" + (char)(y+1) + "): ");
            y2 = scanner.next().charAt(0);
            System.out.println("--------------------------");
            if(x2!=x+1 && x2!=x-1 || y2!=y+1 || field[x2][(int)y2-'A'] != '*')
            {
                System.out.print("Ups... Da ist etwas schiefgelaufen...");
            }
        } while (x2 != x+1 && x2 != x-1 || y2 != y+1 );
        System.out.println(x2 + " " + ((int)y2-'A'));
        char zeichen = field[x][(int)y-'A'];
        field[x][(int)y-64] = '*';
        field[x2][(int)y2-64] = zeichen;
        System.out.println(Integer.toString(x2) + ((int)y2-'A'));
    }


    public int inputx()
    {
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;
        do {
            System.out.println("\n--------------------------");
            System.out.println("Geben sie den X-Wert eines Steines zum Bewegen ein (1-8): ");
            x = Integer.parseInt(scanner.next());
            System.out.println("Geben sie den Y-Wert eines Steines zum Bewegen ein (A-H): ");
            y = scanner.next().charAt(0);
            System.out.println("--------------------------");
            if(x < 0 || x > 8 || y > 'H' || y < 'A' || field[x][(int)y-'A'] != '*')
            {
                System.out.print("Ups... Da ist etwas schiefgelaufen...");
            }

        } while (x < 0 || x > 8 || y > 'H' || y < 'A' || field[x][(int)y-'A'] != '*');
        x--;
        System.out.println(x + " " + ((int)y-'A'));
    }

}