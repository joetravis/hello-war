package org.joe.travis.war.round;

import org.joe.travis.war.deck.Card;
import org.joe.travis.war.player.Player;
import org.joe.travis.war.player.SimplePlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Unit tests for war in real life style round.
 */
public class WarInRealLifeTest {
    /**
     * Subject under test.
     */
    @InjectMocks
    private WarInRealLife round;

    /**
     * Publish round events.
     */
    @Mock
    private ApplicationEventPublisher eventPublisher;

    /**
     * Initialize subject under test and test fixtures.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Verify that high card wins even when lower cards are tied.
     */
    @Test
    public void testLowerCardMatchesDontTriggerWarInRealLifeScenario() {
        final int limit = 20;
        final int playerCount = ThreadLocalRandom.current().nextInt(1, limit);
        Card highCard = new Card(0, ThreadLocalRandom.current().nextInt(limit));
        Player highPlayer = new SimplePlayer(0);
        highPlayer.receive(highCard);

        Collection<Player> players = new ArrayList<>(playerCount);
        players.add(highPlayer);

        while (players.size() < playerCount) {
            Player lowPlayer = new SimplePlayer(ThreadLocalRandom.current().nextInt());
            Card lowCard = new Card(0, highCard.getRank() - 1);
            lowPlayer.receive(lowCard);
            players.add(lowPlayer);
        }

        round.play(players);

        Collection<Card> winnerCards = new ArrayList<>(playerCount);
        while (highPlayer.hasCards()) {
            winnerCards.add(highPlayer.play());
        }
        assertEquals(playerCount, winnerCards.size());
    }

    /**
     * Verify that everyone loses cards when war condition is triggered.
     */
    @Test
    public void highCardMatchTriggersWarWhereNoOneWins() {
        final int limit = 20;
        final int playerCount = ThreadLocalRandom.current().nextInt(1, limit);
        Card highCard = new Card(0, ThreadLocalRandom.current().nextInt(limit));
        Card otherHighCard = new Card(0, highCard.getRank());
        Player highPlayer = new SimplePlayer(0);
        Player otherHighPlayer = new SimplePlayer(1);
        highPlayer.receive(highCard);
        otherHighPlayer.receive(otherHighCard);

        Collection<Player> players = new ArrayList<>(playerCount);
        players.add(highPlayer);
        players.add(otherHighPlayer);

        while (players.size() < playerCount) {
            Player lowPlayer = new SimplePlayer(ThreadLocalRandom.current().nextInt());
            Card lowCard = new Card(0, highCard.getRank() - 1);
            lowPlayer.receive(lowCard);
            players.add(lowPlayer);
        }

        round.play(players);

        for (Player losingPlayer : players) {
            assertFalse("Everyone should have lost this round", losingPlayer.hasCards());
        }
    }

    /**
     * Sending no players to the round should be ok.
     */
    @Test
    public void testNoPlayersShouldCauseNoProblems() {
        Collection<Player> players = new ArrayList<>();
        round.play(players);
    }
}
