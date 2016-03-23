/**
 *
 * @author Jens.Thiel
 */
public class TreasureCardTable {
	public Card[] treasureCardTable;
	public TreasureCardTable() {
		treasureCardTable = new Card[3];
		GenerateVictoryCardTable();

	}

	public void GenerateVictoryCardTable() {
		treasureCardTable[0]=GenerateSpecificTreasureCard(1, "Copper", 2);
		treasureCardTable[1]=GenerateSpecificTreasureCard(2, "Silver", 5);
		treasureCardTable[2]=GenerateSpecificTreasureCard(3, "Gold", 8);
		

	}

	private Card GenerateSpecificTreasureCard(int number, String name, int cost) {
		Card newCard = new Card();
		newCard.setCost(cost);
		newCard.setName(name);
		newCard.setType("treasure");
		newCard.SetNumber(number);
		

		return newCard;
	}

	public void printTreasureCardTable() {
		for (int i = 0; i < treasureCardTable.length; i++) {
			System.out.println(treasureCardTable[i].getNumber() + ", " + treasureCardTable[i].getName() + ", "
					+ treasureCardTable[i].getType() + ", " + treasureCardTable[i].getCost());
		}
	}

}
