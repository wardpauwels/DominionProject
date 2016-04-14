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
        actionCardTable[0] = GenerateSpecificActionCard(1, "Cellar", 2, 10);
        actionCardTable[1] = GenerateSpecificActionCard(2, "Chapel", 2, 10);
        actionCardTable[2] = GenerateSpecificActionCard(3, "Chancellor", 3, 10);
        actionCardTable[3] = GenerateSpecificActionCard(4, "Village", 3, 10);
        actionCardTable[4] = GenerateSpecificActionCard(5, "Woodcutter", 3, 10);
        actionCardTable[5] = GenerateSpecificActionCard(6, "Workshop", 3, 10);
        actionCardTable[6] = GenerateSpecificActionCard(7, "Feast", 4, 10);
        actionCardTable[7] = GenerateSpecificActionCard(8, "Militia", 4, 10);
        actionCardTable[8] = GenerateSpecificActionCard(9, "Moneylender", 4, 10);
        actionCardTable[9] = GenerateSpecificActionCard(10, "Remodel", 4, 10);
        actionCardTable[10] = GenerateSpecificActionCard(11, "Bureaucrat", 4 ,10);
        actionCardTable[11] = GenerateSpecificActionCard(12, "Smithy", 4, 10);
        actionCardTable[12] = GenerateSpecificActionCard(13, "Spy", 4, 10);
        actionCardTable[13] = GenerateSpecificActionCard(14, "Thief", 4, 10);
        actionCardTable[14] = GenerateSpecificActionCard(15, "Throne Room", 4, 10);
        actionCardTable[15] = GenerateSpecificActionCard(16, "Moat", 2, 10);
        actionCardTable[16] = GenerateSpecificActionCard(17, "Council Room", 5, 10);
        actionCardTable[17] = GenerateSpecificActionCard(18, "Festival", 5, 10);
        actionCardTable[18] = GenerateSpecificActionCard(19, "Laboratory", 5, 10);
        actionCardTable[19] = GenerateSpecificActionCard(20, "Library", 5, 10);
        actionCardTable[20] = GenerateSpecificActionCard(21, "Garden", 4, 12);
        actionCardTable[21] = GenerateSpecificActionCard(22, "Market", 5, 10);
        actionCardTable[22] = GenerateSpecificActionCard(23, "Mine", 5, 10);
        actionCardTable[23] = GenerateSpecificActionCard(24, "Witch", 5, 10);
        actionCardTable[24] = GenerateSpecificActionCard(25, "Adventurer", 6, 10);


    }

    private Card GenerateSpecificActionCard(int number, String name, int cost, int amount) {
        Card newCard = new Card();
        newCard.setCost(cost);
        newCard.setName(name);
        newCard.setType("action");
        newCard.setNumber(number);
         newCard.setAmount(amount);

        return newCard;
    }



}
