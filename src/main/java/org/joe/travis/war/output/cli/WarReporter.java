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
        System.out.println(
                String.format(
                        "Starting a game of war with %d players, and %d cards.",
                        warStartedEvent.getNumPlayers(),
                        warStartedEvent.getNumRanks() * warStartedEvent.getNumSuits()
                )
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
        if (warFinishedEvent.getMessage() != null) {
            return String.format(
                    "This war is over. %s",
                    warFinishedEvent.getMessage()
            );
        }

        if (warFinishedEvent.getWinners().size() == 0) {
            return "Sadly, in this war, there are no winners";
        }

        if (warFinishedEvent.getWinners().size() == 1) {
            return String.format(
                    "We have a winner: %s",
                    PlayerFormatter.formatPlayers(warFinishedEvent.getWinners())
            );
        }

        return String.format(
                "We have multiple winners: %s",
                PlayerFormatter.formatPlayers(warFinishedEvent.getWinners())
        );
    }
}
