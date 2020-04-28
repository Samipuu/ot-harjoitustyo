/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author suonpaas
 */
public class Spike extends Tile {
    
    /**
     * Luo piikki tyypin tiilen.
     */
    public Spike() {
        super(new Polygon(0, 30, 15, 0, 30, 30), 3);
        super.setColor(Color.RED);
    }
}
