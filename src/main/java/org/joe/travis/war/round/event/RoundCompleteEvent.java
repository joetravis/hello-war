package org.joe.travis.war.round.event;

import org.joe.travis.war.player.Player;

import java.util.Collection;

/**
 * Created by travisj on 3/13/16.
 */
public class RoundCompleteEvent {
    /**
     * Create a round complete event with winner(s) for the round and any special message.
     * @param winners for the round.
     * @param message special message to include.
     */
    public RoundCompleteEvent(final Collection<Player> winners, final String message) {

    }
}
