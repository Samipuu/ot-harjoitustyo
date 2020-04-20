/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.Scene;
import jumpaddiction.game.Character;
import jumpaddiction.game.Game;
import jumpaddiction.game.Player;
import jumpaddiction.ui.UI;
import jumpaddiction.ui.Main;
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
        player.jump();
        assertTrue(player.getCharacter().getTranslateY() != 100);
    }
    
    @Test
    public void moveCharRight() {
        Character player = new Player(100,100);
        player.moveRight();
        player.move();
        assertTrue(player.getCharacter().getTranslateX() != 100);
    }
    
    @Test
    public void moveCharLeft() {
        Character player = new Player(100,100);
        player.moveLeft();
        player.move();
        assertTrue(player.getCharacter().getTranslateX() != 100);
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
