package org.joe.travis.war.deck;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.Assert.assertEquals;

/**
 * Unit tests for Card class.
 */
public class CardTest {

    /**
     * Verify that the suit argument is stored and accessible.
     */
    @Test
    public void verifySuitIsStored() {
        int anySuit = ThreadLocalRandom.current().nextInt();

        Card card = new Card(anySuit, 0);

        assertEquals(anySuit, card.getSuit());
    }

    /**
     * Verify that the rank argument is stored and accessible.
     */
    @Test
    public void verifyRankIsStored() {
        int anyRank = ThreadLocalRandom.current().nextInt();

        Card card = new Card(0, anyRank);

        assertEquals(anyRank, card.getRank());
    }

    /**
     * Verify that cards of the same rank are equal.
     */
    @Test
    public void verifyRankEqualComparison() {
        int anySuit = ThreadLocalRandom.current().nextInt();
        int anyRank = ThreadLocalRandom.current().nextInt();
        int otherSuit = ThreadLocalRandom.current().nextInt();

        Card card = new Card(anySuit, anyRank);
        Card equalCard = new Card(otherSuit, anyRank);

        assertEquals(0, card.compareTo(equalCard));
    }

    /**
     * Verify that compared with card of lower rank, a card indicates it is higher.
     */
    @Test
    public void verifyLowerRankComparison() {
        final int limit = 20;
        int anySuit = ThreadLocalRandom.current().nextInt();
        int anyRank = ThreadLocalRandom.current().nextInt(1, limit);
        int otherSuit = ThreadLocalRandom.current().nextInt();

        Card card = new Card(anySuit, anyRank);
        Card lowerCard = new Card(otherSuit, anyRank - 1);

        assertEquals(1, card.compareTo(lowerCard));
    }

    /**
     * Verify that compared with card of higher rank, a card indicates it is lower.
     */
    @Test
    public void verifyHigherRankComparison() {
        final int limit = 20;
        int anySuit = ThreadLocalRandom.current().nextInt();
        int anyRank = ThreadLocalRandom.current().nextInt(1, limit);
        int otherSuit = ThreadLocalRandom.current().nextInt();

        Card card = new Card(anySuit, anyRank);
        Card higherCard = new Card(otherSuit, anyRank + 1);

        assertEquals(-1, card.compareTo(higherCard));
    }
}
