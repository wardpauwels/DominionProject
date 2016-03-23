
/**
 * @author Jens.Thiel
 */

public class Card {
    private int cost;
    private String name;
    private String type;
    private int number;
    private int points;


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

    public void setCost(int cost) {
        this.cost = cost;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void SetNumber(int number) {
        this.number = number;
    }

    public void SetPoints(int points) {
        this.points = points;
    }

}

