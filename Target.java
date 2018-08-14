package cc_pi_gui_version;

/**
 * This class is used to store each targets information.
 *
 * @author Zachary Passmore
 */
public class Target {

    private boolean active;
    private boolean lightOn;
    private int lightColor;

    /**
     * Creates a new target object
     */
    public Target() {
        this.active = false;
        this.lightOn = false;
        this.lightColor = 0; //0 for white, 1 for red
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
    }

    /**
     * Turns the target off and stops listening for a hit signal.
     */
    public void turnTargetOff() {
        this.active = false;
        this.lightOn = false;
    }

    /**
     * Called when the target has been hit.
     */
    public void targetHit() {
        if (this.active) {
            this.active = false;
            this.lightColor = 1;
            //Send command to change color to red, wait 1 second then clear the target.
            this.lightOn = false;
            this.lightColor = 0;
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
