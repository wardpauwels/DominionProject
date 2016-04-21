import java.util.*;

/*
*
* @author Robert Plasschaert
*/
// controleer of alles nog nodig is hieronder.


public class Deck {
    private ArrayList<Card> deck;
    private ActionCardTable allActionCards = new ActionCardTable();
    private Card[] actionCards = allActionCards.actionCardTable;
    private TreasureCardTable allTreasureCards = new TreasureCardTable();
    private Card[] treasureCards = allTreasureCards.treasureCardTable;
    private VictoryCardTable allVictoryCards = new VictoryCardTable();
    private Card[] victoryCards = allVictoryCards.victoryCardTable;

    public Deck(){

    }

    public Card getVictoryCard(int victoryCardNumber){
        Card newCard = new Card();
        newCard = victoryCards[victoryCardNumber];
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
            Card coinCard = getCoinCard(0);
            deck.add(coinCard);
        }
        for (int i = 0; i < estateInStarterDeck; i++){
            Card estateCard = getVictoryCard(0);
            deck.add(estateCard);
        }
    }

    public int showAmountOfCardsInDeck(){
        int amount = deck.size();
        return amount;
    }

    public Card getCardOnPos(int i){
        return deck.get(i);
    }
// om nieuwe kaarten toe te voegen aan deck
    public void addToDeck(String typeOfCard, int numberOfCard){
        Card newCard = new Card();
        switch (typeOfCard){
           
            case "action": 
                    newCard = actionCards[numberOfCard - 1];
                    break;
            case "treasure":
                    newCard = treasureCards[numberOfCard - 1];
                    break;
            case "victory":
                    newCard = victoryCards[numberOfCard - 1];
                    break;
                  
        
        }
        deck.add(newCard);
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public void removeFromDeck(int index){
        deck.remove(index);
    }

    public void clearDeck(){
        deck.clear();
    }

    public void printCardsInDeck(){
        for (int i = 0; i < deck.size() ;i ++){
            System.out.println(getCardOnPos(i).getName());
        }
    }


}
