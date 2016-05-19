import java.util.Scanner;

/**
 * Created by jensthiel on 17/05/16.
 */
public class DominionTests {
    Game g = new Game();
    public Scanner in = new Scanner(System.in);
    public DominionTests(){

        // User input


    }

    public void testDrawPhase(Player whichPlayer){

        g.printHand(whichPlayer);
        g.endPhase();
    }
    public void testBuySpecificActionCard(Player whichPlayer,int whatActionCard){
        g.printHand(whichPlayer);
        g.buyCard(whatActionCard,"action",whichPlayer);
        g.printHand(whichPlayer);

    }

}
