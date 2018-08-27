package org.joe.travis.war;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main driver for the war simulator.
 */
public final class HelloWar {

    /**
     * Default value for max number of rounds before ceasing play.
     */
    public static final int MAX_ROUNDS_DEFAULT = 10000;

    /**
     * No need to construct HelloWar when only static main is present.
     */
    private HelloWar() {

    }

    /**
     * Main is where the magic happens.
     *
     * @param args for the program.
     */
    public static void main(final String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(WarConfig.class);
        int numberOfSuits;
        int numberOfRanks;
        int numberOfPlayers;
        int maxRounds;

        CommandLineParser parser = new DefaultParser();
        Options commandLineOptions = context.getBean(Options.class);
        CommandLine line;

        try {
            // parse the command line arguments
            line = parser.parse(commandLineOptions, args);
        }  catch (ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
            return;
        }

        if (line.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java HelloWar.jar", commandLineOptions);
            return;
        }

        try {
            numberOfSuits = getCommandLineArgument(line, "suits", 1);
            numberOfRanks = getCommandLineArgument(line, "ranks", 1);
            numberOfPlayers = getCommandLineArgument(line, "players", 2);
            maxRounds = getMaxRoundsToPlay(line);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid arguments provided: " + e.getMessage());
            return;
        }

        War war = context.getBean(War.class);
        war.play(numberOfSuits, numberOfRanks, numberOfPlayers, maxRounds);
    }

    /**
     * Get the max number of rounds to play from argument or default.
     * @param line CommandLine to pull arg from.
     * @return max rounds to play.
     */
    private static int getMaxRoundsToPlay(final CommandLine line) {
        if (!line.hasOption("maxRounds")) {
            return MAX_ROUNDS_DEFAULT;
        }
        return getCommandLineArgument(line, "maxRounds", 1);
    }

    /**
     * Get a numeric command line argument with a lower bound.
     * @param line parsed command line.
     * @param argName argument name to get.
     * @param minValue minimum value for the argument.
     * @return int value of argument.
     * @throws IllegalArgumentException if the argument is not acceptable.
     */
    private static int getCommandLineArgument(final CommandLine line, final String argName, final int minValue)
            throws IllegalArgumentException {
        if (!line.hasOption(argName)) {
            throw new IllegalArgumentException("Missing " + argName);
        }

        int value;
        try {
            value = Integer.parseInt(line.getOptionValue(argName));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(argName + " must be a positive integer");
        }

        if (value < minValue) {
            throw new IllegalArgumentException(argName + " must be above " + minValue);
        }


        return value;
    }
}
