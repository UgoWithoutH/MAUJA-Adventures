package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.Boutons;
import com.mauja.maujaadventures.modele.Jeu;
import com.mauja.maujaadventures.modele.personnage.PersonnageJouable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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