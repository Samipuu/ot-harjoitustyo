/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author suonpaas
 */
public abstract class Character {
    private Polygon character;
    private double jumpPoint;
    private boolean readyToJump;
    private boolean comingDown;
    
    public Character(Polygon polygon, int x, int y) {
        this.comingDown = true;
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
    }
    
    public Polygon getCharacter() {
        return character;
    }
    
    public void setColor(Color color) {
        character.setFill(color);
    }
    
    public void jump() {
        if (readyToJump) {
            this.jumpPoint = this.character.getTranslateY() - 100;
        }
        
        if (this.character.getTranslateY() > jumpPoint && !comingDown) {
            this.character.setTranslateY(this.character.getTranslateY() - 1.7);
            readyToJump = false;
        } else {
            this.comingDown = true;
        }
        
    }
    
    public void gravity() {
        
        this.character.setTranslateY(this.character.getTranslateY() + 0.7);
        
        
    }
    
    
    public boolean hit(Character player) {
        Shape impactZone = Shape.intersect(this.character, player.getCharacter());
        return impactZone.getBoundsInLocal().getWidth() != -1;
    }

    
    public void setReadyToJump(boolean jump) {
        this.readyToJump = jump;
    }
    
    public boolean getReadyToJump() {
        return this.readyToJump;
    }
    
    public boolean getComingDown() {
        return this.comingDown;
    }
    
    public void setComingDown(boolean x) {
        this.comingDown = x;
    }
    
    
}


