/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jumpaddiction.game.Character;
import jumpaddiction.game.Player;
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
        Character otherPlayer = new Player(100,100);
        assertTrue(player.hit(otherPlayer));
    }
    
    @Test
    public void playerEvadesObjects() {
        Character player = new Player(100,100);
        Character otherPlayer = new Player(200,200);
        assertFalse(player.hit(otherPlayer));
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
