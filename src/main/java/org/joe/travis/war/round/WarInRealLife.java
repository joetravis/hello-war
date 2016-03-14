package org.joe.travis.war.round;

import org.joe.travis.war.player.Player;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    //TODO need to account for no players, null players
    @Override
    public void play(final Collection<Player> players) {
        for (Player player : players) {
            player.play();
        }

        List<Player> winningPlayers = getWinners(players);

        //no one wins if high card match triggers war condition.
        if (winningPlayers.size() > 1) {
            return;
        }

        for (Player player : players) {
            winningPlayers.get(0).receive(player.getLastCard());
        }
    }

    /**
     * Collect the winners (players with the highest card) from this round.
     *
     * @param players that have played this round.
     * @return list of players with highest card this round.
     */
    private List<Player> getWinners(final Collection<Player> players) {
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
