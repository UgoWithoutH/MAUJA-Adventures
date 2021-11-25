package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.*;
import com.mauja.maujaadventures.modele.monde.Decoupeur;
import com.mauja.maujaadventures.modele.monde.JeuDeTuiles;
import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
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
import java.util.LinkedList;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL imgURl= getClass().getResource("carte2.png");
        Image carte = new Image(imgURl.toExternalForm());
        ImageView imageView= new ImageView(carte);
        String imgURlP = getClass().getResource("link_epee.png").toString();
        ProprietesImage img= new ProprietesImage(imgURlP);
        Group racine = new Group();
        Position position = new Position(0, 0);
        Collision collision = new Collision(position, 30, 26);
        PersonnageJouable pj = new PersonnageJouable(position, imgURlP, collision, 10);


        JeuDeTuiles jdt = new JeuDeTuiles(getClass().getResource("carte2.png").toString(),(int)carte.getWidth(),(int)carte.getHeight());
        Decoupeur d = new Decoupeur();
        ArrayList<ProprietesImage> images = new ArrayList<ProprietesImage>();
        images = d.decoupe(jdt,32,32);
        //System.out.println(images.toString());

        System.out.println(img.getImage().getWidth());
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
        var l = new LinkedList<Rectangle2D>();
        l.add(new Rectangle2D(205,231,85,1));



        jeu.boucle(800,400, input, pj,l);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}