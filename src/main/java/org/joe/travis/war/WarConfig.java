package org.joe.travis.war;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.joe.travis.war.deck.Deck;
import org.joe.travis.war.deck.SimpleDeck;
import org.joe.travis.war.deck.shuffler.FisherYatesShuffler;
import org.joe.travis.war.deck.shuffler.Shuffler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Bean config for war.
 */
@Configuration
@ComponentScan("org.joe.travis")
public class WarConfig {
    /**
     * @return Options for the command line parser.
     */
    @Bean(name = "warOptions")
    public Options getOptions() {
        Options warOptions = new Options();
        warOptions.addOption(new Option("s", "suits", true, "the number of suits to include in the deck"));
        warOptions.addOption(new Option("r", "ranks", true, "the number of ranks to include in a suit"));
        warOptions.addOption(new Option("p", "players", true, "the number of players to play war"));
        warOptions.addOption(new Option("h", "help", false, "display this message"));

        return warOptions;
    }

    /**
     * Bean for deck object.
     * @return a simple deck for now.
     */
    @Bean(name = "deck")
    public Deck getDeck() {
        return new SimpleDeck(getShuffler());
    }

    /**
     * Bean for shuffler object.
     * @return a shuffler implementation.
     */
    @Bean(name = "shuffler")
    public Shuffler getShuffler() {
        return new FisherYatesShuffler();
    }
}
