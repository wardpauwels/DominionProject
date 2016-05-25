import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * @author Jens.Thiel
 */


public class Game {
    public VictoryCardTable victoryCardTable;
    public TreasureCardTable treasureCardTable;
    public ActionCardTable actionCardTable;
    public ArrayList<Player> allPlayers;
    public ArrayList<Card> actionCardsOnBoard;

    //een linked list van gespeelde kaarten (nog resetten bij iedere 'beurt' en opvullen bij iedere 'beurt')
    private Deck playedCards = new Deck(); // TODO: wordt niet gebruikt. kan weg?
    public int currentlyActiveAmountOfCoins;
    private int remainingActionsInPhase = 1;
    private int remainingBuysInPhase = 1;
    public int player = 0;
    public int decisionOfPlayerPosition;
    public String decisionOfPlayerType;
    public int currentPhase = 0; // 0 = Action phase 1 = Buy phase
    public boolean actionToBuyCard = false;
    public int amountOfCardsToBeDiscarded = 0;
    public int amountOfCardsToBeTrashed = 0;
    public Card selectedCard;
    public String currentAction;

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

    public int getPlayer(){
        return player;
    }

    public void setDecisionOfPlayerPosition(int numberOfDecision){
        this.decisionOfPlayerPosition = numberOfDecision;
    }
    public void setDecisionOfPlayerType(String typeOfDecision){
        decisionOfPlayerType = typeOfDecision;
    }

    //Random number generator voor het maken van de lijst van actie kaarten
    private int getRandomNumber(int minValue, int maxValue) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(maxValue - minValue + 1) + minValue;
        return randomNumber;
    }
    public int returnRemainingBuys(){
        return remainingBuysInPhase;
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

    public void nextPhase(){
        if(currentPhase == 0){
            currentPhase += 1;
        } else{
            System.out.println("Phase kan niet hoger dan 1");
        }
    }
    //Lijst met 10 random actie kaarten wordt hier gemaakt
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

    public int returnPositionOnBoardForCardWithNumber(int numberOfCard){
        for (int i = 0;i<actionCardsOnBoard.size();i++){
            if(actionCardsOnBoard.get(i).getNumber()==numberOfCard){
                return i;

            }
        }
        return -1;
    }
    public void nextPlayer() {
        if (player != allPlayers.size() - 1) {
            player++;
        } else {
            player = 0;
        }
    }
    public void endTurn() {
        Player currentPlayer = allPlayers.get(player);
        currentPlayer.addToHandDiscardpile();
        currentPlayer.clearHand();
        endTurnForPlayer(currentPlayer);

    }
    public void nextTurn() {

        nextPlayer();


    }



    public void playActionCard() {
        Player activePlayer = allPlayers.get(player);

            Card toBePlayedActionCard = allPlayers.get(player).getCardOnPosInHand(decisionOfPlayerPosition);
            if (toBePlayedActionCard.getType().equals("action")) {
                moveCardFromHandToDiscardPilePosition(decisionOfPlayerPosition, activePlayer);
                useActionCard(toBePlayedActionCard.getName(), player);
                lowerAmountOfActions();
                System.out.println(toBePlayedActionCard.getName() + " is gespeeld");



        }

    }



    private void resetRemainingActions(){ //TODO: wordt niet gebruikt. kan weg?
        remainingActionsInPhase = 1;
    }


    public void ExecuteDrawPhase(Player whichPlayer){ //TODO: wordt niet gebruikt. kan weg?
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



    public Hand returnHand(Player whichPlayer){
        return whichPlayer.returnHand();
    }

    public void buyCard(){
        Card boughtCard = new Card();
        switch(decisionOfPlayerType){
            case "action":
                boughtCard = actionCardsOnBoard.get(decisionOfPlayerPosition);
                break;
            case "victory":
                boughtCard = victoryCardTable.getCardOnPos(decisionOfPlayerPosition);
                break;
            case "treasure":
                boughtCard = treasureCardTable.getCardOnPos(decisionOfPlayerPosition);
                break;
        }
        if (getAmountOfCoinsOfPlayer()>=boughtCard.getCost()) {
            boughtCard.setAmount(boughtCard.getAmount() - 1);
            currentlyActiveAmountOfCoins -= boughtCard.getCost();
            allPlayers.get(player).addCardToDiscardPile(boughtCard);
            remainingBuysInPhase -= 1;
            if (!checkRemainingActions(remainingBuysInPhase)) {
                endTurn();
            }
        }
    }

    private boolean checkRemainingActions(int actions){
        if (actions==0){
            return false;
        }
        else{
            return true;
        }
    }
    public Deck useThiefGui() {
        Deck top2CardsOfPlayer = new Deck();
        for (int i = 0; i < 2; i++) {

        }
        return top2CardsOfPlayer;
    }

    private void executeSpecificAction(Card card){
        card.getNumber();
    }


    //naam player veranderen
    public void setPlayername(int whichPlayer, String playername){
        allPlayers.get(whichPlayer).setName(playername);
    }
    public String getPlayerName(int whichPlayer){
        return allPlayers.get(whichPlayer).getName();
    }

    public void addCardToHand(Player whichPlayer){ //TODO: wordt niet gebruikt. kan weg?
        whichPlayer.addCardFromDeckToHand();
    }

    public boolean checkIfFinished(){
        boolean finished = false;
        for(int i = 0; i < actionCardTable.getSize(); i++)
            if(actionCardTable.getCardOnPos(i).getAmount() == 0){
                finished = true;
            }
        if(victoryCardTable.getCardOnPos(2).getAmount() == 0) {
            finished = true;
        }
        for(int i = 0; i < allPlayers.size(); i ++){
            allPlayers.get(i).calculateVictoryPoints();
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
        remainingActionsInPhase = 1;
        remainingBuysInPhase = 1;
        currentlyActiveAmountOfCoins = 0;
    }
    public int returnAmountOfActionsRemaining(){
        return remainingActionsInPhase;
    }
    public void endPhase(){
        if(currentPhase==0){
            calculateCoinsOfPlayer(allPlayers.get(player));
            remainingActionsInPhase = 0;
            nextPhase();}
    }
    public void setRemainingActionsInPhase(int amount){
        remainingActionsInPhase = amount;
    }

    //---------- Action Cards ------------- //

    private void useVillage (int numberOfThePlayer) {
        addXAmountOfCardsToHandOfPlayerWithNumberY(1, numberOfThePlayer);
        remainingActionsInPhase +=2;


    }

    public void resetPhase(){
        currentPhase = 0;
        amountOfCardsToBeTrashed = 0;
        amountOfCardsToBeDiscarded = 0;
        currentAction = "";
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


    public void useMoneylender(int numberOfThePlayer){
        currentAction = "moneylender";
        amountOfCardsToBeTrashed +=1;
    }

    public void moneyLenderCopperFound(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        currentlyActiveAmountOfCoins += 3;
        activePlayer.removeCardFromHand(decisionOfPlayerPosition);
    }


    private void useSmithy(int numberOfThePlayer){
        allPlayers.get(numberOfThePlayer).addXAmountOfCardsToHand(3);
    }

    private void useWitch(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        activePlayer.addXAmountOfCardsToHand(2);
        for (int i=0;i<allPlayers.size();i++){
            if(i!=numberOfThePlayer){
                if(!checkForCard(actionCardTable.getCardOnPos(15), getActivePlayer(i)))
                {
                    getActivePlayer(i).addCardToDiscardPile(victoryCardTable.getCardOnPos(3));
                    victoryCardTable.getCardOnPos(3).setAmount(victoryCardTable.getCardOnPos(3).getAmount()-1);
                }
            }
        }
    }


    private boolean checkForCard(Card toFindCard, Player specificPlayer){
        return specificPlayer.scanHandForCard(toFindCard);
    }

    private void useWoodCutter(int numberOfThePlayer){
        currentlyActiveAmountOfCoins += 2;
        remainingBuysInPhase += 1;
    }

    private void useWorkshop(int numberOfThePlayer){

        remainingBuysInPhase += 1;
        currentlyActiveAmountOfCoins = 4;
        actionToBuyCard = true;

        /*System.out.println("Wil je een 1. action, 2. victory of 3. treasure kaart kopen? (1 - 3)");
        int intOfTypeCard = in.nextInt();
        if(intOfTypeCard < 1 && intOfTypeCard > 3){
            System.out.println("Ongeldige input, probeer opnieuw");
            useWorkshop(numberOfThePlayer);
        }
        System.out.println("Geef de positie van de kaart die je wilt kopen van 4 coins of lager"); //TODO: check maken voor de positie
        int position = in.nextInt();
        Card chosenCard = new Card();

        chosenCard = getCardOnPosWithType(intOfTypeCard, position);

        Player activePlayer = getActivePlayer(numberOfThePlayer);
        if(chosenCard.getCost() <= 4){
            activePlayer.addCardToDiscardPile(chosenCard);
        }else{
            System.out.println("Gekozen kaart moet 4 coins of minder kosten, probeer opnieuw");
            useWorkshop(numberOfThePlayer);
        }*/

    }

    private Card getCardOnPosWithType(int typeOfCard, int posInTable){
        Card gekozenKaart = new Card();

        switch (typeOfCard){
            case 1:
                gekozenKaart = actionCardTable.getCardOnPos(posInTable);
                break;
            case 2:
                gekozenKaart = victoryCardTable.getCardOnPos(posInTable);
                break;
            case 3:
                gekozenKaart = treasureCardTable.getCardOnPos(posInTable);
                break;
        }
        gekozenKaart.setAmount(gekozenKaart.getAmount() -1);
        return gekozenKaart;
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
            System.out.println(currentCard.getName());
            System.out.println("Wat wil je doen met deze kaart: 1.Bovenaan op deck. 2.Op de discardpile");
            int keuze = in.nextInt();
            if(keuze == 2){
                currentPlayer.addCardToDiscardPile(currentCard);
                currentPlayer.removeFromDeck(0);
            }
        }
    }

    public void useAdventurer(int numberOfThePlayer) {
        int amountOfTreasureCardsFound = 0;
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        while (amountOfTreasureCardsFound != 2) {
            Card topCard = activePlayer.getTopCardFromDeck();
            if (topCard.getType().equals("treasure")) {
                amountOfTreasureCardsFound += 1;
                activePlayer.addSpecificCardToHand(topCard);
            } else {
                activePlayer.removeFromDeck(0);
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
                Deck deckOfPlayerX = returnXAmountOfTopCardsOfPlayerY(2,i);// TODO: bug: krijgt altijd 2 de zelfde kaarten terug
                System.out.println("1: "+ deckOfPlayerX.getCardOnPos(0).getName() + " 2:" + deckOfPlayerX.getCardOnPos(1).getName());
                System.out.println("Geef positie van kaart om af te pakken");
                int positie  = in.nextInt();
                positie  = positie - 1;
                getActivePlayer(i).removeFromDeck(positie);
                tmpDeck.add(deckOfPlayerX.getCardOnPos(positie));
            }
        }
        while(!stopAction || tmpDeck.size() != 0){
            System.out.println("Gestolen kaarten: " );
            for (int i = 0; i < tmpDeck.size(); i++)
                System.out.println((i+1)+ ". " + tmpDeck.get(i).getName());
            System.out.println("Geef positie van kaart om aan je eigen deck toe te voegen of geef 0 om te stoppen");
            int keuze = in.nextInt();
            if(keuze > 0 || keuze <= tmpDeck.size()){
                activePlayer.addCardToDiscardPile(tmpDeck.get(keuze -1));
                tmpDeck.remove(keuze -1);
                if(tmpDeck.size() == 0){
                    stopAction = true;
                }
            } else if (keuze == 0){
                stopAction = true;
            }
        }
    }


    private void useBureaucrat(int numberOfThePlayer)
    {
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        activePlayer.addCardToPlaceInDeck(0,treasureCardTable.getCardOnPos(1));

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
        //promptDeckOpStapel(activePlayer);
        activePlayer.moveAllCardsFromDeckToDiscardPile();

    }

    /*private void promptDeckOpStapel(Player activePlayer)
    {
        System.out.println("Wil je je deck naar de discardpile sturen? (Ja / Nee)");
        String s = in.nextLine();
        if(s.equals("Ja") || s.equals("ja")) {
            activePlayer.moveAllCardsFromDeckToDiscardPile();
            System.out.println("Deck is verplaatst naar de discardpile");


        }else if(s.equals("Nee") || s.equals("nee")){
            System.out.println("Deck is niet verplaatst naar de discardpile");
        }else{
            System.out.println("Geen ja of nee gevonden, probeer opnieuw");
            promptDeckOpStapel(activePlayer);
        }
    }*/

    private void useCellar(int numberOfPlayer){ // Kaart naar discard pile en trek een nieuwe

        remainingActionsInPhase += 1;
        amountOfCardsToBeDiscarded = 5;
        currentAction = "cellar";
    }

    private void useFeast(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);

        Hand playersHand = activePlayer.returnHand();
        for(int i = 0; i < playersHand.getSize(); i++){
            if(playersHand.getCardOnPos(i).getName().equals("Feast")){
                activePlayer.removeCardFromHand(i);
            }
        }
        remainingBuysInPhase += 1;
        currentlyActiveAmountOfCoins = 5;
        actionToBuyCard = true;
    }



    private void useRemodel(int numberOfThePlayer){ // Remodel = kaart trashen, nieuwe kaart kiezen met waarde +2 van getrashte kaart
        // TODO: werkt maar half in de GUI, de overgebleven coins blijven en worden toegevoegd aan current amount.
        amountOfCardsToBeDiscarded += 1;
        remainingBuysInPhase +=1;
        actionToBuyCard = true;
        currentAction = "remodel";
    }

    public void changeCoinsToCostOfCardPlusTwo(){
        currentlyActiveAmountOfCoins = selectedCard.getCost() + 2;
    }

    public boolean discardingCards(){
        if(amountOfCardsToBeDiscarded != 0){
            return true;
        } else{
            return false;
        }
    }

    public boolean trashingCards(){
        if(amountOfCardsToBeTrashed != 0){
            return true;
        }else{
            return false;
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

    public void useThroneRoom(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        System.out.println("Geef de positie van de actie kaart die je 2x wilt uitvoeren");
        Card chosenCard = activePlayer.getCardOnPosInHand(decisionOfPlayerPosition);
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
        amountOfCardsToBeTrashed = 4;
        currentAction = "chapel";
        /*System.out.println("Geef de positie van de kaart om te trashen, je kunt nog " + amountOfTrashesLeft + " kaarten trashen. (Druk op 0 om te stoppen)"); // TODO: check maken voor het aantal kaarten in de hand.
        int i = in.nextInt()-1;
        while (i != 0 && i <= activeplayer.getHandSize()){
            activeplayer.removeCardFromHand(i);
            amountOfTrashesLeft -= 1;
            System.out.println("Geef de positie van de kaart om te trashen, je kunt nog " + amountOfTrashesLeft + " kaarten trashen. (Druk op 0 om te stoppen)");
            i = in.nextInt();
        }*/
    }



    public int getAmountOfPlayers(){
        return allPlayers.size();
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
        currentlyActiveAmountOfCoins = currentlyActiveAmountOfCoins + player.getAmountOfCoinsInHand();
    }
    public int getAmountOfCoinsOfPlayer(){

        return currentlyActiveAmountOfCoins;

    }
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
        printVictoryCards();
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
    public void printVictoryCards() {
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
        whichPlayer.printDiscardPile();
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

    }
    public void printRemainingActions(){
        System.out.println("--------------------");
        System.out.println("Amount of remaining actions:" + remainingActionsInPhase);
        System.out.println("--------------------");

    }
}