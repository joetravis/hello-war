package org.joe.travis.war.deck;

import org.joe.travis.war.deck.shuffler.Shuffler;

/**
 * Simple deck implementation stores cards in an array, and uses a counter to deal them out.
 */
public class SimpleDeck implements Deck {
    /**
     * Delegate card shuffling to a shuffler.
     */
    private final Shuffler shuffler;

    /**
     * Hold onto your cards.
     */
    private Card[] cards;

    /**
     * Indicate the current card to be dealt.
     */
    private int currentIndex;

    /**
     * Create an instance of a simple deck.
     * @param shuffler will shuffle the cards.
     */
    public SimpleDeck(final Shuffler shuffler) {
        this.shuffler = shuffler;
    }

    @Override
    public void create(final int numberOfSuits, final int numberOfRanks) {
        if (numberOfSuits < 1) {
            throw new DeckCreationException("Number of suits must be a positive integer.");
        }

        if (numberOfRanks < 1) {
            throw new DeckCreationException("Number of ranks must be a positive integer.");
        }

        cards = new Card[numberOfRanks * numberOfSuits];
        currentIndex = 0;

        for (int suit = 0; suit < numberOfSuits; suit++) {
            for (int rank = 0; rank < numberOfRanks; rank++) {
                cards[currentIndex] = new Card(suit, rank);
                currentIndex++;
            }
        }

        currentIndex = 0;
    }

    @Override
    public void shuffle() {
        shuffler.shuffle(cards);
        currentIndex = 0;
    }

    @Override
    public Card deal() {
        if (cards == null) {
            return null;
        }

        if (currentIndex >= cards.length) {
            return null;
        }

        Card currentCard = cards[currentIndex];
        currentIndex++;

        return currentCard;
    }
}
