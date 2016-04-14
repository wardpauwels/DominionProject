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

    public void cardsUsedInHand(int index){
        playersDeck.removeFromDeck(index);
    }



    public void discardHand(){
        int handsize = playersHand.showAmountOfCardsInHand();
        for(int i = 0; i < handsize; i++){
            Card c = playersHand.getCardOnPos(i);
            discardPile.addCardToDeck(c);
        }
        playersHand.clearHand();
    }

    public void addToDiscardPile(Card toBeDiscardedCard){
        discardPile.addToDeck(toBeDiscardedCard.getType(), toBeDiscardedCard.getNumber());
    }

    public void printDiscardDeck(){
        int amountInDeck = discardPile.showAmountOfCardsInDeck();
        for(int i = 0; i < amountInDeck; i++){
            System.out.println(discardPile.getCardOnPos(i).getName());
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

    public void printDeck(){
        playersDeck.printDeck();
    }

    public void generateHand(){
        playersHand.generateHand(playersDeck);
    }

    public void addCardFromDeckToHand(){
        playersHand.addCardToHand(playersDeck);
    }

    public void removeCardFromHand(){

    }
}
