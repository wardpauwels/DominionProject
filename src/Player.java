/**
 * Created by jens.thiel on 24/03/16.
 */
import java.util.ArrayList;


public class Player {
    private String name;
    public Deck playersDeck = new Deck();
    public Deck playersHand = new Deck();
    public Deck discardPile = new Deck();
    private int number;
    public int victoryPoints;
    public boolean cursedByMilitia = false;



// speler aanmaken, starter deck maken, kaarten schudden, 5 kaarten trekken.
    public Player(){
        playersDeck.generateStarterDeck();
        playersDeck.shuffleDeck();
        playersHand.generateHand(playersDeck);

    }
    public void moveCardFromDeckToDiscard(){
        discardPile.addCardToDeck(playersDeck.getCardOnPos(0));
        playersDeck.removeFromDeck(0);

    }
    public void calculateVictoryPoints(){
        int sizeOfCompleteDeck = playersHand.getSize()+discardPile.getSize()+playersDeck.getSize();
        playersDeck.actionCards.setGarden((int)Math.floor(sizeOfCompleteDeck/10));
        discardPile.actionCards.setGarden((int)Math.floor(sizeOfCompleteDeck/10));
        int totalAmountOfPoints=0;
        for (int i=0;i<playersDeck.getSize();i++){
            totalAmountOfPoints += playersDeck.getCardOnPos(i).getPoints();
        }

        for (int i=0;i<discardPile.getSize();i++){
            totalAmountOfPoints += discardPile.getCardOnPos(i).getPoints();
        }
        for (int i=0;i<playersHand.getSize();i++){
            totalAmountOfPoints += playersHand.getCardOnPos(i).getPoints();
        }
        victoryPoints = totalAmountOfPoints;
        System.out.println(totalAmountOfPoints);
    }


    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public int getNumber(){return number;}

    public void setNumber(int number){this.number=number;}


    // DECK

    public void printDeck(){
        playersDeck.printDeck();
    }


    public void removeFromDeck(int index){
        playersDeck.removeFromDeck(index);
    }

    public int getAmountOfCoinsInHand()// wordt atm alleen gebruikt om het aantal coins te printen.
    {
        int handsize = playersHand.getSize();
        int amountOfCoins = 0;
        for (int i = 0; i < handsize; i++){
            Card currentCard = playersHand.getCardOnPos(i);

            if(currentCard.getType().equals("treasure")){
                String cardName = currentCard.getName();
                switch (cardName){
                    case "Copper":
                        amountOfCoins += 1;
                        break;
                    case "Silver":
                        amountOfCoins += 2;
                        break;
                    case "Gold":
                        amountOfCoins += 3;
                        break;

                }

            }
        }
        return amountOfCoins;

    }

    public void addToHandDiscardpile(){
        int handsize = playersHand.getSize();
        for(int i = 0; i < handsize; i++){
            Card c = playersHand.getCardOnPos(i);

            discardPile.addToDeck(c.getType(), c.getNumber());
        }

        playersHand.clearHand();
    }

    public void addCardToDiscardPile(Card toBeAddedCard){
        discardPile.addCardToDeck(toBeAddedCard);
    }


    public void resetDiscardPile(){
        playersDeck = discardPile;
        discardPile.clearDeck();
    }

    // HAND

    public void addXAmountOfCardsToHand(int amount){

        for (int i = 0; i < amount ; i++){
            if (playersDeck.getSize()<1){
                for(int j = 0 ;j<discardPile.getSize(); j ++){
                    playersDeck.addCardToDeck(discardPile.getCardOnPos(j));
                }

                playersDeck.shuffleDeck();
                discardPile.clearDeck();
            }
            addCardFromDeckToHand();
        }
    }



    public boolean scanHandForCard(Card whichCard){
        for (int i=0;i < playersHand.getSize(); i++){
            if (playersHand.getCardOnPos(i).getName().equals(whichCard.getName())){
                return true;
            }
        }
        return false;
    }


    public int scanHandForCardAndReturnPosition(String type)
    {
        return playersHand.scanDeckForCardWithTypeXandReturnPosition(type);
    }
    public int scanHandForCardandGetPositionInHand(Card whichCard){
      int positionOfCardInHand=-1;
        for (int i=0;i<playersHand.getSize();i++){
            if(playersHand.getCardOnPos(i).getName().equals(whichCard.getName())){
                positionOfCardInHand=i;
            }
        }
    return positionOfCardInHand;
    }

    public void removeCardFromHand(int spotInHand){
        playersHand.removeFromHand(spotInHand);
    }

    public void addCardFromHandToDiscardPile(Card whichCard){
        int toBeRemovedCard = scanHandForCardandGetPositionInHand(whichCard);
        discardPile.addCardToDeck(whichCard);
        playersHand.removeFromHand(toBeRemovedCard);
    }
    public void moveCardFromHandToDiscard(int spotInHand){
        discardPile.addCardToDeck(playersHand.getCardOnPos(spotInHand));
        playersHand.removeFromHand(spotInHand);
    }
    public int scanHandForCardWithName(String nameOfCard){
        int pos = -1;
        for(int i = 0; i < playersHand.getSize();i++) {
            if(playersHand.getCardOnPos(i).getName().equals(nameOfCard)){
                pos = i;
            }
        }
        return pos;
    }


    public int getHandSize(){
        return playersHand.getSize();
    }

    public void addCardFromHandToDeck(Card c){
        playersDeck.addCardToDeck(c);
        playersHand.removeFromHand(scanHandForCardandGetPositionInHand(c));
    }

    //Om 1 kaart te trekken
    public void addCardFromDeckToHand(){
        playersHand.addCardToHand(playersDeck);
    }

    // wordt gebruikt aan begin van een beurt om de 5 kaarten te generen
    // NIET GEBRUIKEN OM 1 KAART TE TREKKEN
    public void clearHand(){
        playersHand.clearHand();
    }

    public void generateNextHand(){
        int restOfCardsInPlayersDeck = playersDeck.getSize();
        if (restOfCardsInPlayersDeck<5){
            int cardsNeededToFillHand = 5-restOfCardsInPlayersDeck;

                   addXAmountOfCardsToHand(restOfCardsInPlayersDeck);
           for (int i=0;i<discardPile.getSize();i++){
                   playersDeck.addCardToDeck(discardPile.getCardOnPos(i));
            }

                   discardPile.clearDeck();
                   playersDeck.shuffleDeck();
                   addXAmountOfCardsToHand(cardsNeededToFillHand);
               }
               else{
                    playersHand.generateHand(playersDeck);
               }
    }


    public Card getTopCardFromDeck(){
        return playersDeck.getCardOnPos(0);
    }
    public void checkIfCardsInDeckAndMoveDiscardToDeck(){
        if (playersDeck.getSize()==0){
            for(int i = 0; i <discardPile.getSize();i++){
                playersDeck.addCardToDeck(discardPile.getCardOnPos(i));

            }
        }
    }


    public void addSpecificCardToHand(Card toBeAddedCard){
        playersHand.addSpecificCard(toBeAddedCard);
    }

    public Card getCardOnPosInHand(int position){
        return playersHand.getCardOnPos(position);
    }
    public void addCardToPlaceInDeck(int position,Card specificCard){
        playersDeck.addCardToSpecificPositionInDeck(position, specificCard);
    }
    public Deck returnHand(){
        return playersHand;
    }

    public void moveAllCardsFromDeckToDiscardPile(){

        for(int i = 0;i<playersDeck.getSize();i++){
            discardPile.addCardToDeck(playersDeck.getCardOnPos(0));
            playersDeck.removeFromDeck(0);
        }
    }

    public void addSpecificCardToDeck(Card c){
        playersDeck.addCardToDeck(c);
    }


    public boolean scanDiscardPileForCard(Card whichCard){
        for (int i=0;i < discardPile.getSize(); i++){
            if (discardPile.getCardOnPos(i).getName().equals(whichCard.getName())){
                return true;
            }
        }
        return false;
    }


    // --------------------   Print Methods -------------------//

    public void printHand(){
        System.out.println("---------------");
        System.out.println("Hand:");
        System.out.println("---------------");
        playersHand.printHand();
    }


    public void printDiscardPile(){
        System.out.println("---------------");
        System.out.println("Discard pile:");
        System.out.println("---------------");
        for(int i = 0; i < discardPile.getSize(); i++){

            System.out.println(discardPile.getCardOnPos(i).getName());
        }
    }

}
