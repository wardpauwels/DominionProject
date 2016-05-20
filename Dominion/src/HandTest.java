import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Robert on 20-5-2016.
 */


public class HandTest {


    private Deck d = new Deck();
    private Hand h = new Hand();
    private Game g = new Game();

    @Before
    public void setUp(){
        d.generateStarterDeck();
        h.generateHand(d);
    }


    @Test
    public void generateHand() throws Exception {
        assertEquals("Hand niet gevuld", 5, h.getSize());
    }

    @Test
    public void addCardToHand() throws Exception {
        h.addCardToHand(d);
        assertEquals("Geen kaart er bij gekregen", 6, h.getSize());
    }

    @Test
    public void clearHand() throws Exception {
        h.clearHand();
        assertEquals("Hand niet cleared",0, h.getSize());
    }

    @Test
    public void scanDeckForCardWithTypeXandReturnBoolean() throws Exception {
        h.addSpecificCard(g.getCardFromPosInVictoryTable(0));
        assertEquals("Kaart niet gevonden", true, h.scanDeckForCardWithTypeXandReturnBoolean("victory"));
        assertEquals("Kaart wel gevonden", false, h.scanDeckForCardWithTypeXandReturnBoolean("action"));
    }

    @Test
    public void scanDeckForCardWithTypeXandReturnPosition() throws Exception {
        h.addSpecificCard(g.getCardFromPosInActionTable(0));
        assertEquals("Kaart niet gevonden", 5, h.scanDeckForCardWithTypeXandReturnPosition("action"));
    }

    @Test
    public void getCardOnPos() throws Exception {
        h.addSpecificCard(g.getCardFromPosInActionTable(0));
        h.getCardOnPos(5);
        assertEquals("Niet de juiste kaart gevonden","action", h.getCardOnPos(5).getType());
    }

    @Test
    public void removeFromHand() throws Exception {
        h.removeFromHand(0);
        assertEquals("Kaart niet verwijderd",4, h.getSize());
    }

    @Test
    public void getSize() throws Exception {
        assertEquals("Size klopt niet",5, h.getSize());
    }
}