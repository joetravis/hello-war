package org.joe.travis.war.deck.shuffler;

import org.joe.travis.war.deck.Card;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unfortunately, this test is not completely deterministic. The problem with
 * asserting randomness, is that the original order is still a valid permutation,
 * and although the probability of it occurring is only 1/n!, it can still happen.
 * If this test fails once, try, try again. If it fails twice, then you might consider
 * buying a lottery ticket.
 */
public class FisherYatesShufflerTest {

    /**
     * Verify that shuffle will change the card order.
     */
    @Test
    public void shuffleUpdatesTheCardOrder() {
        Shuffler shuffler = new FisherYatesShuffler();
        Card[] cards = getOrderedCards();
        boolean outOfOrderCardFound = false;
        int index = 1;

        shuffler.shuffle(cards);

        while (!outOfOrderCardFound && index < cards.length) {
            if (cards[index].getRank() < cards[index - 1].getRank()) {
                outOfOrderCardFound = true;
            }
            index++;
        }

        assertTrue("Cards were still ordered", outOfOrderCardFound);
    }

    /**
     * Get an array of ordered cards.
     * @return ordered card array.
     */
    private Card[] getOrderedCards() {
        /**
         * Choosing 100000 as a limit, as it shouldn't be too hard on memory,
         * and the test should only have a 1/(100000!) chance of failing.
         */
        final Integer limit = 100000;
        Card[] cards = new Card[limit];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = new Card(0, i);
        }

        return cards;
    }
}
