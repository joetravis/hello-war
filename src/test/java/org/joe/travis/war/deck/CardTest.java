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
}
