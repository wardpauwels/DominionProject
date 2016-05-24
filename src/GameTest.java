
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by Robert on 18-5-2016.
 */


public class GameTest {

    private Game g = new Game();

    @Before
    public void setUp() throws Exception {
        g.createPlayersList(3);
        g.setPlayername(0, "Naam1");
        g.setPlayername(1, "Naam2");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void createPlayersListTest() throws Exception {
        assertEquals("Playerlist is groter of kleiner dan 3", 3, g.allPlayers.size());
    }

    @Test
    public void buyCardAndCheckPlayerDiscardPile() throws Exception {
        g.setDecisionOfPlayerPosition(0);
        g.setDecisionOfPlayerType("victory");
        g.currentlyActiveAmountOfCoins = 20;
        g.buyCard();
        assertEquals("Gekochte kaart niet gevonden in discardpile", "Estate", g.allPlayers.get(0).getCardFromDiscardPileOnPos(0).getName());
    }
    @Test
    public void buyCardAndCheckRemainingActions() throws Exception {
        g.setDecisionOfPlayerPosition(0);
        g.setDecisionOfPlayerType("victory");
        g.buyCard();
        assertEquals("Actions niet omlaag na het kopen van een kaart", 0, g.returnAmountOfActionsRemaining());

    }
    @Test
    public void buyCardAndCheckIfAmountChanged(){
        g.setDecisionOfPlayerPosition(0);
        g.setDecisionOfPlayerType("victory");
        g.buyCard();
        assertEquals("Card amount niet veranderd na het kopen van kaart", 23, g.getCardFromPosInVictoryTable(0).getAmount());
    }

    @Test
    public void getPlayerName() throws Exception {
        assertEquals("Naam klopt niet speler 1", "Naam1", g.getPlayerName(0));
        assertEquals("Naam player 3 is ingevuld zonder mee te geven", null, g.getPlayerName(2));
    }

    @Test
    public void checkIfFinished() throws Exception {
        assertEquals("Game is finished", false, g.checkIfFinished());
    }

    @Test
    public void checkRemaingActionsAtStart(){
        assertEquals("Amount of actions niet begonnen bij 0", 1, g.returnAmountOfActionsRemaining());
    }
    @Test
    public void checkAmountOfCoinsAtStart(){
        assertEquals("Amount of coins niet begonnen bij 0", 0, g.getAmountOfCoinsOfPlayer());
    }

    @Test
    public void lowerAmountOfActionsTester() throws Exception {
        g.lowerAmountOfActions();
        assertEquals("Amount of actions is niet omlaag gegeaan", 0, g.returnAmountOfActionsRemaining());
    }
    @Test
    public void villageAtionCardTester(){
        g.useActionCard("Village", 0);
        assertEquals("Remaining actions niet omhoog", 3, g.returnAmountOfActionsRemaining());
        assertEquals("Geen kaart bij gekomen in de hand", 6, g.allPlayers.get(0).getHandSize());
    }

    @Test
    public void woodcutterAtionCardTester(){
        g.useActionCard("Woodcutter", 0);
        assertEquals("Amount of coins niet omhoog",2,g.getAmountOfCoinsOfPlayer());
        g.resetAmountOfActions();
        assertEquals("Amount of remaining buys niet omhoog", 2, g.returnRemainingBuys());
    }
    @Test
    public void moneylenderAtionCardTesterWithCopperInHand(){
        g.allPlayers.get(0).addSpecificCardToHand(g.getCardFromPosInTreasureTable(0));
        g.useActionCard("Moneylender", 0);
        assertEquals("Amount of coins niet omhoog met copper in hand", 3, g.getAmountOfCoinsOfPlayer());
    }
    @Test
    public void moneylenderAtionCardTesterWithoutCopperInHand(){
        g.allPlayers.get(0).clearHand();
        g.useActionCard("Moneylender", 0);
        assertEquals("Amount of coins wel omhoog zonder copper in hand", 0, g.getAmountOfCoinsOfPlayer());
    }
    @Test
    public void bureaucratAtionCardTester(){
        g.useActionCard("Bureaucrat", 0);
        assertEquals("Silver kaart niet gekregen", "Silver", g.allPlayers.get(0).getTopCardFromDeck().getName());
    }
    @Test
    public void SmithyAtionCardTester(){
        g.useActionCard("Smithy", 0);
        assertEquals("Extra kaarten niet gekregen", 8, g.allPlayers.get(0).getHandSize());
    }
    @Test
    public void moatAtionCardTester(){
        g.useActionCard("Moat", 0);
        assertEquals("Extra kaarten niet gekregen", 7, g.allPlayers.get(0).getHandSize());
    }
    @Test
    public void councilRoomAtionCardTester(){
        g.useActionCard("Council Room", 0);
        assertEquals("Geen extra kaarten gekregen", 9, g.allPlayers.get(0).getHandSize());
        g.resetAmountOfActions();
        assertEquals("Geen buys bij gekregen", 2, g.returnAmountOfActionsRemaining());
    }
    @Test
    public void festivalAtionCardTester(){
        g.useActionCard("Festival", 0);
        assertEquals("Geen extra acties gekregen", 3, g.returnAmountOfActionsRemaining());
        assertEquals("Geen extra coins gekregen", 2, g.getAmountOfCoinsOfPlayer());
        g.resetAmountOfActions();
        assertEquals("Geen extra buys gekregen", 2, g.returnAmountOfActionsRemaining());
    }
    @Test
    public void labratoryAtionCardTester(){
        g.useActionCard("Laboratory", 0);
        assertEquals("Geen extra kaarten gekregen", 7, g.allPlayers.get(0).getHandSize());
        assertEquals("Geen extra acties gekregen", 2, g.returnAmountOfActionsRemaining());
    }
    @Test
    public void libraryAtionCardTester(){
        g.useActionCard("Library", 0);
        assertEquals("Geen extra kaarten gekregen",7, g.allPlayers.get(0).getHandSize());
    }
    @Test
    public void marketAtionCardTester(){
        g.useActionCard("Market", 0);
        assertEquals("Geen extra acties gekregen", 2, g.returnAmountOfActionsRemaining());
        assertEquals("Geen extra coins gekregen", 1, g.getAmountOfCoinsOfPlayer());
        assertEquals("Geen extra kaarten gekregen",6, g.allPlayers.get(0).getHandSize());
        g.resetAmountOfActions();
        assertEquals("Geen extra buys gekregen", 2, g.returnAmountOfActionsRemaining());
    }
    @Test
    public void WitchAtionCardTester(){
        g.useActionCard("Witch", 0);
        assertEquals("Andere speler heeft geen curse kaart gekregen", true , g.allPlayers.get(1).scanDiscardPileForCard(g.getCardFromPosInVictoryTable(3)));
        assertEquals("Geen extra kaarten gekregen",7, g.allPlayers.get(0).getHandSize());
    }

    @Test
    public void adventurerAtionCardTester() {
        g.useActionCard("Adventurer", 0);
        g.allPlayers.get(1).addSpecificCardToDeck(g.getCardFromPosInTreasureTable(0));
        g.allPlayers.get(1).addSpecificCardToDeck(g.getCardFromPosInTreasureTable(0));
        g.allPlayers.get(2).addSpecificCardToDeck(g.getCardFromPosInTreasureTable(0));
        g.allPlayers.get(2).addSpecificCardToDeck(g.getCardFromPosInTreasureTable(0));
        assertEquals("Geen extra kaarten gekregen", 7, g.allPlayers.get(0).getHandSize());
        assertEquals("Extra gekregen kaarten zijn geen treasuere cards", "treasure", g.allPlayers.get(0).getCardOnPosInHand(6).getType());
    }








}

