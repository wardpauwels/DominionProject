

public class main {

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
        Game g = new Game();
        g.printDeck(g.playerOne);
        printLine();
        g.printDeck(g.playerOne);
        printLine();
        g.printHand(g.playerTwo);
        printLine();
        g.printHand(g.playerTwo);
        printLine();
        g.setPlayername(g.playerOne, "Robert");
        System.out.println(g.playerOne.getName());
        printLine();
        g.setPlayername(g.playerTwo, "Jens");
        System.out.println(g.playerTwo.getName());
        printLine();
        g.addCardToHand(g.playerOne);
        g.printHand(g.playerOne);
        printLine();
        g.printDeck(g.playerOne);

    }
    public static void printLine(){
        System.out.println("------------------");
    }



}


