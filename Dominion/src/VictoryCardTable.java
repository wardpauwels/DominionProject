import java.util.ArrayList;

/**
 * @author Jens.Thiel
 */
public class VictoryCardTable {
    public ArrayList<Card> victoryCardTable;

    public VictoryCardTable() {
        victoryCardTable = new ArrayList<Card>();
        GenerateVictoryCardTable();
    }

    public void GenerateVictoryCardTable() {
        victoryCardTable.add(GenerateSpecificVictoryCard(1, "Estate", 2, 1, 24));
        victoryCardTable.add(GenerateSpecificVictoryCard(2, "Duchy", 5, 3, 12));
        victoryCardTable.add(GenerateSpecificVictoryCard(3, "Province", 8, 6, 12));
        victoryCardTable.add(GenerateSpecificVictoryCard(4, "Curse", 0, -1, 30));

    }

    private Card GenerateSpecificVictoryCard(int number, String name, int cost, int points, int amount) {
        Card newCard = new Card();
        newCard.setCost(cost);
        newCard.setName(name);
        newCard.setType("victory");
        newCard.setNumber(number);
        newCard.setPoints(points);
        newCard.setAmount(amount);

        return newCard;
    }

    public Card getCardOnPos(int numberOfCard){
        return victoryCardTable.get(numberOfCard);
    }

    public int getSize(){
        return victoryCardTable.size();
    }

}
