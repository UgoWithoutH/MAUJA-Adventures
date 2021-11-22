package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.*;
import com.mauja.maujaadventures.modele.personnage.ImageSource;
import com.mauja.maujaadventures.modele.personnage.PersonnageJouable;
import javafx.application.Application;

import javafx.geometry.Rectangle2D;
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
        String imgURlP = getClass().getResource("link_epee.png").toString();
        ImageSource img= new ImageSource(imgURlP);
        Group racine = new Group();
        PersonnageJouable pj = new PersonnageJouable(new Position(0, 0),
                                                     new ImageSource(imgURlP),
                                                     new Collision(new Rectangle2D(1,1,1,1)),
                                                     10);

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
        Jeu jeu=new Jeu(gc);
        input=b.lecture(scene);
        jeu.boucle(800,400, input, pj);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}