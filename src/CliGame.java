import java.util.Scanner;



public class CliGame {


    public Scanner in = new Scanner(System.in);

    //
    private int player = 0;
    private int amountOfPlayers;
    private int amountOfCoins = 0;
    private Game g;


     public CliGame() {
         g = new Game();
         newGame();
         showBoard();
         firstTurn();
         boolean finished = false;
         while (!finished) // hierin de acties per turn zetten
        {
            showBoard();
            nextTurn();
            finished = g.checkIfFinished(); //TODO: bug: Stopt wanneer er 1 stapel actie kaarten op is, moet 3 zijn
        }
    }



    private void newGame() {
        System.out.println("Geef aantal spelers (2 - 4)");
        amountOfPlayers = in.nextInt();
        g.createPlayersList(amountOfPlayers);
        in.nextLine(); // deze nextline staat hier omdat anders de volgende input niet werkt, geen andere oplossing gevonden
        for (int i = 0; i < amountOfPlayers; i++) {
            System.out.println("Geef username speler " + (i + 1));
            String stringInput = in.nextLine();
            g.setPlayername(i, stringInput);
        }
    }

    private void showBoard() {
        g.printBoard();
    }

    private void firstTurn() {
        g.calculateCoinsOfPlayer(g.allPlayers.get(player));
        amountOfCoins = g.getAmountOfCoinsOfPlayer();
        turn();
    }

    private void nextTurn() {
        g.calculateCoinsOfPlayer(g.allPlayers.get(player));
        amountOfCoins = g.getAmountOfCoinsOfPlayer();
        nextPlayer();
        turn();

    }

    private void turn(){
        String playerName = g.getPlayerName(player);
        System.out.println("-------------------");
        System.out.println(playerName + " is aan de beurt");
        g.printHand(g.allPlayers.get(player));
        g.printCoins();
        g.printRemainingActions();
        actionMenu();
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
    }

    private void buyCard() {
        int remainingBuys = g.getAmountOfActionsRemaining();
        while (remainingBuys != 0) {
            System.out.println("Welk type kaart wil je kopen? 1. Actie. 2. Treasure 3. Victory 4. Stop");
            int keuze = in.nextInt();
            Card card;
            int kaartKeuze;
            amountOfCoins = g.getAmountOfCoinsOfPlayer();
            switch (keuze) {
                case 1:
                    kaartKeuze = chooseCard();
                    card = g.getCardFromPosInActionTable(kaartKeuze);
                    buyCardCheck(card, kaartKeuze);
                    break;
                case 2:
                    kaartKeuze = chooseCard();
                    card = g.getCardFromPosInTreasureTable(kaartKeuze);
                    buyCardCheck(card, kaartKeuze);
                    break;
                case 3:
                    kaartKeuze = chooseCard();
                    card = g.getCardFromPosInVictoryTable(kaartKeuze);
                    buyCardCheck(card, kaartKeuze);
                    break;
                case 4:
                    break;
            }
            g.printCoins();
            remainingBuys -= 1;
        }
    }

    private void buyCardCheck(Card card, int kaartKeuze){
        if (card.getCost() <= amountOfCoins) {
            g.setDecisionOfPlayerPosition(kaartKeuze);
            g.setDecisionOfPlayerType(card.getType());
            g.buyCard();
            System.out.println("Kaart " + card.getName() + " gekocht");
        } else {
            System.out.println("onvoldoende coins, probeer opnieuw");
            buyCard();
        }
    }

    private int chooseCard(){
        System.out.println("Geef positie van te kopen kaart");
        return in.nextInt() - 1;
    }


    private void playActionCard() {
        Player activePLayer = g.allPlayers.get(player);
        while (g.getAmountOfActionsRemaining() != 0) {
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
}

