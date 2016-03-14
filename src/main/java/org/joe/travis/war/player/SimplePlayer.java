package org.joe.travis.war.player;

import org.joe.travis.war.deck.Card;

import java.util.LinkedList;

/**
 * Simple player implementation with LinkedList for card tracking.
 */
public class SimplePlayer implements Player {
    /**
     * Player's identifier.
     */
    private final int id;

    /**
     * Cards in player's pile.
     */
    private final LinkedList<Card> cards;

    /**
     * Players need an identifier.
     * @param id player ID.
     */
    public SimplePlayer(final int id) {
        this.id = id;
        this.cards = new LinkedList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Card play() {
        if (cards.isEmpty()) {
            return null;
        }

        return cards.removeFirst();
    }

    @Override
    public void receive(final Card card) {
        if (card == null) {
            return;
        }
        cards.addLast(card);
    }

    @Override
    public boolean hasCards() {
        return !cards.isEmpty();
    }
}
