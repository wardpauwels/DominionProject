
import java.util.*;


public class Hand {
    public ArrayList<Card> hand = new ArrayList<Card>();
    
    
    public Hand(){
        
    }
    
    
     private Card getCardFromDeck(){
        Card cardFromDeck = new Card();
        return cardFromDeck;
    }
    
    private void fillHand(){
        for(int i=0; i < 5; i++){
            Card newCard = getCardFromDeck();
            hand.add(newCard);
        }   
    }
     
    public void addCardToHand(Card card){
        hand.add(card);
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
    

    
    public void printHand(){
        for (int i = 0; i < hand.size() ;i ++){
            System.out.println(getCardOnPos(i).getName());
            }
        }
    
}
