
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

    public Card getCardOnPos(int i){
        return hand.get(i);
    }

    public Card getFirstCard(){
        return hand.get(0);
    }



    public void removeFromHand(int index){
        hand.remove(index);
    }

    public void addSpecificCard(Card toBeAddedCard){
        hand.add(toBeAddedCard);
    }

    
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

