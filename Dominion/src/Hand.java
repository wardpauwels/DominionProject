
import java.util.*;



public class Hand {
    private ArrayList<Card> hand = new ArrayList<Card>();

    
    
    public Hand(Deck playersDeck){

    }
    //bovenste kaart van het pakje nemen
    
     private Card getCardFromDeck(Deck playersDeck){
         Card cardFromDeck = playersDeck.getCardOnPos(0);
         playersDeck.removeFromDeck(0);
         return cardFromDeck;
    }
    
    public void generateHand(Deck playersDeck){
        for(int i=0; i < 5; i++){
            Card newCard = getCardFromDeck(playersDeck);
            hand.add(newCard);
        }   
    }
    public Deck scanDeckForCardWithTypeX(String typeOfCard){
        Deck specificCardsInDeck = new Deck();
        for (int i=0;i<hand.size();i++) {
            if (hand.get(i).getType().equals(typeOfCard)){
                specificCardsInDeck.addCardToDeck(hand.get(i));
            }
        }
        return specificCardsInDeck;

    }
    public boolean scanDeckForCardWithTypeXandReturnBoolean(String typeOfCard){
        Deck specificCardsInDeck = new Deck();
        for (int i=0;i<hand.size();i++) {
            if (hand.get(i).getType().equals(typeOfCard)){
                specificCardsInDeck.addCardToDeck(hand.get(i));
            }
        }
        if (specificCardsInDeck.getSize()==0){
            return true;
        }
        else return false;


    }
    public int scanDeckForCardWithTypeXandReturnPosition(String typeOfCard) {
        Deck specificCardsInDeck = new Deck();
        int positionOfCard = 100000;

        if (scanDeckForCardWithTypeXandReturnBoolean(typeOfCard)) {
            while (positionOfCard == 100000) {
                for (int i = 0; i < hand.size(); i++) {
                    if (hand.get(i).getType().equals(typeOfCard)) {
                        positionOfCard = i;
                    }
                }

            }
            return positionOfCard;
        }
        else{

            return -1;
        }

    }

    public void addSpecificCard(Card toBeAddedCard){
        hand.add(toBeAddedCard);
    }
     // 5 kaarten nemen
    public void addCardToHand(Deck playersDeck){

        hand.add(getCardFromDeck(playersDeck));
    }


    public void clearHand(){
        hand.clear();

    }

    public Card getCardOnPos(int i){
        return hand.get(i);
    }    
    
    public void removeFromHand(int index){
        hand.remove(index);
    }

    //testfunctie


    
    public void printHand(){
        for (int i = 0; i < hand.size() ;i ++){
            System.out.println(getCardOnPos(i).getName());
            }
        }
    public int showAmountOfCardsInHand(){
        int amount = hand.size();
        return amount;
    }
}
