package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.entrees.GestionnaireDeTouchesFX;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.jeu.Jeu;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;


public class FenetrePrincipale extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<String> input;
        VBox noeud = new VBox();
        Scene scene = new Scene(noeud);
        scene.setFill(Color.BLACK);
        Canvas canvas = new Canvas(964, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        noeud.getChildren().add(canvas);

        GestionnaireDeTouchesFX gestionnaireDeTouches = new GestionnaireDeTouchesFX(scene);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.DOWN, Touche.FLECHE_BAS);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.UP, Touche.FLECHE_HAUT);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.LEFT, Touche.FLECHE_GAUCHE);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.RIGHT, Touche.FLECHE_DROITE);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.SPACE, Touche.ESPACE);
        gestionnaireDeTouches.ajouteToucheFX(KeyCode.B, Touche.B);
        gestionnaireDeTouches.initialisation();

        Jeu jeu = new Jeu(gestionnaireDeTouches);
        jeu.start();
        FenetreDeJeu fenetre = new FenetreDeJeu(gc, jeu);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}