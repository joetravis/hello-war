package org.joe.travis.war.deck.shuffler;

import org.joe.travis.war.deck.Card;

/**
 * These are the components that will provide shuffling capability.
 */
public interface Shuffler {
    /**
     * Randomize the cards.
     *
     * @param cards array to randomize.
     */
    void shuffle(Card[] cards);
}
