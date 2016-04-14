import java.util.*;


/*
*
* @author Robert Plasschaert
*/
// controleer of alles nog nodig is hieronder.


public class Deck {
    private ArrayList<Card> cardList;
    private ActionCardTable allActionCards = new ActionCardTable();
    private Card[] actionCards = allActionCards.actionCardTable;
    private TreasureCardTable allTreasureCards = new TreasureCardTable();
    private Card[] treasureCards = allTreasureCards.treasureCardTable;
    private VictoryCardTable allVictoryCards = new VictoryCardTable();
    private Card[] victoryCards = allVictoryCards.victoryCardTable;

    public Deck(){
        cardList = new ArrayList<Card>();
    }

    private Card getVictoryCard(int victoryCardNumber){
        Card newCard = new Card();
        newCard = victoryCards[victoryCardNumber];
        return newCard;
    }

    private Card getCoinCard(int coinNumber){
        Card newCard = new Card();
        newCard = treasureCards[coinNumber];
        return newCard;

    }

    public void generateStarterDeck(){
        int coinsInStarterDeck = 7;
        int estateInStarterDeck = 3;

        for (int i = 0; i < coinsInStarterDeck; i++){
            addToDeck("treasure", 1);
        }
        for (int i = 0; i < estateInStarterDeck; i++){
            addToDeck("victory", 1);
        }
    }

    public int showAmountOfCardsInDeck(){
        return cardList.size();
    }

    public Card getCardOnPos(int i){
        return cardList.get(i);
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
        cardList.add(newCard);
    }

    public void addCardToDeck(Card c){
        cardList.add(c);
    }

    public void shuffleDeck(){
        Collections.shuffle(cardList);
    }

    public void removeFromDeck(int index){
        cardList.remove(index);
    }

    public void clearDeck(){
        cardList.clear();
    }

    public void printDeck(){
        int length = cardList.size();
        for(int i = 0; i < length; i++){
            System.out.println(getCardOnPos(i));
        }
    }


}
