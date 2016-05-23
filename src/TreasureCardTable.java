import java.util.ArrayList;

/**
 * @author Jens.Thiel
 */
public class TreasureCardTable {
    public ArrayList<Card> treasureCardTable;

    public TreasureCardTable() {
        treasureCardTable = new ArrayList<Card>();
        GenerateVictoryCardTable();

    }

    public void GenerateVictoryCardTable() {
        treasureCardTable.add(GenerateSpecificTreasureCard(1, "Copper", 2, 60));
        treasureCardTable.add(GenerateSpecificTreasureCard(2, "Silver", 5, 40));
        treasureCardTable.add(GenerateSpecificTreasureCard(3, "Gold", 8, 30));


    }

    private Card GenerateSpecificTreasureCard(int number, String name, int cost, int amount) {
        Card newCard = new Card();
        newCard.setCost(cost);
        newCard.setName(name);
        newCard.setType("treasure");
        newCard.setNumber(number);
        newCard.setAmount(amount);


        return newCard;
    }

    public Card getCardOnPos(int numberOfCard){
        return treasureCardTable.get(numberOfCard);
    }

    public int getSize(){
        return treasureCardTable.size();
    }
    public Card searchForCardOnName(String nameOfCard){
        Card foundCard=new Card();
        for(int i=0;i<treasureCardTable.size();i++){
            if (nameOfCard.equals(treasureCardTable.get(i).getName())){
                foundCard = treasureCardTable.get(i);
            }
        }
        return foundCard;
    };
}
