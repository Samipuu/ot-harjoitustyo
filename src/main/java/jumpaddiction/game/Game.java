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
import javafx.geometry.Point2D;
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
 * Peli luokka pitää sisällään pelissä käytettävän animaation ja toiminnallisuudet. Peli luokka kokoaa pelin yhteen. Hyödyntää map luokkaa. 
 * 
 * @see jumpaddiction.map.Map
 * @author suonpaas
 */
public class Game {
    public static int width;
    public static int height;
    
    private final Scene gameScene;
    
    private final Map map;
    
    private final Player ball;
    
    private boolean gravity;
    
    private double difficulty;
    
    /**
     * Lataa pelin objektit ja kartan. Luo myös pelin animaation ja toiminnallisuudet.
     * 
     * @param playerColor hahmon väri.
     * @param width ikkunan leveys.
     * @param height ikkunan korkeus.
     * @param difficulty valittu vaikeuaste.
     */
    public Game(Color playerColor, int width, int height, double difficulty) {
        Game.width = width;
        Game.height = height;
        
        this.difficulty = difficulty;
        
        GridPane window = setupGridPane();
        
        this.gameScene = new Scene(window, width, height);
        
        map = new Map(window, "level1.txt");
        
        ball = setupPlayer(playerColor);
        
        window.getChildren().add(ball.getCharacter());
        
        HashMap<KeyCode, Boolean> pressedButtons = new HashMap<>();
         
        gameScene.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), Boolean.TRUE);
        });
        
        gameScene.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), Boolean.FALSE);
        });
        
        Long time = System.currentTimeMillis();
        
        double speedX = (double)width / 200 * difficulty;
        double speedJump = (double)height / 88 * difficulty;
        double speedGravity = (double)height / 214 * difficulty;
        double jumpHeight = height / 6;
        
        new AnimationTimer() {
            
            @Override
            public void handle(long currentTime) {
                long elapsed = (System.currentTimeMillis() - time);
                
                if (pressedButtons.getOrDefault(KeyCode.UP, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.W, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.SPACE, Boolean.FALSE)) {
                    ball.jump(speedJump, jumpHeight);
                } else {
                    ball.setComingDown(true);
                }
                
                gravity = true;
                
                for (Tile tile:map.getTiles()) {
                    
                    if (elapsed > 2000) {
                        tile.getTile().setTranslateX(tile.getTile().getTranslateX() - speedX);
                    }
                    
                    if(checkMapCollision(tile)) {
                        this.stop();
                    }
                }
                
                if (gravity) {
                    
                    ball.gravity(speedGravity);
                    
                    if (ball.getCharacter().getTranslateY() > Game.height) {
                        this.stop();
                        UI.gameOver();
                    }
                }
                
                for (Tile spike : map.getSpikes()) {
                    if (elapsed > 2000) {
                        spike.getTile().setTranslateX(spike.getTile().getTranslateX() - speedX);
                    }
                    
                    if (ball.hit(spike)) {
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
    
    /**
     * Alustaa pelaajan hahmon. Luo sen käyttäen pleyer luokkaa. 
     * 
     * @see jumpaddiction.game.Player
     * @param playerColor hahmon väri.
     * @return palauttaa Player objektin.
     */
    private Player setupPlayer(Color playerColor) {
        Point2D startPoint = map.getStartPoint();
        double ballSize = (double)map.getTileSize() * (2.0/3.0);
        Player player = new Player((int)startPoint.getX(), (int)startPoint.getY(), playerColor, ballSize);
        return player;
    }
    
    /**
     * Tarkistaa pelaajan osumisen karttaan.
     * 
     * @see jumpaddiction.map.Map
     * @see jumpaddiction.map.Tile
     * @param tile tarkistettava tiili.
     * @return palauttaa onko osuttu tiilen.
     */
    private Boolean checkMapCollision(Tile tile) {
        if (Shape.intersect(getFuture(), tile.getTile()).getBoundsInLocal().getWidth() != -1) {
            if (tile.getType() == 2) {
                UI.gameWon();
                return true;
            } else {
                UI.gameOver();
                return true;
            }
        }

        if (ball.hit(tile)) {
            ball.setComingDown(false);
            ball.setReadyToJump(true);
            gravity = false;
        }
        return false;
    }
    
    /**
     * Palauttaa hahmon seuraavan pisteen. 
     * @return 
     */
    private Shape getFuture() {
        Double futureX = ball.getCharacter().getTranslateX();
        Double futureY = ball.getCharacter().getTranslateY();
                
        Shape future = new Rectangle(futureX, futureY, ball.getSize(), ball.getSize());
        
        if(difficulty == 2) {
            future = new Rectangle(futureX, futureY - 1, ball.getSize(), ball.getSize());
        } 
                
        return future;
    }
}

