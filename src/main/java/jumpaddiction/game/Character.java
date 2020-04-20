/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.game;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author suonpaas
 */
public abstract class Character {
    private Polygon character;
    private Point2D movement;
    private double jumpPoint;
    private Point2D location;
    private boolean readyToJump;
    private boolean comingDown;
    
    public Character(Polygon polygon, int x, int y) {
        this.comingDown = true;
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        
        this.movement = new Point2D(0, 0);
        this.location = new Point2D(x, y);
    }
    
    public Polygon getCharacter() {
        return character;
    }
    
    public void setColor(Color color) {
        character.setFill(color);
    }
    
    public void move() {
        
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
        
        this.movement = new Point2D(0, 0);
        
    }

    public void moveLeft() {
        //double muutosX = Math.cos(Math.toRadians(this.character.getRotate()));
        //double muutosY = Math.sin(Math.toRadians(this.character.getRotate()));
        
        double muutosX = -1;
        double muutosY = 0.0;
        
        //System.out.println(muutosX);
        //System.out.println(muutosY);
        
        this.movement = this.movement.add(muutosX, muutosY);
    }
    
    public void moveRight() {
        this.movement = this.movement.add(1, 0);
    }
    
    public void jump() {
        if(readyToJump) {
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
    
    public Point2D getLocation() {
        //location.(this.character.getTranslateX(), this.character.getTranslateY());
        return this.location;
    }
    
    
    public boolean hit(Character player) {
        Shape impactZone = Shape.intersect(this.character, player.getCharacter());
        return impactZone.getBoundsInLocal().getWidth() != -1;
    }
    
    public Point2D getMovement() {
        return movement;
    }
    
    public void setMovement(Point2D movement) {
        this.movement = movement;
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


