package org.joe.travis.war.output.cli;

import org.joe.travis.war.round.Round;
import org.joe.travis.war.round.war.WarRound;

/**
 * Provides capability to format round related information for normal and war rounds.
 */
public final class RoundFormatter {
    /**
     * Private constructor for utility class.
     */
    private RoundFormatter() {

    }

    /**
     * Formats an individual round.
     * @param round to format.
     * @return formatted round string.
     */
    public static String formatRound(final Round round) {
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
