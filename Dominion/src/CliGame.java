import java.util.Scanner;

/**
 * Created by Robert on 21-4-2016.
 */


public class CliGame {


    // User input
    public int intInput;
    public String stringInput;
    public Scanner in = new Scanner(System.in);
    //
    public Game g;




    public CliGame() {
        g = new Game();
    }

    public void newGame(){
        System.out.println("Geef aantal spelers");
        intInput = in.nextInt();
        int amountOfPlayers = intInput;
        for (int i = 0; i < amountOfPlayers; i++){
            System.out.println("Geef username speler "+ (i + 1) );
            stringInput = in.nextLine();
            g.setPlayername(i, stringInput);
        }
    }

    public void startTurn(){

    }

    public void endTurn(){

    }



    /*public void printPlayers(){
        for (int i = 0 ; i < ){

        }
    }*/




    //public void


}
