package org.joe.travis.war.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Service for generating new rounds. For now, there is only one round type, but this can be extended.
 */
@Service
public class RoundGenerator {
    /**
     * Round generator needs an event publisher to pass to created rounds.
     * @param eventPublisher to pass to created rounds.
     */
    @Autowired
    public RoundGenerator(final ApplicationEventPublisher eventPublisher) {

    }

    public Round getNextRound() {
        return null;
    }
}
