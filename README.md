# HelloWar

A War (card game) simulator. Currently the simulator allows you to choose the number of suits
and ranks per suit to allow for custom decks. It also allows you to choose the number of players.
Once these criteria have been provided the War simulation will begin.

## Building

This project relies on Maven to build and run tests. If you would like to play the war simulator:

* run ```mvn clean package``` to generate the HellowWar.jar file in the target directory.

## Running War Simulator

Once you have a HelloWar.jar you can run it as follows:

```
java -jar HelloWar.jar --suits 4 --ranks 13 --players 2 --maxRounds 10000
```

Where:

* suits is the number of suits to include in your deck (must be greater than 0)
* ranks is the number of cards per suit (must be greater than 0)
* players is the number of players to simulate playing (must be greater than 1)
* maxRounds is an optional argument that will limit the total number of rounds played 

If you ever forget the necessary parameters, you can just run: ```java -jar HelloWar.jar --help``` for help.

## Extension
I would like to see different variations of War implemented with a parameter to control the type of war.
This should be relatively easy by adding new round types, and configuring the round generator to consider
the round type passed in.


