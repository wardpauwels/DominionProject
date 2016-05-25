import java.util.Scanner;
import java.util.ArrayList;



public class CliGame {
//TODO ROBERT DIT IS CODE VAN USE MILITIA, DIT MOET NAAR HIER VERHUIZEN, DE 2 COINS GEBEUREN NOG IN ENGINE private void
/*useMilitia(int numberOfThePlayer){
Player activePlayer = getActivePlayer(numberOfThePlayer);
    currentlyActiveAmountOfCoins += 2;
    for(int i = 0; i < allPlayers.size(); i++){
        if(i != numberOfThePlayer){
            while(getActivePlayer(i).getHandSize() > 3) {
                System.out.println(getActivePlayer(i).getName() + ", geef de positie van een kaart om weg te doen tot je er 3 hebt");
                int pos = in.nextInt();
                Card chosenCard = activePlayer.getCardOnPosInHand(pos);
                activePlayer.addCardFromHandToDiscardPile(chosenCard);
            }
        }

    }
}
*/
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
    private int numberOTurn = 1;


     public CliGame() {
        g = new Game();
        newGame();
        showBoard();
        firstTurn();
        while (!finished) // hierin de acties per turn zetten
        {
            clearScreen(); // TODO: clear screen werkend maken
            showBoard();
            nextTurn();
            finished = g.checkIfFinished();

        }
    }

    private static void clearScreen() { // TODO: werkt niet
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void newGame() {
        System.out.println("Geef aantal spelers (2 - 4)");
        intInput = in.nextInt();

        amountOfPlayers = intInput;
        g.createPlayersList(amountOfPlayers);
        in.nextLine(); // deze nextline staat hier omdat anders de volgende input niet werkt, geen andere oplossing gevonden
        for (int i = 0; i < amountOfPlayers; i++) {
            System.out.println("Geef username speler " + (i + 1));
            stringInput = in.nextLine();
            g.setPlayername(i, stringInput);
        }
    }

    private void showBoard() {
        g.printBoard();
    }

    private void firstTurn() {
        g.calculateCoinsOfPlayer(g.allPlayers.get(player));
        amountOfCoins = g.getAmountOfCoinsOfPlayer();
        String playerName = g.getPlayerName(player);
        System.out.println("-------------------");
        System.out.println(playerName + " is aan de beurt");
        g.printHand(g.allPlayers.get(player));
        g.printCoins();
        g.printRemainingActions();
        actionMenu();
        numberOTurn ++; // TODO: weg na test
        endTurn();
    }

    private void nextTurn() {
        g.calculateCoinsOfPlayer(g.allPlayers.get(player));
        amountOfCoins = g.getAmountOfCoinsOfPlayer();

        nextPlayer();
        String playerName = g.getPlayerName(player);
        System.out.println("-------------------");
        System.out.println(playerName + " is aan de beurt");
        System.out.println("Nummer van beurt: "+ numberOTurn ); // TODO: weg na test
        g.printHand(g.allPlayers.get(player));
        g.printCoins();
        g.printRemainingActions();
        actionMenu();
        numberOTurn++; // TODO: weg na test
        endTurn();

    }

    private void endTurn() {
        Player currentPlayer = g.allPlayers.get(player);
        currentPlayer.addToHandDiscardpile();
        currentPlayer.clearHand();
        g.endTurnForPlayer(currentPlayer);
    }

    private void nextPlayer() {
        if (player != amountOfPlayers - 1) {
            player++;
        } else {
            player = 0;
        }
    }

    private void actionMenu() {


        playActionCard();
        g.resetAmountOfActions();


        buyCard();
        g.endPhase();
        g.printDeck(g.allPlayers.get(player));




    }

    private void buyCard() {
        int remainingBuys = g.returnAmountOfActionsRemaining();
        while (remainingBuys != 0) {
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
                    if (cardCost <= amountOfCoins) {
                        g.setDecisionOfPlayerPosition(kaartKeuze);
                        g.setDecisionOfPlayerType(card.getType());
                        g.buyCard();
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
                    if (card.getCost() <= amountOfCoins) {
                        g.setDecisionOfPlayerPosition(kaartKeuze);
                        g.setDecisionOfPlayerType(card.getType());
                        g.buyCard();
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
                    if (card.getCost() <= amountOfCoins) {
                        g.setDecisionOfPlayerPosition(kaartKeuze);
                        g.setDecisionOfPlayerType(card.getType());
                        g.buyCard();
                        System.out.println("Kaart " + card.getName() + " gekocht");
                    } else {
                        System.out.println("onvoldoende coins, probeer opnieuw");
                        buyCard();
                    }
                    break;
                case 4:
                    break;

            }
            g.printCoins();
            remainingBuys -= 1;
        }
    }

       private void playActionCard() {
        Player activePLayer = g.allPlayers.get(player);
        while (g.returnAmountOfActionsRemaining() != 0) {
        System.out.println("Geef positie in hand van te spelen actie kaart (Geef 0 in om te stoppen)");
        int i = in.nextInt();

        if (i == 0) {
            g.setRemainingActionsInPhase(0);
        } else {

                Card toBePlayedActionCard = g.allPlayers.get(player).getCardOnPosInHand(i - 1);
                if (toBePlayedActionCard.getType().equals("action")) {
                    g.moveCardFromHandToDiscardPilePosition(i-1, activePLayer);
                    g.useActionCard(toBePlayedActionCard.getName(), player);

                    g.printHand(activePLayer);
                    g.lowerAmountOfActions();
                    g.printRemainingActions();

                } else {
                    System.out.println("Gekozen kaart is geen actie kaart, probeer opnieuw");
                    playActionCard();
                }

            }

        }

        g.printCoins();

    }


    /*private void useFeast(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);

        Hand playersHand = activePlayer.returnHand();
        for(int i = 0; i < playersHand.getSize(); i++){
            if(playersHand.getCardOnPos(i).getName().equals("Feast")){
                activePlayer.removeCardFromHand(i);
            }
        }System.out.println("Wil je een 1. action, 2. victory of 3. treasure kaart kopen?");
        int intOfTypeCard = in.nextInt();
        if(intOfTypeCard < 1 && intOfTypeCard > 3){
            System.out.println("Ongeldige input, probeer opnieuw");
            useFeast(numberOfThePlayer);
        }
        System.out.println("Geef de positie van de kaart die je wilt kopen met de waarde van 5 of lager"); //TODO: check maken voor de positie
        int position = in.nextInt();
        Card chosenCard = new Card();

        chosenCard = getCardOnPosWithType(intOfTypeCard, position);

        activePlayer = getActivePlayer(numberOfThePlayer);
        if(chosenCard.getCost() <= 5){
            activePlayer.addCardToDiscardPile(chosenCard);
        }else{
            System.out.println("Gekozen kaart moet 5 coins of minder kosten, probeer opnieuw");
            useWorkshop(numberOfThePlayer);
        }
    }

    private void useRemodel(int numberOfThePlayer){ // Remodel = kaart trashen, nieuwe kaart kiezen met waarde +2 van getrashte kaart
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        System.out.println("Geef positie van de kaart in je hand om te trashen");
        int i = in.nextInt();
        Card selectedCard = activePlayer.getCardOnPosInHand(i);
        int amountOfCoinsToBuyNextCard = selectedCard.getCost() +2;
        System.out.println("Kies een kaart om te kopen met de waarde van " + amountOfCoinsToBuyNextCard + ". 1.Actie 2.victory 3.treasure");
        int typeOfCard = in.nextInt();
        if(typeOfCard < 1 && typeOfCard > 3){
            System.out.println("Ongeldige input, probeer opnieuw");
            useRemodel(numberOfThePlayer);

        }
        System.out.println("Geef de positie van de kaart die je wilt kopen"); //TODO: check maken voor de positie
        int position = in.nextInt();
        Card chosenCard = new Card();

        chosenCard = getCardOnPosWithType(typeOfCard, position);

        if(chosenCard.getCost() <= amountOfCoinsToBuyNextCard){
            activePlayer.addCardToDiscardPile(chosenCard);
        }else{
            System.out.println("Gekozen kaart moet " + amountOfCoinsToBuyNextCard + " coins of minder kosten, probeer opnieuw");
            useRemodel(numberOfThePlayer);
        }
    }

    private void useCellar(int numberOfPlayer){
        Player activePlayer = getActivePlayer(numberOfPlayer);
        remainingActionsInPhase += 1;
        System.out.println("Geef positie van kaart in de hand om te verplaatsen naar de discard pile, geef 0 om te stoppen");
        int i = in.nextInt();
        while(i != 0){
            moveCardFromHandToDiscardPilePosition(i-1, activePlayer);
            activePlayer.addXAmountOfCardsToHand(1);
            printHand(activePlayer);
            System.out.println("Geef positie van kaart in de hand om te verplaatsen naar de discard pile, geef 0 om te stoppen");
            i = in.nextInt();
        }
        printHand(activePlayer);
        printCoins();
    }




    */
}