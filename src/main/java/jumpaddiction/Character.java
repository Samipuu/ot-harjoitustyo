/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/**
 *
 * @author suonpaas
 */
public abstract class Character {
    private Polygon character;
    private Point2D movement;
    private boolean alive;
    private double jumpPoint;
    
    public Character(Polygon polygon, int x, int y) {
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        
        this.movement = new Point2D(0,0);
        
        this.alive = true;
    }
    
    public Polygon getCharacter() {
        return character;
    }
    
    public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
        
        
    }
    
    public void acc() {
        double speedX = Math.cos(Math.toRadians(this.character.getRotate()));
        double speedY = Math.sin(Math.toRadians(this.character.getRotate()));
        
        speedX *= 0.05;
        speedY *= 0.05;
        
        
        this.movement.add(speedX, speedY);
    }
    
    public void moveLeft() {
        this.character.setTranslateX(this.character.getTranslateX() - 1);
    }
    
    public void moveRight() {
        this.character.setTranslateX(this.character.getTranslateX() + 1);
    }
    
    public void jump() {
        if(this.character.getTranslateY() == 500) {
            jumpPoint = this.character.getTranslateY();
        }
        
        if(this.character.getTranslateY() > jumpPoint - 100) {
            this.character.setTranslateY(this.character.getTranslateY() - 1);
        }
        
    }
    
    public void gravity() {
        if(this.character.getTranslateY() < 500) {
            this.character.setTranslateY(this.character.getTranslateY() + 0.5);
        }
        
    }
    
    public Point2D getMovement() {
        return movement;
    }
    
    public void setMovement(Point2D movement) {
        this.movement = movement;
    }
    
    public boolean getAlive() {
        return alive;
    }
    
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    
}


