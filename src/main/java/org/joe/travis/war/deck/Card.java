package org.joe.travis.war.deck;

/**
 * Represents a playing card. Rank and suit are stored by integer.
 * Natural comparison between integers will determine higher cards.
 */
public class Card implements Comparable<Card> {
    /**
     * Natural comparison between integers will determine higher suits.
     */
    private final int suit;

    /**
     * Natural comparison between integers will determine higher ranks.
     */
    private final int rank;

    /**
     * Create a new card by specifying its suit and rank.
     * @param suit of the card.
     * @param rank of the card.
     */
    public Card(final int suit, final int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * @return the card's suit.
     */
    public final int getSuit() {
        return suit;
    }

    /**
     * @return the card's rank.
     */
    public final int getRank() {
        return rank;
    }

    @Override
    public int compareTo(final Card otherCard) {
        if (otherCard.getRank() > getRank()) {
            return -1;
        }
        if (otherCard.getRank() < getRank()) {
            return 1;
        }
        return 0;
    }
}
