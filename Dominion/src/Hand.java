
import java.util.*;



public class Hand {
    private ArrayList<Card> hand;

    
    
    public Hand(Deck playersDeck){
        hand = new ArrayList<>();
    }

    //bovenste kaart van het pakje nemen
     private Card getCardFromDeck(Deck playersDeck){
         Card cardFromDeck = playersDeck.getCardOnPos(0);
         playersDeck.removeFromDeck(0);
         return cardFromDeck;
    }

    // 5 kaarten nemen
    public void generateHand(Deck playersDeck){
        for(int i=0; i < 5; i++){
            Card newCard = getCardFromDeck(playersDeck);
            hand.add(newCard);
        }   
    }

    public void addCardToHand(Deck playersDeck){
        hand.add(getCardFromDeck(playersDeck));
    }

    public void clearHand(){
        hand.clear();

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
        int positionOfCard = -1;

        if (scanDeckForCardWithTypeXandReturnBoolean(typeOfCard)) {
            while (positionOfCard == -1) {
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


    public Card getCardOnPos(int i){
        return hand.get(i);
    }

    public Card getFirstCard(){
        return hand.get(0);
    }



    public void removeFromHand(int i){
        hand.remove(i);

    }

    public void addSpecificCard(Card toBeAddedCard){
        hand.add(toBeAddedCard);
    }

    
    public void printHand(){

        for (int i = 0; i < hand.size() ;i ++){
            System.out.println(i+1 + "." + getCardOnPos(i).getName());
        }
    }



    public int getSize(){
        int amount = hand.size();
        return amount;
    }
}

