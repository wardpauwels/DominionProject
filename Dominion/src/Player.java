/**
 * Created by jensthiel on 24/03/16.
 */

public class Player {
    private String name;
    private Deck playersDeck = new Deck();
    private Hand playersHand = new Hand(playersDeck);
    private Deck discardDeck = new Deck();



    public Player(){
        playersDeck.generateStarterDeck();
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

            discardDeck.addToDeck(c.getType(), c.getNumber());
        }

        playersHand.clearHand();
    }

    public void printDiscardple(){
        for(int i = 0; i < discardDeck.showAmountOfCardsInDeck(); i++){
            System.out.println(discardDeck.getCardOnPos(i));
        }
    }

    // HAND
    public void printHand(){

        playersHand.printHand();
    }

    public void addCardFromDeckToHand(){
        playersHand.addCardToHand(playersDeck);
    }
}
