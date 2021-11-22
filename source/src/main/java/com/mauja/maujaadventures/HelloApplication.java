package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.Boutons;
import com.mauja.maujaadventures.modele.Jeu;
import com.mauja.maujaadventures.modele.personnage.PersonnageJouable;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL imgURl= getClass().getResource("carte2.png");
        Image carte = new Image(imgURl.toExternalForm());
        ImageView imageView= new ImageView(carte);
        URL imgURlP= getClass().getResource("link_epee.png");
        Image img= new Image(imgURlP.toExternalForm());
        Group racine = new Group();
        PersonnageJouable pj = new PersonnageJouable(0, 0, 15,img);

        ArrayList<String> input;
        VBox content = new VBox();
        content.setBackground(new Background(new BackgroundImage(carte,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        Scene scene = new Scene(content);
        stage.setScene(scene);
        scene.setFill(Color.BLACK);

        Boutons b=new Boutons();
        Canvas canvas = new Canvas(800, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        content.getChildren().add( canvas );
        Jeu jeu=new Jeu();
        input=b.lecture(scene);
        jeu.boucle(gc,800,400,input,pj);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}