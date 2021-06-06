import java.util.ArrayList;

public class Player {
    private String name;
    public Deck mainDeck;
    public Deck sideDeck;

    public Player(String name) {
        this.name = name;
        this.mainDeck = new Deck(false);
        this.sideDeck = new Deck(false);

    }

    public String getName() {
        return name;
    }

    public void addToMainDeck(Card card) {
        mainDeck.addCard(card);
    }

    public void addToSideDeck(Card card) {
        sideDeck.addCard(card);
    }

    public Card drawCard() {
        if(mainDeck.getDeckSize() == 0){
            sideDeck.shuffle();
            mainDeck = sideDeck;
            sideDeck = new Deck(false);
        }

        return mainDeck.removeTopCard();
    }

    public boolean outOfCard() {
        if ((mainDeck.isEmpty()) && (sideDeck.isEmpty())) {
            return true;
        }
        return false;
    }

    public String toString(){
        return name;
    }
}
