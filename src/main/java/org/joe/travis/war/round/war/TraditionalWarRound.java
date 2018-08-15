package org.joe.travis.war.round.war;

import org.joe.travis.war.deck.Card;
import org.joe.travis.war.player.Player;
import org.joe.travis.war.round.AbstractRound;
import org.joe.travis.war.round.Round;
import org.joe.travis.war.round.event.RoundCompleteEvent;
import org.joe.travis.war.round.event.RoundStartedEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a war round in a traditional war game.
 */
public class TraditionalWarRound extends AbstractWarRound implements WarRound {
    /**
     * War round for a traditional game of war.
     * @param id round id.
     * @param eventPublisher to publish events to.
     */
    public TraditionalWarRound(final int id, final ApplicationEventPublisher eventPublisher, Round parentRound) {
        super(id, eventPublisher, parentRound);
    }

    @Override
    public void play(final Collection<Player> players) {
        getEventPublisher().publishEvent(new RoundStartedEvent(this, players));

        Collection<Card> pot = playARound(players);
        pot.addAll(playARound(players));

        List<Player> winners = getWinners(players);

        if (winners.size() > 1) {
            getRoundGenerator().getWarRound(this).play(winners);
        }

        players.clear();
        players.addAll(winners);

        for (Card card : pot) {
            winners.get(0).receive(card);
        }

        getEventPublisher().publishEvent(new RoundCompleteEvent(this, winners));
    }

    @Override
    public Collection<Card> playARound(final Collection<Player> players) {
        List<Player> outOfCards = players.stream()
                .filter(player -> !player.hasCards())
                .collect(Collectors.toList());

        if (outOfCards.size() != players.size()) {
            players.removeAll(outOfCards);
        }

        return super.playARound(players);
    }
}
