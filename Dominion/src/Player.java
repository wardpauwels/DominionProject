/**
 * Created by jensthiel on 24/03/16.
 */

public class Player {
    private String name;
    private Deck playersDeck = new Deck();
    private Hand playersHand = new Hand(playersDeck);
    private Deck discardPile = new Deck();




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

    public void addCardFromDeckToHand(){
        playersHand.addCardToHand(playersDeck);
    }
}
