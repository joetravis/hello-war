package org.joe.travis.war.output.cli;

import org.joe.travis.war.round.event.CardPlayedEvent;
import org.joe.travis.war.round.event.RoundCompleteEvent;
import org.joe.travis.war.round.event.RoundStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Command line reporter for round level events.
 */
@Service
public class RoundReporter {
    /**
     * Report on card being played.
     * @param cardPlayedEvent to report on.
     */
    @EventListener
    public void onCardPlayed(final CardPlayedEvent cardPlayedEvent) {
        System.out.println(
                String.format(
                        "-Player %d plays card rank %d, suit %d",
                        cardPlayedEvent.getPlayer().getId(),
                        cardPlayedEvent.getCard().getRank(),
                        cardPlayedEvent.getCard().getSuit()
                )
        );
    }

    /**
     * Report on round starting.
     * @param roundStartedEvent to report on.
     */
    @EventListener
    public void onRoundStarted(final RoundStartedEvent roundStartedEvent) {
        System.out.println(
                String.format(
                        "Round %d begins with %s",
                        roundStartedEvent.getRound().getId(),
                        PlayerFormatter.formatPlayers(roundStartedEvent.getPlayers())
                )
        );
    }

    /**
     * Report on a round that just finished.
     * @param roundCompleteEvent to report on.
     */
    @EventListener
    public void onRoundFinished(final RoundCompleteEvent roundCompleteEvent) {
        System.out.println(getRoundCompleteMessage(roundCompleteEvent));
    }

    /**
     * Get a custom message for round completed event.
     * @param roundCompleteEvent to format message for.
     * @return formatted message.
     */
    private String getRoundCompleteMessage(final RoundCompleteEvent roundCompleteEvent) {
        if (roundCompleteEvent.getMessage() == null) {
            return String.format(
                    "Round %d ends with winner: %s",
                    roundCompleteEvent.getRound().getId(),
                    PlayerFormatter.formatPlayers(roundCompleteEvent.getWinners())
            );
        }

        if (roundCompleteEvent.getWinners().size() == 0) {
            return String.format(
                    "Round %d ends with no winners. %s",
                    roundCompleteEvent.getRound().getId(),
                    roundCompleteEvent.getMessage()
            );
        }

        return String.format(
                "Round %d ends with winner: %s. %s",
                roundCompleteEvent.getRound().getId(),
                PlayerFormatter.formatPlayers(roundCompleteEvent.getWinners()),
                roundCompleteEvent.getMessage()
        );
    }
}
