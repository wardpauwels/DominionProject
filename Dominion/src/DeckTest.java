import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robert on 20-5-2016.
 */
public class DeckTest {
    private Deck d = new Deck();
    private Game g = new Game();

    @Before
    public void setUp() throws Exception {
        d.generateStarterDeck();
    }

    @Test
    public void generateStarterDeck() throws Exception {
        assertEquals("Deck niet gevuld", 10, d.getSize());
    }

    @Test
    public void addCardToDeck() throws Exception {
        d.addCardToDeck(g.getCardFromPosInActionTable(0));
        assertEquals("Geen kaart toegevoegd", 11, d.getSize());
    }

    @Test
    public void addCardToSpecificPositionInDeck() throws Exception {
        d.addCardToSpecificPositionInDeck(7, g.getCardFromPosInActionTable(3));
        assertEquals("niet de juiste kaart gevonden", "action",d.getCardOnPos(7).getType());
    }

    @Test
    public void addToDeck() throws Exception {
        d.addToDeck("action", 3);
        assertEquals("Geen kaart toegevoegd",11,d.getSize());
    }

    @Test
    public void removeFromDeck() throws Exception {
        d.removeFromDeck(5);
        assertEquals("Kaart niet verwijderd", 9,d.getSize());
    }

    @Test
    public void clearDeck() throws Exception {
        d.clearDeck();
        assertEquals("Deck niet leef", 0, d.getSize());
    }
}