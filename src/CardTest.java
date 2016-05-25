import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robert on 20-5-2016.
 */


public class CardTest {
    private Card c = new Card();

    @Test
    public void setCost() throws Exception {
        c.setCost(1);
        assertEquals("Get of Set cost werkt niet", 1, c.getCost());
    }

    @Test
    public void setName() throws Exception {
        c.setName("Test");
        assertEquals("Get of Set name werkt niet", "Test", c.getName());
    }

    @Test
    public void setType() throws Exception {
        c.setType("action");
        assertEquals("Get of Set type werkt niet", "action", c.getType());
    }

    @Test
    public void setNumber() throws Exception {
        c.setNumber(1);
        assertEquals("Get of Set number werkt niet", 1, c.getNumber());
    }

    @Test
    public void setPoints() throws Exception {
        c.setPoints(1);
        assertEquals("Get of Set points werkt niet", 1, c.getPoints());
    }

    @Test
    public void setAmount() throws Exception {
        c.setAmount(1);
        assertEquals("Get of Set amount werkt niet", 1, c.getAmount());
    }
}
