
/**
 * @author Jens.Thiel
 */

public class Card {
    private int cost;
    private String name;
    private String type;
    private int number;
    private int points;
    private int amount;

    public Card() {

    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public int getPoints() {
        return points;
    }

    public int getAmount() {
        return amount;
    }
    public void setCost(int cost) {
        this.cost = cost;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }


}

