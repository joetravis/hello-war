package org.joe.travis.war;

import org.joe.travis.war.player.Player;

import java.util.Collection;

/**
 * Event created when war has finished.
 */
public class WarFinishedEvent {
    /**
     * Winners of the war.
     */
    private Collection<Player> winners;

    /**
     * Special message.
     */
    private String message;

    /**
     * When the war finishes, declare the winner(s).
     * @param winners of the war.
     */
    public WarFinishedEvent(final Collection<Player> winners) {
        this.winners = winners;
    }

    /**
     * Allow for war to end with a message rather than a winner.
     * @param message to end the war with.
     */
    public WarFinishedEvent(final String message) {
        this.message = message;
    }

    public Collection<Player> getWinners() {
        return winners;
    }

    public String getMessage() {
        return message;
    }
}
