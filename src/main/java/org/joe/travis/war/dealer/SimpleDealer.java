package org.joe.travis.war.dealer;

import org.joe.travis.war.deck.Card;
import org.joe.travis.war.deck.Deck;
import org.joe.travis.war.player.Player;

import java.util.Collection;

/**
 * Simple implementation of the dealer interface. Nothing fancy here.
 */
public class SimpleDealer implements Dealer {
    @Override
    public void deal(final Deck deck, final Collection<Player> players) {
        Card card = deck.deal();

        while (card != null) {
            for (Player player : players) {
                player.receive(card);
                card = deck.deal();
            }
        }
    }
}
