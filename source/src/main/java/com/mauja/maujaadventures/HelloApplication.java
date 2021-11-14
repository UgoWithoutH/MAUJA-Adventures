package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.Boutons;
import com.mauja.maujaadventures.modele.Jeu;
import com.mauja.maujaadventures.modele.personnage.PersonnageJouable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("carte2.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //Scene scene = new Scene(root);
        Stage fCarte = new Stage();
        String imgURl= getClass().getResource("carte2.png").toString();
        Image carte = new Image(imgURl);
        ImageView imageView= new ImageView(carte);
        //imageView.setX(500);
        imageView.setFitWidth(1600);
        imageView.setFitHeight(1600);
        Pane carteA = new Pane();
        carteA.getChildren().setAll(imageView);
        Scene sceneCarte = new Scene(carteA);
        fCarte.setTitle("Carte");
        fCarte.setScene(sceneCarte);
        fCarte.show();

        Group racine = new Group();
        PersonnageJouable pj = new PersonnageJouable(0, 0, 15);

        ArrayList<String> input;
        Scene scene = new Scene(racine);
        Boutons b=new Boutons();
        stage.setScene(scene);
        Canvas canvas = new Canvas(800, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        racine.getChildren().add( canvas );
        Jeu jeu=new Jeu();
        input=b.lecture(scene);
        jeu.boucle(gc,800,400,input,pj);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}