
public class deckTester {
	public static void main(String[] args){
		
		Deck d = new Deck();
		d.generateStarterDeck();
		System.out.println(d.showAmountOfCardsInDeck());
		d.displayDeck();
		Card coin = new Card();
		d.addToDeck(coin);
		
		System.out.println(d.showAmountOfCardsInDeck());
	}
}

