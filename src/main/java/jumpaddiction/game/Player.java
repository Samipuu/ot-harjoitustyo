/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Pelaaja luokka, joka jatkaa hahmo luokkaa. 
 * 
 * @see jumpaddiction.game.Character
 * @author suonpaas
 */
public class Player extends Character {
    private double size;
    /**
     * Luo uuden pelaaja tyypin hahmon.
     * @param x pelaajan sijainti x-akselilla
     * @param y pelaajan sijainti y-akselilla
     * @param color pelaajan hahmon väri. 
     * @param size double muuttujana annettava koko.
     */
    public Player(int x, int y, Color color, double size) {
        super(new Polygon(0, 0, 0, size, size, size, size, 0), x, y);
        super.setColor(color);
        this.size = size;
    }
    
    public double getSize() {
        return size;
    }
}
