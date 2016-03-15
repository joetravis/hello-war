package org.joe.travis.war.round.event;

import org.joe.travis.war.deck.Card;
import org.joe.travis.war.player.Player;

/**
 * Event to communicate that a card has been played.
 */
public class CardPlayedEvent {
    /**
     * Card played event should be fired when a card is played.
     *
     * @param player participating in the card played event.
     * @param card the player played.
     */
    public CardPlayedEvent(final Player player, final Card card) {

    }

    public Player getPlayer() {
        return null;
    }

    public Card getCard() {
        return null;
    }
}
