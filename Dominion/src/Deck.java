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
//        deck = new ArrayList<Card>();

        for (int i = 0; i < coinsInStarterDeck; i++){
            Card coinCard = getCoinCard(0);
            cardList.add(coinCard);
        }
        for (int i = 0; i < estateInStarterDeck; i++){
            Card estateCard = getVictoryCard(0);
            cardList.add(estateCard);
        }
    }

    public int showAmountOfCardsInDeck(){
        int amount = cardList.size();
        return amount;
    }

    public Card getCardOnPos(int i){
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

    public void printCardsInDeck(){
        for (int i = 0; i < cardList.size() ;i ++){
            System.out.println(getCardOnPos(i).getName());
        }
    }

}
