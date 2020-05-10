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
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author suonpaas
 */
public class Map {
    private ArrayList<Tile> tiles;
    private ArrayList<Tile> spikes;
    private int[][]map;
    
    private int rowCount;
    private int colCount;
    
    private Point2D startPoint;
    
    private int tileSize;
    
    /**
     * Lataa kartan tiedostosta annettuun GridPane ruutuun. 
     * @param window muuttujana annetaan ruutu mihin kartta ladataan. 
     * @param level String muuttujana annettava tiedoston nimi. 
     */
    public Map(GridPane window, String level) {
        this.tiles = new ArrayList<>();
        this.spikes = new ArrayList<>();
        
        tileSize = (int) window.getHeight() / 20;
        
        readMapFile(level);
        
        
        for (int row = 0; row < rowCount; row++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setMaxHeight(tileSize);
            rowConst.setMinHeight(tileSize);
            window.getRowConstraints().add(rowConst);
            for (int col = 0; col < colCount; col++) {
                if (row == 0) {
                    ColumnConstraints colConst = new ColumnConstraints();
                    colConst.setMaxWidth(tileSize);
                    colConst.setMinWidth(tileSize);
                    window.getColumnConstraints().add(colConst);
                }
                if (map[row][col] == 0) {
                    Tile tile = new Wall(tileSize);
                    tiles.add(tile);
                    window.add(tile.getTile(), col, row);
                } else if (map[row][col] == 2) {
                    Tile tile = new Goal(tileSize);
                    tiles.add(tile);
                    window.add(tile.getTile(), col, row);
                } else if (map[row][col] == 3) {
                    Tile spike = new Spike(tileSize);
                    spikes.add(spike);
                    window.add(spike.getTile(), col, row);
                } else if (map[row][col] == 4) {
                    startPoint = new Point2D(col * tileSize, row * tileSize);
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
    
    public int getTileSize() {
        return this.tileSize;
    }
    
    /**
     * Lataa tekstitiedostosta kartan. 
     * @param mapFile String muuttujana tiedoston nimi.
     */
    private void readMapFile(String mapFile) {
        try {
            
            InputStream in = getClass().getClassLoader().getResourceAsStream(mapFile);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );
            
            this.rowCount = Integer.valueOf(br.readLine());
            
            this.colCount = Integer.valueOf(br.readLine());
            
            this.map = new int[rowCount][colCount];
            
            for (int row = 0; row < rowCount; row++) {
                
                String line = br.readLine();
                String[] tokens = line.split(" ");
                for (int col = 0; col < colCount; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                    
                }
            }
        } catch (Exception e) {
            alert();
            e.printStackTrace();
        }
    }
    
    private void alert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Game ran into a problem");
        alert.setContentText("Map could not be load. Check map file.");
        alert.showAndWait();
    }
    
    /**
     * Palauttaa pelaajan aloituspisteen.
     * @return Point2D tyyppisena aloituspiste.
     */
    public Point2D getStartPoint() {
        return startPoint;
    }
}
