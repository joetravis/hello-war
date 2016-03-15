package org.joe.travis.war.round.event;

import org.joe.travis.war.player.Player;
import org.joe.travis.war.round.Round;

import java.util.Collection;

/**
 * Event for completion of rounds.
 */
public class RoundCompleteEvent {
    /**
     * Round that just completed.
     */
    private final Round round;

    /**
     * Winners of the round.
     */
    private final Collection<Player> winners;

    /**
     * A special message for the winners.
     */
    private String message = null;

    /**
     * Complete a round without a special message.
     * @param round that just completed.
     * @param winners for the round.
     */
    public RoundCompleteEvent(final Round round, final Collection<Player> winners) {
        this.round = round;
        this.winners = winners;
    }

    /**
     * Create a round complete event with winner(s) for the round and any special message.
     * @param round that just completed.
     * @param winners for the round.
     * @param message special message to include.
     */
    public RoundCompleteEvent(final Round round, final Collection<Player> winners, final String message) {
        this.round = round;
        this.winners = winners;
        this.message = message;
    }

    public Round getRound() {
        return round;
    }

    public Collection<Player> getWinners() {
        return winners;
    }

    public String getMessage() {
        return message;
    }
}
