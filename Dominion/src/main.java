import java.util.*;

public class main {
//testklasse
    public static void main(String[] args) {
        int i;
        String s;

        Scanner in = new Scanner(System.in);
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
        /*
        Game g = new Game();
        g.printDeck(g.playerOne);
        printLine();
        g.printDeck(g.playerTwo);
        printLine();
        g.printHand(g.playerOne);
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
        g.printActionCards();
        g.printTreasureCards();
        g.printVicotryCards();
        g.buyCard(2, "action", g.playerOne);
        g.printDiscardPile(g.playerOne);
        g.printActionCards();
        g.buyCard(2, "action", g.playerOne);
        g.printDiscardPile(g.playerOne);
        g.printActionCards();
        g.printHand(g.playerOne);
        */
        System.out.println("Geef aantal spelers");
        i = in.nextInt();
        Game g = new Game(i);


        System.out.println("Geef username player 1");
        s = in.nextLine();
        s = in.nextLine();
        g.setPlayername(0, s);



        System.out.println("Geef username player 2");
        s = in.nextLine();
        g.setPlayername(1, s);


        System.out.println("Player 1: " + g.allPlayers.get(0).getName() + " Player 2: " + g.allPlayers.get(1).getName());
        g.printBoard();
        g.printHand(g.allPlayers.get(0));
        g.printCoins(g.allPlayers.get(0));
        printLine();
        System.out.println("Kies actie:");
        printLine();
        System.out.println("1: Buy action card");
        printLine();
        i = in.nextInt();
        printLine();
        System.out.println("Give position of card to buy");
        printLine();
        i = in.nextInt();
        g.buyCard(i,"action", g.allPlayers.get(0));
        g.printActionCards();






    }
    public static void printLine(){
        System.out.println("------------------");
    }



}


