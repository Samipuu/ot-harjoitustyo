/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author suonpaas
 */
public class Map {
    ArrayList<Tile> tiles;
    ArrayList<Tile> spikes;
    int[][]map;
    
    /**
     * Lataa kartan tiedostosta annettuun GridPane ruutuun. 
     * @param window muuttujana annetaan ruutu mihin kartta ladataan. 
     */
    public Map(GridPane window) {
        this.tiles = new ArrayList<>();
        this.spikes = new ArrayList<>();
        this.map = new int[20][240];
        
        try {
            
            InputStream in = getClass().getClassLoader().getResourceAsStream("test.txt");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );
            
            for (int row = 0; row < 20; row++) {
                
                String line = br.readLine();
                String[] tokens = line.split(" ");
                for (int col = 0; col < 240; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                    
                }
            }
        } catch (Exception e) {
            System.out.println("Kartan lataus epäonnistui. Tarkista kartta.");
            e.printStackTrace();
        }
        
        for (int row = 0; row < 20; row++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setMaxHeight(30);
            rowConst.setMinHeight(30);
            window.getRowConstraints().add(rowConst);
            for (int col = 0; col < 240; col++) {
                if (row == 0) {
                    ColumnConstraints colConst = new ColumnConstraints();
                    colConst.setMaxWidth(30);
                    colConst.setMinWidth(30);
                    window.getColumnConstraints().add(colConst);
                }
                if (map[row][col] == 0) {
                    Tile tile = new Wall();
                    tiles.add(tile);
                    window.add(tile.getTile(), col, row);
                } else if (map[row][col] == 2) {
                    Tile tile = new Goal();
                    tiles.add(tile);
                    window.add(tile.getTile(), col, row);
                } else if (map[row][col] == 3) {
                    Tile spike = new Spike();
                    spikes.add(spike);
                    window.add(spike.getTile(), col, row);
                }
                

            }
        }
    }
    
    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }
    
    public ArrayList<Tile> getSpikes() {
        return this.spikes;
    }
}
