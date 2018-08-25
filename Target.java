package cc_pi_gui_version;

import java.util.concurrent.TimeUnit;
import javafx.scene.paint.Color;

/**
 * This class is used to store each targets information.
 *
 * @author Zachary Passmore
 */
public class Target {

    private boolean active;
    private boolean lightOn;
    private String lightColor;

    /**
     * Creates a new target object
     */
    public Target() {
        this.active = false;
        this.lightOn = false;
        this.lightColor = "Black"; //0 for white, 1 for red
    }
    
    public String getTargetColor(){
        return this.lightColor;
    }

    /**
     * Returns if the target is active and able to be hit.
     *
     * @return The active boolean value.
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Turns the target on and waits for target hit.
     */
    public void turnTargetOn() {
        this.active = true;
        this.lightOn = true;
        this.lightColor = "White";
    }

    /**
     * Turns the target off and stops listening for a hit signal.
     */
    public void turnTargetOff() {
        this.active = false;
        this.lightOn = false;
        this.lightColor = "Black";
    }

    /**
     * Called when the target has been hit.
     */
    public void targetHit() throws InterruptedException {
        if (this.active) {
            this.active = false;
            this.lightColor = "Red";
            //TimeUnit.SECONDS.sleep(1); //This holds up the gui
            this.lightOn = false;
            this.lightColor = "Red";
        }
    }

    public String toString() {
        if (this.active) {
            return "This target is currently on.";
        } else {
            return "This target is currently off";
        }

    }
}
