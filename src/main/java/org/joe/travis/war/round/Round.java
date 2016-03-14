package org.joe.travis.war.round;

import org.joe.travis.war.player.Player;

import java.util.Collection;

/**
 * Represents a round of war.
 */
public interface Round {
    /**
     * Execute a round of war for the collection of players.
     * @param players to include in the round of war.
     */
    void play(Collection<Player> players);
}
