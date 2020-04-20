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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import jumpaddiction.ui.UI;

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
    private List<Shape> spikes;
    
    private boolean readyToJump;
    private double jumpHeight;

    public Game() throws Exception {
        GridPane window = new GridPane();
        window.setPrefSize(width, height);
        window.setGridLinesVisible(true);
        
        tiles = new ArrayList<>();
        spikes = new ArrayList<>();
        map = new int[20][240];
        
        
        try {
            
            InputStream in = getClass().getClassLoader().getResourceAsStream("test.txt");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );
            
            for (int row = 0; row < 20; row++) {
                
                String line = br.readLine();
                String[] tokens = line.split(" ");
                for (int col = 0; col < 240; col++) {
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
            for (int col = 0; col < 240; col++) {
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
                } else if (map[row][col] == 2) {
                    Rectangle tile = new Rectangle(row * 30, col * 30, 30, 30);
                    tile.setFill(Color.GREEN);
                    tiles.add(tile);
                    window.add(tile, col, row);
                } else if (map[row][col] == 3) {
                    Polygon spike = new Polygon(0, 30, 15, 0, 30, 30);
                    spike.setFill(Color.RED);
                    tiles.add(spike);
                    spikes.add(spike);
                    window.add(spike, col, row);
                }
                

            }
        }
        
        Character ball = new Player(100, 400);
        
        window.getChildren().add(ball.getCharacter());
        
        gameScene = new Scene(window, width, height);
        
        Map<KeyCode, Boolean> pressedButtons = new HashMap<>();
         
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
                
                if (pressedButtons.getOrDefault(KeyCode.UP, Boolean.FALSE) || pressedButtons.getOrDefault(KeyCode.W, Boolean.FALSE)) {
                    
                    ball.jump();
                } else {
                    ball.setComingDown(true);
                }
                
                
                
                
                boolean hit = false;
                boolean gravity = true;
                Double futureX = ball.getCharacter().getTranslateX() + ball.getMovement().getX();
                Double futureY = ball.getCharacter().getTranslateY() + ball.getMovement().getY();
                Shape future = new Rectangle(futureX, futureY, 20, 20);
                for (Shape tile:tiles) {
                    
                    if(elapsed > 2000) {
                        tile.setTranslateX(tile.getTranslateX()-1);
                    }
                    
                    
                    
                        if (Shape.intersect(future, tile).getBoundsInLocal().getWidth() != -1) {
                            
                            hit = true;
                            this.stop();
                            if(tile.getFill() == Color.GREEN) {
                                System.out.println("You won the GAME!");
                            } else {
                                System.out.println("You lost the game!");
                            }
                            UI.gameOver();
                        }
                        if (Shape.intersect(ball.getCharacter(), tile).getBoundsInLocal().getWidth() != -1) {
                            ball.setComingDown(false);
                            ball.setReadyToJump(true);
                            gravity = false;
                        }
                    }
                if(gravity) {
                    
                    ball.gravity();
                    
                    for(Shape spike : spikes) {
                        if(Shape.intersect(ball.getCharacter(), spike).getBoundsInLocal().getWidth() != -1) {
                            System.out.println("You lost the game!");
                            this.stop();
                            UI.gameOver();
                        }
                    }
                    if(ball.getCharacter().getTranslateY() > 600) {
                        System.out.println("You lost the game!");
                        this.stop();
                        UI.gameOver();
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
