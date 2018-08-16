/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc_pi_gui_version;

import java.net.URL;
import javafx.util.Duration;
import java.time.temporal.TemporalUnit;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author firea
 */
public class FXMLDocumentController implements Initializable {

    public Game theGame;

    @FXML
    private Label timeRemaining;
    @FXML
    private Label blueScore;
    @FXML
    private Label redScore;

    //Blue Castle Targets
    @FXML
    private Circle target1;
    @FXML
    private Circle target2;
    @FXML
    private Circle target3;
    @FXML
    private Circle target4;
    @FXML
    private Circle target0;
    @FXML
    private Circle target5;
    @FXML
    private Circle target6;
    @FXML
    private Circle target7;

    //Red Castle Targets
    @FXML
    private Circle target9;
    @FXML
    private Circle target10;
    @FXML
    private Circle target11;
    @FXML
    private Circle target12;
    @FXML
    private Circle target8;
    @FXML
    private Circle target13;
    @FXML
    private Circle target14;
    @FXML
    private Circle target15;

    /**
     * Handles the mouse click on a target.
     * @param mouseEvent The Mouse event provided by the gui.
     */
    @FXML
    private void handleTargetHit(MouseEvent mouseEvent) {
        try {
            String theTargetId = mouseEvent.getPickResult().getIntersectedNode().getId();
            int id = Integer.parseInt(theTargetId.replaceAll("[^0-9]", ""));
            if (id < 8) {
                if (theGame.getBlueTargets()[id].isActive()) {
                    theGame.scorePointBlue();
                    theGame.getBlueTargets()[id].targetHit();
                }
            }else{
                id = id - 8;
                if (theGame.getRedTargets()[id].isActive()){
                    theGame.scorePointRed();
                    theGame.getRedTargets()[id].targetHit();
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Button pressed");
        Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                theGame.startNewGame();
            }
        });
        gameThread.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        theGame = new Game();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        // Call update method for every 0.5 sec.
                        updateDisplay();
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void updateDisplay() throws NullPointerException {
        this.timeRemaining.setText("" + theGame.getTimeRemaining());
        this.blueScore.setText("" + theGame.getBlueScore());
        this.redScore.setText("" + theGame.getRedScore());
        this.target0.setFill(theGame.getBlueTargets()[0].getTargetColor());
        this.target1.setFill(theGame.getBlueTargets()[1].getTargetColor());
        this.target2.setFill(theGame.getBlueTargets()[2].getTargetColor());
        this.target3.setFill(theGame.getBlueTargets()[3].getTargetColor());
        this.target4.setFill(theGame.getBlueTargets()[4].getTargetColor());
        this.target5.setFill(theGame.getBlueTargets()[5].getTargetColor());
        this.target6.setFill(theGame.getBlueTargets()[6].getTargetColor());
        this.target7.setFill(theGame.getBlueTargets()[7].getTargetColor());
        this.target8.setFill(theGame.getRedTargets()[0].getTargetColor());
        this.target9.setFill(theGame.getRedTargets()[1].getTargetColor());
        this.target10.setFill(theGame.getRedTargets()[2].getTargetColor());
        this.target11.setFill(theGame.getRedTargets()[3].getTargetColor());
        this.target12.setFill(theGame.getRedTargets()[4].getTargetColor());
        this.target13.setFill(theGame.getRedTargets()[5].getTargetColor());
        this.target14.setFill(theGame.getRedTargets()[6].getTargetColor());
        this.target15.setFill(theGame.getRedTargets()[7].getTargetColor());
    }

}
