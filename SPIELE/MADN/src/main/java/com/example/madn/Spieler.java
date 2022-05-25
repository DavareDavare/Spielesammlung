package com.example.madn;

import java.util.Random;
import java.util.Scanner;

public class Spieler {

    private char playerInitial;
    private int startPosition;
    private boolean hasWon;
    private int stoneIn;
    private int stoneHome;
    //Stein stein1 = new Stein('1','1',0);



    public Spieler(char init, int start){
        playerInitial=init;
        startPosition=start;
    }

    //GETTER UND SETTER
    public char getPlayerInitial() {
        return playerInitial;
    }
    public void setPlayerInitial(char init){
        playerInitial=init;
    }
    public int getStartPosition(){
        return startPosition;
    }
    public void setStartPosition(int start){
        startPosition=start;
    }
    public boolean getHasWon() {
        return hasWon;
    }
    public void setHasWon(boolean win) {
        this.hasWon = win;
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






}
