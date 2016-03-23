/**
 *
 * @author Jens.Thiel
 */
public class VictoryCardTable {
	public Card[] victoryCardTable;
	public VictoryCardTable() {
		victoryCardTable = new Card[4];
		GenerateVictoryCardTable();

	}

	public void GenerateVictoryCardTable() {
		victoryCardTable[0]=GenerateSpecificVictoryCard(1, "Estate", 2, 1);
		victoryCardTable[1]=GenerateSpecificVictoryCard(2, "Duchy", 5, 3);
		victoryCardTable[2]=GenerateSpecificVictoryCard(3, "Province", 8, 6);
		victoryCardTable[3]=GenerateSpecificVictoryCard(4, "Curse", 0, -1);

	}

	private Card GenerateSpecificVictoryCard(int number, String name, int cost, int points) {
		Card newCard = new Card();
		newCard.setCost(cost);
		newCard.setName(name);
		newCard.setType("victory");
		newCard.SetNumber(number);
		newCard.SetPoints(points);

		return newCard;
	}

	public void printVictoryCardTable() {
		for (int i = 0; i < victoryCardTable.length; i++) {
			System.out.println(victoryCardTable[i].getNumber() + ", " + victoryCardTable[i].getName() + ", "
					+ victoryCardTable[i].getType() + ", " + victoryCardTable[i].getCost()+ ", " + victoryCardTable[i].getPoints());
		}
	}

}
