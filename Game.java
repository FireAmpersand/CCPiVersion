package CCPiVersion;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 * This class runs the Castle Clash game.
 *
 * @author Zachary Passmore
 */
public class Game {

    //Final values for the game.
    private final int numberOfTargetsPerCastle = 8;
    private final int secondsPerGame = 90;
    private int targetChangeDelay = 3000;

    //Values for when the game is runnning
    private int timeLeft;
    private Timer gameTimer;

    //These arrays hold the information about if the target is active or not.
    private Target[] blueCastleTargets;
    private Target[] redCastleTargets;

    //These values hold the current scores for each team.
    private int redScore;
    private int blueScore;

    //These values tell the game which cannon to fire next.
    private String nextBlueCannon;
    private String nextRedCannon;

    /**
     * Creates a new Game object
     */
    public Game() {
        this.blueCastleTargets = new Target[numberOfTargetsPerCastle];
        this.redCastleTargets = new Target[numberOfTargetsPerCastle];
        this.redScore = 0;
        this.blueScore = 0;
        this.timeLeft = 0;
        this.nextBlueCannon = "left";
        this.nextRedCannon = "left";
        createTargets();
    }

    /**
     * Returns the current time remaining in the match
     * @return The time remaining in the match
     */
    public int getTimeRemaining() {
        return this.timeLeft;
    }

    /**
     * Returns a Target array of the blue team's targets
     * @return Blue team target array.
     */
    public Target[] getBlueTargets() {
        return this.blueCastleTargets;
    }

    /**
     * Returns a Target array of the red teams targets
     * @return Red team target array.
     */
    public Target[] getRedTargets() {
        return this.redCastleTargets;
    }

    /**
     * Returns the current score for the blue team
     * @return the blue team's score.
     */
    public int getBlueScore() {
        return this.blueScore;
    }

    /**
     * Returns the current score for the red team
     * @return the red team's score.
     */
    public int getRedScore() {
        return this.redScore;
    }

    /**
     * Add a point to the blue teams score
     */
    public void scorePointBlue() {
        this.blueScore++;
        if (this.blueScore % 5 == 0){
            this.blueCastleTargets[7].turnTargetOn();
        }
    }
    
    /**
     * Add a point to the red teams score.
     */
    public void scorePointRed() {
        this.redScore++;
        if (this.redScore % 5 == 0){
            this.redCastleTargets[7].turnTargetOn();
        }
    }

    /**
     * Sets all targets to off.
     */
    private void clearTargets() {
        for (int i = 0; i < this.blueCastleTargets.length; i++) {
            this.blueCastleTargets[i].turnTargetOff();
            this.redCastleTargets[i].turnTargetOff();
        }
    }

    /**
     * Prints out the targets to the console.
     */
    private void targetToConsole() {
        String blueText = "";
        String redText = "";
        for (int i = 0; i < this.blueCastleTargets.length; i++) {
            if (this.blueCastleTargets[i].isActive()) {
                blueText = blueText + "On, ";
            } else {
                blueText = blueText + "Off, ";
            }
            if (this.redCastleTargets[i].isActive()) {
                redText = redText + "On, ";
            } else {
                redText = redText + "Off, ";
            }
        }
        System.out.println("Blue: " + blueText);
        System.out.println("Red: " + redText);
        System.out.println("");

    }

    /**
     * Prints out the time remaining to the console
     */
    private void timeToConsole() {
        System.out.println("Time Remaining: " + this.timeLeft);
    }

    /**
     * Starts a new game of castle clash
     */
    public void startNewGame() {

        //Resetting values to defualt
        clearTargets();
        this.redScore = 0;
        this.blueScore = 0;
        this.timeLeft = this.secondsPerGame;

        runGame();

    }

    /**
     * Runs a game of castle clash
     */
    private void runGame() {
        changeTargets();
        timeToConsole();
        targetToConsole();
        //Starting the game timer
        startGameTimer();

        //Running the game if there is time remaining
        while (this.timeLeft > 0) {
            //just a place holder so the game doesn't move on untill time is up
            System.out.println(this.timeLeft);
        }
        System.out.println("Boop");
        clearTargets();
        //Post Game actions
        if (this.blueScore > this.redScore) {
            System.out.println("BLUE WINS!");
            //Blue Winner Audio
            //Fire blue cannons
            //Fire Red Misters
            //Fire Blue Dragon
        } else if (this.blueScore < this.redScore) {
            System.out.println("RED WINS!");
            //Red Winner Audio
            //Fire Red Cannons
            //Fire Blue Misters
            //Fire Red Dragon
        } else {
            System.out.println("DRAW!");
            //No Winner Audio
            //Fire Red Cannons
            //Fire Blue Cannons
        }

        clearTargets();
    }

    /**
     * Cycles the targets around the castle
     */
    private void changeTargets() {
        for (int i = 0; i < this.blueCastleTargets.length - 1; i++) {
            int onCheck = (int) (Math.random() * 2);
            if (onCheck == 0) {
                this.blueCastleTargets[i].turnTargetOn();
                this.redCastleTargets[i].turnTargetOn();
            } else {
                this.blueCastleTargets[i].turnTargetOff();
                this.redCastleTargets[i].turnTargetOff();
            }
        }
    }

    /**
     * Runs the game timer.
     */
    private void startGameTimer() {
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                setInterval();
                timeToConsole();
            }
        }, 1000, 1000);
        gameTimer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                changeTargets();
                targetToConsole();
            }
        }, this.targetChangeDelay, this.targetChangeDelay);
    }

    /**
     * Used by startGameTimer to adjust the remaining time in the game.
     */
    private final void setInterval() {
        if (this.timeLeft == 1) {
            gameTimer.cancel();
        }
        --this.timeLeft;
    }

    /**
     * Creates new target objects for the castle
     */
    private void createTargets() {
       for (int i =0; i < this.numberOfTargetsPerCastle; i++){
           this.blueCastleTargets[i] = new Target();
           this.redCastleTargets[i] = new Target();
       }
    }
}
