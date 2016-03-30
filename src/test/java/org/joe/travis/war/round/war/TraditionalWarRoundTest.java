package org.joe.travis.war.round.war;

import org.joe.travis.war.deck.Card;
import org.joe.travis.war.player.Player;
import org.joe.travis.war.player.SimplePlayer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit tests for a traditional war round.
 */
public class TraditionalWarRoundTest {
    /**
     * Subject under test.
     */
    private TraditionalWarRound round;

    /**
     * Publisher dependency.
     */
    @Mock
    private ApplicationEventPublisher publisher;

    /**
     * Player to take the role of winner in tests.
     */
    private Player winner;

    /**
     * Player to take the role of loser in tests.
     */
    private Player loser;

    /**
     * List of players for war round tests.
     */
    private List<Player> players;

    /**
     * Initialize subject under test and test fixtures.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        round = new TraditionalWarRound(ThreadLocalRandom.current().nextInt(), publisher);
        winner = new SimplePlayer(ThreadLocalRandom.current().nextInt());
        loser = new SimplePlayer(ThreadLocalRandom.current().nextInt());
        players = new ArrayList<>();
        players.add(loser);
        players.add(winner);
    }

    /**
     * Verify the normal functioning of the war round.
     */
    @Test
    public void playShouldSpendTwoCardsAndDecideWinnerOnThirdCard() {
        Integer lowRank = ThreadLocalRandom.current().nextInt();
        Card lowCard = new Card(ThreadLocalRandom.current().nextInt(), lowRank);
        Card highCard = new Card(ThreadLocalRandom.current().nextInt(), lowRank + 1);

        for (int i = 0; i < 2; i++) {
            loser.receive(lowCard);
            winner.receive(lowCard);
        }

        loser.receive(lowCard);
        winner.receive(highCard);

        round.play(players);

        assertEquals(1, players.size());
        assertTrue("Expected winner did not have any cards", winner.hasCards());
        assertFalse("Expected loser had cards left", loser.hasCards());
    }

    /**
     * Players without enough cards for a round should lose.
     */
    @Test
    public void playerWithoutEnoughCardsForWarShouldLose() {
        Integer lowRank = ThreadLocalRandom.current().nextInt();
        Card lowCard = new Card(ThreadLocalRandom.current().nextInt(), lowRank);
        Card highCard = new Card(ThreadLocalRandom.current().nextInt(), lowRank + 1);

        for (int i = 0; i < 2; i++) {
            loser.receive(lowCard);
            winner.receive(lowCard);
        }

        winner.receive(highCard);

        round.play(players);

        assertEquals(1, players.size());
        assertTrue("Expected winner did not have any cards", winner.hasCards());
        assertFalse("Expected loser had cards left", loser.hasCards());
    }

    /**
     * Ensure that if both players do not have neough cards, the player with the most cards should win.
     */
    @Test
    public void playerWithMostCardsWhenNoneHaveEnoughShouldWIn() {
        Integer lowRank = ThreadLocalRandom.current().nextInt();
        Card lowCard = new Card(ThreadLocalRandom.current().nextInt(), lowRank);

        loser.receive(lowCard);
        winner.receive(lowCard);
        winner.receive(lowCard);

        round.play(players);

        assertEquals(1, players.size());
        assertTrue("Expected winner did not have any cards", winner.hasCards());
        assertFalse("Expected loser had cards left", loser.hasCards());
    }
}
