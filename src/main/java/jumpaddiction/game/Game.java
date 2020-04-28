/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.game;

import jumpaddiction.map.Map;

import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import jumpaddiction.map.Tile;
import jumpaddiction.ui.UI;

/**
 *
 * @author suonpaas
 */
public class Game {
    public static int width = 800;
    public static int height = 600;
    
    private Scene gameScene;
    
    /**
     * Lataa pelin objektit ja kartan. Luo myös pelin animaatiomoottorin.
     * 
     * @throws Exception 
     */
    public Game() throws Exception {
        GridPane window = setupGridPane();
        
        this.gameScene = new Scene(window, width, height);
        
        Map map = new Map(window);
        
        Character ball = new Player(100, 400);
        
        window.getChildren().add(ball.getCharacter());
        
        HashMap<KeyCode, Boolean> pressedButtons = new HashMap<>();
         
        gameScene.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), Boolean.TRUE);
        });
        
        gameScene.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), Boolean.FALSE);
        });
        
        Long time = System.currentTimeMillis();
        
        new AnimationTimer() {
            
            @Override
            public void handle(long currentTime) {
                
                long elapsed = (System.currentTimeMillis() - time);
                
                if (pressedButtons.getOrDefault(KeyCode.UP, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.W, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.SPACE, Boolean.FALSE)) {
                    ball.jump();
                } else {
                    ball.setComingDown(true);
                }
                
                boolean gravity = true;
                Double futureX = ball.getCharacter().getTranslateX();
                Double futureY = ball.getCharacter().getTranslateY();
                Shape future = new Rectangle(futureX, futureY, 20, 20);
                for (Tile tile:map.getTiles()) {
                    
                    if (elapsed > 2000) {
                        tile.getTile().setTranslateX(tile.getTile().getTranslateX() - 4);
                    }
                    
                    if (Shape.intersect(future, tile.getTile()).getBoundsInLocal().getWidth() != -1) {
                            
                        this.stop();
                    
                        if (tile.getType() == 2) {
                            System.out.println("You won the GAME! You died " + UI.getDeaths() + " times during the game.");
                            UI.emptyDeaths();
                        } else {
                            System.out.println("You lost the game!");
                        }
                        
                        UI.gameOver();
                    }
                    
                    if (ball.hit(tile)) {
                        ball.setComingDown(false);
                        ball.setReadyToJump(true);
                        gravity = false;
                    }
                }
                if (gravity) {
                    
                    ball.gravity();
                    
                    if (ball.getCharacter().getTranslateY() > 600) {
                        System.out.println("You lost the game!");
                        this.stop();
                        UI.gameOver();
                    }
                }
                
                for (Tile spike : map.getSpikes()) {
                    if (elapsed > 2000) {
                        spike.getTile().setTranslateX(spike.getTile().getTranslateX() - 4);
                    }
                    
                    if (ball.hit(spike)) {
                        System.out.println("You lost the game!");
                        this.stop();
                        UI.gameOver();
                    }
                }
            }
        }.start();
        
    }
    
    /**
     * Metodi palauttaa Game luokan Scene objektin. 
     * 
     * @return Pelin Scene
     */
    public Scene getGameScene() {
        return this.gameScene;
    }
    /**
     * Alustaa Game luokan GridPane ikkunan.
     * @return Gridpane
     */
    private GridPane setupGridPane() {
        GridPane window = new GridPane();
        window.setPrefSize(width, height);
        window.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        return window;
    }
    
    
}
