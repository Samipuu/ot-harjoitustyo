/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author suonpaas
 */
public class Game {
    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    
    public static Scene game() throws Exception {
        Pane window = new Pane();
        window.setPrefSize(WIDTH, HEIGHT);
        
        Character ball = new Player(400, 300);
        
        window.getChildren().add(ball.getCharacter());
        
        Scene scene = new Scene(window);
        
        Map<KeyCode, Boolean> pressedButtons = new HashMap<>();
        
        scene.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), Boolean.TRUE);
        });
        
        scene.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), Boolean.FALSE);
        });
        
        new AnimationTimer() {
            
            @Override
            public void handle(long currentTime) {
                if(pressedButtons.getOrDefault(KeyCode.LEFT, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.A, Boolean.FALSE)) {
                    ball.moveLeft();
                    ball.acc();
                }
                
                if(pressedButtons.getOrDefault(KeyCode.RIGHT, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.D, Boolean.FALSE)) {
                    ball.moveRight();
                    ball.acc();
                }
                
                if(pressedButtons.getOrDefault(KeyCode.UP, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.W, Boolean.FALSE)) {
                    ball.jump();
                    ball.acc();
                } else {
                    ball.gravity();
                    ball.acc();
                }
                ball.move();
            }
        }.start();
        return scene;
    }
    
}
