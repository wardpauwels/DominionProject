/**
 * Created by jensthiel on 24/03/16.
 */
public class Player {
    private String name;
    private Deck playersDeck = new Deck();
    private Hand playersHand = new Hand(playersDeck);

    public Player(){
        playersDeck.generateStarterDeck();
        playersHand.generateHand(playersDeck);

    }
    public void printHand(){

        playersHand.printHand();
    }
}
