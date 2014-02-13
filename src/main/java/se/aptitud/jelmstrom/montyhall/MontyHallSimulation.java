package se.aptitud.jelmstrom.montyhall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Runs a single game highlighting the MontyHall problem.
 *
 * Each game is created with a set of three boxes, the contestant makes his/her choice after which the host opens one
 * of the remaining empty boxes and asks the contestant to choose between his/her original choice and the last remaining box.
 *
 * This solution operates on a list where the contestants choice is removed from the list, leaving the host two boxes to choose
 * between to open. One of these will be an empty box, and the first one is removed, leaving a single box for the contestant.
 *
 */
public class MontyHallSimulation {

    private static final int NUMBER_OF_BOXES = 3;

    private MontyHallSimulation(){

    }

    public enum Prize {
        EMPTY,
        MONEY
    }

    /**
     * Runs a game where the contestant does not change his mind after the initial selection is made.
     *
     * @return The Prize of the contestants chosen box
     */


    public static Prize runGameWithoutChangingBox() {
        List<Prize> boxes = initiateGame();
        int contestantsChoice = new Random().nextInt(NUMBER_OF_BOXES);
        Prize prize = getContestantsChoice(boxes, contestantsChoice);
        //There is no point 'opening' an empty box as it does not affect the likelyhood of winning. Return the prize
        return prize;

    }

    /**
     * Initiates a game with 3 boxes where a random box contains the Prize Money
     * @return a List of boxes containing Prize
     */
    private static List<Prize> initiateGame() {
        int winningBox = new Random().nextInt(NUMBER_OF_BOXES);
        List<Prize> boxes = new ArrayList<Prize>(Arrays.asList(Prize.EMPTY, Prize.EMPTY, Prize.EMPTY));
        boxes.set(winningBox,Prize.MONEY);
        return boxes;
    }

    /**
     * Removes the box that the contestant chooses from the list of boxes.
     * Limiting the choices of boxes the Host can open
     */
    private static Prize getContestantsChoice(List<Prize> boxes, int contestantsChoice) {
        return boxes.remove(contestantsChoice);
    }

    /**
     * Runs a single instance of the game where the contestant changes his mind and pick the remaining unopened box.
     */
    public static Prize runGameWithChangingBox() {
        List<Prize> boxes = initiateGame();
        int contestantsChoice = new Random().nextInt(NUMBER_OF_BOXES);
        getContestantsChoice(boxes, contestantsChoice);
        List<Prize> remainingBoxes = openUnselectedBox(boxes);
        return remainingBoxes.get(0);
    }

    /**
     * Removes (or 'opens') an empty box from the list of boxes.
     */
    private static List<Prize> openUnselectedBox(List<Prize> boxes) {
        boxes.remove(boxes.indexOf(Prize.EMPTY));
        return boxes;
    }


    public static void main (String[] args) {

        final int simulations = 10000;
        float nonChangingStrategyWins = 0;
        float changingStrategyWins = 0;

        for(int i = 0; i < simulations ; i++){
            Prize prize = MontyHallSimulation.runGameWithoutChangingBox();
            if(Prize.MONEY == prize){
                nonChangingStrategyWins++;
            }
        }

        for(int i = 0; i < simulations ; i++){
            Prize prize = MontyHallSimulation.runGameWithChangingBox();
            if(Prize.MONEY == prize){
                changingStrategyWins++;
            }
        }

        System.out.println("************************************************************");
        System.out.println(" Over " + simulations + " simulations " +
                    "the win rate of switching doors is  " + (changingStrategyWins / simulations)*100 + "% " +
                    "compared to  " + (nonChangingStrategyWins/simulations)*100 + "% when not switching");

    }
}
