/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static jumpaddiction.ui.UI.mainMenu;

/**
 * Asetukset valikon luokka. Valikossa on pelin resoluutio, väri ja vaikeus asetukset.
 * @author suonpaas
 */
public class Settings {
    
    private int resI;
    
    private int colorI;
    
    private int difI;
    
    private int[] resWidth = new int[] {640, 800, 1024};
    private int[] resHeight = new int[] {480, 600, 768};
        
    private String[] difList = new String[] {"Easy", "Normal", "Hard", "Impossible"};
        
    private Color[] colorList = new Color[] {Color.CORAL, Color.VIOLET, Color.BROWN};
    
    private Stage stage;
    
    private Scene settings;
    
    
    Settings(Stage stage) {
        this.stage = stage;
        
    }
    
    /**
     * Asettaa asetukset ikkunaan näkyviin. 
     */
    public void getSettings() {
        settings = new Scene(setupSettings());
        stage.setScene(settings);
    }
    
    /**
     * Asettaa vaikeusasteen. 
     */
    public void setDifficulty() {
        double difficulty;
        String difName;
        
        if(difI == 0) {
            difficulty = 0.5;
            difName = difList[difI];
            UI.setDifficulty(difName, difficulty);
        } else if (difI == 1) {
            difficulty = 1;
            difName = difList[difI];
            UI.setDifficulty(difName, difficulty);        
        } else if (difI == 2) {
            difficulty = 1.5;
            difName = difList[difI];
            UI.setDifficulty(difName, difficulty);
        } else {
            difficulty = 2;
            difName = difList[difI];
            UI.setDifficulty(difName, difficulty);
        }
    }
    
    /**
     * Määrittelee asetukset valikon toiminnallisuuden ja UI:n.
     * @return Palauttaa näkymän GridPane muuttujassa.
     */
    private GridPane setupSettings() {
        GridPane window = new GridPane();
        
        resI = UI.resI;
        
        colorI = UI.colorI;
    
        difI = UI.difI;
        
        window.setPrefSize(UI.width, UI.height);
        
        Label res = new Label(resWidth[resI] + "x" + resHeight[resI]);
        
        res.setMinWidth(100);
        res.setAlignment(Pos.CENTER);
        
        Button reduceRes = new Button("<");
        reduceRes.setOnAction(e -> {
            if(resI == 0) {
                resI = resHeight.length-1;
            } else {
                resI--;
        stage.setScene(settings);
            }
            UI.setResolution(resWidth[resI], resHeight[resI]);
            res.setText(resWidth[resI] + "x" + resHeight[resI]);
        });
        
        Button increaseRes = new Button(">");
        increaseRes.setOnAction(e -> {
            if(resI == resHeight.length-1) {
                resI = 0;
            } else {
                resI++;
            }
            UI.setResolution(resWidth[resI], resHeight[resI]);
            res.setText(resWidth[resI] + "x" + resHeight[resI]);
        });
        
        Label color = new Label();
        color.setMinHeight(10);
        color.setMinWidth(100);
        color.setBackground(new Background(new BackgroundFill(colorList[colorI], CornerRadii.EMPTY, Insets.EMPTY)));
        
        Button changeColorLeft = new Button("<");
        changeColorLeft.setOnAction(e -> {
            if(colorI == 0) {
                colorI = colorList.length-1;
            } else {
                colorI--;
            }
            UI.setPlayerColor(colorList[colorI]);
            color.setBackground(new Background(new BackgroundFill(colorList[colorI], CornerRadii.EMPTY, Insets.EMPTY)));
        });
        
        Button changeColorRight = new Button(">");
        changeColorRight.setOnAction(e -> {
            if(colorI == colorList.length-1) {
                colorI = 0;
            } else {
                colorI++;
            }
            UI.setPlayerColor(colorList[colorI]);
            color.setBackground(new Background(new BackgroundFill(colorList[colorI], CornerRadii.EMPTY, Insets.EMPTY)));
        });
        
        Label dif = new Label(difList[difI]);
        dif.setMinWidth(100);
        dif.setAlignment(Pos.CENTER);
        
        Button changeDifLeft = new Button("<");
        changeDifLeft.setOnAction(e -> {
            if(difI == 0) {
                difI = difList.length-1;
            } else {
                difI--;
            }
            setDifficulty();
            dif.setText(difList[difI]);
            UI.emptyDeaths();
        });
        
        Button changeDifRight = new Button(">");
        changeDifRight.setOnAction(e -> {
            if(difI == difList.length -1) {
                difI = 0;
            } else {
                difI++;
            }
            setDifficulty();
            
            dif.setText(difList[difI]);
            UI.emptyDeaths();
        });
        
        Button mainMenu = new Button("Main Menu");
        mainMenu.setOnAction(e -> {
            UI.resI = resI;
            UI.colorI = colorI;
            UI.difI = difI;
            stage.setScene(mainMenu());
        });
        
        mainMenu.setMinWidth(100.0);
        
        window.add(reduceRes, 0, 0);
        window.add(res, 1, 0);
        window.add(increaseRes, 2, 0);
        
        window.add(changeColorLeft, 0, 1);
        window.add(color, 1, 1);
        window.add(changeColorRight, 2, 1);
        
        window.add(changeDifLeft, 0, 2);
        window.add(dif, 1, 2);
        window.add(changeDifRight, 2, 2);
        
        window.add(mainMenu, 1, 3);
        
        window.setHgap(10.0);
        window.setVgap(10.0);
        
        window.setAlignment(Pos.CENTER);
        
        return window;
    }
}
