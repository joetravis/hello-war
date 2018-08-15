package org.joe.travis.war.output.cli;

import org.joe.travis.war.round.Round;
import org.joe.travis.war.round.war.WarRound;

public class RoundFormatter {
    public static String formatRound(Round round) {
        StringBuilder builder = new StringBuilder();
        if (round instanceof WarRound) {
            WarRound warRound = (WarRound) round;
            return builder.append("War Round ")
                    .append(warRound.getParentRound().getId())
                    .append("-")
                    .append(warRound.getId())
                    .toString();
        }

        return builder.append("Round ")
                .append(round.getId())
                .toString();
    }
}
