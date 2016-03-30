package org.joe.travis.war.round;

import org.joe.travis.war.deck.Card;
import org.joe.travis.war.player.Player;
import org.joe.travis.war.round.event.RoundCompleteEvent;
import org.joe.travis.war.round.event.RoundStartedEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collection;
import java.util.List;

/**
 * Represents a traditional round of war. War scenario triggers a war round.
 */
public class TraditionalRound extends AbstractRound implements Round {
    /**
     * Create a round where war has no winners.
     * @param id for the round.
     * @param eventPublisher to publish round events with.
     */
    public TraditionalRound(final int id, final ApplicationEventPublisher eventPublisher) {
        super(id, eventPublisher);
    }

    @Override
    public void play(final Collection<Player> players) {
        getEventPublisher().publishEvent(new RoundStartedEvent(this, players));
        if (players == null) {
            return;
        }

        Collection<Card> pot = playARound(players);

        List<Player> winningPlayers = getWinners(players);

        //If there are multiple winners, then winnow them down until there is only one.
        if (winningPlayers.size() > 1) {
            getRoundGenerator().getWarRound(this).play(winningPlayers);
        }

        for (Card card : pot) {
            winningPlayers.get(0).receive(card);
        }

        getEventPublisher().publishEvent(new RoundCompleteEvent(this, winningPlayers));
    }
}
