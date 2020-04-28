/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import jumpaddiction.map.Tile;

/**
 *
 * @author suonpaas
 */
public abstract class Character {
    private Polygon character;
    private double jumpPoint;
    private boolean readyToJump;
    private boolean comingDown;
    
    /**
     * Luo hahmon.
     * 
     * @param polygon muotoinen hahmo objekti.
     * @param x hahmon sijainti x-akselilla.
     * @param y hahmon sijainti y-akselilla.
     */
    public Character(Polygon polygon, int x, int y) {
        this.comingDown = true;
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
    }
    
    /**
     * Palauttaa hahmon polygon objektin. 
     * @return polygon object
     */
    public Polygon getCharacter() {
        return character;
    }
    
    /**
     * Asettaa hahmon värin. 
     * @param color haluttu väri.
     */
    public void setColor(Color color) {
        character.setFill(color);
    }
    
    /**
     * Hahmon hyppy metodi. Liikuttaa hahmoa ylöspäin mikäli hahmo on maassa.
     */
    public void jump() {
        if (readyToJump) {
            this.jumpPoint = this.character.getTranslateY() - 100;
        }
        
        if (this.character.getTranslateY() > jumpPoint && !comingDown) {
            this.character.setTranslateY(this.character.getTranslateY() - 6.8);
            readyToJump = false;
        } else {
            this.comingDown = true;
        }
        
    }
    
    /**
     * Hahmon painovoima. Liikuttaa hahmoa alaspäin. 
     */
    public void gravity() {
        
        this.character.setTranslateY(this.character.getTranslateY() + 2.8);
        
        
    }
    
    /**
     * Tarkistaa osuuko hahmo ruutuun. 
     * 
     * @param tile kentän tiili/ruutu.
     * @return palauttaa boolean arvona osuiko vaiko ei.
     */
    public boolean hit(Tile tile) {
        Shape impactZone = Shape.intersect(this.character, tile.getTile());
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


