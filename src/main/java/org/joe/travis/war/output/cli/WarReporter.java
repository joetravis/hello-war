package org.joe.travis.war.output.cli;

import org.joe.travis.war.WarFinishedEvent;
import org.joe.travis.war.WarStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * CLI reporter for war events.
 */
@Service
public class WarReporter  {
    /**
     * Report on war started events.
     * @param warStartedEvent to report on.
     */
    @EventListener
    public void onWarStarted(final WarStartedEvent warStartedEvent) {
        StringBuilder builder = new StringBuilder();
        System.out.println(
                builder.append("Starting a game of war with ")
                .append(warStartedEvent.getNumPlayers())
                .append(" players, and ")
                .append(warStartedEvent.getNumRanks() * warStartedEvent.getNumSuits())
                .append(" cards.")
                .toString()
        );
    }

    /**
     * Report on war finished events.
     * @param warFinishedEvent to report on.
     */
    @EventListener
    public void onApplicationEvent(final WarFinishedEvent warFinishedEvent) {
        System.out.println(getWarFinishedMessage(warFinishedEvent));
    }

    /**
     * Gets a custom war finished event for the number of winners.
     * @param warFinishedEvent to format message for.
     * @return custom message.
     */
    private String getWarFinishedMessage(final WarFinishedEvent warFinishedEvent) {
        StringBuilder builder = new StringBuilder();
        if (warFinishedEvent.getMessage() != null) {
            return builder.append("This war is over. ")
                    .append(warFinishedEvent.getMessage())
                    .toString();
        }

        if (warFinishedEvent.getWinners().size() == 0) {
            return "Sadly, in this war, there are no winners";
        }

        if (warFinishedEvent.getWinners().size() == 1) {
            return builder.append("We have a winner: ")
                    .append(PlayerFormatter.formatPlayers(warFinishedEvent.getWinners()))
                    .toString();
        }

        return builder.append("We have multiple winners: ")
                .append(PlayerFormatter.formatPlayers(warFinishedEvent.getWinners()))
                .toString();
    }
}
