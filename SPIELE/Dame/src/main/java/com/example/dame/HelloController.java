package com.example.dame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.Scanner;

public class HelloController{
    @FXML
    private GridPane grid1;
    @FXML
    private GridPane grid2;
    @FXML
    private Button playbutton;
    @FXML
    private Pane playpane;
    @FXML
    private Rectangle r;
    Button [][] btn = new Button[8][8];

    char[][] field = new char [8][8];
    char[] steine = {'e','b'};
    char feldhintergrund = '-';
    Feld field1 = new Feld(feldhintergrund, steine);

    int x;
    char y;
    int x2;
    char y2;

    int temp=0;

    int spieler = 0;
    char currPlayer;

    int anz1 = 0;
    int anz2 = 0;

    public void initialize()
    {/*
        fill();
        place();
        do{
            if(spieler==0)
            {
                currPlayer = steine[0];
            }
            else if(spieler==1)
            {
                currPlayer = steine[1];
            }
            play();
            getAnzahl();
        }while(anz1 != 0 || anz2 != 0);

        if(anz1 == 0)
        {
            System.out.println("Spieler 1 hat gewonnen!");
        }
        else if(anz2 == 0)
        {
            System.out.println("Spieler 2 hat gewonnen!");
        }
        */
        fill();
        field1.fill();
        field1.placePlayerStones();
        fillText();
        field1.output();


    }

    public void fill()
    {
        int z=0;
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                z++;
                grid1.add(createButton(z), j, i);
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
        System.out.println("Aktueller Spieler: " + currPlayer);
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
                        field[j][h] = steine[0];
                    }
                } else {
                    if(h%2 == 1)
                    {
                        field[j][h] = steine[0];
                    }
                }
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

    public TextField createText(char c)
    {
        TextField t = new TextField(Character.toString(c));
        t.setText(Character.toString(c));
        t.setStyle("-fx-background-color: transparent;" +
                "-fx-font-size: 20;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: black;" +
            "-fx-translate-x: 20px");
        return t;
    }

    public Button createButton(int z)
    {
        Button button = new Button(Integer.toString(z));
        button.setMaxSize(75, 75);
        button.setDisable(false);
        button.setOpacity(0);

        button.setOnAction(e -> {

                field1.output();
                play(button);
                getAnzahl();
                if(temp==0)
                {
                    temp=1;
                }else
                {
                    temp=0;
                }
                field1.output();

        });

        return button;
    }

    public void setXY(Button button)
    {
        if((Integer.parseInt(button.getText()))%8==0)
        {
            field1.y =(char)(((Integer.parseInt(button.getText())/8)+65)-1);
            field1.x=7;
        }
        else
        {
            field1.y=(char)((Integer.parseInt(button.getText())/8)+65);
            field1.x=(Integer.parseInt(button.getText())%8)-1;
        }

        System.out.println(field1.x + " " + field1.y);
    }

    public void setXY2(Button button)
    {
        if((Integer.parseInt(button.getText()))%8==0)
        {
            field1.y2 =(char)(((Integer.parseInt(button.getText())/8)+65)-1);
            field1.x2=7;
        }
        else
        {
            field1.y2=(char)((Integer.parseInt(button.getText())/8)+65);
            field1.x2=(Integer.parseInt(button.getText())%8)-1;
        }

        System.out.println(field1.x2 + " " + field1.y2);
    }

    public void play(Button button)
    {
        field1.checkOP();
        if(temp==0)
        {
            setXY(button);
            field1.fm();
            System.out.println("test1");

        }
        if(temp == 1)
        {
            setXY2(button);
            if(field1.IsViable())
            if (field1.field[(int) field1.y - 'A'][field1.x] == field1.steine[0] || field1.field[(int) field1.y - 'A'][field1.x] == field1.steine[1]) {
                if(field1.smn())
                {
                    System.out.println(field1.getX() + " " + (int)(field1.getY()-65));
                    System.out.println(field1.field[field1.getX()][(field1.getY()-65)]);
                    System.out.println(field1.getAt(field1.getX(), (field1.getY()-65)));
                    char zeichen = field1.getAt(field1.getX(), (field1.getY()-65));
                    System.out.println(zeichen);
                    field1.setAt(field1.getX2(), (field1.getY2()-'A'), zeichen);
                    field1.setAt(field1.getX(), (field1.getY()-'A'), field1.getFeldhintergrund());
                    field1.output();
                    fillText();
                    field1.setCurrPlayer();
                    field1.setSpieler();
                }
            } else {
                if(field1.smo())
                {
                    System.out.println(field1.getX() + " " + (int)(field1.getY()-65));
                    System.out.println(field1.field[field1.getX()][(field1.getY()-65)]);
                    System.out.println(field1.getAt(field1.getX(), (field1.getY()-65)));
                    char zeichen = field1.getAt(field1.getX(), (field1.getY()-65));
                    System.out.println(zeichen);
                    field1.setAt(field1.getX2(), (field1.getY2()-'A'), zeichen);
                    field1.setAt(field1.getX(), (field1.getY()-'A'), field1.getFeldhintergrund());
                    field1.output();
                    fillText();
                    field1.setCurrPlayer();
                    field1.setSpieler();
                }
            }
        }




    }


    public void inputStart()
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
                if (!IsViable() || !IsBackground() || !IsPlayer()) {
                    System.out.println(field[(int) y - 'A'][x]);
                    System.out.println(((int) y - 'A') + " " + x);
                    System.out.print("Ups... Da ist etwas schiefgelaufen...");
                }
            }
            }
        } while (!IsViable() || !IsBackground() || !IsPlayer());
        System.out.println(x + " " + ((int)y-'A'));
    }

    public void inputGoaln()
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

    public void inputGoals()
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
                System.out.println("Geben sie den X-Wert ein wohin der Stein bewegt werden soll (" + (x-1) + "): ");
            }

            x2 = Integer.parseInt(scanner.next());

                if(y=='A')
                {
                    System.out.println("Geben sie den Y-Wert ein wohin der Stein bewegt werden soll (" + (char)(y+1) + "): ");
                }
                else if(y=='H')
                {
                    System.out.println("Geben sie den Y-Wert ein wohin der Stein bewegt werden soll (" + (char)(y-1) + "): ");
                }
                else if(y<'H' && y>'A')
                {
                    System.out.println("Geben sie den Y-Wert ein wohin der Stein bewegt werden soll (" + (char)(y-1) + "/" + (char)(y+1) + "): ");
                }


            y2 = scanner.next().charAt(0);
            System.out.println("--------------------------");
            if(x2!=x+1 && x2!=x-1 ||y2!=y+1 && y2!=y-1 || x2 <0 && x2 > 7 || y2 < 0 && y2 > 7)
            {
                System.out.println(x2 + " " + ((int)y2-'A'));
                System.out.print("Ups... Da ist etwas schiefgelaufen...");
            }


        } while(x2!=x+1 && x2!=x-1 ||y2!=y+1 && y2!=y-1 || x2 <0 && x2 > 7 || y2 < 0 && y2 > 7);
    }

    public void checkop()
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

    public void getAnzahl()
    {
        anz1 = 0;
        anz2 = 0;
        field = field1.getField();
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                if(field[i][j] == steine[0] || field[i][j] == (char)(steine[0]-32))
                {
                    anz1++;
                }
                if(field[i][j] == steine[1] || field[i][j] == (char)(steine[1]-32))
                {
                    anz2++;
                }
            }
        }
        System.out.println("Anzahl 1:");
        System.out.println(anz1);
        System.out.println("Anzahl 2:");
        System.out.println(anz2);
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
        return field[(int) y - 'A'][x] == currPlayer || field[(int) y - 'A'][x] == (char) (currPlayer - 32);
    }

    public boolean IfStop()
    {
        return x == 9 || y == '9' || x2 == 9 || y2 == '9';
    }

    public boolean CanKill()
    {
        if(x==0 && y<6)
        {
            if(field[(int)y-'A'-1][x+1] != feldhintergrund && field[(int)y-'A'-2][x+2] == feldhintergrund)
            {
               return true;
            }
        }
        else if(x==7 && y<6)
        {
            if(field[(int)y-'A'-1][x-1] != feldhintergrund && field[(int)y-'A'-2][x-2] == feldhintergrund)
            {
                return true;
            }
        }
        else if(x>=1 && x<=6)
        {

        }

        return true;
    }
@FXML
    public void startGame(ActionEvent actionEvent) {
        playbutton.setDisable(true);
        playbutton.setOpacity(0);
        playpane.setDisable(true);
        playpane.setOpacity(0);
        r.setDisable(true);
        r.setOpacity(0);
        field1.fill();
        field1.placePlayerStones();
        grid1.setDisable(false);
    }

    public void fillText()
    {
        grid2.getChildren().clear();
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                grid2.add(createText(field1.field[i][j]), j, i);
                grid2.add(createText(field1.field[i][j]), j, i);
                grid2.add(createText(field1.field[i][j]), j, i);
            }
        }
    }
}