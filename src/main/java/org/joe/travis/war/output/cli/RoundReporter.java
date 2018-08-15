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
        StringBuilder builder = new StringBuilder();
        System.out.println(
                builder.append("-Player ")
                    .append(cardPlayedEvent.getPlayer().getId())
                    .append(" plays card rank ")
                    .append(cardPlayedEvent.getCard().getRank())
                    .append(", suit ")
                    .append(cardPlayedEvent.getCard().getSuit())
                    .toString()
        );
    }

    /**
     * Report on round starting.
     * @param roundStartedEvent to report on.
     */
    @EventListener
    public void onRoundStarted(final RoundStartedEvent roundStartedEvent) {
        StringBuilder builder = new StringBuilder();
        System.out.println(
                builder.append(RoundFormatter.formatRound(roundStartedEvent.getRound()))
                    .append(" begins with ")
                    .append(PlayerFormatter.formatPlayers(roundStartedEvent.getPlayers()))
                    .toString()
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
        StringBuilder builder = new StringBuilder();
        String formattedRound = RoundFormatter.formatRound(roundCompleteEvent.getRound());

        if (roundCompleteEvent.getMessage() == null) {
            return builder.append(formattedRound)
                    .append(" ends with winner: ")
                    .append(PlayerFormatter.formatPlayers(roundCompleteEvent.getWinners()))
                    .toString();
        }

        if (roundCompleteEvent.getWinners().size() == 0) {
            return builder.append(formattedRound)
                    .append(" ends with no winners. ")
                    .append(roundCompleteEvent.getMessage())
                    .toString();
        }

        return builder.append(formattedRound)
                .append(" ends with winner: ")
                .append(PlayerFormatter.formatPlayers(roundCompleteEvent.getWinners()))
                .append(". ")
                .append(roundCompleteEvent.getMessage())
                .toString();
    }
}
