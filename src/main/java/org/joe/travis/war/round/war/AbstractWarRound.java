package org.joe.travis.war.round.war;

import org.joe.travis.war.round.AbstractRound;
import org.joe.travis.war.round.Round;
import org.springframework.context.ApplicationEventPublisher;

abstract class AbstractWarRound extends AbstractRound {
    private final Round parentRound;

    public AbstractWarRound(int id, ApplicationEventPublisher eventPublisher, Round parentRound) {
        super(id, eventPublisher);
        this.parentRound = parentRound;
    }

    public Round getParentRound() {
        return parentRound;
    }
}
