/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jumpaddiction.game.Character;
import jumpaddiction.game.Game;
import jumpaddiction.game.Player;
import jumpaddiction.map.Goal;
import jumpaddiction.map.Tile;
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
        Character player = new Player(200,200);
        player.gravity();
        assertTrue(player.getCharacter().getTranslateY() != 200);
    }
    
    @Test
    public void jumpMoveCharUp() {
        Character player = new Player(100,100);
        player.setReadyToJump(true);
        player.setComingDown(false);
        player.jump();
        assertTrue(player.getCharacter().getTranslateY() != 100);
    }
    
    @Test
    public void cantJumpWhenFalling() {
        Character player = new Player(100,100);
        player.setComingDown(true);
        player.jump();
        assertTrue(player.getCharacter().getTranslateY() == 100 && player.getComingDown());
    }
    
    @Test
    public void cantJumpWhenNotReady() {
        Character player = new Player(100,100);
        player.setReadyToJump(false);
        player.jump();
        assertTrue(player.getCharacter().getTranslateY() == 100 && !player.getReadyToJump());
        
    }
    
    @Test
    public void playerHitsObjects() {
        Character player = new Player(100,100);
        Wall wall = new Wall();
        Goal goal = new Goal();
        wall.getTile().setTranslateY(100);
        wall.getTile().setTranslateX(100);
        goal.getTile().setTranslateY(100);
        goal.getTile().setTranslateX(100);
        assertTrue(player.hit(wall) && player.hit(goal));
    }
    
    @Test
    public void playerEvadesObjects() {
        Character player = new Player(200,100);
        Wall wall = new Wall();
        Goal goal = new Goal();
        wall.getTile().setTranslateY(100);
        wall.getTile().setTranslateX(100);
        goal.getTile().setTranslateX(100);
        goal.getTile().setTranslateY(100);
        assertFalse(player.hit(wall) && player.hit(goal));
    }
    
    @Test
    public void playerHitsSpikes() {
        Character player = new Player(100,100);
        Spike spike = new Spike();
        spike.getTile().setTranslateY(100);
        spike.getTile().setTranslateX(100);
        assertTrue(player.hit(spike));
    }
    
    @Test
    public void playerEvadesSpikes() {
        Character player = new Player(100,200);
        Spike spike = new Spike();
        spike.getTile().setTranslateX(100);
        spike.getTile().setTranslateY(100);
        assertFalse(player.hit(spike));
    }
    
    @Test
    public void mapLoads() {
        GridPane grid = new GridPane();
        Map map = new Map(grid);
        assertTrue(!map.getSpikes().isEmpty());
        assertTrue(!map.getTiles().isEmpty());
    }
    
    @Test
    public void animation() throws InterruptedException {
        
        Thread thread = new Thread(new Runnable() {
            
            @Override
            public void run() {
                
                Platform.runLater(new Runnable() {
                    
                    @Override
                    public void run() {
                        try {
                            
                            new UI().start(new Stage());
                            Game newGame = new Game();
                            Scene gameScene = newGame.getGameScene();
                        } catch (Exception ex) {
                            Logger.getLogger(JumpAddictionTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });
        
        
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}


}
