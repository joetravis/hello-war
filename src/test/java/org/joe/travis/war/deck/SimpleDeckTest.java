package org.joe.travis.war.deck;

import org.joe.travis.war.deck.shuffler.Shuffler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the SimpleDeck implementation of Deck.
 */
public class SimpleDeckTest {
    /**
     * Subject under test.
     */
    private SimpleDeck deck;

    /**
     * Shuffler dependency.
     */
    @Mock
    private Shuffler shuffler;

    /**
     * For verification of mocked dependency.
     */
    @Captor
    private ArgumentCaptor<Card[]> cardsArgument;

    /**
     * Suit count to be used for valid deck tests.
     */
    private int suitCount;

    /**
     * Rank count to be used for valid deck tests.
     */
    private int rankCount;

    /**
     * Initialize subject under tests and test fixtures.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        deck = new SimpleDeck(shuffler);

        final int limit = 10;

        rankCount = ThreadLocalRandom.current().nextInt(1, limit);
        suitCount = ThreadLocalRandom.current().nextInt(1, limit);
    }

    /**
     * Verify that cards cannot be dealt until deck is created.
     */
    @Test
    public void dealShouldReturnNullIfDeckHasNotBeenCreated() {
        assertNull("Cards should not be dealt before deck creation", deck.deal());
    }

    /**
     * Verify that ranks * suits total cards are created.
     */
    @Test
    public void createShouldCreateRankCardsInEachSuit() {
        List<List<Card>> cardOrganizer = createEmptyCardOrganizer();

        deck.create(suitCount, rankCount);

        Card card = deck.deal();

        /**
         * Organize cards into sub-lists indexed by suit.
         */
        while (card != null) {
            cardOrganizer.get(card.getSuit()).add(card);

            card = deck.deal();
        }

        /**
         * Verify that each suit indexed sub list contains the expected cards.
         */
        verifyExpectedCards(cardOrganizer);
    }

    /**
     * Generates and returns an empty card organizer to be used for deck verification.
     *
     * @return empty card organizer.
     */
    private List<List<Card>> createEmptyCardOrganizer() {
        List<List<Card>> cardOrganizer = new ArrayList<>(suitCount);

        for (int i = 0; i < suitCount; i++) {
            cardOrganizer.add(new ArrayList<Card>(rankCount));
        }

        return cardOrganizer;
    }

    /**
     * Verify all cards are present in organizer by suit and rank.
     *
     * @param cardOrganizer filled with cards.
     */
    private void verifyExpectedCards(final List<List<Card>> cardOrganizer) {
        /**
         * Verify that each suit indexed sub list contains the expected cards.
         */
        for (int i = 0; i < suitCount; i++) {
            verifyExpectedRanks(cardOrganizer.get(i), rankCount);
        }
    }

    /**
     * Verify that all the expected card ranks are present in the list.
     * @param cards list of cards to verify.
     * @param rankCount expected ranks to find.
     */
    private void verifyExpectedRanks(final List<Card> cards, final int rankCount) {
        assertEquals("Expected card ranks were not represented.", rankCount, cards.size());

        Collections.sort(cards);

        for (int i = 0; i < rankCount; i++) {
            assertEquals("Missing an expected card rank.", i, cards.get(i).getRank());
        }
    }

    /**
     * Verify that the deck uses a shuffler to shuffle.
     */
    @Test
    public void deckDelegatesShufflingToShuffler() {
        List<List<Card>> cardOrganizer = createEmptyCardOrganizer();

        deck.create(suitCount, rankCount);
        deck.shuffle();

        verify(shuffler).shuffle(cardsArgument.capture());

        /**
         * Verify that all expected cards were passed to shuffler.
         */
        for (Card card : cardsArgument.getValue()) {
            cardOrganizer.get(card.getSuit()).add(card);
        }

        verifyExpectedCards(cardOrganizer);
    }

    /**
     * Shuffle will allow an expended deck to be re-dealt.
     */
    @Test
    public void shuffleAllowsForReDealingCards() {
        deck.create(suitCount, rankCount);
        int initialCount = getCardCount();

        deck.shuffle();
        int subsequentCount = getCardCount();

        assertEquals(initialCount, subsequentCount);
    }

    /**
     * Deal and get the count of dealt cards.
     *
     * @return count of dealt cards.
     */
    private int getCardCount() {
        int count = 0;

        Card card = deck.deal();

        while (card != null) {
            card = deck.deal();
            count++;
        }

        return count;
    }

    /**
     * Deck should not allow creation with a negative value for suits.
     */
    @Test(expected = DeckCreationException.class)
    public void verifyNegativeSuitsResultsInException() {
        int negativeSuit = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, -1);
        int validRank = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);

        deck.create(negativeSuit, validRank);
    }

    /**
     * Deck should not allow creation with a zero value for suits.
     */
    @Test(expected = DeckCreationException.class)
    public void verifyZeroSuitsResultsInException() {
        int validRank = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);

        deck.create(0, validRank);
    }

    /**
     * Deck should not allow creation with a negative value for ranks.
     */
    @Test(expected = DeckCreationException.class)
    public void verifyNegativeRanksResultsInException() {
        int validSuit = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        int negativeRank = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, -1);

        deck.create(validSuit, negativeRank);
    }

    /**
     * Deck should not allow creation with a zero value for ranks.
     */
    @Test(expected = DeckCreationException.class)
    public void verifyZeroRanksResultsInException() {
        int validSuit = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);

        deck.create(validSuit, 0);
    }
}
