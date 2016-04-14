/**
 * @author Jens.Thiel
 */
public class VictoryCardTable {
    public Card[] victoryCardTable;

    public VictoryCardTable() {
        victoryCardTable = new Card[4];
        GenerateVictoryCardTable();

    }

    public void GenerateVictoryCardTable() {
        victoryCardTable[0] = GenerateSpecificVictoryCard(1, "Estate", 2, 1, 24);
        victoryCardTable[1] = GenerateSpecificVictoryCard(2, "Duchy", 5, 3, 12);
        victoryCardTable[2] = GenerateSpecificVictoryCard(3, "Province", 8, 6, 12);
        victoryCardTable[3] = GenerateSpecificVictoryCard(4, "Curse", 0, -1, 30);

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

}
