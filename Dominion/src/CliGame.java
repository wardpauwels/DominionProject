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
    private int player = 0;
    private int amountOfPlayers;
    private int amountOfCoins = 0;
    private boolean finished = false;
    private Game g;
    private boolean turn = true;




    public CliGame() {
        g = new Game();
        newGame();
        showBoard();
        firstTurn();
        while(!finished) // hierin de acties per turn zetten
        {
            clearScreen();
            showBoard();
            nextTurn();
            finished = g.checkIfFinished();

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
    }


    private void firstTurn(){
        amountOfCoins = g.getAmountOfCoinsOfPlayer(g.allPlayers.get(player));
        String playerName = g.getPlayerName(player);
        System.out.println("-------------------");
        System.out.println(playerName + " is aan de beurt");
        g.printHand(g.allPlayers.get(player));
        g.printCoins(g.allPlayers.get(player));
        actionMenu();
    }

    private void nextTurn(){
        amountOfCoins = g.getAmountOfCoinsOfPlayer(g.allPlayers.get(player));
        turn = true;
        nextPlayer();
        String playerName = g.getPlayerName(player);
        System.out.println("-------------------");
        System.out.println(playerName + " is aan de beurt");
        g.printHand(g.allPlayers.get(player));
        g.printCoins(g.allPlayers.get(player));
        actionMenu();
    }


    private void nextPlayer(){
        if(player != amountOfPlayers-1){
            player ++;
        }else{
            player = 0;
        }
    }

    public void actionMenu(){
        System.out.println("Geef uit te voeren actie: 1.Speel actie kaart. 2. Koop een kaart. 3.Einde beurt");
        int choice = in.nextInt();
        switch (choice){
            case 1:
                playActionCard();
                break;
            case 2:
                buyCard();
                break;
            case 3:
                turn = false;
                break;
        }
    }

    /*public void endTurn(){
        g.nextTurnPlayer(g.allPlayers.get(player));
    }*/

    public void buyCard(){
        System.out.println("Welk type kaart wil je kopen? 1. Actie. 2. Treasure 3. Victory 4. Stop");
        int keuze = in.nextInt();
        int kaartKeuze;
        int cardCost;
        Card card;
        System.out.println("Geef positie van te kopen kaart");
        switch (keuze){
            case 1:
                kaartKeuze = in.nextInt();
                card = g.getCardFromPosInActionTable(kaartKeuze);
                cardCost = card.getCost();
                if(cardCost <= amountOfCoins){
                    g.buyCard(kaartKeuze -1, card.getType(), g.allPlayers.get(player));
                    System.out.println("Kaart " + card.getName() + " gekocht");
                }else{
                    System.out.println("onvoldoende coins, probeer opnieuw");
                    buyCard();
                }
                break;
            case 2:
                kaartKeuze = in.nextInt();
                card = g.getCardFromPosInTreasureTable(kaartKeuze);
                cardCost = card.getCost();
                if(card.getCost() <= amountOfCoins){
                    g.buyCard(kaartKeuze -1, card.getType(), g.allPlayers.get(player));
                    System.out.println("Kaart " + card.getName() + " gekocht");
                }else{
                    System.out.println("onvoldoende coins, probeer opnieuw");
                    buyCard();
                }
                break;
            case 3:
                kaartKeuze = in.nextInt();
                card = g.getCardFromPosInVictoryTable(kaartKeuze);
                cardCost = card.getCost();
                if(card.getCost() <= amountOfCoins){
                    g.buyCard(kaartKeuze -1, card.getType(), g.allPlayers.get(player));
                    System.out.println("Kaart " + card.getName() + " gekocht");
                }else{
                    System.out.println("onvoldoende coins, probeer opnieuw");
                    buyCard();
                }
                break;
            case 4:
                break;
        }
    }

    public void cardBought(Card card){
        System.out.println("Kaart " + card.getName() + " gekocht");
    }

    public void playActionCard(){
        System.out.println("Geef positie in hand van te spelen actie kaart (Geef 0 in om te stoppen)");
        int i = in.nextInt();
        if(i == 0){

        }else{
            Card toBePlayedActionCard = g.allPlayers.get(player).getCardOnPosInHand(i);
            if (toBePlayedActionCard.getType().equals("action")){
                g.useActionCard(toBePlayedActionCard.getName(),player);
            }else{
                System.out.println("Gekozen kaart is geen actie kaart, probeer opnieuw");
                playActionCard();
            }
        }

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



    /*public void printPlayers(){
        for (int i = 0 ; i < ){

        }
    }*/




    //public void


}
