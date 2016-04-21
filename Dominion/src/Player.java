/**
 * Created by jens.thiel on 24/03/16.
 */

public class Player {
    private String name;
    private Deck playersDeck = new Deck();
    private Hand playersHand = new Hand(playersDeck);
    private Deck discardPile = new Deck();
    private int number;



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

    public int getNumber(){return number;}

    public void setNumber(int number){this.number=number;}


    // DECK

    public void printDeck(){

        playersDeck.printDeck();
    }


    public void cardsUsedInHand(int index){
        playersDeck.removeFromDeck(index);
    }

    public int getAmountOfCoinsInHand(){
        int handsize = playersHand.showAmountOfCardsInHand();
        int amountOfCoins = 0;
        for (int i = 0; i < handsize; i++){
            Card currentCard = playersHand.getCardOnPos(i);

            if(currentCard.getType() == "treasure"){
                name = currentCard.getName();
                switch (name){
                    case "Copper":
                        amountOfCoins += 1;
                        break;
                    case "Silver":
                        amountOfCoins += 2;
                        break;
                    case "Gold":
                        amountOfCoins +=3;
                        break;

                }

            }
        }
        return amountOfCoins;

    }

    public void addToHandDiscardpile(){
        int handsize = playersHand.showAmountOfCardsInHand();
        for(int i = 0; i < handsize; i++){
            Card c = playersHand.getCardOnPos(i);

            discardPile.addToDeck(c.getType(), c.getNumber());
        }

        playersHand.clearHand();
    }

    public void addCardToDiscardPile(Card toBeAddedCard){
        discardPile.addCardToDeck(toBeAddedCard);
    }


    public void resetDiscardDeck(){
        playersDeck = discardPile;
        discardPile.clearDeck();
    }

    // HAND

    //Om 1 kaart te trekken
    public void addCardFromDeckToHand(){
        playersHand.addCardToHand(playersDeck);
    }

    // wordt gebruikt aan begin van een beurt om de 5 kaarten te generen
    // NIET GEBRUIKEN OM 1 KAART TE TREKKEN
    public void generateNextHand(){
        int restOfCardsInPlayersDeck = playersDeck.getSize();
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
        return playersDeck.getSize();

    }



    // --------------------   Print Methods -------------------//

    public void printHand(){
        System.out.println("---------------");
        System.out.println("Hand:");
        System.out.println("---------------");
        playersHand.printHand();
    }


    public void printDiscardDeck(){
        System.out.println("---------------");
        System.out.println("Discard pile:");
        System.out.println("---------------");
        for(int i = 0; i < discardPile.getSize(); i++){

            System.out.println(discardPile.getCardOnPos(i).getName());
        }
    }

}
