package org.joe.travis.war.round;

import org.joe.travis.war.player.Player;
import org.joe.travis.war.round.event.RoundCompleteEvent;
import org.joe.travis.war.round.event.RoundStartedEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collection;
import java.util.List;

/**
 * For those with a poetic bent, nobody wins at war.
 */
public class WarInRealLife extends AbstractRound implements Round {
    /**
     * Create a round where war has no winners.
     * @param id for the round.
     * @param eventPublisher to publish round events with.
     */
    public WarInRealLife(final int id, final ApplicationEventPublisher eventPublisher) {
        super(id, eventPublisher);
    }

    @Override
    public void play(final Collection<Player> players) {
        getEventPublisher().publishEvent(new RoundStartedEvent(this, players));
        if (players == null) {
            return;
        }

        playARound(players);

        List<Player> winningPlayers = getWinners(players);

        //no one wins if high card match triggers war condition.
        if (winningPlayers.size() > 1) {
            winningPlayers.clear();
            getEventPublisher().publishEvent(
                    new RoundCompleteEvent(
                            this,
                            winningPlayers,
                            "In war there are no winners. The spoils of war are lost."
                    )
            );
            return;
        }

        for (Player player : players) {
            winningPlayers.get(0).receive(player.getLastCard());
        }

        getEventPublisher().publishEvent(new RoundCompleteEvent(this, winningPlayers));
    }

}
