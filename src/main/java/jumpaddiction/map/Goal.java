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
public class Goal extends Tile {
    /**
     * Luo maali tyypin tiilen. 
     */
    public Goal() {
        super(new Rectangle(30, 30), 2);
        super.setColor(Color.GREEN);
        
    }
}
