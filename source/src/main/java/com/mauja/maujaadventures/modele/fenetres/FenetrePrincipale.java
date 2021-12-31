package com.mauja.maujaadventures.modele.fenetres;

import com.mauja.maujaadventures.modele.entites.PersonnageJouable;
import com.mauja.maujaadventures.modele.entrees.Boutons;
import com.mauja.maujaadventures.modele.jeu.Jeu;
import com.mauja.maujaadventures.modele.logique.Dimension;
import com.mauja.maujaadventures.modele.logique.Position;
import com.mauja.maujaadventures.modele.logique.Rectangle;
import com.mauja.maujaadventures.modele.utilitaires.RecuperateurRessources;
import javafx.application.Application;

import javafx.scene.Scene;
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
        VBox content = new VBox();

        Scene scene = new Scene(content);
        stage.setScene(scene);
        scene.setFill(Color.BLACK);

        Boutons b = new Boutons();
        Canvas canvas = new Canvas(964, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        content.getChildren().add(canvas);
        Jeu jeu = new Jeu(gc);
        input = b.lecture(scene);

        jeu.boucle(input);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}