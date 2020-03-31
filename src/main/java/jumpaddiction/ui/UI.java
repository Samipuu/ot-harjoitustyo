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
import jumpaddiction.Game;

/**
 *
 * @author suonpaas
 */
public class UI extends Application{
    
    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane window = new Pane();
        window.setPrefSize(WIDTH, HEIGHT);
        
        primaryStage.setTitle("JumpAddiction");
        
        Button start = new Button("Start");
        
        start.setOnAction(e->{
            Scene gameScene = new Scene(window, 1,1);
            try {
                gameScene = Game.game();
            } catch (Exception ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            primaryStage.setScene(gameScene);
            
        });
        //Button leaderboards = new Button("Leaderboards");
        Button exit = new Button("Exit");
        
        exit.setOnAction(e->{
            primaryStage.close();
        });
        
        VBox layout = new VBox(start,exit);
        
        Scene scene = new Scene(layout, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
