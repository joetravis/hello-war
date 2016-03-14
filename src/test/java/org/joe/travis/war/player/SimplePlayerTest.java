package org.joe.travis.war.player;

import org.joe.travis.war.deck.Card;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for simple player.
 */
public class SimplePlayerTest {
    /**
     * Subject under test.
     */
    private SimplePlayer player;

    /**
     * Random id for the player generated for each test.
     */
    private int id;

    /**
     * Initialize simple player with an ID.
     */
    @Before
    public void setUp() {
        id = ThreadLocalRandom.current().nextInt();
        player = new SimplePlayer(id);
    }

    /**
     * Verify that id is stored and accessible.
     */
    @Test
    public void idShouldBeAccessible() {
        assertEquals(id, player.getId());
    }

    /**
     * Verify that player starts with no cards.
     */
    @Test
    public void playerStartsWithNoCards() {
        assertFalse(player.hasCards());
        assertNull(player.play());
    }

    /**
     * Verify that null values will not be added to the player's pile.
     */
    @Test
    public void playerShouldntAcceptNullCards() {
        player.receive(null);
        assertFalse(player.hasCards());
    }

    /**
     * Verify that player can play cards after receiving them.
     */
    @Test
    public void playerCanPlayReceivedCards() {
        Set<Card> cards = getRandomCards();

        for (Card card : cards) {
            player.receive(card);
        }

        int counter = 0;

        while (player.hasCards()) {
            Card playerCard = player.play();
            assertTrue("player has an unexpected card", cards.contains(playerCard));
            counter++;
        }

        assertEquals(cards.size(), counter);
    }

    /**
     * Verify that a player indicates they have cards after receiving cards.
     */
    @Test
    public void playerShouldIndicateTheyHaveCardsAfterReceivingCards() {
        Set<Card> cards = getRandomCards();

        for (Card card : cards) {
            player.receive(card);
        }

        assertTrue(player.hasCards());
    }

    /**
     * Get a random set of cards.
     * @return random sized set of random cards.
     */
    public Set getRandomCards() {
        final int limit = 20;
        int count = ThreadLocalRandom.current().nextInt(1, limit);
        Set<Card> cards = new HashSet<>(count);

        while (cards.size() < limit) {
            cards.add(new Card(
                    ThreadLocalRandom.current().nextInt(),
                    ThreadLocalRandom.current().nextInt()
            ));
        }

        return cards;
    }
}
