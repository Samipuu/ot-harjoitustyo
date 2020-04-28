/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author suonpaas
 */
public class Wall extends Tile {
    /**
     * Luo seinä tyypin tiilen.
     */
    public Wall() {
        super(new Rectangle(30, 30), 0);
        super.setColor(Color.BLACK);
    }
    
    
}
