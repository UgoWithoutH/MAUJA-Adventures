package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.*;
import com.mauja.maujaadventures.modele.personnage.PersonnageJouableFX;
import com.mauja.maujaadventures.modele.personnage.PersonnageJouable;
import com.mauja.maujaadventures.modele.utilitaires.recuperateurRessources;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*URL imgURl= getClass().getResource("/images/carte2.png");
        //URL imgURlhypto= getClass().getResource("hyptosis_tile-art-batch-5.tsx");
        //URL imgURltest= getClass().getResource("carteTest.tmx");
        Image carte = new Image(imgURl.toExternalForm());
        ImageView imageView= new ImageView(carte);*/

        String imgURlP = recuperateurRessources.getRessource("/images/tilesets/entites/link_epee.png", getClass());

        Position position = new Position(482, 400);
        Rectangle rectangle = new Rectangle(new Position(3, 46), new Dimension(27, 1));
        PersonnageJouable pj = new PersonnageJouableFX(position, rectangle, 10, imgURlP);

        ArrayList<String> input;
        VBox content = new VBox();

        Scene scene = new Scene(content);
        stage.setScene(scene);
        scene.setFill(Color.BLACK);

        Boutons b = new Boutons();
        Canvas canvas = new Canvas(964, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        System.out.println(gc.getCanvas().getWidth());
        System.out.println(gc.getCanvas().getHeight());
        content.getChildren().add( canvas );
        Jeu jeu = new Jeu(gc);
        input = b.lecture(scene);

        jeu.boucle(input, pj);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}