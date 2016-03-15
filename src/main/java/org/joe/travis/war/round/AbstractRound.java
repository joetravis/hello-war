package org.joe.travis.war.round;

import org.springframework.context.ApplicationEventPublisher;

/**
 * Provide generic round capabilities.
 */
public class AbstractRound {
    /**
     * ID for the round.
     */
    private int id;

    /**
     * Publisher for round events.
     */
    private ApplicationEventPublisher eventPublisher;

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
}
