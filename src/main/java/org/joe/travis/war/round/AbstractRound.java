package org.joe.travis.war.round;

import org.joe.travis.war.deck.Card;
import org.joe.travis.war.player.Player;
import org.joe.travis.war.round.event.CardPlayedEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Provide generic round capabilities.
 */
public abstract class AbstractRound implements Round {
    /**
     * ID for the round.
     */
    private int id;

    /**
     * Publisher for round events.
     */
    private ApplicationEventPublisher eventPublisher;

    /**
     * Used to generate war rounds.
     */
    private RoundGenerator roundGenerator;

    /**
     * Every round should have id and publishing capabilities.
     * @param id for the round.
     * @param eventPublisher to publish round events.
     */
    public AbstractRound(final int id, final ApplicationEventPublisher eventPublisher) {
        this.id = id;
        this.eventPublisher = eventPublisher;
    }

    /**
     * @return the ID for the round.
     */
    public int getId() {
        return id;
    }

    /**
     * @return the event publisher for the round.
     */
    public ApplicationEventPublisher getEventPublisher() {
        return eventPublisher;
    }

    /**
     * @return the round generator used to create war rounds.
     */
    public RoundGenerator getRoundGenerator() {
        return roundGenerator;
    }

    /**
     * Set the round generator used to create war rounds.
     * @param roundGenerator to use.
     * @return Round Generator.
     */
    public Round setRoundGenerator(final RoundGenerator roundGenerator) {
        this.roundGenerator = roundGenerator;
        return this;
    }

    /**
     * Let the players play a round.
     * @param players to play this round.
     * @return collection of cards in the pot for the round.
     */
    protected Collection<Card> playARound(final Collection<Player> players) {
        List<Card> pot = new ArrayList<>();

        for (Player player : players) {
            Card card = player.play();
            pot.add(card);
            if (card != null) {
                getEventPublisher().publishEvent(new CardPlayedEvent(player, card));
            }
        }

        return pot;
    }

    /**
     * Collect the winners (players with the highest card) from this round.
     *
     * @param players that have played this round.
     * @return list of players with highest card this round.
     */
    protected List<Player> getWinners(final Collection<Player> players) {
        List<Player> winningPlayers = new ArrayList<>();

        for (Player player : players) {
            addToWinnersList(winningPlayers, player);
        }

        return winningPlayers;
    }

    /**
     * Add the current player to the winners circle, or start a new winners circle.
     *
     * @param winningPlayers current winning players.
     * @param player to check for winning status.
     */
    private void addToWinnersList(final List<Player> winningPlayers, final Player player) {
        // If there are no winning players yet, current player is the defacto winner.
        if (winningPlayers.size() == 0) {
            winningPlayers.add(player);
            return;
        }

        // If player ran out of cards, they lose
        if (player.getLastCard() == null) {
            return;
        }

        // If card doesn't win, they don't belong in the winner's circle.
        if (player.getLastCard().compareTo(winningPlayers.get(0).getLastCard()) == -1) {
            return;
        }

        // On ties, the winner's circle will expand.
        if (player.getLastCard().compareTo(winningPlayers.get(0).getLastCard()) == 0) {
            winningPlayers.add(player);
            return;
        }

        // if we've made it this far, the player is ahead of the winner's circle.
        winningPlayers.clear();
        winningPlayers.add(player);
    }
}
