import java.util.*;


/*
*
* @author Robert Plasschaert
*/


public class Deck {
    private ArrayList<Card> cardList;
    private ActionCardTable actionCards;
    private VictoryCardTable victoryCards;
    private TreasureCardTable treasureCards;


    public Deck(){
        cardList = new ArrayList<Card>();
        victoryCards = new VictoryCardTable();
        actionCards = new ActionCardTable();
        treasureCards = new TreasureCardTable();

    }


    public void generateStarterDeck(){
        Card copperCard = treasureCards.getCardOnPos(0);
        Card estateCard = victoryCards.getCardOnPos(0);
        Card goldCard = treasureCards.getCardOnPos(2); // TODO: weg halen na test
        Card actionCard = actionCards.getCardOnPos(0); // TODO: weg halen na test
        int coinsInStarterDeck = 15; //TODO Terug veranderen naar 7 na test
        int estateInStarterDeck = 3;

        for(int i = 0; i < coinsInStarterDeck; i++){ //TODO: weg halen na test
            addCardToDeck(goldCard);
        }
        addCardToDeck(actionCard); // TODO weg halen na test

        /*for (int i = 0; i < coinsInStarterDeck; i++){ TODO: terug uit comments halen na het testen van alle actie kaarten
            addCardToDeck(copperCard);;
        }
        for (int i = 0; i < estateInStarterDeck; i++){
            addCardToDeck(estateCard);
        }*/
    }

    public int getSize(){
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
                    newCard = actionCards.getCardOnPos(numberOfCard -1);
                    break;
            case "treasure":
                    newCard = treasureCards.getCardOnPos(numberOfCard - 1);
                    break;
            case "victory":
                    newCard = victoryCards.getCardOnPos(numberOfCard -1);
                    break;
        }
        cardList.add(newCard);
    }

    public void addCardToSpecificPositionInDeck(int position, Card specificCard){
        cardList.add(position,specificCard);
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
