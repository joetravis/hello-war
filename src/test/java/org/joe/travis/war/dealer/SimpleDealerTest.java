package org.joe.travis.war.dealer;

import org.joe.travis.war.deck.Card;
import org.joe.travis.war.deck.Deck;
import org.joe.travis.war.player.Player;
import org.joe.travis.war.player.SimplePlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the simple dealer implementation.
 */
public class SimpleDealerTest {
    /**
     * Subject under test.
     */
    private SimpleDealer dealer;

    /**
     * Initialize subject under test.
     */
    @Before
    public void setUp() {
        dealer = new SimpleDealer();
    }

    /**
     * Verify that cards are split evenly if possible.
     */
    @Test
    public void cardsAreEvenlySplitWhenPlayersDivideDeck() {
        final int deckSize = 52;
        Deck deck = mock(Deck.class);
        when(deck.deal()).thenAnswer(new Answer<Card>() {
            private int count = 0;

            @Override
            public Card answer(final InvocationOnMock invocationOnMock) throws Throwable {
                if (count >= deckSize) {
                    return null;
                }
                count++;
                return new Card(0, ThreadLocalRandom.current().nextInt());
            }
        });

        Player one = mock(Player.class);
        Player two = mock(Player.class);
        Collection<Player> players = new ArrayList<>(2);
        players.add(one);
        players.add(two);

        dealer.deal(deck, players);

        verify(one, times(deckSize / 2)).receive(any(Card.class));
        verify(two, times(deckSize / 2)).receive(any(Card.class));
    }

    /**
     * Verify that cards are split unevenly when necessary.
     */
    @Test
    public void cardsAreUnevenlySplitWhenPlayersDontDivideDeck() {
        final int deckSize = 11;
        final int maxHandSize = 4;
        final int otherHandSize = 3;
        Deck deck = mock(Deck.class);
        when(deck.deal()).thenAnswer(new Answer<Card>() {
            private int count = 0;

            @Override
            public Card answer(final InvocationOnMock invocationOnMock) throws Throwable {
                if (count >= deckSize) {
                    return null;
                }
                count++;
                return new Card(0, ThreadLocalRandom.current().nextInt());
            }
        });

        Player one = new SimplePlayer(ThreadLocalRandom.current().nextInt());
        Player two = new SimplePlayer(ThreadLocalRandom.current().nextInt());
        Player three = new SimplePlayer(ThreadLocalRandom.current().nextInt());
        Collection<Player> players = new ArrayList<>();
        players.add(one);
        players.add(two);
        players.add(three);

        dealer.deal(deck, players);

        assertEquals(maxHandSize, countPlayerCards(one));
        assertEquals(maxHandSize, countPlayerCards(two));
        assertEquals(otherHandSize, countPlayerCards(three));
    }

    /**
     * Count the number of cards the player has.
     * @param player to count cards for.
     *
     * @return number of cards player has.
     */
    public int countPlayerCards(final Player player) {
        int count = 0;

        while (player.hasCards()) {
            count++;
            player.play();
        }

        return count;
    }
}
