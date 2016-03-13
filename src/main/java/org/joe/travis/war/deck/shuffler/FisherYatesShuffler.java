package org.joe.travis.war.deck.shuffler;

import org.joe.travis.war.deck.Card;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This shuffler uses the Fisher-Yates algorithm for random distribution.
 *
 * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 *
 * It is performed in place at a time complexity of )(n).
 */
public class FisherYatesShuffler implements Shuffler {
    @Override
    public void shuffle(final Card[] cards) {
        for (int i = cards.length - 1; i > 0; i--) {
            exchange(cards, i, ThreadLocalRandom.current().nextInt(i + 1));
        }
    }

    /**
     * Exchanges cards at startIndex and swapIndex.
     * @param cards array to exchange cards within.
     * @param startIndex index of first card to exchange.
     * @param swapIndex index of second card in the exchange.
     */
    private void exchange(final Card[] cards, final int startIndex, final int swapIndex) {
        Card temp = cards[swapIndex];
        cards[swapIndex] = cards[startIndex];
        cards[startIndex] = temp;
    }
}
