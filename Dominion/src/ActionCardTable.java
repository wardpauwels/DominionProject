/**
 * testtest
 *
 * @author Jens.Thiel
 */
public class ActionCardTable {
    public Card[] actionCardTable;

    public ActionCardTable() {
        actionCardTable = new Card[25];
        GenerateActionCardTable();

    }

    public void GenerateActionCardTable() {
        actionCardTable[0] = GenerateSpecificActionCard(1, "Cellar", 2);
        actionCardTable[1] = GenerateSpecificActionCard(2, "Chapel", 2);
        actionCardTable[2] = GenerateSpecificActionCard(3, "Chancellor", 3);
        actionCardTable[3] = GenerateSpecificActionCard(4, "Village", 3);
        actionCardTable[4] = GenerateSpecificActionCard(5, "Woodcutter", 3);
        actionCardTable[5] = GenerateSpecificActionCard(6, "Workshop", 3);
        actionCardTable[6] = GenerateSpecificActionCard(7, "Feast", 4);
        actionCardTable[7] = GenerateSpecificActionCard(8, "Militia", 4);
        actionCardTable[8] = GenerateSpecificActionCard(9, "Moneylender", 4);
        actionCardTable[9] = GenerateSpecificActionCard(10, "Remodel", 4);
        actionCardTable[10] = GenerateSpecificActionCard(11, "Bureaucrat", 4);
        actionCardTable[11] = GenerateSpecificActionCard(12, "Smithy", 4);
        actionCardTable[12] = GenerateSpecificActionCard(13, "Spy", 4);
        actionCardTable[13] = GenerateSpecificActionCard(14, "Thief", 4);
        actionCardTable[14] = GenerateSpecificActionCard(15, "Throne Room", 4);
        actionCardTable[15] = GenerateSpecificActionCard(16, "Moat", 2);
        actionCardTable[16] = GenerateSpecificActionCard(17, "Council Room", 5);
        actionCardTable[17] = GenerateSpecificActionCard(18, "Festival", 5);
        actionCardTable[18] = GenerateSpecificActionCard(19, "Laboratory", 5);
        actionCardTable[19] = GenerateSpecificActionCard(20, "Library", 5);
        actionCardTable[20] = GenerateSpecificActionCard(21, "Garden", 4);
        actionCardTable[21] = GenerateSpecificActionCard(22, "Market", 5);
        actionCardTable[22] = GenerateSpecificActionCard(23, "Mine", 5);
        actionCardTable[23] = GenerateSpecificActionCard(24, "Witch", 5);
        actionCardTable[24] = GenerateSpecificActionCard(25, "Adventurer", 6);


    }

    private Card GenerateSpecificActionCard(int number, String name, int cost) {
        Card newCard = new Card();
        newCard.setCost(cost);
        newCard.setName(name);
        newCard.setType("action");
        newCard.SetNumber(number);

        return newCard;
    }

    public void printActionCardTable() {
        for (int i = 0; i < actionCardTable.length; i++) {
            System.out.println(actionCardTable[i].getNumber() + ", " + actionCardTable[i].getName() + ", " + actionCardTable[i].getType() + ", " + actionCardTable[i].getCost());
        }
    }

}
