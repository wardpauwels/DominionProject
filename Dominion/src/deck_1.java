import java.util.*;

/*
*
* @author Robert Plasschaert
*/

public class deck {
	private ArrayList<Card> deck;
	private TreasureCardTable allTreasureCards = new TreasureCardTable();
	private Card[] treasureCards = allTreasureCards.treasureCardTable;
	private VictoryCardTable allVictoryCards = new VictoryCardTable();
	private Card[] victorycards = allVictoryCards.victoryCardTable;
	
	public deck(){
		
	}
	
	public Card getVictoryCard(int victoryCardNumber){
		Card newCard = new Card();
		newCard = victorycards[victoryCardNumber];
		return newCard;
	}
	
	public Card getCoinCard(int coinNumber){
		Card newCard = new Card();
		newCard = treasureCards[coinNumber];
		return newCard;
		
	}
	
	public void generateStarterDeck(){
		int coinsInStarterDeck = 7;
		int estateInStarterDeck = 3;
		deck = new ArrayList<Card>();
		
		for (int i = 0; i < coinsInStarterDeck; i++){
			Card coinCard = getCoinCard(1);
			deck.add(coinCard);
		}
		for (int i = 0; i < estateInStarterDeck; i++){
			Card estateCard = getVictoryCard(1);
			deck.add(estateCard);
		}
	}
	
	public int showAmountOfCardsInDeck(){
		int amount = deck.size();
		return amount;
	}
	
    public void displayDeck(){
        for(int i = 0; i < deck.size(); i++){
        	System.out.println(getCardOnPos(i));
        }
    }
    
    public Card getCardOnPos(int i){
    	return deck.get(i);
    }

    public void addToDeck(Card newCard){
    	deck.add(newCard);
    }


}




