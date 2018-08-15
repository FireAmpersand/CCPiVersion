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
    private Circle blueLeftTarget;
    @FXML
    private Circle blueBLeftTarget;
    @FXML
    private Circle blueMiddleTarget;
    @FXML
    private Circle blueBRightTarget;
    @FXML
    private Circle blueLeftMist;
    @FXML
    private Circle blueRightTarget;
    @FXML
    private Circle blueRightMist;
    @FXML
    private Circle blueCannon;
    
    @FXML
    private Circle redLeftTarget;
    @FXML
    private Circle redBLeftTarget;
    @FXML
    private Circle redMiddleTarget;
    @FXML
    private Circle redBRightTarget;
    @FXML
    private Circle redLeftMist;
    @FXML
    private Circle redRightTarget;
    @FXML
    private Circle redRightMist;
    @FXML
    private Circle redCannon;
    
    
    @FXML
    private void handleTargetHit(ActionEvent event){
        try {
            theGame.getBlueTargets()[0].targetHit();
            theGame.scorePointBlue();
            System.out.println("Boop");
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
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
                        // Call update method for every 2 sec.
                        updateDisplay();
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void updateDisplay() {
        this.timeRemaining.setText("" + theGame.getTimeRemaining());
        this.blueScore.setText("" + theGame.getBlueScore());
        this.blueLeftMist.setFill(theGame.getBlueTargets()[0].getTargetColor());
        this.blueLeftTarget.setFill(theGame.getBlueTargets()[1].getTargetColor());
        this.blueBLeftTarget.setFill(theGame.getBlueTargets()[2].getTargetColor());
        this.blueMiddleTarget.setFill(theGame.getBlueTargets()[3].getTargetColor());
        this.blueBRightTarget.setFill(theGame.getBlueTargets()[4].getTargetColor());
        this.blueRightTarget.setFill(theGame.getBlueTargets()[5].getTargetColor());
        this.blueRightMist.setFill(theGame.getBlueTargets()[6].getTargetColor());
        this.blueCannon.setFill(theGame.getBlueTargets()[7].getTargetColor());
        this.redLeftMist.setFill(theGame.getRedTargets()[0].getTargetColor());
        this.redLeftTarget.setFill(theGame.getRedTargets()[1].getTargetColor());
        this.redBLeftTarget.setFill(theGame.getRedTargets()[2].getTargetColor());
        this.redMiddleTarget.setFill(theGame.getRedTargets()[3].getTargetColor());
        this.redBRightTarget.setFill(theGame.getRedTargets()[4].getTargetColor());
        this.redRightTarget.setFill(theGame.getRedTargets()[5].getTargetColor());
        this.redRightMist.setFill(theGame.getRedTargets()[6].getTargetColor());
        this.redCannon.setFill(theGame.getRedTargets()[7].getTargetColor());
    }

}
