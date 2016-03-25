import java.util.*;

/*
*
* @author Robert Plasschaert
*/

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

    private void generateStarterDeck(){
        int coinsInStarterDeck = 7;
        int estateInStarterDeck = 3;

        for (int i = 0; i < coinsInStarterDeck; i++){
            Card coinCard = getCoinCard(0);
            cardList.add(coinCard);
        }
        for (int i = 0; i < estateInStarterDeck; i++){
            Card estateCard = getVictoryCard(0);
            cardList.add(estateCard);
        }
    }

    private int showAmountOfCardsInDeck(){
        return cardList.size();
    }

    private Card getCardOnPos(int i){
        return cardList.get(i);
    }

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

    private void addCardToDeck(Card c){
        cardList.add(c);
    }

    private void shuffleDeck(){
        Collections.shuffle(cardList);
    }

    private void removeFromDeck(int index){
        cardList.remove(index);
    }

    private void clearDeck(){
        cardList.clear();
    }

}
