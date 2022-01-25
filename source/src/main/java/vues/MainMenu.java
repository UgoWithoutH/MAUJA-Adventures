package vues;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.fenetres.FenetreDeJeu;
import com.mauja.maujaadventures.jeu.Jeu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class MainMenu{

    @FXML
    private GridPane mainGrid;
    @FXML
    private Button solo;
    @FXML
    private Button param;
    @FXML
    private Button quitter;
    private Navigateur navigateur;
    private Stage myStage;

    public MainMenu(Navigateur navigateur){
        this.navigateur = navigateur;
        myStage = navigateur.getMyStage();

        myStage.widthProperty().addListener((listener) -> {
            solo.setPrefWidth(myStage.getWidth()*0.70);
            param.setPrefWidth(myStage.getWidth()*0.70);
            quitter.setPrefWidth(myStage.getWidth()*0.70);
        });
    }

    @FXML
    public void initialize() {
        solo.setPrefHeight(40);
        param.setPrefHeight(40);
        quitter.setPrefHeight(40);
        solo.setMaxWidth(400);
        param.setMaxWidth(400);
        quitter.setMaxWidth(400);
    }

    public void startSolo() {
        Partie partie = new Partie(navigateur);
        navigateur.naviguerVers("Partie.fxml",partie);
        partie.start();
    }

    public void quitter(ActionEvent buttonQuitter){
        myStage.close();
    }
}