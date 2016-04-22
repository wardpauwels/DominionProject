import java.util.ArrayList;
import java.util.Scanner;

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
    private int remainingBuysInPhase;

    public Scanner in = new Scanner(System.in); // scanner voor user input


    public Game() {
        victoryCardTable = new VictoryCardTable();
        treasureCardTable = new TreasureCardTable();
        actionCardTable = new ActionCardTable();
        allPlayers = new ArrayList<Player>();
    }
    // lijst aanmaken met spelers
    public void createPlayersList(int amount){
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

    //Switch voor card te analyseren TODO: checken of dit wel nodig is?
    public void thisCardHasBeenUsed(Card usedCard) {

        switch (usedCard.getType()) {

            case "action":
                executeSpecificAction(usedCard);
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

    public void addCardToHand(Player whichPlayer){
        whichPlayer.addCardFromDeckToHand();
    }



    //---------- Action Cards ------------- //

    private void useVillage (int numberOfThePlayer) {
        addXAmountOfCardsToHandOfPlayerWithNumberY(1, numberOfThePlayer);
        remainingActionsInPhase = +2;


    }
    private void useMilitia(int numberOfThePlayer){
        addXAmountOfCardsToHandOfPlayerWithNumberY(2, numberOfThePlayer);
    // TODO nog 2de deel van discarden toevoegen enemies tot 3 kaarten laaten discarden met while hand.size > 3

    }
    private void useMoneylender(int numberOfThePlayer){

        if(allPlayers.get(numberOfThePlayer).scanHandForCard(treasureCardTable.getCardOnPos(0))) {

            int pickedCopper = allPlayers.get(numberOfThePlayer).scanHandForCardandGetPositionInHand(treasureCardTable.getCardOnPos(0));
            allPlayers.get(numberOfThePlayer).addCardFromHandToDiscardPile(treasureCardTable.getCardOnPos(0));
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
        currentlyActiveAmountOfCoins =+ 2;
        remainingBuysInPhase =+ 1;
    }
    private void useWorkshop(int numberOfThePlayer){
        Deck availableCards = scanArrayForXCostCards(4,actionCardTable);

        //TODO: speler optie geven om 1 vd kaarten met kost van 4 te kopen, zie deck hierboven



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
        //TODO laatste deeltje van spy maken (robert)
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
            Deck DeckOfPlayerX = returnXAmountOfTopCardsOfPlayerY(2,i);
            //TODO nog toevoegen dat huidige speler kaart(en) moet selecteren (robert)
        }

    }


    private void useBureaucrat(int numberOfThePlayer)
    {
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        activePlayer.addCardToPlaceInDeck(0,treasureCardTable.getCardOnPos(1));
        //TODO nog toevoegen dat andere spelers victory card moeten kiezen (robert)
    }


    private void useMoat(int numberOfThePlayer){
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        activePlayer.addXAmountOfCardsToHand(2);

    }
    private void useMine(int numberOfThePlayer){
/// todo treasure cards in hand zoeken,
        Player activePlayer = getActivePlayer(numberOfThePlayer);
        Deck treasureCardsInHand = scanArrayForSpecificCard(treasureCardTable.getCardOnPos(0),activePlayer.returnHand());
        // todo vraag welke kaart hij wil wegdoen;
        // todo output van de vraag , de kost van de kaart nemen en speler de optie geven om een treasurekaart te kopen met de kost +3
    }

    private void useChancellor(int numberOfPlayer){
        Player activePlayer = getActivePlayer(numberOfPlayer);

        currentlyActiveAmountOfCoins+=2;
        // TODO wil je complete deck op de stapel gooien
        promptDeckOpStapel(activePlayer);

    }

    private void promptDeckOpStapel(Player activePlayer)
    {
        boolean moveall = false;
        System.out.println("Wil je je deck naar de discardpile sturen? (Ja / Nee)");
        String s = in.nextLine();
        if (s.equals("Ja")) {
            moveall = true;
        }
        if(moveall) {
            activePlayer.moveAllCardsFromDeckToDiscardPile();
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

        for (int i=0; i<toBeScannedArray.showAmountOfCardsInHand(); i++){


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


