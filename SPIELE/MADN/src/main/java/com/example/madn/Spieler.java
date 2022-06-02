package com.example.madn;
import java.util.Random;
import java.util.Scanner;


public class Spieler {

    protected char playerInitial;
    protected int startPosition;
    private boolean hasWon;
    protected int stoneIn;
    protected int stoneHome;
    protected int stoneEnd;
    protected static int ZeahlA;
    protected static int ZeahlB;
    protected int allThrows = 0;


    public Spieler(char init, int start){
        playerInitial=init;
        startPosition=start;
    }

    //GETTER UND SETTER
    public char getPlayerInitial() {
        return playerInitial;
    }
    public int getStartPosition(){
        return startPosition;
    }
    public boolean getHasWon() {
        return hasWon;
    }
    public int getStoneIn() {
        return stoneIn;
    }
    public void setStoneIn(int in) {
        this.stoneIn = in;
    }
    public int getStoneHome() {
        return stoneHome;
    }
    public void setStoneHome(int home) {
        this.stoneHome = home;
    }
    public int getZeahlA(){return ZeahlA;}
    public void setZeahlA(int zeahler) {
        this.ZeahlA = zeahler;
    }
    public int getZeahlB(){return ZeahlB;}
    public void setZeahlB(int zeahler) {
        this.ZeahlB = zeahler;
    }
    public void setStoneEnd(int zahl){this.stoneEnd=zahl;}


}

//Klasse für den Spieler - enthält alle Infos zum Spieler selbst, die für das Spiel wichtig sind
