package org.joe.travis.war.round;

import org.joe.travis.war.deck.Card;
import org.joe.travis.war.player.Player;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Collection;

/**
 * For those with a poetic bent, nobody wins at war.
 */
public class WarInRealLife implements Round {
    /**
     * Create a round where war has no winners.
     *
     * @param eventPublisher to publish round events with.
     */
    public WarInRealLife(final ApplicationEventPublisher eventPublisher) {

    }

    @Override
    public void play(final Collection<Player> players) {
        int highestRank = -1;
        int highestRankCount = 0;
        Player highestPlayer = null;

        Collection<Card> playedCards = new ArrayList<>(players.size());

        for (Player player : players) {
            Card playerCard = player.play();
            playedCards.add(playerCard);

            if (playerCard.getRank() == highestRank) {
                highestRankCount++;
            }
            if (playerCard.getRank() > highestRank) {
                highestRank = playerCard.getRank();
                highestPlayer = player;
                highestRankCount = 0;
            }
        }

        //no one wins if high card match triggers war condition.
        if (highestRankCount > 0) {
            return;
        }

        for (Card spoils : playedCards) {
            highestPlayer.receive(spoils);
        }
    }
}
