import java.util.ArrayList;
import java.util.Random;

/**
 * testtest
 *
 * @author Jens.Thiel
 */
public class ActionCardTable {
    public ArrayList<Card> actionCardTable;
    public ArrayList<Card> allActionCards;

    public ActionCardTable() {
        actionCardTable = new ArrayList<Card>();
        allActionCards = new ArrayList<Card>();
        allActionCards();
        generateActionCardTable();

    }


    public void allActionCards() {
        allActionCards.add(GenerateSpecificActionCard(1, "Cellar", 2, 10));
        allActionCards.add(GenerateSpecificActionCard(2, "Chapel", 2, 10));
        allActionCards.add(GenerateSpecificActionCard(3, "Chancellor", 3, 10));
        allActionCards.add(GenerateSpecificActionCard(4, "Village", 3, 10));
        allActionCards.add(GenerateSpecificActionCard(5, "Woodcutter", 3, 10));
        allActionCards.add(GenerateSpecificActionCard(6, "Workshop", 3, 10));
        allActionCards.add(GenerateSpecificActionCard(7, "Feast", 4, 10));
        allActionCards.add(GenerateSpecificActionCard(9, "Moneylender", 4, 10));
        allActionCards.add(GenerateSpecificActionCard(8, "Militia", 4, 10));
        allActionCards.add(GenerateSpecificActionCard(10, "Remodel", 4, 10));
        allActionCards.add(GenerateSpecificActionCard(11, "Bureaucrat", 4 ,10));
        allActionCards.add(GenerateSpecificActionCard(12, "Smithy", 4, 10));
        allActionCards.add(GenerateSpecificActionCard(13, "Spy", 4, 10));
        allActionCards.add(GenerateSpecificActionCard(14, "Thief", 4, 10));
        allActionCards.add(GenerateSpecificActionCard(15, "Throne Room", 4, 10));
        allActionCards.add(GenerateSpecificActionCard(16, "Moat", 2, 10));
        allActionCards.add(GenerateSpecificActionCard(17, "Council Room", 5, 10));
        allActionCards.add(GenerateSpecificActionCard(18, "Festival", 5, 10));
        allActionCards.add(GenerateSpecificActionCard(19, "Laboratory", 5, 10));
        allActionCards.add(GenerateSpecificActionCard(20, "Library", 5, 10));
        allActionCards.add(GenerateSpecificActionCard(21, "Garden", 4, 12));
        allActionCards.add(GenerateSpecificActionCard(22, "Market", 5, 10));
        allActionCards.add(GenerateSpecificActionCard(23, "Mine", 5, 10));
        allActionCards.add(GenerateSpecificActionCard(24, "Witch", 5, 10));
        allActionCards.add(GenerateSpecificActionCard(25, "Adventurer", 6, 10));


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

    public Card getCardOnPos(int numberOfCard){
        return actionCardTable.get(numberOfCard);
    }

    public int getSize(){
        return actionCardTable.size();
    }

    public Card getCardFromAllActionCards(int pos){ // TODO: verwijderen na test
        Card card = allActionCards.get(pos);
        return card;
    }



    // Action card table maken voor bord

    public void generateActionCardTable() {
        for (int i = 0; i < 10; i++) {
            int randomNumber = getRandomNumber(1, 25);
            while (!checkRandom(randomNumber)) {
                randomNumber = getRandomNumber(1, 25);
            }

            Card newCard = allActionCards.get(randomNumber - 1);
            actionCardTable.add(newCard);
        }

    }



    private int getRandomNumber(int minValue, int maxValue) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(maxValue - minValue + 1) + minValue;
        return randomNumber;
    }

    private boolean checkRandom(int randomNumber) {
        for (int i = 0; i < actionCardTable.size(); i++) {
            int currentNumber = actionCardTable.get(i).getNumber();
            if (randomNumber == currentNumber) {
                return false;
            }
        }
        return true;
    }



}
