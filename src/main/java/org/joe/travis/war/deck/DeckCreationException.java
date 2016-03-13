package org.joe.travis.war.deck;

/**
 * Exception for deck creation problems.
 */
public class DeckCreationException extends RuntimeException {
    /**
     * Pass the message into the existing RuntimeException framework.
     *
     * @param message issue with creating the deck.
     */
    public DeckCreationException(final String message) {
        super(message);
    }
}
