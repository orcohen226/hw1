import java.util.Iterator;

public class WarGame {
    Player player1;
    Player player2;
    int firstPlayer;
    Player playerWhoPlayFirst;
    Player playerWhoPlaySecond;

    public WarGame(String player1, String player2) {
        this.player1 = new Player (player1);
        this.player2 = new Player(player2);
        this.firstPlayer = firstPlayerInt(this.player1, this.player2);
        if (firstPlayer == 1) {
            this.playerWhoPlayFirst = this.player1;
            this.playerWhoPlaySecond = this.player2;
        } else {
            this.playerWhoPlayFirst = this.player2;
            this.playerWhoPlaySecond = this.player1;
        }
    }

    public int firstPlayerInt(Player player1, Player player2) {
        if (player1.getName().compareTo(player2.getName()) < 0) {
            return 1;
        }
        return 2;
    }

    public void initializeGame() {
        System.out.println("Initializing the game...");
        Deck startingDeck = new Deck(true);
        startingDeck.shuffle();

        while (!startingDeck.isEmpty()) {

            if (firstPlayer == 1) {
                player1.addToMainDeck(startingDeck.removeTopCard());
                player2.addToMainDeck(startingDeck.removeTopCard());
            } else {
                player2.addToMainDeck(startingDeck.removeTopCard());
                player1.addToMainDeck(startingDeck.removeTopCard());
            }
        }
    }

    public String start() {
        initializeGame();
        boolean oneIsOut = false;
        int n = 0;
        while (!player1.outOfCard() && !player2.outOfCard()) {
            ++n;
            System.out.println("------------------------- Round number " + n + " ------------------------");
            round();
        }
        if (player1.outOfCard()) {
            return player2.getName();
        } else {
            return player1.getName();
        }
    }

    public void round() {
        Card firstTopCard = playerWhoPlayFirst.drawCard();
        System.out.println(playerWhoPlayFirst + " drew " + firstTopCard);
        Card secondTopCard = playerWhoPlaySecond.drawCard();
        System.out.println(playerWhoPlaySecond + " drew " + secondTopCard);
        int compareCards = firstTopCard.compare(secondTopCard);
        if (compareCards == 1) {
            // player1 card > player2 card
            // check which first we take first
            player1.addToSideDeck(secondTopCard); // top card of one of the players
            player1.addToSideDeck(firstTopCard); // top card of the other player
            System.out.println(playerWhoPlayFirst + " won");
        } else if (compareCards == -1) {
            // player2 card > player1 card
            player2.addToSideDeck(secondTopCard); // top card of one of the players
            player2.addToSideDeck(firstTopCard); // top card of the other player
            System.out.println(playerWhoPlaySecond + " won");
        } else {
            // starting war
            System.out.println("Starting a war...");
//            boolean oneIsOut = startingWar(firstTopCard, secondTopCard);
            startingWar(firstTopCard, secondTopCard);
        }
    }

    public void startingWar(Card firstTopCard, Card secondTopCard) {
        Deck warDeck = new Deck(false);
        int compareCardsInWar = 0;
        warDeck.addCard(firstTopCard); // top card of one of the players
        warDeck.addCard(secondTopCard); // top card of the other player
        while (compareCardsInWar == 0) {

            for (int i = 0; i < 2; i++) {
                if (playerWhoPlayFirst.outOfCard() || (playerWhoPlaySecond.outOfCard())) {
                    break;
                }
                warDeck.addCard(playerWhoPlayFirst.drawCard());
                System.out.println(playerWhoPlayFirst + " drew a war card");
                warDeck.addCard(playerWhoPlaySecond.drawCard());
                System.out.println(playerWhoPlaySecond + " drew a war card");
            }
            Card topCardOfFirstPlayerInWar = playerWhoPlayFirst.drawCard();
            System.out.println(playerWhoPlayFirst + " drew " + topCardOfFirstPlayerInWar);
            Card topCardOfSecondPlayerInWar = playerWhoPlaySecond.drawCard();
            System.out.println(playerWhoPlaySecond + " drew " + topCardOfSecondPlayerInWar);
            warDeck.addCard(topCardOfFirstPlayerInWar);
            warDeck.addCard(topCardOfSecondPlayerInWar); // check if player 1 or player 2 is first
            compareCardsInWar = topCardOfFirstPlayerInWar.compare(topCardOfSecondPlayerInWar);

            if (compareCardsInWar == 1) {
                // get all in the sideDeck player who plays first
                for (int i = 0; i < warDeck.getDeckSize(); i++) {
                    playerWhoPlayFirst.addToSideDeck(warDeck.removeTopCard());
                }
                System.out.println(playerWhoPlayFirst + " won the war");
            } else if (compareCardsInWar == -1) {
                // get all in the sideDeck player who plays second
                for (int i = 0; i < warDeck.getDeckSize(); i++) {
                    playerWhoPlaySecond.addToSideDeck(warDeck.removeTopCard());
                }
                System.out.println(playerWhoPlaySecond + " won the war");
            }
        }
    }
}