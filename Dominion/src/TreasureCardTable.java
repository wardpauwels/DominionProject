/**
 * @author Jens.Thiel
 */
public class TreasureCardTable {
    public Card[] treasureCardTable;

    public TreasureCardTable() {
        treasureCardTable = new Card[3];
        GenerateVictoryCardTable();

    }

    public void GenerateVictoryCardTable() {
        treasureCardTable[0] = GenerateSpecificTreasureCard(1, "Copper", 2, 60);
        treasureCardTable[1] = GenerateSpecificTreasureCard(2, "Silver", 5, 40);
        treasureCardTable[2] = GenerateSpecificTreasureCard(3, "Gold", 8, 30);


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

}
