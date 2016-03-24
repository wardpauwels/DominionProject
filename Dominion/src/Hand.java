
import java.util.*;



public class Hand {
    private ArrayList<Card> hand = new ArrayList<Card>();

    
    
    public Hand(Deck playersDeck){

    }
    
    
     private Card getCardFromDeck(Deck playersDeck){
         getRandom randomNumber = new getRandom();
         int cardFromDeckIndex = randomNumber.getRandomNumber(0,playersDeck.showAmountOfCardsInDeck()-1);

         Card cardFromDeck = playersDeck.getCardOnPos(cardFromDeckIndex);

         playersDeck.removeFromDeck(cardFromDeckIndex);

         return cardFromDeck;
    }
    
    public void generateHand(Deck playersDeck){
        for(int i=0; i < 5; i++){
            Card newCard = getCardFromDeck(playersDeck);
            hand.add(newCard);
        }   
    }
     
    public void addCardToHand(Deck playersDeck){

        hand.add(getCardFromDeck(playersDeck));
    }


    private void clearHand(){
        int handSize = hand.size();
        for(int i=0; i < handSize; i++){
            hand.remove(i);
        }
        
        
    }
    public Card getCardOnPos(int i){
        return hand.get(i);
    }    
    
    public void removeFromHand(int index){
        hand.remove(index);
    }
    
    public void printHand(){
        for (int i = 0; i < hand.size() ;i ++){
            System.out.println(getCardOnPos(i).getName());
            }
        }
    
}
