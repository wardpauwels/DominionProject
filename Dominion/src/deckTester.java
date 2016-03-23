
public class deckTester {
	public static void main(String[] args){
		
		deck d = new deck();
		d.generateStarterDeck();
		System.out.println(d.showAmountOfCardsInDeck());
		d.displayDeck();
		Card coin = new Card();
		d.addToDeck(coin);
		
		System.out.println(d.showAmountOfCardsInDeck());
	}
}

