/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpaddiction.ui;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jumpaddiction.google.GoogleAuth;
import static jumpaddiction.ui.UI.mainMenu;

/**
 * Tulostaulua hallinnoiva luokka. Päivittää tulostaulua GoogleAuth luokan avulla. 
 * 
 * @see jumpaddiction.google.GoogleAuth
 * @author suonpaas
 */
public class Leaderboards {
    Scene leaderboard;
    GoogleAuth auth;
    Stage stage;
    
    Leaderboards(Stage stage) {
        this.stage = stage;
        
        auth = new GoogleAuth();
        
    }
    
    public Leaderboards() {
        auth = new GoogleAuth();
    }
    
    /**
     * Lataa helpon vaikeusasteen tulokset ja asettaa ne näkyviin. Käyttää metodeita setupGridPane, loadScores ja buttons.
     * 
     * @see jumpaddiction.ui.Leaderboards#setupGridPane(java.lang.String) 
     * @see jumpaddiction.ui.Leaderboards#loadScores(javafx.scene.layout.GridPane, java.lang.String) 
     * @see jumpaddiction.ui.Leaderboards#buttons() 
     */
    private void loadEasy() {
        GridPane window = setupGridPane("Easy");
        
        loadScores(window, "easy");
        
        window.addRow(13, buttons());
        
        leaderboard = new Scene(window);
        stage.setScene(leaderboard);
        
    }
    
    /**
     * Lisää tuloksen tulostaululle. 
     * @param name String muuttujana nimi
     * @param result Int muuttujana tuleva tulos
     * @param difficulty String muuttujana vaikeusaste
     */
    public void addResult(String name, int result, String difficulty) {
        
        
        try {
            auth.addResult(name, result, difficulty);
        } catch (GeneralSecurityException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Game ran into a problem");
            alert.setContentText("We couldn't add your score. Authentication to the server failed.");
            alert.showAndWait();
            Logger.getLogger(Leaderboards.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Game ran into a problem");
            alert.setContentText("We couldn't add your score.");
            alert.showAndWait();
            Logger.getLogger(Leaderboards.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Luo tulostaulun painikkeet. 
     * @return VBox laatikossa olevat painikkeet.
     */
    private VBox buttons() {
        Button easy = new Button("Easy");
        easy.setOnAction(e -> {
            loadEasy();
        });
        
        Button normal = new Button("Normal");
        normal.setOnAction(e -> {
            loadNormal();
        });        
        
        Button hard = new Button("Hard");
        hard.setOnAction(e -> {
            loadHard();
        });       
        
        Button impossible = new Button("Impossible");
        impossible.setOnAction(e -> {
            loadImpossible();
        });
        
        
        HBox layoutH = new HBox(easy, normal, hard, impossible);
        
        layoutH.setSpacing(10);
        
        layoutH.setAlignment(Pos.CENTER);

        Button mainMenu = new Button("Main Menu");
        mainMenu.setOnAction(e -> {
            stage.setScene(mainMenu());
        });
        
        mainMenu.setMinWidth(120.0);
        
        VBox layoutV = new VBox(layoutH, mainMenu);
        
        layoutV.setSpacing(10);
        
        layoutV.setAlignment(Pos.CENTER);
        
        return layoutV; 
    }
    
    /**
     * Määrittää tulostaulun ikkunan ulkoasuineen.
     * @param difficulty String muuttujana annettava katsottava vaikeusaste.
     * @return Palauttaa GridPane muuttujana ikkunan.
     */
    private GridPane setupGridPane(String difficulty) {
        GridPane window = new GridPane();
        
        window.setPrefSize(UI.width, UI.height);
        
        window.setPadding(new Insets(10, 10, 10, 10));
        window.setVgap(5);
        window.setHgap(5);
        
        window.setAlignment(Pos.CENTER);
        
        Label title = new Label(difficulty);
        title.setAlignment(Pos.CENTER);
        title.setFont(new Font(40));
        title.setMinWidth(400);
        window.addRow(0, title);
        
        Label name = new Label("Name");
        Label score = new Label("Deaths");
        
        window.add(name, 0, 1);
        window.add(score, 3, 1);
        
        return window;
    }
    
    /**
     * Kutsuu loadEasy() metodia
     * @see jumpaddiction.ui.Leaderboards#loadEasy() 
     */
    public void getLeaderboard() {
        loadEasy();
    }
    
    /**
     * Lataa mahdottoman vaikeusasteen tulokset ja asettaa ne näkyviin. Käyttää metodeita setupGridPane, loadScores ja buttons.
     * 
     * @see jumpaddiction.ui.Leaderboards#setupGridPane(java.lang.String) 
     * @see jumpaddiction.ui.Leaderboards#loadScores(javafx.scene.layout.GridPane, java.lang.String) 
     * @see jumpaddiction.ui.Leaderboards#buttons() 
     */
    private void loadImpossible() {
        GridPane window = setupGridPane("Impossible");
        
        loadScores(window, "impossible");
        
        window.addRow(13, buttons());
        
        leaderboard = new Scene(window);
        stage.setScene(leaderboard);
    }
    
    /**
     * Lataa vaikean vaikeusasteen tulokset ja asettaa ne näkyviin. Käyttää metodeita setupGridPane, loadScores ja buttons.
     * 
     * @see jumpaddiction.ui.Leaderboards#setupGridPane(java.lang.String) 
     * @see jumpaddiction.ui.Leaderboards#loadScores(javafx.scene.layout.GridPane, java.lang.String) 
     * @see jumpaddiction.ui.Leaderboards#buttons() 
     */
    private void loadHard() {
        GridPane window = setupGridPane("Hard");
        
        loadScores(window, "hard");
        
        window.addRow(13, buttons());
        
        leaderboard = new Scene(window);
        stage.setScene(leaderboard);
    }
    
    /**
     * Lataa normaalin vaikeusasteen tulokset ja asettaa ne näkyviin. 
     */
    private void loadNormal() {
        GridPane window = setupGridPane("Normal");
        
        loadScores(window, "normal");
        
        window.addRow(13, buttons());
        
        leaderboard = new Scene(window);
        stage.setScene(leaderboard);
    }
    
    /**
     * Lataa määritellyn vaikeuasteen tulokset ja asettaa ne ikkunaan. 
     * @param window Annetaan ikkuna mihin tulokset asetetaan.
     * @param difficulty String muuttujana annettava vaikeusasteen nimi. 
     */
    private void loadScores(GridPane window, String difficulty) {
        List<List<Object>> scores = new ArrayList<>();
        
        try {
            scores = auth.readData(difficulty);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Leaderboards.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Game ran into a problem");
            alert.setContentText("We couldn't load scores. Authentication to the server failed.");
            alert.showAndWait();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Game ran into a problem");
            alert.setContentText("We couldn't load scores.");
            alert.showAndWait();
            Logger.getLogger(Leaderboards.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        for (int i = 0; i < 10; i++) {
            if (scores == null || i >= scores.size()) {
                Label name = new Label(String.valueOf(i + 1));
                window.add(name, 0, i + 2);
                continue;
            }
            
            List row = scores.get(i);
            Label name = new Label((i + 1) + ". " + row.get(0));
            name.setMaxWidth(300);
            Label score = new Label(row.get(1).toString());
            score.setMinWidth(40);
            score.setAlignment(Pos.CENTER);
            window.add(name, 0, i + 2);
            window.add(score, 3, i + 2);
        }
    }
    
    
    
}
