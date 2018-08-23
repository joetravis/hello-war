package org.joe.travis.war.round.war;

import org.joe.travis.war.round.AbstractRound;
import org.joe.travis.war.round.Round;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Provide default behavior for War Rounds.
 */
abstract class AbstractWarRound extends AbstractRound {
    /**
     * The round that spawned this War Round.
     */
    private final Round parentRound;

    /**
     *
     * @param id for this War Round within its parent round.
     * @param eventPublisher to publish round events to.
     * @param parentRound that spawned this War Round.
     */
    AbstractWarRound(final int id, final ApplicationEventPublisher eventPublisher, final Round parentRound) {
        super(id, eventPublisher);
        this.parentRound = parentRound;
    }

    /**
     * Gets the parent round of the War Round.
     * @return this War Round's parent round.
     */
    public Round getParentRound() {
        return parentRound;
    }
}
