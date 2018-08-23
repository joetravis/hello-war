package org.joe.travis.war.round;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for round generator.
 */
public class RoundGeneratorTest {
    /**
     * Subject under test.
     */
    @InjectMocks
    private RoundGenerator roundGenerator;

    /**
     * Event publisher dependency.
     */
    @Mock
    private ApplicationEventPublisher eventPublisher;

    /**
     * Initialize subject under test and test fixtures.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * For now the only round type is war in real life.
     */
    @Test
    public void getNextRoundShouldReturnTraditionalWarRound() {
        assertThat(roundGenerator.getNextRound(), instanceOf(TraditionalRound.class));
    }

    /**
     * Generator should supply unique IDs.
     */
    @Test
    public void getNextRoundShouldSupplyUniqueIds() {
        Round one = roundGenerator.getNextRound();
        Round two = roundGenerator.getNextRound();

        assertFalse(one.getId() == two.getId());
    }
}
