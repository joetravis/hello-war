package org.joe.travis.war.dealer;

import org.joe.travis.war.deck.Deck;
import org.joe.travis.war.player.Player;

import java.util.Collection;

/**
 * Implementation of allocating cards to players.
 */
public interface Dealer {
    /**
     * Deal cards from the deck to a collection of players.
     *
     * @param deck to deal cards from.
     * @param players collection to deal cards to.
     */
    void deal(Deck deck, Collection<Player> players);
}
