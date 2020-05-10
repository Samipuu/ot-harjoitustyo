/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.ui;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jumpaddiction.game.Game;

/**
 * Sovelluksen valikoista vastaava luokka.
 * @author suonpaas
 */
public class UI extends Application {
    
    public static int width = 800;
    public static int height = 600;
    
    public static int resI = 1;
    public static int colorI = 1;
    public static int difI = 1;
    
    private static int deaths = 0;
    
    private static Stage stage;
    
    private static Color playerColor = Color.CORAL;
    
    private static double difficulty = 1.0;
    private static String difName = "Normal";
    
    private static boolean played = true;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Pyytää käyttäjän nimen ja lisää tämän jälkeen tuloksen tulostaulukkoon. Asettaa aloitusnäkymän näkyviin.
     */
    public static void gameWon() {
        if(!played) {
            return;
        }
        played = false;
        
        Leaderboards leaderboard = new Leaderboards();
        stage.setScene(mainMenu());
        stage.show();
        
        
        TextInputDialog textInput = new TextInputDialog();
        
        textInput.setTitle("JumpAddiction");
        
        
        textInput.getDialogPane().setContentText("Enter your name for the leaderboards.\n You died " + deaths + " times during the game.");
        
        
        textInput.setOnHidden(e -> {
            String name = textInput.getEditor().getText();
            leaderboard.addResult(name, deaths, difName);
            deaths = 0;
        });
        
        textInput.show();
        
    }

    
    /**
     * Sovelluksen käynnistys metodi. Asettaa aloitusvalikon näkyviin.  
     * @param primaryStage pääikkuna
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("JumpAddiction");
                
        stage.setScene(mainMenu());
        stage.show();
    }
    
    /**
     * Tason häviämisen metodi. Asettaa yhden kuoleman lisää ja aloitusvalikon aktiiviseksi.
     */
    public static void gameOver() {
        deaths++;
        stage.setScene(mainMenu());
        stage.show();
    }
    
    
    public static int getDeaths() {
        return deaths;
    }
    
    /**
     * Palauttaa aloitusvalikon Scene-tyyppisessä muuttujassa. Määrittää aloitusvalikon UI:n ja painikkeiden toiminnallisuudet. 
     * @return Scene-tyypin muuttujana aloitusvalikon
     */
    public static Scene mainMenu () {
        GridPane window = new GridPane();
        Leaderboards leaderboard = new Leaderboards(stage);
        Settings settingsUI = new Settings(stage);
        window.setPrefSize(width, height);
        Button start = new Button("Start");
        start.setOnAction(e-> {
            played = true;
            Pane gamePane = new Pane();
            Scene gameScene = new Scene(gamePane);
            try {
                Game newGame = new Game(playerColor, width, height, difficulty);
                gameScene = newGame.getGameScene();
            } catch (Exception ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            stage.setScene(gameScene);
            
        });
        
        Button leaderboards = new Button("Leaderboards");
        leaderboards.setOnAction(e -> {
           leaderboard.getLeaderboard();
        });
        
        Button settings = new Button("Settings");
        settings.setOnAction(e -> {
            settingsUI.getSettings();
        });
        
        
        Button exit = new Button("Exit");
        exit.setOnAction(e-> {
            stage.close();
        });
        
        start.setMinWidth(120.0);
        settings.setMinWidth(120.0);
        leaderboards.setMinWidth(120.0);
        exit.setMinWidth(120.0);
        
        window.add(start, 0, 0);
        window.add(settings, 0, 1);
        window.add(leaderboards, 0, 2);
        window.add(exit, 0, 3);
        
        
        window.setAlignment(Pos.CENTER);
        
        window.setHgap(10.0);
        window.setVgap(10.0);
        
        
        Scene mainMenu = new Scene(window, width, height);
        
        return mainMenu;
    }
    
    /**
     * Asettaa vaikeusasteen muuttujat.
     * @param name Vaikeusasteen nimi
     * @param dif Vaikeusasteen kerroin
     */
    public static void setDifficulty(String name, double dif) {
       difficulty = dif;
       difName = name;
    }
    
    /**
     * Asettaa pelaajan värin.
     * @param color pelaajan väri Color-tyyppisenä muuttujana.
     */
    public static void setPlayerColor(Color color) {
        playerColor = color;
    }
    
    /**
     * Asettaa sovelluksessa käytettävän resoluution.
     * @param x Ikkunan leveys pikseleissä.
     * @param y Ikkunan korkeus pikseleissä.
     */
    public static void setResolution(int x, int y) {
        width = x;
        height = y;
    }
    
    /**
     * Tyhjentää tämän hetkiset kuolemat. 
     */
    public static void emptyDeaths() {
        deaths = 0;
    }
    
    /**
     * Asettaa asetuksien indeksin taulukoissa. 
     * @param x vaikeusasteen indeksi
     * @param y värin indeksi
     * @param z resoluution indeksi
     */
    public static void setIndex(int x, int y, int z) {
        difI = x;
        colorI = y;
        resI = z;
    }
    
}
