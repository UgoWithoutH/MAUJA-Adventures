package vues;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.fenetres.FenetreDeJeu;
import com.mauja.maujaadventures.jeu.Jeu;
import com.mauja.maujaadventures.logique.Dimension;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Partie {

    @FXML
    private Pane partiePane;
    private Navigateur navigateur;
    private StackPane pausePane;
    private Scene scene;

    public Partie(Navigateur navigateur){
        this.navigateur = navigateur;
    }

    @FXML
    public void initialize(){
    }

    public void start(){
        ArrayList<String> input;
        VBox noeud = new VBox();
        partiePane.getChildren().add(noeud);
        Stage myStage = navigateur.getMyStage(); //temporaire
        myStage.getScene().setRoot(partiePane);
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

        jeu.pauseProperty().addListener((listener) -> {
            if(pausePane == null){
                try {
                    FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/PauseMenu.fxml"));
                    fxml.setController(new PauseMenu(navigateur));
                    pausePane = fxml.load();
                    Dimension dimension = Jeu.getDimensionCameraParDefaut();
                    pausePane.setPrefSize(myStage.getWidth()*0.70, myStage.getHeight()*0.70);
                    partiePane.getChildren().add(pausePane);
                    myStage.widthProperty().addListener((listenerStage) -> {

                    });
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(pausePane.isVisible()){
                pausePane.setVisible(false);
            }
            else {
                pausePane.setVisible(true);
            }
        });
        jeu.start();
    }
}
