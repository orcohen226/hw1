import java.util.ArrayList;
import java.util.Collections;


public class Deck {
    // return 1-13 cards Spades, Diamonds, Clubs, Hearts
    // else leave th Deck empty
    ArrayList<Card> deck;

    public Deck(boolean parameterBoolean) {
        if (parameterBoolean) {
            this.deck = new ArrayList<Card>(52);

            for (int shapeI = 0; shapeI <= 3; shapeI++) {
                Shape shape = Shape.values()[shapeI];
                for (int i = 0; i <= 13; i++) {
                    Card card = new Card(i, shape);
                    deck.add(card);
                }
            }
        }else {
            this.deck = new ArrayList<>(0);
        }
    }


    public void addCard(Card card) {
        // take the card and put it on the top of the Deck
        deck.add(card);
    }

    public Card removeTopCard() {
        // take the last card and return it
        Card topCard = deck.get(deck.size()-1);
        deck.remove((deck.size() - 1));
        return topCard;
    }

    public int getDeckSize(){
        return this.deck.size();
    }
    public boolean isEmpty() {
        if (deck.size() == 0){
            return true;
        }
        return false;
    }
    // return if th Deck is empty

    public void shuffle() {
        //shuffle the Deck in this way:
        // we make a for that run 50 times
        //each iteration we will take randomly two cards from the list
        //and we change the value of the two indexes that
        //present those indexes
        for (int i = 0; i < 50; i++) {
            int randomInteger1 = (int) (deck.size() * Math.random());
            int randomInteger2 = (int) (deck.size() * Math.random());
            Collections.swap(deck, randomInteger1, randomInteger2);
        }
    }
}