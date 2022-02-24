package com.example.dame;

import javafx.scene.paint.Color;

public class Stein {
    char character;
    Color farbe;

    public char getCharacter() {
        return character;
    }

    public Color getFarbe() {
        return farbe;
    }

    public void setFarbe(Color farbe) {
        this.farbe = farbe;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}
