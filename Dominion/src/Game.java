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
    private ActionCardTable allActionCards = new ActionCardTable();
    private Card[] actionCards = allActionCards.actionCardTable;
    public Player playerOne = new Player();
    public Player playerTwo = new Player();

    //een linked list van gespeelde kaarten (nog resetten bij iedere 'phase' en opvullen bij iedere 'phase')
    private Deck playedCards = new Deck();
    private int currentlyActiveAmountOfCoins;
    private int remainingActionsInPhase;

    public Game() {
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
                executeSpecificAction();
                break;
            case "treasure":
                calculateCoins(usedCard);
                break;
            case "victory":
                //make alert that tells you its not possible to use victory cards.
                break;

        }
    }

    private void executeSpecificAction(){

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


}


