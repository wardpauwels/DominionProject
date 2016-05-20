import java.util.*;

/*
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
        int coinsInStarterDeck = 7;
        int estateInStarterDeck = 3;


        for(int i = 0; i < coinsInStarterDeck; i++){
            addCardToDeck(copperCard);
        }

        for (int i = 0; i < estateInStarterDeck; i++){
            addCardToDeck(estateCard);
        }
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
            System.out.println(getCardOnPos(i).getName());
        }
    }



// HAND//

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
            cardList.add(newCard);
        }
    }

    public void addCardToHand(Deck playersDeck){
        cardList.add(getCardFromDeck(playersDeck));
    }

    public void clearHand(){
        cardList.clear();

    }
    public boolean scanDeckForCardWithTypeXandReturnBoolean(String typeOfCard){
        ArrayList<Card> specificCardsInDeck = new ArrayList<>();
        boolean typeCheck = false;
        for (int i=0;i<cardList.size();i++) {
            if (cardList.get(i).getType().equals(typeOfCard)){
                specificCardsInDeck.add(cardList.get(i));
            }
        }
        if (specificCardsInDeck.size() != 0){
            typeCheck = true;
        }
        return typeCheck;
    }

    public int scanDeckForCardWithTypeXandReturnPosition(String typeOfCard) {
        int positionOfCard = -1;
        if (scanDeckForCardWithTypeXandReturnBoolean(typeOfCard)) {
            while (positionOfCard == -1) {
                for (int i = 0; i < cardList.size(); i++) {
                    if (cardList.get(i).getType().equals(typeOfCard)) {
                        positionOfCard = i;
                    } else{
                        positionOfCard = -1;
                    }
                }
            }
            return positionOfCard;
        }
        else{
            return -1;
        }

    }




    public void removeFromHand(int index){
        cardList.remove(index);
    }

    public void addSpecificCard(Card toBeAddedCard){
        cardList.add(toBeAddedCard);
    }


    public void printHand(){
        for (int i = 0; i < cardList.size() ;i ++){
            System.out.println(i+1 + "." + getCardOnPos(i).getName());
        }
    }


    public int getSize(){
        return cardList.size();
    }
}


