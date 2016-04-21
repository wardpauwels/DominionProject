import java.util.ArrayList;

/**
 * @author Jens.Thiel
 */


public class Game {
    private VictoryCardTable victoryCardTable;
    private TreasureCardTable treasureCardTable;
    private ActionCardTable actionCardTable;
    public ArrayList<Player> allPlayers;

    //een linked list van gespeelde kaarten (nog resetten bij iedere 'beurt' en opvullen bij iedere 'beurt')
    private Deck playedCards = new Deck();
    private int currentlyActiveAmountOfCoins;
    private int remainingActionsInPhase;


    public Game() {
        victoryCardTable = new VictoryCardTable();
        treasureCardTable = new TreasureCardTable();
        actionCardTable = new ActionCardTable();
        allPlayers = new ArrayList<Player>();
    }
    // TODO: kijken of dit wel nodig is
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
                boughtCard = actionCardTable.getCardOnPos(positionOnTheBoard-1);
                break;
            case "victory":
                boughtCard = victoryCardTable.getCardOnPos(positionOnTheBoard -1);
                break;
            case "treasure":
                boughtCard = treasureCardTable.getCardOnPos(positionOnTheBoard-1);
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
        for (int i = 0; i < actionCardTable.getSize(); i++) {
            System.out.println(i +1 + ". " +  actionCardTable.getCardOnPos(i).getName() +  ", Cost: " + actionCardTable.getCardOnPos(i).getCost() + ", Amount: " + actionCardTable.getCardOnPos(i).getAmount());
        }
    }
    public void printVicotryCards() {
        System.out.println("---------------");
        System.out.println("Victory cards:");
        System.out.println("---------------");
        for (int i = 0; i < victoryCardTable.getSize(); i++) {
            System.out.println(victoryCardTable.getCardOnPos(i).getName() +  ", Cost: " + victoryCardTable.getCardOnPos(i).getCost() + ", Amount: " + victoryCardTable.getCardOnPos(i).getAmount());
        }
    }
    public void printTreasureCards() {
        System.out.println("---------------");
        System.out.println("Treasure cards:");
        System.out.println("---------------");
        for (int i = 0; i < treasureCardTable.getSize(); i++) {
            System.out.println(treasureCardTable.getCardOnPos(i).getName() +  ", Cost: " + treasureCardTable.getCardOnPos(i).getCost() + ", Amount: " + treasureCardTable.getCardOnPos(i).getAmount());
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


