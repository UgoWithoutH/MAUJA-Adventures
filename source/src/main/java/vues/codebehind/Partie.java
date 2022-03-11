package vues.codebehind;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.fenetres.FenetreDeJeu;
import com.mauja.maujaadventures.jeu.Jeu;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import vues.codebehind.MenuPause;
import vues.navigation.Navigateur;

import java.io.IOException;
import java.util.ArrayList;

public class Partie {
    private Navigateur navigateur;
    private Jeu jeu;

    private VBox contentPause;
    private StackPane partiePane;

    public Partie(Navigateur navigateur, Jeu jeu) throws IllegalArgumentException {
        if (jeu == null) {
            throw new IllegalArgumentException("Le jeu passé en paramètre ne peut pas être null.");
        }
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
        this.jeu = jeu;
    }

    public void initialiserVue(){
        VBox noeud = new VBox();
        contentPause = null;
        partiePane = new StackPane();
        partiePane.setAlignment(Pos.CENTER);
        partiePane.getChildren().add(noeud);
        Scene scene = navigateur.getSceneCourante();
        scene.setRoot(partiePane);
        scene.setFill(Color.BLACK);
        Canvas canvas = new Canvas(964, 608);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        noeud.getChildren().add(canvas);

        GestionnaireDeTouchesFX gestionnaireDeTouches = new GestionnaireDeTouchesFX(scene);
        gestionnaireDeTouches.initialisation();
        jeu.setGestionnaireDeTouches(gestionnaireDeTouches);
        new FenetreDeJeu(gc, jeu);
        jeu.getGestionnaireDeTouches().echapProperty().addListener((listener) -> {
            if (contentPause == null) {
                try {
                    FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/MenuPause.fxml"));
                    MenuPause pauseMenu = new MenuPause(navigateur, jeu);
                    fxml.setController(pauseMenu);
                    pauseMenu.setPane(partiePane);
                    contentPause = fxml.load();
                    partiePane.getChildren().add(contentPause);
                    jeu.getBoucle().setRunning(false);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (contentPause.isVisible()) {
                if (!jeu.isParamOuvert()) {
                    contentPause.setVisible(false);
                    jeu.start();
                }
            } else {
                contentPause.setVisible(true);
                jeu.getBoucle().setRunning(false);
            }
        });
    }

    public void start(){
        jeu.start();
    }

    public void restart(){
        VBox noeud = new VBox();
        partiePane = new StackPane();
        partiePane.setAlignment(Pos.CENTER);
        partiePane.getChildren().add(noeud);
        Scene scene = navigateur.getSceneCourante();
        scene.setRoot(partiePane);
        scene.setFill(Color.BLACK);
        Canvas canvas = new Canvas(964, 608);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        noeud.getChildren().add(canvas);
        new FenetreDeJeu(gc, jeu);
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/MenuPause.fxml"));
        MenuPause pauseMenu = new MenuPause(navigateur, jeu);
        fxml.setController(pauseMenu);
        pauseMenu.setPane(partiePane);
        try {
            contentPause = fxml.load();
            contentPause.setVisible(false);
            partiePane.getChildren().add(contentPause);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new FenetreDeJeu(gc, jeu);
        jeu.start();
    }

}
