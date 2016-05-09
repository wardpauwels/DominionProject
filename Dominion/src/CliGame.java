import java.util.Scanner;

/**
 * Created by Robert on 21-4-2016.
 */


public class CliGame {


    public Scanner in = new Scanner(System.in);
    // User input
    private int intInput;
    private String stringInput;
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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
        g.calculateCoinsOfPlayer(g.allPlayers.get(player));
        amountOfCoins = g.getAmountOfCoinsOfPlayer();
        String playerName = g.getPlayerName(player);
        System.out.println("-------------------");
        System.out.println(playerName + " is aan de beurt");
        g.printHand(g.allPlayers.get(player));
        g.printCoins(g.allPlayers.get(player));
        actionMenu();
        endTurn();
    }

    private void nextTurn(){
        g.calculateCoinsOfPlayer(g.allPlayers.get(player));
        amountOfCoins = g.getAmountOfCoinsOfPlayer();
        turn = true;
        nextPlayer();
        String playerName = g.getPlayerName(player);
        System.out.println("-------------------");
        System.out.println(playerName + " is aan de beurt");
        g.printHand(g.allPlayers.get(player));
        g.printCoins(g.allPlayers.get(player));
        actionMenu();
        endTurn();
    }

    private void endTurn(){
        Player currentPlayer = g.allPlayers.get(player);
        currentPlayer.addToHandDiscardpile();
        currentPlayer.clearHand();
        g.endTurnForPlayer(currentPlayer);
    }

    private void nextPlayer(){
        if(player != amountOfPlayers-1){
            player ++;
        }else{
            player = 0;
        }
    }

    public void actionMenu(){


                playActionCard();
                g.resetAmountOfActions();


                buyCard();
                g.endPhase();


                turn = false;

        }

    public void buyCard(){
        int remainingBuys = g.returnAmountOfActionsRemaining();
        while (remainingBuys!=0){
        System.out.println("Welk type kaart wil je kopen? 1. Actie. 2. Treasure 3. Victory 4. Stop");
        int keuze = in.nextInt();
        int kaartKeuze;
        int cardCost;
        Card card;
        amountOfCoins = g.getAmountOfCoinsOfPlayer();

        switch (keuze) {
            case 1:
                System.out.println("Geef positie van te kopen kaart");
                kaartKeuze = in.nextInt() - 1;
                card = g.getCardFromPosInActionTable(kaartKeuze);
                cardCost = card.getCost();
                if (cardCost <= amountOfCoins){
                    g.buyCard(kaartKeuze, card.getType(), g.allPlayers.get(player));
                    System.out.println("Kaart " + card.getName() + " gekocht");
                } else {
                    System.out.println("onvoldoende coins, probeer opnieuw " + card.getName() + " is te duur");
                    buyCard();
                }
                break;
            case 2:
                System.out.println("Geef positie van te kopen kaart");
                kaartKeuze = in.nextInt() - 1;
                card = g.getCardFromPosInTreasureTable(kaartKeuze);
                cardCost = card.getCost();
                if (card.getCost() <= amountOfCoins ) {
                    g.buyCard(kaartKeuze, card.getType(), g.allPlayers.get(player));
                    System.out.println("Kaart " + card.getName() + " gekocht");
                } else {
                    System.out.println("onvoldoende coins, probeer opnieuw");
                    buyCard();
                }
                break;
            case 3:
                System.out.println("Geef positie van te kopen kaart");
                kaartKeuze = in.nextInt() - 1;
                card = g.getCardFromPosInVictoryTable(kaartKeuze);
                cardCost = card.getCost();
                if (card.getCost() <= amountOfCoins ) {
                    g.buyCard(kaartKeuze, card.getType(), g.allPlayers.get(player));
                    System.out.println("Kaart " + card.getName() + " gekocht");
                } else {
                    System.out.println("onvoldoende coins, probeer opnieuw");
                    buyCard();
                }
                break;
            case 4:

                break;

        }
            remainingBuys-=1;
        }
    }

    public void cardBought(Card card){
        System.out.println("Kaart " + card.getName() + " gekocht");
    }

    public void playActionCard(){
        System.out.println("Geef positie in hand van te spelen actie kaart (Geef 0 in om te stoppen)");
        int i = in.nextInt();
        if(i == 0){
            g.setRemainingActionsInPhase(0);
        }else{
            while(g.returnAmountOfActionsRemaining()!=0) {
                Card toBePlayedActionCard = g.allPlayers.get(player).getCardOnPosInHand(i-1);
                if (toBePlayedActionCard.getType().equals("action")) {
                    g.useActionCard(toBePlayedActionCard.getName(), player);
                    g.lowerAmountOfActions();
                } else {
                    System.out.println("Gekozen kaart is geen actie kaart, probeer opnieuw");
                    playActionCard();
                }

            }

        }

    }



    /*public void printPlayers(){
        for (int i = 0 ; i < ){

        }
    }*/




    //public void


}
