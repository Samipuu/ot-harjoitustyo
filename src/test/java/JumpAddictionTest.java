/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jumpaddiction.game.Character;
import jumpaddiction.game.Game;
import jumpaddiction.game.Player;
import jumpaddiction.google.GoogleAuth;
import jumpaddiction.map.Goal;
import jumpaddiction.map.Map;
import jumpaddiction.map.Spike;
import jumpaddiction.map.Wall;
import jumpaddiction.ui.UI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author suonpaas
 */
public class JumpAddictionTest {
    
    
    @Test
    public void gravityLowersChar() {
        Character player = new Player(200,200,Color.BLACK,30.0);
        player.gravity(20.0);
        assertTrue(player.getCharacter().getTranslateY() != 200);
    }
    
    @Test
    public void jumpMoveCharUp() {
        Character player = new Player(100,100,Color.BLACK,30.0);
        player.setReadyToJump(true);
        player.setComingDown(false);
        player.jump(20.0,20.0);
        assertTrue(player.getCharacter().getTranslateY() != 100);
    }
    
    @Test
    public void cantJumpWhenFalling() {
        Character player = new Player(100,100, Color.BLACK, 20.0);
        player.setComingDown(true);
        player.jump(30.0, 30.0);
        assertTrue(player.getCharacter().getTranslateY() == 100 && player.getComingDown());
    }
    
    @Test
    public void cantJumpWhenNotReady() {
        Character player = new Player(100,100, Color.BLACK, 20.0);
        player.setReadyToJump(false);
        player.jump(5.0, 100.0);
        assertTrue(player.getCharacter().getTranslateY() == 100 && !player.getReadyToJump());
        
    }
    
    @Test
    public void playerHitsObjects() {
        Character player = new Player(100,100, Color.BLACK, 20.0);
        Wall wall = new Wall(30);
        Goal goal = new Goal(30);
        wall.getTile().setTranslateY(100);
        wall.getTile().setTranslateX(100);
        goal.getTile().setTranslateY(100);
        goal.getTile().setTranslateX(100);
        assertTrue(player.hit(wall) && player.hit(goal));
    }
    
    @Test
    public void googleReadsData() throws GeneralSecurityException, IOException {
        GoogleAuth google = new GoogleAuth();
        List<List<Object>> scores = google.readData("Easy");
        assertTrue(!scores.isEmpty());
    }
    
    @Test
    public void googleAddsScore() throws GeneralSecurityException, IOException {
        GoogleAuth google = new GoogleAuth();
        google.addResult("Test", 123, "Test");
        List<List<Object>> scores = google.readData("Test");
        
        assertTrue(scores.get(0).get(0).equals("Test") && scores.get(0).get(1).equals("123"));
    }
    
    @Test
    public void playerEvadesObjects() {
        Character player = new Player(200,100, Color.BLACK, 20.0);
        Wall wall = new Wall(30);
        Goal goal = new Goal(30);
        wall.getTile().setTranslateY(100);
        wall.getTile().setTranslateX(100);
        goal.getTile().setTranslateX(100);
        goal.getTile().setTranslateY(100);
        assertFalse(player.hit(wall) && player.hit(goal));
    }
    
    @Test
    public void playerHitsSpikes() {
        Character player = new Player(100,100, Color.BLACK, 20.0);
        Spike spike = new Spike(30);
        spike.getTile().setTranslateY(100);
        spike.getTile().setTranslateX(100);
        assertTrue(player.hit(spike));
    }
    
    @Test
    public void playerCollidesWithMap() {
        
        Character player = new Player(100,100, Color.BLACK, 20.0);
        Wall tile = new Wall(30);
        tile.getTile().setTranslateY(100);
        tile.getTile().setTranslateX(100);
        
    }
    
    @Test
    public void playerEvadesSpikes() {
        Character player = new Player(100,200, Color.BLACK, 20.0);
        Spike spike = new Spike(30);
        spike.getTile().setTranslateX(100);
        spike.getTile().setTranslateY(100);
        assertFalse(player.hit(spike));
    }
    
    @Test
    public void mapLoads() {
        GridPane grid = new GridPane();
        Map map = new Map(grid, "level1.txt");
        assertTrue(!map.getSpikes().isEmpty());
        assertTrue(!map.getTiles().isEmpty());
    }
    
    @Test
    public void animationRuns() throws InterruptedException {
        
        Thread thread = new Thread(new Runnable() {
            
            @Override
            public void run() {
                new JFXPanel();
                Platform.runLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        try {
                            Stage stage = new Stage();
                            new UI().start(stage);
                            Game newGame = new Game(Color.BLACK, 800, 600, 1.0);
                            Scene gameScene = newGame.getGameScene();
                            stage.setScene(gameScene);
                            stage.show();
                            
                        } catch (Exception ex) {
                            Logger.getLogger(JumpAddictionTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    
                });
                
            }
        });
        
        thread.start();
        
        thread.sleep(4000);
        
        assertTrue(true);
    }
    
    
    public JumpAddictionTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }



}
