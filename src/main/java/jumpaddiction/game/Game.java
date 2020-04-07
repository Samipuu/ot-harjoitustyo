/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author suonpaas
 */
public class Game {
    public static int width = 800;
    public static int height = 600;
    
    private Scene gameScene;
    private PixelReader pixelReader;
    
    private int[][] map;
    private List<Shape> tiles;

    public Game() throws Exception {
        GridPane window = new GridPane();
        window.setPrefSize(width, height);
        window.setGridLinesVisible(true);
        
        tiles = new ArrayList<>();
        
        map = new int[20][30];
        
        try {
            
            InputStream in = getClass().getClassLoader().getResourceAsStream("test.txt");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );
            
            for (int row = 0; row < 20; row++) {
                
                String line = br.readLine();
                String[] tokens = line.split(" ");
                for (int col = 0; col < 30; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int tileSize = 30;
        
        for (int row = 0; row < 20; row++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setMaxHeight(30);
            rowConst.setMinHeight(30);
            window.getRowConstraints().add(rowConst);
            for (int col = 0; col < 30; col++) {
                if (row == 0) {
                    ColumnConstraints colConst = new ColumnConstraints();
                    colConst.setMaxWidth(30);
                    colConst.setMinWidth(30);
                    window.getColumnConstraints().add(colConst);
                }
                if (map[row][col] == 0) {
                    Rectangle tile = new Rectangle(row * 30, col * 30, 30, 30);
                    tile.setFill(Color.BLACK);
                    tiles.add(tile);
                    window.add(tile, col, row);
                } 

            }
        }
        
        Character ball = new Player(400, 300);
        
        window.getChildren().add(ball.getCharacter());
        
        gameScene = new Scene(window, width, height);
        
        Map<KeyCode, Boolean> pressedButtons = new HashMap<>();
         
        gameScene.setOnKeyPressed(event -> {
            pressedButtons.put(event.getCode(), Boolean.TRUE);
        });
        
        gameScene.setOnKeyReleased(event -> {
            pressedButtons.put(event.getCode(), Boolean.FALSE);
        });
        
        new AnimationTimer() {
            
            @Override
            public void handle(long currentTime) {
                if (pressedButtons.getOrDefault(KeyCode.LEFT, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.A, Boolean.FALSE)) {
                    ball.moveLeft();
                    
                }
                
                if (pressedButtons.getOrDefault(KeyCode.RIGHT, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.D, Boolean.FALSE)) {
                    
                    ball.moveRight();
                }
                
                if (pressedButtons.getOrDefault(KeyCode.UP, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.W, Boolean.FALSE)) {
                    ball.jump();
                    
                } else {
                    boolean hit = false;
                    for (Shape tile:tiles) {
                        if (Shape.intersect(ball.getCharacter(), tile).getBoundsInLocal().getWidth() != -1) {
                            hit = true;
                        }
                    }
                    
                    if (!hit) {
                        ball.gravity();
                        
                    }

                }
                boolean hit = false;
                Double futureX = ball.getCharacter().getTranslateX() + ball.getMovement().getX();
                Double futureY = ball.getCharacter().getTranslateY() + ball.getMovement().getY();
                Shape future = new Rectangle(futureX, futureY, 20, 20);
                for (Shape tile:tiles) {
                        if (Shape.intersect(future, tile).getBoundsInLocal().getWidth() != -1) {
                            
                            hit = true;
                        }
                    }
                
                if (!hit) {
                    ball.move();
                } else {
                    ball.setMovement(Point2D.ZERO);
                }
                
            }
        }.start();
        
    }
    
    public Scene getGameScene() {
        return this.gameScene;
    }
    
    
}
