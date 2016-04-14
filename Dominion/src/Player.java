/**
 * Created by jens.thiel on 24/03/16.
 */

public class Player {
    private String name;
    private Deck playersDeck = new Deck();
    private Hand playersHand = new Hand(playersDeck);
    private Deck discardPile = new Deck();



// speler aanmaken, starter deck maken, kaarten schudden, 5 kaarten trekken.
    public Player(){
        playersDeck.generateStarterDeck();
        playersDeck.shuffleDeck();
        playersHand.generateHand(playersDeck);

    }


    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }




    // DECK

    public void printDeck(){

        playersDeck.printCardsInDeck();
    }
    public void cardsUsedInHand(int index){
        playersDeck.removeFromDeck(index);
    }

    public void addToDiscardpile(){
        int handsize = playersHand.showAmountOfCardsInHand();
        for(int i = 0; i < handsize; i++){
            Card c = playersHand.getCardOnPos(i);

            discardPile.addToDeck(c.getType(), c.getNumber());
        }

        playersHand.clearHand();
    }

    public void addCardToDiscardPile(Card toBeAddedCard){

        discardPile.addToDeck(toBeAddedCard.getType(),toBeAddedCard.getNumber());
    }

    public void removeCardFromHand(int spotInHand){
        playersHand.removeFromHand(spotInHand);
    }
    public void addCardFromHandToDiscardPile(Card whichCard){
        int toBeRemovedCard = scanHandForCardandGetPositionInHand(whichCard);
        playersHand.removeFromHand(toBeRemovedCard);

    }

    public void addXAmountOfCardsToHand(int amount){
        for (int i = 0; i < amount ; i++){
            addCardFromDeckToHand();
        }
    }
    public void printDiscardDeck(){
        for(int i = 0; i < discardPile.showAmountOfCardsInDeck(); i++){
            System.out.println(discardPile.getCardOnPos(i));
        }
    }

    public void resetDiscardDeck(){
        playersDeck = discardPile;
        discardPile.clearDeck();
    }

    // HAND
    public void printHand(){

        playersHand.printHand();
    }

    public boolean scanHandForCard(Card whichCard){
        for (int i=0;i < playersHand.showAmountOfCardsInHand(); i++){
            if (playersHand.getCardOnPos(i)==whichCard){
                return true;
            }
        }
        return false;
    }
    public int scanHandForCardandGetPositionInHand(Card whichCard){
        for (int i=0;i < playersHand.showAmountOfCardsInHand(); i++){
            if (playersHand.getCardOnPos(i)==whichCard){
                return i;
            }
        }
        return 0;
    }
//Om 1 kaart te trekken
    public void addCardFromDeckToHand(){
        playersHand.addCardToHand(playersDeck);
    }
// wordt gebruikt aan begin van een beurt om de 5 kaarten te generen
    // NIET GEBRUIKEN OM 1 KAART TE TREKKEN
    public void generateNextHand(){
        int restOfCardsInPlayersDeck = playersDeck.showAmountOfCardsInDeck();
               if (restOfCardsInPlayersDeck<=5)
               {
                   for(int i=0;i<restOfCardsInPlayersDeck;i++){
                       playersHand.addCardToHand(playersDeck);
                   }
                   playersDeck = discardPile;
                   discardPile.clearDeck();
                   playersDeck.shuffleDeck();
               }
               else{
                    playersHand.generateHand(playersDeck);
               }
    }

    public int returnAmountOfCardsInDeck(){
        return playersDeck.showAmountOfCardsInDeck();

    }
}
