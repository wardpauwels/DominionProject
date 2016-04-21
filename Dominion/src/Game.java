import java.util.*;


/**
 * @author Jens.Thiel
 */


public class Game {
    private Card[] actionCardsOnBoard;
    private Card[] victoryCardsOnBoard;
    private Card[] treasureCardsOnBoard;
    private VictoryCardTable victoryCardTable = new VictoryCardTable();
    private TreasureCardTable treasureCardTable = new TreasureCardTable();
    private ActionCardTable allActionCards = new ActionCardTable();
    private Card[] actionCards = allActionCards.actionCardTable;
    public Player playerOne = new Player();
    public Player playerTwo = new Player();
    private ArrayList<Player> allPlayers;
    //een linked list van gespeelde kaarten (nog resetten bij iedere 'phase' en opvullen bij iedere 'phase')
    private Deck playedCards = new Deck();
    private int currentlyActiveAmountOfCoins;
    private int remainingActionsInPhase;
    private int amountOfActionsInNextPhase;

    public Game(int amountOfPlayers) {
        addPlayersToArrayList(amountOfPlayers);
        actionCardsOnBoard = new Card[10];
        generateArray();
        generateBoard();
        generateVictoryCardsOnBoard();
        generateTreasureCardsOnBoard();
    }

    private void resetRemainingActions(){
        remainingActionsInPhase = 1;
    }
    public void nextTurnPlayer(Player whichPlayer){

    }
    private void addPlayersToArrayList(int amount){
        for(int i=0;i<amount;i++){
            Player newPlayer = new Player();
            newPlayer.setNumber(i);
            allPlayers.add(newPlayer);
        }
    }
    private void generateVictoryCardsOnBoard(){
        victoryCardsOnBoard = victoryCardTable.victoryCardTable;
    }
    private void generateTreasureCardsOnBoard() {
     treasureCardsOnBoard = treasureCardTable.treasureCardTable;
    }
    public void nextTurnFor (Player whichPlayer){
        currentlyActiveAmountOfCoins = 0;


    }

    private void ExecuteDrawPhase(Player whichPlayer){
        whichPlayer.generateNextHand();
    }





    public void buyCard(int positionOnTheBoard, String type, Player whichPlayer){
        Card boughtCard = new Card();
        switch(type){
            case "action":
                boughtCard = actionCardsOnBoard[positionOnTheBoard-1];

                break;
            case "victory":
                boughtCard = victoryCardsOnBoard[positionOnTheBoard-1];
                break;
            case "treasure":
                boughtCard = treasureCardsOnBoard[positionOnTheBoard-1];
                break;
        }
        currentlyActiveAmountOfCoins -= boughtCard.getCost();
        boughtCard.setAmount(boughtCard.getAmount()-1);
        whichPlayer.addCardToDiscardPile(boughtCard);
        remainingActionsInPhase = remainingActionsInPhase - 1;



    }
    public void printDeck(Player whichPlayer){
        whichPlayer.printDeck();

    }
    public void printHand(Player whichPlayer){
        whichPlayer.printHand();
    }
    //Switch voor card te analyseren
    public void thisCardHasBeenUsed(Card usedCard) {


        switch (usedCard.getType()) {

            case "action":
                executeSpecificAction(usedCard.getNumber());
                break;
            case "treasure":
                calculateCoins(usedCard);
                break;
            case "victory":
                //make alert that tells you its not possible to use victory cards.
                break;

        }
    }

    private void executeSpecificAction(int numberOfCard){


    }

    private void calculateCoins(Card usedCard){
        currentlyActiveAmountOfCoins = currentlyActiveAmountOfCoins + usedCard.getNumber() + 1;
    }

    //naam player veranderen
    public void setPlayername(Player whichPlayer, String playername){
        whichPlayer.setName(playername);
    }

    public void addCardToHand(Player whichPlayer){
        whichPlayer.addCardFromDeckToHand();
    }


    private void generateBoard() {
        //maakt 10 action cards in begin van game
        generateActionBoard();

    }
// vult de array met action cards op het board
    private void fillUpArray() {
        for (int i = 0; i < actionCardsOnBoard.length; i++) {
            int number = actionCardsOnBoard[i].getNumber();
            actionCardsOnBoard[i].setName(actionCards[number - 1].getName());
            actionCardsOnBoard[i].setCost(actionCards[number - 1].getCost());
            actionCardsOnBoard[i].setType(actionCards[number - 1].getType());


        }

    }

    private Card generateActionCard(int number) {

        Card actionCard = new Card();
        actionCard.setNumber(number);


        return actionCard;


    }

    private void generateArray() {
        for (int i = 0; i < actionCardsOnBoard.length; i++) {
            actionCardsOnBoard[i] = new Card();
        }
    }

    private void generateActionBoard() {

        for (int i = 0; i < 10; i++) {
            int randomNumber = getRandomNumber(1, 25);
            while (!checkRandom(randomNumber)) {
                randomNumber = getRandomNumber(1, 25);
            }

            actionCardsOnBoard[i] = generateActionCard(randomNumber);
        }
        fillUpArray();

    }

    private boolean checkRandom(int randomNumber) {
        for (int i = 0; i < actionCardsOnBoard.length; i++) {


            int currentNumber = actionCardsOnBoard[i].getNumber();


            if (randomNumber == currentNumber) {
                return false;
            }
        }
        return true;
    }

    private int getRandomNumber(int minValue, int maxValue) {
        Random rand = new Random();

        int randomNumber = rand.nextInt(maxValue - minValue + 1) + minValue;

        return randomNumber;
    }

    public void printArray() {
        for (int i = 0; i < actionCardsOnBoard.length; i++) {

            System.out.println(actionCardsOnBoard[i].getNumber() + ", " + actionCardsOnBoard[i].getName() + ", " + actionCardsOnBoard[i].getType() + ", " + actionCardsOnBoard[i].getCost());
        }
    }

/*
    private void useCellar(Player whichPlayer){
        remainingActionsInPhase += 1;
        while (keepDiscarding()){
            whichPlayer.addCardToDiscardPile();
        }

    }
    private boolean keepDiscarding(){

        if (pressButton()) {
            return false;
        }
        else{
            return true;
        }
    }

    public boolean pressButton(){
        return true;

    }
*/
private void useVillage (int numberOfThePlayer) {
    addXAmountOfCardsToHandOfPlayerWithNumberY(1, numberOfThePlayer);
    remainingActionsInPhase = +2;


}
    private void useMilitia(int numberOfThePlayer){
        addXAmountOfCardsToHandOfPlayerWithNumberY(2, numberOfThePlayer);
/* nog 2de deel van discarden toevoegen */

    }
    private void useMoneylender(int numberOfThePlayer){

       if(allPlayers.get(numberOfThePlayer-1).scanHandForCard(treasureCardsOnBoard[0])) {

           int pickedCopper = allPlayers.get(numberOfThePlayer-1).scanHandForCardandGetPositionInHand(treasureCardsOnBoard[0]);
           allPlayers.get(numberOfThePlayer-1).addCardFromHandToDiscardPile(treasureCardsOnBoard[0]);
           currentlyActiveAmountOfCoins=+3;
       }
       };

    private void useSmithy(int numberOfThePlayer){
        allPlayers.get(numberOfThePlayer-1).addXAmountOfCardsToHand(3);
    }
    private void useWitch(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        activePlayer.addXAmountOfCardsToHand(2);
        for (int i=0;i<allPlayers.size();i++){
            if(i!=numberOfThePlayer-1){
                allPlayers.get(i).addCardToDiscardPile(victoryCardsOnBoard[3]);
            }

        }

    }
    private void useThroneRoom(int numberOfThePlayer,int positionOfCardThatsNeeded){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        Card toBeUsedCard = activePlayer.getCardOnPosInHand(positionOfCardThatsNeeded);
        for (int i=0;i<2;i++) {
            executeSpecificAction(toBeUsedCard.getNumber());
        }
    }
    private void useWoodCutter(int numberOfThePlayer){
        currentlyActiveAmountOfCoins =+ 2;
        amountOfActionsInNextPhase =+ 1;
    }
    private void useWorkshop(int numberOfThePlayer){
        ArrayList<Card> availableCards = scanArrayForXCostCards(4,actionCardsOnBoard);



    }

    private void useFestival(int numberOfThePlayer){
        remainingActionsInPhase += 2;
        amountOfActionsInNextPhase += 1;
        currentlyActiveAmountOfCoins += 2;
    }

    private void useCouncilRoom(int numberOfThePlayer){
        addXAmountOfCardsToHandOfPlayerWithNumberY(4,numberOfThePlayer);
        amountOfActionsInNextPhase += 1;

    }
    private void useLaboratory(int numberOfThePlayer){
        addXAmountOfCardsToHandOfPlayerWithNumberY(2,numberOfThePlayer);
        remainingActionsInPhase += 1;

    }
    private void useMarket(int numberOfThePlayer){
        addXAmountOfCardsToHandOfPlayerWithNumberY(1,numberOfThePlayer);
        remainingActionsInPhase += 1;
        amountOfActionsInNextPhase += 1;
        currentlyActiveAmountOfCoins += 1;
    }

    private void useSpy(int numberOfThePlayer){
        addXAmountOfCardsToHandOfPlayerWithNumberY(1,numberOfThePlayer);
        remainingActionsInPhase+=1;
        //laatste deeltje van spy maken (robert)
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

    }
    private void useThief(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        for (int i = 0; i < allPlayers.size(); i++){
            Deck DeckOfPlayerX = returnXamountOfTopCardsOfPlayerY(2,i);

        }

    }

    private Deck returnXamountOfTopCardsOfPlayerY(int amountOfCardsToBeReturned,int numberOfThePlayer){
        Deck top2Cards = new Deck();
        Player toBeScannedPlayer = getActivePlayer(numberOfThePlayer);
        for (int i = 0;i<amountOfCardsToBeReturned;i++){
            top2Cards.addCardToDeck(toBeScannedPlayer.getTopCardFromDeck());
        }
        return top2Cards;
    }
    private ArrayList<Card> scanArrayForXCostCards(int cost,Card[] toBeScannedArray){
        ArrayList<Card> scannedArray ;
        scannedArray = new ArrayList<Card>();
        for (int i=0; i<toBeScannedArray.length; i++){
           if( toBeScannedArray[i].getCost()==cost){
               scannedArray.add(toBeScannedArray[i]);
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

    }





