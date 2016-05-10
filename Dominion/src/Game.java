
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * @author Jens.Thiel
 */


public class Game {
    private VictoryCardTable victoryCardTable;
    private TreasureCardTable treasureCardTable;
    private ActionCardTable actionCardTable;
    public ArrayList<Player> allPlayers;
    private ArrayList<Card> actionCardsOnBoard;

    //een linked list van gespeelde kaarten (nog resetten bij iedere 'beurt' en opvullen bij iedere 'beurt')
    private Deck playedCards = new Deck();
    private int currentlyActiveAmountOfCoins;
    private int remainingActionsInPhase = 1;
    private int remainingBuysInPhase = 1;

    public Scanner in = new Scanner(System.in); // scanner voor user input


    public Game() {
        victoryCardTable = new VictoryCardTable();
        treasureCardTable = new TreasureCardTable();
        actionCardTable = new ActionCardTable();
        allPlayers = new ArrayList<Player>();
        actionCardsOnBoard = new ArrayList<Card>();
        generateActionCardTable();
    }
    // lijst aanmaken met spelers
    public void createPlayersList(int amount){
        for(int i=0;i<amount;i++){
            Player newPlayer = new Player();
            newPlayer.setNumber(i);
            allPlayers.add(newPlayer);
        }
    }
    private int getRandomNumber(int minValue, int maxValue) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(maxValue - minValue + 1) + minValue;
        return randomNumber;
    }

    private boolean checkRandom(int randomNumber) {
        for (int i = 0; i < actionCardsOnBoard.size(); i++) {
            int currentNumber = actionCardsOnBoard.get(i).getNumber();
            if (randomNumber == currentNumber) {
                return false;
            }
        }
        return true;
    }
    public void generateActionCardTable() {
        for (int i = 0; i < 10; i++) {
            int randomNumber = getRandomNumber(1, 25);
            while (!checkRandom(randomNumber)) {
                randomNumber = getRandomNumber(1, 25);
            }

            Card newCard = actionCardTable.getCardOnPos(randomNumber - 1);
            actionCardsOnBoard.add(newCard);
        }

    }

    private void resetRemainingActions(){
        remainingActionsInPhase = 1;
    }


    public void ExecuteDrawPhase(Player whichPlayer){
        whichPlayer.generateNextHand();
    }

    public Card getCardFromPosInActionTable(int pos){
        return actionCardsOnBoard.get(pos);
    }
    public Card getCardFromPosInTreasureTable(int pos){
        return treasureCardTable.getCardOnPos(pos);
    }
    public Card getCardFromPosInVictoryTable(int pos){
        return victoryCardTable.getCardOnPos(pos);
    }





    public void buyCard(int positionOnTheBoard, String type, Player whichPlayer){
        Card boughtCard = new Card();
        switch(type){
            case "action":
                boughtCard = actionCardsOnBoard.get(positionOnTheBoard);
                break;
            case "victory":
                boughtCard = victoryCardTable.getCardOnPos(positionOnTheBoard);
                break;
            case "treasure":
                boughtCard = treasureCardTable.getCardOnPos(positionOnTheBoard);
                break;
        }
        boughtCard.setAmount(boughtCard.getAmount()-1);
        currentlyActiveAmountOfCoins-=boughtCard.getCost();
        whichPlayer.addCardToDiscardPile(boughtCard);
        remainingActionsInPhase = remainingActionsInPhase - 1;

    }

    private void executeSpecificAction(Card card){
        card.getNumber();
    }

    public void calculateCoins(Card usedCard){
        currentlyActiveAmountOfCoins = currentlyActiveAmountOfCoins + usedCard.getNumber() + 1;
    }

    //naam player veranderen
    public void setPlayername(int whichPlayer, String playername){
        allPlayers.get(whichPlayer).setName(playername);
    }
    public String getPlayerName(int whichPlayer){
        return allPlayers.get(whichPlayer).getName();
    }

    public void addCardToHand(Player whichPlayer){
        whichPlayer.addCardFromDeckToHand();
    }

    public boolean checkIfFinished(){
        boolean finished = false;
        for(int i = 0; i < actionCardTable.getSize(); i++)
            if(actionCardTable.getCardOnPos(i).getAmount() == 0){
                finished = true;
            }
        for(int i = 0; i < treasureCardTable.getSize(); i++){
            if(treasureCardTable.getCardOnPos(i).getAmount() == 0){
                finished = true;
            }
        }
        return finished;
    }

    public void endTurnForPlayer(Player p){
        p.generateNextHand();
    }
    public void lowerAmountOfActions(){
        remainingActionsInPhase -= 1;
    }

    public void resetAmountOfActions(){
        remainingActionsInPhase = remainingBuysInPhase;

    }
    public int returnAmountOfActionsRemaining(){
        return remainingActionsInPhase;
    }
    public void endPhase(){
        remainingActionsInPhase = 1;
        remainingBuysInPhase = 1;
    }
    public void setRemainingActionsInPhase(int amount){
        remainingActionsInPhase = amount;
    }

    //---------- Action Cards ------------- //

    private void useVillage (int numberOfThePlayer) {
        addXAmountOfCardsToHandOfPlayerWithNumberY(1, numberOfThePlayer);
        remainingActionsInPhase = +2;


    }
    private void useMilitia(int numberOfThePlayer){
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


    private void useMoneylender(int numberOfThePlayer){

        if(allPlayers.get(numberOfThePlayer).scanHandForCard(treasureCardTable.getCardOnPos(0))) {

            int pickedCopper = allPlayers.get(numberOfThePlayer).scanHandForCardandGetPositionInHand(treasureCardTable.getCardOnPos(0));
            allPlayers.get(numberOfThePlayer).addCardFromHandToDiscardPile(treasureCardTable.getCardOnPos(0));
            currentlyActiveAmountOfCoins+=3;
        }
    };

    private void useSmithy(int numberOfThePlayer){
        allPlayers.get(numberOfThePlayer-1).addXAmountOfCardsToHand(3);
    }
    private void useWitch(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        activePlayer.addXAmountOfCardsToHand(2);
        for (int i=0;i<allPlayers.size();i++){
            if(i!=numberOfThePlayer){
                if(!checkForCard(actionCardTable.getCardOnPos(15), getActivePlayer(i)))
                    {
                    getActivePlayer(i).addCardToDiscardPile(victoryCardTable.getCardOnPos(3));
                }
            }
        }

    }
    private boolean checkForCard(Card toFindCard, Player specificPlayer){
        if (specificPlayer.scanHandForCard(toFindCard)) {
            return true;
        }
        else{
            return false;
        }

    }
    private void useThroneRoom(int numberOfThePlayer,int positionOfCardThatsNeeded){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        Card toBeUsedCard = activePlayer.getCardOnPosInHand(positionOfCardThatsNeeded);
        for (int i=0;i<2;i++) {
            executeSpecificAction(toBeUsedCard);
        }
    }
    private void useWoodCutter(int numberOfThePlayer){
        currentlyActiveAmountOfCoins += 2;
        remainingBuysInPhase += 1;
    }
    private void useWorkshop(int numberOfThePlayer){
        System.out.println("Wil je een 1. action, 2. victory of 3. treasure kaart kopen? (1 - 3)");
        int intOfTypeCard = in.nextInt();
        if(intOfTypeCard < 1 && intOfTypeCard > 3){
            System.out.println("Ongeldige input, probeer opnieuw");
            useWorkshop(numberOfThePlayer);
        }
        System.out.println("Geef de positie van de kaart die je wilt kopen"); //TODO: check maken voor de positie
        int positie = in.nextInt();
        Card gekozenKaart = new Card();
        switch (intOfTypeCard){
            case 1:
                gekozenKaart = actionCardTable.getCardOnPos(positie);
                break;
            case 2:
                gekozenKaart = victoryCardTable.getCardOnPos(positie);
                break;
            case 3:
                gekozenKaart = treasureCardTable.getCardOnPos(positie);
                break;
        }
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        if(gekozenKaart.getCost() <= 4){
            activePlayer.addCardToDiscardPile(gekozenKaart);
        }else{
            System.out.println("Gekozen kaart moet 4 coins of minder kosten, probeer opnieuw");
            useWorkshop(numberOfThePlayer);
        }



    }

    private void useFestival(int numberOfThePlayer){
        remainingActionsInPhase += 2;
        remainingBuysInPhase += 1;
        currentlyActiveAmountOfCoins += 2;
    }

    private void useCouncilRoom(int numberOfThePlayer){
        addXAmountOfCardsToHandOfPlayerWithNumberY(4,numberOfThePlayer);
        remainingBuysInPhase += 1;

    }
    private void useLaboratory(int numberOfThePlayer){
        addXAmountOfCardsToHandOfPlayerWithNumberY(2,numberOfThePlayer);
        remainingActionsInPhase += 1;

    }
    private void useMarket(int numberOfThePlayer){
        addXAmountOfCardsToHandOfPlayerWithNumberY(1,numberOfThePlayer);
        remainingActionsInPhase += 1;
        remainingBuysInPhase += 1;
        currentlyActiveAmountOfCoins += 1;
    }

    private void useSpy(int numberOfThePlayer){
        addXAmountOfCardsToHandOfPlayerWithNumberY(1,numberOfThePlayer);
        remainingActionsInPhase+=1;
        for(int i = 0; i < allPlayers.size(); i++){
            Player currentPlayer = getActivePlayer(i);
            Card currentCard = currentPlayer.getTopCardFromDeck();
            System.out.println("Kaart van: " + currentPlayer.getName());
            System.out.println(currentCard.getName() + currentCard.getType());
            System.out.println("Wat wil je doen met deze kaart: 1.Bovenaan op deck. 2.In de discardpile");
            int keuze = in.nextInt();
            if(keuze == 2){
                currentPlayer.addCardToDiscardPile(currentCard);
                currentPlayer.removeFromDeck(0);
            }
        }
    }

    private void useAdventurer (int numberOfThePlayer){
        int amountOfTreasureCardsFound = 0;
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        while (amountOfTreasureCardsFound!=2) {
            Card topCard = activePlayer.getTopCardFromDeck();
            if (topCard.getType().equals("treasure")) {
                amountOfTreasureCardsFound += 1;
                activePlayer.addSpecificCardToHand(topCard);
            } else {
                activePlayer.addCardToDiscardPile(topCard);
            }
        }
        activePlayer.printHand();

    }
    private void useThief(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        ArrayList<Card> tmpDeck = new ArrayList<Card>();
        boolean stopAction = false;
        for (int i = 0; i < allPlayers.size(); i++){
            if(numberOfThePlayer != i){
                Deck deckOfPlayerX = returnXAmountOfTopCardsOfPlayerY(2,i);
                System.out.println("Geef positie van kaart om af te pakken");
                int positie  = in.nextInt();
                positie  = positie - 1;
                getActivePlayer(i).removeFromDeck(positie);
                tmpDeck.add(deckOfPlayerX.getCardOnPos(positie));
            }
        }
        while(!stopAction || tmpDeck.size() > 0){
            System.out.println("Geef positie van kaart om aan je eigen deck toe te voegen of geef 9 om te stoppen");
            int keuze = in.nextInt();
            if(keuze > 0 || keuze <= tmpDeck.size()){
                activePlayer.addCardToDiscardPile(tmpDeck.get(keuze));
            } else if (keuze == 9){
                stopAction = true;
            }
        }
    }


    private void useBureaucrat(int numberOfThePlayer)
    {
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        activePlayer.addCardToPlaceInDeck(0,treasureCardTable.getCardOnPos(1));
        //TODO nog toevoegen dat andere spelers victory card moeten kiezen (robert)
        for (int i=0;i<allPlayers.size();i++){
            if (i != numberOfThePlayer) {
                int positionOfVictoryCardInHandOfPlayer = allPlayers.get(i).scanHandForCardAndReturnPosition("victory");
                if (positionOfVictoryCardInHandOfPlayer != -1) {
                    allPlayers.get(i).addCardToDiscardPile(allPlayers.get(i).getCardOnPosInHand(positionOfVictoryCardInHandOfPlayer));
                }
            }
        }
    }


    private void useMoat(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        activePlayer.addXAmountOfCardsToHand(2);

    }
    private void useMine(int numberOfThePlayer){
        Deck treasureCardsInHand = new Deck(); // tijdelijk dek om treasure kaarten in te doen
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        treasureCardsInHand = scanArrayForSpecificCard(treasureCardTable.getCardOnPos(0),activePlayer.returnHand());
        int cost = 0;
        if(treasureCardsInHand.getSize() > 0){
            cost = getCostOfCard(activePlayer);
            System.out.println("Geef de positie van een treasure kaart om te kopen met de waarde " + (cost + 3) + " of lager");
            int pos = in.nextInt();
            Card gekozenKaart = treasureCardTable.getCardOnPos(pos);
            if (gekozenKaart.getCost() <= cost+3){
                activePlayer.addCardToDiscardPile(gekozenKaart);
            }else{
                System.out.println("Gekozen kaart is te duur, probeer opnieuw");
                useMine(activePlayer.getNumber());
            }
        }else{
            System.out.println("Geen treasure kaarten in hand gevonden");
        }
    }
    private int getCostOfCard(Player activePlayer){
        int cost = 0;
        System.out.println("Geef de positie van de treasure kaart in hand om weg te doen");
        int i = in.nextInt();
        Card pickedCard = activePlayer.getCardOnPosInHand(i);
        if(pickedCard.getType().equals("treasure")){
            cost = activePlayer.getCardOnPosInHand(i).getCost();
            activePlayer.removeCardFromHand(i);
        }else{
            System.out.println("Gekozen kaart is geen treasure kaart, probeer opnieuw");
            useMine(activePlayer.getNumber());
        }
        return cost;
    }
    private void moveCardFromHandToDiscardPile(int position,Player whichPlayer){
        whichPlayer.moveCardFromHandToDiscard(position);
    }
    private void useChancellor(int numberOfPlayer){
        Player activePlayer = getActivePlayer(numberOfPlayer);
        currentlyActiveAmountOfCoins+=2;
        promptDeckOpStapel(activePlayer);

    }

    private void useCellar(int numberOfPlayer){
        Player activePlayer = getActivePlayer(numberOfPlayer);
        remainingActionsInPhase += 1;
        System.out.println("Geef positie van kaart in de hand om te verplaatsen naar de discard pile, geef 0 om te stoppen");
        int i = in.nextInt();
        while(i != 0){
            //Card card = activePlayer.getCardOnPosInHand(i);
            //activePlayer.addCardToDiscardPile(card);
            //activePlayer.removeCardFromHand(i);
            //activePlayer.addCardFromHandToDiscardPile(card);

            moveCardFromHandToDiscardPilePosition(i, activePlayer);
            activePlayer.addXAmountOfCardsToHand(1);
            printHand(activePlayer);
            System.out.println("Geef positie van kaart in de hand om te verplaatsen naar de discard pile, geef 0 om te stoppen");
            i = in.nextInt();
        }
        printHand(activePlayer);
        printCoins();

    }

    private void useFeast(int numberOfThePlayer){
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
        System.out.println("Geef de positie van de kaart die je wilt kopen"); //TODO: check maken voor de positie
        int positie = in.nextInt();
        Card gekozenKaart = new Card();
        switch (intOfTypeCard){
            case 1:
                gekozenKaart = actionCardTable.getCardOnPos(positie);
                break;
            case 2:
                gekozenKaart = victoryCardTable.getCardOnPos(positie);
                break;
            case 3:
                gekozenKaart = treasureCardTable.getCardOnPos(positie);
                break;
        }
        activePlayer = getActivePlayer(numberOfThePlayer);
        if(gekozenKaart.getCost() <= 5){
            activePlayer.addCardToDiscardPile(gekozenKaart);
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
        System.out.println("Kies een kaart om te kopen met de waarde van " + amountOfCoinsToBuyNextCard + " 1.Actie 2.victory 3.treasure");
        int typeOfCard = in.nextInt();
        if(typeOfCard < 1 && typeOfCard > 3){
            System.out.println("Ongeldige input, probeer opnieuw");
            useFeast(numberOfThePlayer);
        }
        System.out.println("Geef de positie van de kaart die je wilt kopen"); //TODO: check maken voor de positie
        int positie = in.nextInt();
        Card gekozenKaart = new Card();
        switch (typeOfCard){
            case 1:
                gekozenKaart = actionCardTable.getCardOnPos(positie);
                break;
            case 2:
                gekozenKaart = victoryCardTable.getCardOnPos(positie);
                break;
            case 3:
                gekozenKaart = treasureCardTable.getCardOnPos(positie);
                break;
        }
        if(gekozenKaart.getCost() <= amountOfCoinsToBuyNextCard){
            activePlayer.addCardToDiscardPile(gekozenKaart);
        }else{
            System.out.println("Gekozen kaart moet " + amountOfCoinsToBuyNextCard + " coins of minder kosten, probeer opnieuw");
            useRemodel(numberOfThePlayer);
        }
    }


    private void useLibrary(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        while(activePlayer.getHandSize() != 7){
            Card currentCard = activePlayer.getTopCardFromDeck();
            if (!currentCard.getType().equals("action")){
                activePlayer.addCardFromDeckToHand();
            }
        }
    }

    private void useThroneRoom(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        System.out.println("Geef de positie van de actie kaart die je 2x wilt uitvoeren");
        int positie = in.nextInt();
        Card chosenCard = activePlayer.getCardOnPosInHand(positie);
        if(chosenCard.getType().equals("action")){
            for(int i = 0; i < 2; i++){
                useActionCard(chosenCard.getName(), numberOfThePlayer);
            }
        }else{
            System.out.println("Gekozen kaart is geen actie kaart, probeer opnieuw");
            useThroneRoom(numberOfThePlayer);
        }
    }

    private void useChapel(int numberOfThePlayer){
        Player activeplayer  = getActivePlayer(numberOfThePlayer);
        int amountOfTrashesLeft = 4;
        System.out.println("Geef de positie van de kaart om te trashen, je kunt nog " + amountOfTrashesLeft + " kaarten trashen. (Druk op 0 om te stoppen)");
        int i = in.nextInt()-1;
        while (i != 0 && i <= activeplayer.getHandSize()){
            activeplayer.removeCardFromHand(i);
            amountOfTrashesLeft -= 1;
            System.out.println("Geef de positie van de kaart om te trashen, je kunt nog " + amountOfTrashesLeft + " kaarten trashen. (Druk op 0 om te stoppen)");
            i = in.nextInt();
        }
    }

    private void promptDeckOpStapel(Player activePlayer)
    {
        System.out.println("Wil je je deck naar de discardpile sturen? (Ja / Nee)");
        String s = in.nextLine();
        if(s.equals("Ja") || s.equals("ja")) {
            activePlayer.moveAllCardsFromDeckToDiscardPile();
            System.out.println("Deck is verplaatst naar de discardpile");


        } else if(s.equals("Nee") || s.equals("nee")){
            System.out.println("Deck is niet verplaatst naar de discardpile");
        }else{
            System.out.println("Geen ja of nee gevonden, probeer opnieuw");
            promptDeckOpStapel(activePlayer);
        }
    }



    private Deck returnXAmountOfTopCardsOfPlayerY(int amountOfCardsToBeReturned,int numberOfThePlayer){
        Deck top2Cards = new Deck();
        Player toBeScannedPlayer = getActivePlayer(numberOfThePlayer);
        for (int i = 0;i<amountOfCardsToBeReturned;i++){
            top2Cards.addCardToDeck(toBeScannedPlayer.getTopCardFromDeck());
        }
        return top2Cards;
    }
    private Deck scanArrayForXCostCards(int cost,ActionCardTable toBeScannedArray){
        Deck scannedArray = new Deck();

        for (int i=0; i<toBeScannedArray.getSize(); i++){


            if( toBeScannedArray.getCardOnPos(i).getCost()==cost){
                scannedArray.addCardToDeck(toBeScannedArray.getCardOnPos(i));
            }
        }
        return scannedArray;
    }
    private Deck scanArrayForSpecificCard(Card toBeFoundCard,Hand toBeScannedArray){
        Deck scannedArray = new Deck();

        for (int i=0; i<toBeScannedArray.getSize(); i++){
            if( toBeScannedArray.getCardOnPos(i).getType().equals(toBeFoundCard.getType())){
                scannedArray.addCardToDeck(toBeScannedArray.getCardOnPos(i));
            }
        }
        return scannedArray;
    }
    private void addXAmountOfCardsToHandOfPlayerWithNumberY(int amountOfCardsNeeded, int numberOfPLayer){
        allPlayers.get(numberOfPLayer).addXAmountOfCardsToHand(amountOfCardsNeeded);
    }
    private Player getActivePlayer(int numberOfThePlayer){
        return allPlayers.get(numberOfThePlayer);
    }

    public void calculateCoinsOfPlayer(Player player){
        currentlyActiveAmountOfCoins = player.getAmountOfCoinsInHand();
    }
    public int getAmountOfCoinsOfPlayer(){

        return currentlyActiveAmountOfCoins;

    }
    public void moveCardFromHandToDiscardPilePosition(int position, Player whichPlayer){

        whichPlayer.moveCardFromHandToDiscard(position);
    public void moveCardFromHandToDiscardPilePosition(int position, Player whichPlayer){
        whichPlayer.moveCardFromHandToDiscard(position);
    }


    public void useActionCard(String nameOfActionCard,int numberOfThePlayer) {

        switch (nameOfActionCard) {
            case "Cellar":
                useCellar(numberOfThePlayer);
                break;
            case "Chapel":
                useChapel(numberOfThePlayer);
                break;
            case "Chancellor":
                useChancellor(numberOfThePlayer);
                break;
            case "Village":
                useVillage(numberOfThePlayer);
                break;
            case "Woodcutter":
                useWoodCutter(numberOfThePlayer);
                break;
            case "Workshop":
                useWorkshop(numberOfThePlayer);
                break;
            case "Feast":
                useFeast(numberOfThePlayer);
                break;
            case "Militia":
                useMilitia(numberOfThePlayer);
                break;
            case "Moneylender":
                useMoneylender(numberOfThePlayer);
                break;
            case "Remodel":
                useRemodel(numberOfThePlayer);
                break;
            case "Bureaucrat":
                useBureaucrat(numberOfThePlayer);
                break;
            case "Smithy":
                useSmithy(numberOfThePlayer);
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
                useMoat(numberOfThePlayer);
                break;
            case "Council Room":
                useCouncilRoom(numberOfThePlayer);
                break;
            case "Festival":
                useFestival(numberOfThePlayer);
                break;
            case "Laboratory":
                useLaboratory(numberOfThePlayer);
                break;
            case "Library":
                useLibrary(numberOfThePlayer);
                break;
            case "Garden":
                System.out.println("Kaart kan niet gespeeld worden");
                remainingActionsInPhase += 1;
                break;
            case "Market":
                useMarket(numberOfThePlayer);
                break;
            case "Mine":
                useMine(numberOfThePlayer);
                break;
            case "Witch":
                useWitch(numberOfThePlayer);
                break;
            case "Adventurer":
                useAdventurer(numberOfThePlayer);
                break;
        }
    }

// ------------   Print Methods ----------- //


    public void printBoard(){
        printActionCards();
        printVicotryCards();
        printTreasureCards();
    }


    public void printActionCards() {
        System.out.println("---------------");
        System.out.println("Action cards:");
        System.out.println("---------------");
        for (int i = 0; i < actionCardsOnBoard.size(); i++) {
            System.out.println(i +1 + ". " +  actionCardsOnBoard.get(i).getName() +  ", Cost: " + actionCardsOnBoard.get(i).getCost() + ", Amount: " + actionCardsOnBoard.get(i).getAmount());
        }
    }
    public void printVicotryCards() {
        System.out.println("---------------");
        System.out.println("Victory cards:");
        System.out.println("---------------");
        for (int i = 0; i < victoryCardTable.getSize(); i++) {
            System.out.println(i+1 +"."+ victoryCardTable.getCardOnPos(i).getName() +  ", Cost: " + victoryCardTable.getCardOnPos(i).getCost() + ", Amount: " + victoryCardTable.getCardOnPos(i).getAmount());
        }
    }
    public void printTreasureCards() {
        System.out.println("---------------");
        System.out.println("Treasure cards:");
        System.out.println("---------------");
        for (int i = 0; i < treasureCardTable.getSize(); i++) {
            System.out.println(i+1 +"."+ treasureCardTable.getCardOnPos(i).getName() +  ", Cost: " + treasureCardTable.getCardOnPos(i).getCost() + ", Amount: " + treasureCardTable.getCardOnPos(i).getAmount());
        }
    }


    public void printDiscardPile(Player whichPlayer){
        whichPlayer.printDiscardDeck();
    }


    public void printDeck(Player whichPlayer){
        whichPlayer.printDeck();

    }

    public void printHand(Player whichPlayer){
        whichPlayer.printHand();
    }
    public void printCoins() {
        System.out.println("--------------------");
        System.out.println("Amount of coins in current hand:" + currentlyActiveAmountOfCoins);
        System.out.println("--------------------");
    }
    public void printRemainingActions(){
        System.out.println("--------------------");
        System.out.println("Amount of remaining actions:" + remainingActionsInPhase);
        System.out.println("--------------------");

    }
}


