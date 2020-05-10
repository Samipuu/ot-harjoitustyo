/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Piikki tyyppisen esteen loukka. Jatkaa tiililuokkaa. 
 * @author suonpaas
 */
public class Spike extends Tile {
    
    /**
     * Luo piikki tyypin tiilen.
     * @param tileSize int muuttujana annettava tiilenkoko.
     */
    public Spike(int tileSize) {
        super(new Polygon(0, tileSize, tileSize / 2, 0, tileSize, tileSize), 3);
        super.setColor(Color.RED);
    }
}
