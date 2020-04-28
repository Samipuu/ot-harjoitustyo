/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author suonpaas
 */
public class Player extends Character {
    
    /**
     * Luo uuden pelaaja tyypin hahmon.
     * @param x pelaajan sijainti x-akselilla
     * @param y pelaajan sijainti y-akselilla
     */
    public Player(int x, int y) {
        super(new Polygon(0, 0, 0, 20, 20, 20, 20, 0), x, y);
        super.setColor(Color.CORAL);
    }
}
