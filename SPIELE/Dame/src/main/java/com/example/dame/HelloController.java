package com.example.dame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HelloController {
    @FXML
    private GridPane grid;
    public void initialize()
    {
        int z=0;
        for(int i=0; i<10; i++)
        {
            for(int j=0; j<10; j++)
            {
                z++;

                grid.add(createButton(z), i, j);
            }
        }
    }


    public Button createButton(int z)
    {
        Button button = new Button(Integer.toString(z));
        button.setMaxSize(75, 75);

        button.setOnAction(e -> {
            System.out.println("Button: " + Integer.toString(z));
        });

        return button;
    }


}