package org.joe.travis.war;

/**
 * Notification that a war has started.
 */
public class WarStartedEvent {
    /**
     * Number of suits to used in the deck.
     */
    private final int numSuits;

    /**
     * Number of ranks included per suit.
     */
    private final int numRanks;

    /**
     * Number of players included in the game.
     */
    private final int numPlayers;

    /**
     * Create a war started event indicating the war's parameters.
     * @param numSuits number of suits to use in the deck.
     * @param numRanks number of ranks to include per suit.
     * @param numPlayers number of players to include in the game.
     */
    public WarStartedEvent(final int numSuits, final int numRanks, final int numPlayers) {
        this.numSuits = numSuits;
        this.numRanks = numRanks;
        this.numPlayers = numPlayers;
    }

    public int getNumSuits() {
        return numSuits;
    }

    public int getNumRanks() {
        return numRanks;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}
