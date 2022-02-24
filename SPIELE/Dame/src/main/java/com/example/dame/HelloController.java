package com.example.dame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                System.out.print(field[i][j] + "   ");
            }
            System.out.println("\n");
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


}