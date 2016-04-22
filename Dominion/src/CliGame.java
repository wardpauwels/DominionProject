import java.util.Scanner;

/**
 * Created by Robert on 21-4-2016.
 */


public class CliGame {


    // User input
    private int intInput;
    private String stringInput;
    public Scanner in = new Scanner(System.in);
    //
    private int player = 1;
    private int amountOfPlayers;
    private int amountOfCoins = 0;

    private Game g;




    public CliGame() {
        g = new Game();
        newGame();
        showBoard();


        while(!checkIfGameIsFinished()) // hierin de acties per turn zetten
        {
            nextTurn();
        }
    }

    private void newGame(){
        System.out.println("Geef aantal spelers (2 - 4)");
        intInput = in.nextInt();
        amountOfPlayers = intInput;
        g.createPlayersList(amountOfPlayers);
        in.nextLine(); // deze nextline staat hier omdat anders de volgende input niet werkt, geen andere oplossing gevonden
        for (int i = 0; i < amountOfPlayers; i++){
            System.out.println("Geef username speler "+ (i + 1) );
            stringInput = in.nextLine();
            g.setPlayername(i, stringInput);
        }
    }

    private void showBoard(){
        g.printBoard();
        g.printHand(g.allPlayers.get(0));
        g.printCoins(g.allPlayers.get(0)); //niet nodig?
    }


    private boolean checkIfGameIsFinished(){
        boolean finished = false;
        //while(){ TODO: check maken om te kijken of de province kaarten of 3 stapels action kaarten op zijn.

        //

        return finished;
    }

    private void nextTurn(){
        nextPlayer();


    }


    private void nextPlayer(){
        if(player != amountOfPlayers){
            player ++;
        }else{
            player = 1;
        }
    }


    public void endTurn(){

    }



    /*public void printPlayers(){
        for (int i = 0 ; i < ){

        }
    }*/




    //public void


}
