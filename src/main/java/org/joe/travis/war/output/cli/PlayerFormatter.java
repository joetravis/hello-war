package org.joe.travis.war.output.cli;

import org.joe.travis.war.player.Player;

import java.util.Collection;

/**
 * Formats a collection of players into a readable format.
 */
public final class PlayerFormatter {
    /**
     * String builder for providing player formatted strings.
     */
    private static StringBuilder builder = new StringBuilder();

    /**
     * No public constructor for utility classes.
     */
    private PlayerFormatter() {

    }

    /**
     * Format a list of players.
     * @param players to format.
     * @return formatted string.
     */
    public static String formatPlayers(final Collection<Player> players) {
        if (players == null || players.size() == 0) {
            return "";
        }

        builder.setLength(0);

        for (Player player : players) {
            builder.append("player ")
                    .append(player.getId())
                    .append(", ");
        }

        return builder.deleteCharAt(builder.lastIndexOf(", "))
                .toString();
    }
}
