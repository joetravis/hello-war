package org.joe.travis.war.deck;

/**
 * Deck interface. These objects will create, allow for shuffling and dealing.
 */
public interface Deck {
    /**
     * Create the deck of cards.
     * @param numberOfSuits number of suits to build into deck.
     * @param numberOfRanks number of ranks to build into deck.
     */
    void create(int numberOfSuits, int numberOfRanks);

    /**
     * Shuffle the deck.
     */
    void shuffle();

    /**
     * Deal a card from the deck.
     *
     * @return the top card.
     */
    Card deal();
}
