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
        System.out.println(handsize);
        for(int i = 0; i < handsize -1; i++){
            Card c = playersHand.getCardOnPos(i);
            System.out.println(c.getName());

            discardPile.addToDeck(c.getType(), c.getNumber());
            discardPile.printCardsInDeck();

        }
        playersHand.clearHand();
    }

    public void printDiscardDeck(){
        int amountInDeck = discardPile.showAmountOfCardsInDeck() -1 ;
        for(int i = 0; i < amountInDeck; i++){
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
    public void generateHand(){
        playersHand.generateHand(playersDeck);
    }

    public void addCardFromDeckToHand(){
        playersHand.addCardToHand(playersDeck);
    }
}
