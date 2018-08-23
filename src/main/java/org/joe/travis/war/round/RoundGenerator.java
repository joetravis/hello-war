package org.joe.travis.war.round;

import org.joe.travis.war.round.war.TraditionalWarRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Service for generating new rounds. For now, there is only one round type, but this can be extended.
 */
@Service
public class RoundGenerator {
    /**
     * Counter for giving rounds IDs.
     */
    private int roundCounter;

    /**
     * Counter for indicating the war round ID within a round.
     */
    private int warRoundCounter;

    /**
     * Event publisher to pass to created rounds.
     */
    private final ApplicationEventPublisher eventPublisher;

    /**
     * Round generator needs an event publisher to pass to created rounds.
     * @param eventPublisher to pass to created rounds.
     */
    @Autowired
    public RoundGenerator(final ApplicationEventPublisher eventPublisher) {
        this.roundCounter = 0;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Generate a new round.
     * @return next round.
     */
    public Round getNextRound() {
        roundCounter++;
        warRoundCounter = 0;
        return new TraditionalRound(roundCounter, eventPublisher).setRoundGenerator(this);
    }

    /**
     * Get the appropriate round of war.
     * @param round that spawned the war round.
     * @return new war round.
     */
    public Round getWarRound(final Round round) {
        warRoundCounter++;
        return new TraditionalWarRound(warRoundCounter, eventPublisher, round).setRoundGenerator(this);
    }
}
