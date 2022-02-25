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
        output();
        //play();
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
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

    }

}