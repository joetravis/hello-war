package org.joe.travis.war.round.event;

import org.joe.travis.war.deck.Card;
import org.joe.travis.war.player.Player;

/**
 * Event to communicate that a card has been played.
 */
public class CardPlayedEvent {
    /**
     * Player who played the card.
     */
    private final Player player;

    /**
     * Card that was played.
     */
    private final Card card;

    /**
     * Card played event should be fired when a card is played.
     *
     * @param player participating in the card played event.
     * @param card the player played.
     */
    public CardPlayedEvent(final Player player, final Card card) {
        this.player = player;
        this.card = card;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getCard() {
        return card;
    }
}
