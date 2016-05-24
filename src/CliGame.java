import java.util.ArrayList;
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

    private void useRemodel(int numberOfThePlayer) { // Remodel = kaart trashen, nieuwe kaart kiezen met waarde +2 van getrashte kaart
        Player activePlayer = g.getActivePlayer(numberOfThePlayer);
        System.out.println("Geef positie van de kaart in je hand om te trashen");
        int i = in.nextInt();
        Card selectedCard = activePlayer.getCardOnPosInHand(i);
        int amountOfCoinsToBuyNextCard = selectedCard.getCost() + 2;
        System.out.println("Kies een kaart om te kopen met de waarde van " + amountOfCoinsToBuyNextCard + ". 1.Actie 2.victory 3.treasure");
        int typeOfCard = in.nextInt();
        if (typeOfCard < 1 && typeOfCard > 3) {
            System.out.println("Ongeldige input, probeer opnieuw");
            useRemodel(numberOfThePlayer);
        }
        System.out.println("Geef de positie van de kaart die je wilt kopen");
        int position = in.nextInt();
        Card chosenCard = new Card();

        chosenCard = getCardOnPosWithType(typeOfCard, position);

        if (chosenCard.getCost() <= amountOfCoinsToBuyNextCard) {
            activePlayer.addCardToDiscardPile(chosenCard);
        } else {
            System.out.println("Gekozen kaart moet " + amountOfCoinsToBuyNextCard + " coins of minder kosten, probeer opnieuw");
            useRemodel(numberOfThePlayer);
        }
    }



    private Card getCardOnPosWithType(int typeOfCard, int posInTable) {
        Card gekozenKaart = new Card();

        switch (typeOfCard) {
            case 1:
                gekozenKaart = g.getCardOnPosWithType("action", posInTable);
                break;
            case 2:
                gekozenKaart = g.getCardOnPosWithType("action", posInTable);
                break;
            case 3:
                gekozenKaart = g.getCardOnPosWithType("action", posInTable);
                break;
        }
        gekozenKaart.setAmount(gekozenKaart.getAmount() - 1);
        return gekozenKaart;
    }



    private void promptDeckOpStapel(Player activePlayer) {
        System.out.println("Wil je je deck naar de discardpile sturen? (Ja / Nee)");
        String s = in.nextLine();
        if (s.equals("Ja") || s.equals("ja")) {
            activePlayer.moveAllCardsFromDeckToDiscardPile();
            System.out.println("Deck is verplaatst naar de discardpile");


        } else if (s.equals("Nee") || s.equals("nee")) {
            System.out.println("Deck is niet verplaatst naar de discardpile");
        } else {
            System.out.println("Geen ja of nee gevonden, probeer opnieuw");
            promptDeckOpStapel(activePlayer);
        }
    }

    private void useChapel(int numberOfThePlayer) {
        Player activeplayer = g.getActivePlayer(numberOfThePlayer);
        int amountOfTrashesLeft = 4;
        System.out.println("Geef de positie van de kaart om te trashen, je kunt nog " + amountOfTrashesLeft + " kaarten trashen. (Druk op 0 om te stoppen)");
        int i = in.nextInt() - 1;
        while (i != 0 && i <= activeplayer.getHandSize()) {
            activeplayer.removeCardFromHand(i);
            amountOfTrashesLeft -= 1;
            System.out.println("Geef de positie van de kaart om te trashen, je kunt nog " + amountOfTrashesLeft + " kaarten trashen. (Druk op 0 om te stoppen)");
            i = in.nextInt();
        }
    }

    private void useThroneRoom(int numberOfThePlayer) {
        Player activePlayer = g.getActivePlayer(numberOfThePlayer);
        System.out.println("Geef de positie van de actie kaart die je 2x wilt uitvoeren");
        int positie = in.nextInt();
        Card chosenCard = activePlayer.getCardOnPosInHand(positie);
        if (chosenCard.getType().equals("action")) {
            for (int i = 0; i < 2; i++) {
                g.useActionCard(chosenCard.getName(), numberOfThePlayer);
            }
        } else {
            System.out.println("Gekozen kaart is geen actie kaart, probeer opnieuw");
            useThroneRoom(numberOfThePlayer);
        }
    }

    private void useFeast(int numberOfThePlayer) {
        Player activePlayer = g.getActivePlayer(numberOfThePlayer);

        Deck playersHand = activePlayer.returnHand();
        for (int i = 0; i < playersHand.getSize(); i++) {
            if (playersHand.getCardOnPos(i).getName().equals("Feast")) {
                activePlayer.removeCardFromHand(i);
            }
        }
        System.out.println("Wil je een 1. action, 2. victory of 3. treasure kaart kopen?");
        int intOfTypeCard = in.nextInt();
        if (intOfTypeCard < 1 && intOfTypeCard > 3) {
            System.out.println("Ongeldige input, probeer opnieuw");
            useFeast(numberOfThePlayer);
        }
        System.out.println("Geef de positie van de kaart die je wilt kopen met de waarde van 5 of lager");
        int position = in.nextInt();
        Card chosenCard = new Card();

        chosenCard = getCardOnPosWithType(intOfTypeCard, position);

        activePlayer = g.getActivePlayer(numberOfThePlayer);
        if (chosenCard.getCost() <= 5) {
            activePlayer.addCardToDiscardPile(chosenCard);
        } else {
            System.out.println("Gekozen kaart moet 5 coins of minder kosten, probeer opnieuw");
            useFeast(numberOfThePlayer);
        }
    }

    private void useCellar(int numberOfPlayer) {
        Player activePlayer = g.getActivePlayer(numberOfPlayer);
        g.remainingActionsInPhase += 1;
        System.out.println("Geef positie van kaart in de hand om te verplaatsen naar de discard pile, geef 0 om te stoppen");
        int i = in.nextInt();
        while (i != 0) {
            g.moveCardFromHandToDiscardPilePosition(i - 1, activePlayer);
            activePlayer.addXAmountOfCardsToHand(1);
            g.printHand(activePlayer);
            System.out.println("Geef positie van kaart in de hand om te verplaatsen naar de discard pile, geef 0 om te stoppen");
            i = in.nextInt();
        }
        g.printHand(activePlayer);
        g.printCoins();
    }

    private void useMine(int numberOfThePlayer) {
        Deck treasureCardsInHand = new Deck(); // tijdelijk dek om treasure kaarten in te doen
        Player activePlayer = g.getActivePlayer(numberOfThePlayer);
        treasureCardsInHand = g.scanArrayForSpecificCard(g.treasureCardTable.getCardOnPos(0), activePlayer.returnHand());
        int cost = 0;
        if (treasureCardsInHand.getSize() > 0) {
            cost = getCostOfCard(activePlayer);
            System.out.println("Geef de positie van een treasure kaart om te kopen met de waarde " + (cost + 3) + " of lager");
            int pos = in.nextInt();
            Card gekozenKaart = g.treasureCardTable.getCardOnPos(pos);
            if (gekozenKaart.getCost() <= cost + 3) {
                activePlayer.addCardToDiscardPile(gekozenKaart);
            } else {
                System.out.println("Gekozen kaart is te duur, probeer opnieuw");
                useMine(activePlayer.getNumber());
            }
        } else {
            System.out.println("Geen treasure kaarten in hand gevonden");
        }
    }



    private void useThief(int numberOfThePlayer) {
        Player activePlayer = g.getActivePlayer(numberOfThePlayer);
        ArrayList<Card> tmpDeck = new ArrayList<Card>();
        boolean stopAction = false;
        for (int i = 0; i < g.allPlayers.size(); i++) {
            if (numberOfThePlayer != i) {
                Deck deckOfPlayerX = g.returnXAmountOfTopCardsOfPlayerY(2, i);
                System.out.println("1: " + deckOfPlayerX.getCardOnPos(0).getName() + " 2:" + deckOfPlayerX.getCardOnPos(1).getName());
                System.out.println("Geef positie van kaart om af te pakken");
                int positie = in.nextInt();
                positie = positie - 1;
                g.getActivePlayer(i).removeFromDeck(positie);
                tmpDeck.add(deckOfPlayerX.getCardOnPos(positie));
            }
        }
        while (!stopAction || tmpDeck.size() != 0) {
            System.out.println("Gestolen kaarten: ");
            for (int i = 0; i < tmpDeck.size(); i++)
                System.out.println((i + 1) + ". " + tmpDeck.get(i).getName());
            System.out.println("Geef positie van kaart om aan je eigen deck toe te voegen of geef 0 om te stoppen");
            int keuze = in.nextInt();
            if (keuze > 0 || keuze <= tmpDeck.size()) {
                activePlayer.addCardToDiscardPile(tmpDeck.get(keuze - 1));
                tmpDeck.remove(keuze - 1);
                if (tmpDeck.size() == 0) {
                    stopAction = true;
                }
            } else if (keuze == 0) {
                stopAction = true;
            }
        }
    }

    private void useSpy(int numberOfThePlayer) {
        g.addXAmountOfCardsToHandOfPlayerWithNumberY(1, numberOfThePlayer);
        g.remainingActionsInPhase += 1;
        for (int i = 0; i < g.allPlayers.size(); i++) {
            Player currentPlayer = g.getActivePlayer(i);
            Card currentCard = currentPlayer.getTopCardFromDeck();
            System.out.println("Kaart van: " + currentPlayer.getName());
            System.out.println(currentCard.getName());
            System.out.println("Wat wil je doen met deze kaart: 1.Bovenaan op deck. 2.Op de discardpile");
            int keuze = in.nextInt();
            if (keuze == 2) {
                currentPlayer.addCardToDiscardPile(currentCard);
                currentPlayer.removeFromDeck(0);
            }
        }
    }

    private void useWorkshop(int numberOfThePlayer) {
        System.out.println("Wil je een 1. action, 2. victory of 3. treasure kaart kopen? (1 - 3)");

        int intOfTypeCard = in.nextInt();
        if (intOfTypeCard < 1 && intOfTypeCard > 3) {
            System.out.println("Ongeldige input, probeer opnieuw");
            useWorkshop(numberOfThePlayer);
        }
        System.out.println("Geef de positie van de kaart die je wilt kopen van 4 coins of lager");
        int position = in.nextInt();
        Card chosenCard = new Card();

        chosenCard = getCardOnPosWithType(intOfTypeCard, position);

        Player activePlayer = g.getActivePlayer(numberOfThePlayer);
        if (chosenCard.getCost() <= 4) {
            activePlayer.addCardToDiscardPile(chosenCard);
        } else {
            System.out.println("Gekozen kaart moet 4 coins of minder kosten, probeer opnieuw");
            useWorkshop(numberOfThePlayer);
        }
    }

    private int getCostOfCard(Player activePlayer) {
        int cost = 0;
        System.out.println("Geef de positie van de treasure kaart in hand om weg te doen");
        int i = in.nextInt();
        Card pickedCard = activePlayer.getCardOnPosInHand(i);
        if (pickedCard.getType().equals("treasure")) {
            cost = activePlayer.getCardOnPosInHand(i).getCost();
            activePlayer.removeCardFromHand(i);
        } else {
            System.out.println("Gekozen kaart is geen treasure kaart, probeer opnieuw");
            useMine(activePlayer.getNumber());
        }
        return cost;
    }



    public void useActionCard(String nameOfActionCard, int numberOfThePlayer) {

        switch (nameOfActionCard) {
            case "Cellar":
                useCellar(numberOfThePlayer);
                break;
            case "Chapel":
                useChapel(numberOfThePlayer);
                break;
            case "Chancellor":
                g.useChancellor(numberOfThePlayer);
                break;
            case "Village":
                g.useVillage(numberOfThePlayer);
                break;
            case "Woodcutter":
                g.useWoodCutter(numberOfThePlayer);
                break;
            case "Workshop":
                useWorkshop(numberOfThePlayer);
                break;
            case "Feast":
                useFeast(numberOfThePlayer);
                break;
            case "Militia":
                g.useMilitia(numberOfThePlayer);
                break;
            case "Moneylender":
                g.useMoneylender(numberOfThePlayer);
                break;
            case "Remodel":
                useRemodel(numberOfThePlayer);
                break;
            case "Bureaucrat":
                g.useBureaucrat(numberOfThePlayer);
                break;
            case "Smithy":
                g.useSmithy(numberOfThePlayer);
                break;
            case "Spy":
                useSpy(numberOfThePlayer);
                break;
            case "Thief":
                useThief(numberOfThePlayer);
                break;
            case "Throne Room":
                useThroneRoom(numberOfThePlayer);
                break;
            case "Moat":
                g.useMoat(numberOfThePlayer);
                break;
            case "Council Room":
                g.useCouncilRoom(numberOfThePlayer);
                break;
            case "Festival":
                g.useFestival(numberOfThePlayer);
                break;
            case "Laboratory":
                g.useLaboratory(numberOfThePlayer);
                break;
            case "Library":
                g.useLibrary(numberOfThePlayer);
                break;
            case "Garden":
                System.out.println("Kaart kan niet gespeeld worden");
                g.remainingActionsInPhase += 1;
                break;
            case "Market":
                g.useMarket(numberOfThePlayer);
                break;
            case "Mine":
                useMine(numberOfThePlayer);
                break;
            case "Witch":
                g.useWitch(numberOfThePlayer);
                break;
            case "Adventurer":
                g.useAdventurer(numberOfThePlayer);
                break;
        }

    }
}