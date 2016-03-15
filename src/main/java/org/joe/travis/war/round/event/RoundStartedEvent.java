package org.joe.travis.war.round.event;

import org.joe.travis.war.player.Player;

import java.util.Collection;

/**
 * Event published when a round starts.
 */
public class RoundStartedEvent {
    /**
     * Round started event.
     * @param players that started the round.
     */
    public RoundStartedEvent(final Collection<Player> players) {

    }

    public Collection<Player> getPlayers() {
        return null;
    }
}
