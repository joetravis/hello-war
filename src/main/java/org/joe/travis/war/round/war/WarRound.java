package org.joe.travis.war.round.war;

import org.joe.travis.war.round.Round;

/**
 * Defines capabilities specific to a War Round.
 */
public interface WarRound extends Round {
    /**
     * Gets the parent round of the War Round.
     * @return this War Round's parent round.
     */
    Round getParentRound();
}
