


public class main {
//testklasse
    public static void main(String[] args) {

        /*Card c = new Card();
		c.SetNumber(45);
		System.out.println(c.getNumber());*/
		
		/*Game g = new Game();
		g.printArray();*/
		
		/*ActionCardTable a = new ActionCardTable();
		a.printActionCardTable();*/
		/*VictoryCardTable v = new VictoryCardTable();
		v.printVictoryCardTable();*/
        /*TreasureCardTable t = new TreasureCardTable();
        t.printTreasureCardTable();*/
      /*Deck d = new Deck();
        d.generateStarterDeck();
        d.addToDeck("victory",2);
        d.printCardsInDeck();*/
        /*Hand h = new Hand();
        h.printHand();
        h.addCardToHand();
        System.out.println("-------------");
        h.printHand();
        /*System.out.println("-------------");*/
        /*Player p = new Player();
        p.printDeck();
        System.out.println("-------------");
        p.printHand();*/

        // start game test

        Game g = new Game();
        /*
        System.out.println("DECK PLAYER 1");
        g.printDeck(g.playerOne);
        printLine();
        g.printDeck(g.playerTwo);
        printLine();
        System.out.println("HAND PLAYER 1");
        g.printHand(g.playerOne);
        printLine();
        g.printHand(g.playerTwo);
        printLine();
*/
        // set name test
        /*
        g.setPlayername(g.playerOne, "Robert");
        System.out.println(g.playerOne.getName());
        printLine();
        g.setPlayername(g.playerTwo, "Jens");
        System.out.println(g.playerTwo.getName());
        printLine();
        */
        // adding cards test


        System.out.println("1e HAND");
        printLine();
        g.printHand(g.playerOne);
        printLine();
        System.out.println("DECK");
        printLine();
        g.printDeck(g.playerOne);
        printLine();
        System.out.println("DISCARDPILE");
        printLine();
        g.playerOne.discardHand();
        g.playerOne.printDiscardDeck();
        printLine();
        System.out.println("NEW HAND");
        printLine();
        g.playerOne.generateHand();
        g.playerOne.printHand();
        printLine();
        System.out.println("DISCARDPILE");
        printLine();
        g.playerOne.discardHand();
        g.playerOne.printDiscardDeck();



    }
    public static void printLine(){
        System.out.println("------------------");
    }



}


