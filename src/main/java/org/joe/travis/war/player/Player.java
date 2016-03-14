package org.joe.travis.war.player;

import org.joe.travis.war.deck.Card;

/**
 * All the contracts a Player ought to meet.
 */
public interface Player {
    /**
     * Get the player's identifier.
     * @return integer id.
     */
    int getId();

    /**
     * Player plays their next card.
     * @return the player's next card.
     */
    Card play();

    /**
     * Player receives a card and puts it in their "pile".
     *
     * @param card to add to the pile.
     */
    void receive(Card card);

    /**
     * Indicates whether or not the player currently has any cards.
     *
     * @return true if the player still has cards.
     */
    boolean hasCards();
}
