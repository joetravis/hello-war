package org.joe.travis.war.round.event;

import org.joe.travis.war.player.Player;
import org.joe.travis.war.round.Round;

import java.util.Collection;

/**
 * Event published when a round starts.
 */
public class RoundStartedEvent {
    /**
     * Round that started.
     */
    private final Round round;

    /**
     * Players playing the round.
     */
    private final Collection<Player> players;

    /**
     * Round started event.
     * @param round that started.
     * @param players that started the round.
     */
    public RoundStartedEvent(final Round round, final Collection<Player> players) {
        this.round = round;
        this.players = players;
    }

    public Round getRound() {
        return round;
    }

    public Collection<Player> getPlayers() {
        return players;
    }
}
