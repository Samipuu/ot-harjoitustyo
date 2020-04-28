/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jumpaddiction.game.Game;

/**
 *
 * @author suonpaas
 */
public class UI extends Application {
    
    public static int width = 800;
    public static int height = 600;
    
    private static int deaths = 0;
    
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Pane window = new Pane();
        window.setPrefSize(width, height);
        primaryStage.setTitle("JumpAddiction");
        Button start = new Button("Start");
        start.setOnAction(e-> {
            Scene gameScene = new Scene(window);
            try {
                Game newGame = new Game();
                gameScene = newGame.getGameScene();
            } catch (Exception ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            primaryStage.setScene(gameScene);
            
        });
        //Button leaderboards = new Button("Leaderboards");
        Button exit = new Button("Exit");
        exit.setOnAction(e-> {
            primaryStage.close();
        });
        
        VBox layout = new VBox(start, exit);
        Scene scene = new Scene(layout, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void gameOver() {
        deaths++;
        Pane window = new Pane();
        window.setPrefSize(width, height);
        stage.setTitle("JumpAddiction");
        Button restart = new Button("Retry");
        restart.setOnAction(e -> {
            Scene gameScene = new Scene(window, 1, 1);
                        try {
                Game newGame = new Game();
                gameScene = newGame.getGameScene();
            } catch (Exception ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            stage.setScene(gameScene);
        });
        
        Button exit = new Button("Exit");
        exit.setOnAction(e-> {
            stage.close();
        });
        
        VBox layout = new VBox(restart, exit);
        Scene scene = new Scene(layout, 200, 100);
        stage.setScene(scene);
        stage.show();
    }
    
    public static int getDeaths() {
        return deaths;
    }
    
    public static void emptyDeaths() {
        deaths = -1;
    }
}
