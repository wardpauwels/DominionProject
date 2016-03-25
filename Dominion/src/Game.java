import java.util.Random;

/**
 * @author Jens.Thiel
 */


public class Game {
    private Card[] actionCardsOnBoard;
    private ActionCardTable allActionCards = new ActionCardTable();
    private Card[] actionCards = allActionCards.actionCardTable;
    public Player playerOne = new Player();
    public Player playerTwo = new Player();



    public Game() {
        actionCardsOnBoard = new Card[10];
        generateArray();
        generateBoard();
    }

    public void printDeck(Player whichPlayer){
        whichPlayer.printDeck();
    }

    public void printHand(Player whichPlayer){
        whichPlayer.printHand();
    }

    public void setPlayername(Player whichPlayer, String playername){
        whichPlayer.setName(playername);
    }

    public void addCardToHand(Player whichPlayer){
        whichPlayer.addCardFromDeckToHand();
    }


    private void generateBoard() {

        generateActionBoard();

    }

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
        actionCard.SetNumber(number);


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


