/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Seinä luokka. Jatkaa tiili luokkaa.
 * 
 * @see jumpaddiction.map.Tile
 * @author suonpaas
 */
public class Wall extends Tile {
    /**
     * Luo seinä tyypin tiilen.
     * @param tileSize int muuttujana annettava tiilenkoko.
     */
    public Wall(int tileSize) {
        super(new Rectangle(tileSize, tileSize), 0);
        super.setColor(Color.BLACK);
    }
    
    
}
