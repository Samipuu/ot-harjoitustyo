/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Maali luokka. Jatkaa tiili luokkaa.
 * 
 * @see jumpaddiction.map.Tile
 * @author suonpaas
 */
public class Goal extends Tile {
    /**
     * Luo maali tyypin tiilen. 
     * @param tileSize int muuttujana tiilenkoko.
     */
    public Goal(int tileSize) {
        super(new Rectangle(tileSize, tileSize), 2);
        super.setColor(Color.GREEN);
        
    }
}
