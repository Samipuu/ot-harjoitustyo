/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.game;

import javafx.scene.shape.Polygon;

/**
 *
 * @author suonpaas
 */
public class Spike extends Character{
    
    public Spike(int x, int y) {
        super(new Polygon(0, 20, 10, 0, 20, 20), x, y);
    }
}
