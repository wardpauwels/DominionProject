
import java.util.*;



public class Hand {
    private ArrayList<Card> hand = new ArrayList<Card>();
    private Deck playersDeck = new Deck();
    
    
    public Hand(){
        generateHand();
    }
    
    
     private Card getCardFromDeck(){
         getRandom randomNumber = new getRandom();

        Card cardFromDeck = playersDeck.getCardOnPos(randomNumber.getRandomNumber(0,playersDeck.showAmountOfCardsInDeck()));
        return cardFromDeck;
    }
    
    private void generateHand(){
        for(int i=0; i < 5; i++){
            Card newCard = getCardFromDeck();
            hand.add(newCard);
        }   
    }
     
    public void addCardToHand(){

        hand.add(getCardFromDeck());
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
