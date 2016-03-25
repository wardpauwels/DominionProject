/**
 * Created by jensthiel on 24/03/16.
 */
import java.util.Random;

public class getRandom {
    public getRandom(){

    }


    public int getRandomNumber(int minValue, int maxValue) {
        Random rand = new Random();

        int randomNumber = rand.nextInt(maxValue - minValue + 1) + minValue;

        return randomNumber;
    }
}
