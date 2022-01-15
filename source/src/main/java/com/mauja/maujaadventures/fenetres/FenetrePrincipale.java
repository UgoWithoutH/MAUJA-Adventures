package com.mauja.maujaadventures.fenetres;

import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.entrees.Boutons;
import com.mauja.maujaadventures.jeu.Jeu;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.utilitaires.RecuperateurRessources;
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

        Boutons b = new Boutons(scene);
        Canvas canvas = new Canvas(964, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        content.getChildren().add(canvas);
        input = b.lecture();
        Jeu jeu = new Jeu(gc,input);
        jeu.start();
        //jeu.boucle(input);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}