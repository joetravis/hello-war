package org.joe.travis.war.round.war;

import org.joe.travis.war.round.Round;

public interface WarRound extends Round {
    Round getParentRound();
}
