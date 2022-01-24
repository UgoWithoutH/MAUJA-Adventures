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
    private Stage myStage;



    public MainMenu(Navigateur navigateur){
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
        ArrayList<String> input;
        VBox noeud = new VBox();
        //Scene scene = new Scene(noeud);
        myStage.getScene().setRoot(noeud);
        Scene scene = myStage.getScene();
        scene.setFill(Color.BLACK);
        Canvas canvas = new Canvas(964, 608);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        noeud.getChildren().add(canvas);

        GestionnaireDeTouchesFX gestionnaireDeTouches = new GestionnaireDeTouchesFX(scene);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.DOWN, Touche.FLECHE_BAS);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.UP, Touche.FLECHE_HAUT);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.LEFT, Touche.FLECHE_GAUCHE);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.RIGHT, Touche.FLECHE_DROITE);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.SPACE, Touche.ESPACE);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.B, Touche.B);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.ESCAPE, Touche.ECHAP);
        gestionnaireDeTouches.initialisation();

        Jeu jeu = null;
        try {
            jeu = new Jeu(gestionnaireDeTouches);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        new FenetreDeJeu(gc, jeu);
        jeu.start();
    }

    public void quitter(ActionEvent buttonQuitter){
        myStage.close();
    }
}
