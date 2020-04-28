/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author suonpaas
 */
public abstract class Tile {
    private Shape tile;
    private int type;
    
    /**
     * Luo tiilen. Muuttujana annetaan tiilin muoto ja tyyppinumero.
     * @param shape haluttu tiilen muoto.
     * @param type tiilen tyyppinumero.
     */
    public Tile(Shape shape, int type) {
        this.tile = shape;
        this.type = type;
    }
    
    public void setColor(Color color) {
        this.tile.setFill(color);
    }
    
    public int getType() {
        return this.type;
    }
    
    public Shape getTile() {
        return this.tile;
    }
}
