package org.joe.travis.war;

import org.joe.travis.war.dealer.Dealer;
import org.joe.travis.war.dealer.SimpleDealer;
import org.joe.travis.war.deck.Deck;
import org.joe.travis.war.player.Player;
import org.joe.travis.war.player.SimplePlayer;
import org.joe.travis.war.round.Round;
import org.joe.travis.war.round.RoundGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Driver for the game of war.
 */
@Service
public class War {
    /**
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(War.class);

    /**
     * Event publisher for publishing war events.
     */
    private final ApplicationEventPublisher eventPublisher;

    /**
     * Generates the next round.
     */
    private final RoundGenerator roundGenerator;

    /**
     * Deck to play war with.
     */
    private Deck deck;

    /**
     * In order to play war, you need a deck.
     * @param deck to use for the game.
     * @param roundGenerator for generating new rounds.
     * @param eventPublisher for publishing war events.
     */
    @Autowired
    public War(
            final Deck deck,
            final RoundGenerator roundGenerator,
            final ApplicationEventPublisher eventPublisher
    ) {
        this.deck = deck;
        this.roundGenerator = roundGenerator;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Drive a game of war.
     * @param numberOfSuits to include in the deck.
     * @param numberOfRanks to include per suit.
     * @param numberOfPlayers to play the game with.
     */
    public void play(final int numberOfSuits, final int numberOfRanks, final int numberOfPlayers) {
        final int mercyRuleLimit = 10000;
        eventPublisher.publishEvent(new WarStartedEvent(numberOfSuits, numberOfRanks, numberOfPlayers));
        if (numberOfPlayers < 2) {
            return;
        }

        LOGGER.debug("Creating {} players.", numberOfPlayers);
        Collection<Player> players = createPlayers(numberOfPlayers);
        deck.create(numberOfSuits, numberOfRanks);
        deck.shuffle();

        Dealer dealer = new SimpleDealer();
        dealer.deal(deck, players);

        Collection<Player> contenders = getContenders(players);
        //keep playing until less than 2 contenders remain
        while (contenders.size() > 1) {
            Round round = roundGenerator.getNextRound();
            round.play(contenders);
            contenders = getContenders(players);
            if (round.getId() > mercyRuleLimit) {
                eventPublisher.publishEvent(
                        new WarFinishedEvent("Are you really still playing? I'm invoking the mercy rule."
                        )
                );
                return;
            }
        }

        eventPublisher.publishEvent(new WarFinishedEvent(contenders));
    }

    /**
     * Create the player collection.
     * @param numberOfPlayers to create.
     * @return a collection of players.
     */
    private Collection<Player> createPlayers(final int numberOfPlayers) {
        Collection<Player> players = new ArrayList<>(numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new SimplePlayer(i));
        }

        return players;
    }

    /**
     * Get the contenders (players with cards) for the next round.
     * @param players to consider for contender status.
     * @return list of contenders.
     */
    private Collection<Player> getContenders(final Collection<Player> players) {
        Collection<Player> contenders = new ArrayList<>(players.size());

        for (Player player : players) {
            if (player.hasCards()) {
                contenders.add(player);
            }
        }

        return contenders;
    }
}
