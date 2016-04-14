import java.util.ArrayList;
import java.util.Random;

/**
 * @author Jens.Thiel
 */


public class Game {
    private Card[] actionCardsOnBoard;
    private Card[] victoryCardsOnBoard;
    private Card[] treasureCardsOnBoard;
    private VictoryCardTable victoryCardTable = new VictoryCardTable();
    private TreasureCardTable treasureCardTable = new TreasureCardTable();
    private ActionCardTable actionCardTable = new ActionCardTable();
    private Card[] actionCards = actionCardTable.actionCardTable;
    private Card[] victoryCards = victoryCardTable.victoryCardTable;
    private Card[] treasureCards = treasureCardTable.treasureCardTable;
    public ArrayList<Player> allPlayers = new ArrayList<Player>();

    //een linked list van gespeelde kaarten (nog resetten bij iedere 'phase' en opvullen bij iedere 'phase')
    private Deck playedCards = new Deck();
    private int currentlyActiveAmountOfCoins;
    private int remainingActionsInPhase;


    public Game(int amountOfPlayers) {
        addPlayersToArrayList(amountOfPlayers);
        actionCardsOnBoard = new Card[10];
        victoryCardsOnBoard = new Card[4];
        treasureCardsOnBoard = new Card[3];

        generateArray();
        generateBoard();
        generateVictoryCardsOnBoard();
        generateTreasureCardsOnBoard();
    }

    private void addPlayersToArrayList(int amount){
        for(int i=0;i<amount;i++){
            Player newPlayer = new Player();
            newPlayer.setNumber(i);
            allPlayers.add(newPlayer);
        }
    }

    private void resetRemainingActions(){
        remainingActionsInPhase = 1;
    }
    public void nextTurnPlayer(Player whichPlayer){

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
        boughtCard.setAmount(boughtCard.getAmount()-1);
        whichPlayer.addCardToDiscardPile(boughtCard);
        remainingActionsInPhase = remainingActionsInPhase - 1;

    }

    //Switch voor card te analyseren
    public void thisCardHasBeenUsed(Card usedCard) {

        switch (usedCard.getType()) {

            case "action":
                executeSpecificAction();
                break;
            case "treasure":
                //
                calculateCoins(usedCard);
                break;
            case "victory":
                //make alert that tells you its not possible to use victory cards.
                break;
        }
    }

    private void executeSpecificAction(){

    }

    public void calculateCoins(Card usedCard){

        currentlyActiveAmountOfCoins = currentlyActiveAmountOfCoins + usedCard.getNumber() + 1;
    }

    //naam player veranderen
    public void setPlayername(int whichPlayer, String playername){
        allPlayers.get(whichPlayer).setName(playername);
    }

    public void addCardToHand(Player whichPlayer){
        whichPlayer.addCardFromDeckToHand();
    }

    private void generateBoard() {
        //maakt 10 action cards in begin van game
        generateActionBoard();
        generateVictoryCards();
        generateTreasureCards();
    }

    private void generateVictoryCards() {
        for(int i = 0; i < victoryCardsOnBoard.length; i++) {
            int number = victoryCardsOnBoard[i].getNumber();

            victoryCardsOnBoard[i].setName(victoryCards[number].getName());
            victoryCardsOnBoard[i].setCost(victoryCards[number].getCost());
            victoryCardsOnBoard[i].setType(victoryCards[number].getType());
            victoryCardsOnBoard[i].setAmount(victoryCards[number].getAmount());
        }
    }

    private void generateTreasureCards() {
        for(int i = 0; i < treasureCardsOnBoard.length; i++) {
            int number = treasureCardsOnBoard[i].getNumber();

            treasureCardsOnBoard[i].setName(treasureCards[number].getName());
            treasureCardsOnBoard[i].setCost(treasureCards[number].getCost());
            treasureCardsOnBoard[i].setType(treasureCards[number].getType());
            treasureCardsOnBoard[i].setAmount(treasureCards[number].getAmount());
        }
    }

    private void generateArray() {
        for (int i = 0; i < actionCardsOnBoard.length; i++) {
            actionCardsOnBoard[i] = new Card();
        }
        for (int i = 0; i < treasureCardsOnBoard.length; i++) {
            treasureCardsOnBoard[i] = new Card();
        }
        for (int i = 0; i < victoryCardsOnBoard.length; i++) {
            victoryCardsOnBoard[i] = new Card();
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
        fillUpActionCardArray();

    }

    private Card generateActionCard(int number) {
        Card actionCard = new Card();
        actionCard.setNumber(number);
        return actionCard;

    }

    // vult de array met action cards op het board
    private void fillUpActionCardArray() {
        for (int i = 0; i < actionCardsOnBoard.length; i++) {
            int number = actionCardsOnBoard[i].getNumber();

            actionCardsOnBoard[i].setName(actionCards[number - 1].getName());
            actionCardsOnBoard[i].setCost(actionCards[number - 1].getCost());
            actionCardsOnBoard[i].setType(actionCards[number - 1].getType());
            actionCardsOnBoard[i].setAmount(actionCards[number -1].getAmount());
        }
    }

    private int getRandomNumber(int minValue, int maxValue) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(maxValue - minValue + 1) + minValue;
        return randomNumber;
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
        for (int i = 0; i < actionCardsOnBoard.length; i++) {
            System.out.println(i +1 + ". " +  actionCardsOnBoard[i].getName() +  ", Cost: " + actionCardsOnBoard[i].getCost() + ", Amount: " + actionCardsOnBoard[i].getAmount());
        }
    }
    public void printVicotryCards() {
        System.out.println("---------------");
        System.out.println("Victory cards:");
        System.out.println("---------------");
        for (int i = 0; i < victoryCardsOnBoard.length; i++) {
            System.out.println(victoryCardsOnBoard[i].getName() +  ", Cost: " + victoryCardsOnBoard[i].getCost() + ", Amount: " + victoryCardsOnBoard[i].getAmount());
        }
    }
    public void printTreasureCards() {
        System.out.println("---------------");
        System.out.println("Treasure cards:");
        System.out.println("---------------");
        for (int i = 0; i < treasureCardsOnBoard.length; i++) {
            System.out.println(treasureCardsOnBoard[i].getName() +  ", Cost: " + treasureCardsOnBoard[i].getCost() + ", Amount: " + treasureCardsOnBoard[i].getAmount());
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
    public void printCoins(Player whichplayer) {
        System.out.println("--------------------");
        System.out.println("Amount of coins in current hand:");
        System.out.println("--------------------");
        System.out.println(whichplayer.getAmountOfCoinsInHand());
    }
}


