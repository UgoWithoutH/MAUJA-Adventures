package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.*;
import com.mauja.maujaadventures.modele.monde.Carte;
import com.mauja.maujaadventures.modele.monde.Decoupeur;
import com.mauja.maujaadventures.modele.monde.JeuDeTuiles;
import com.mauja.maujaadventures.modele.monde.Tuile;
import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
import com.mauja.maujaadventures.modele.personnage.PersonnageJouable;
import com.mauja.maujaadventures.modele.utilitaires.recuperateurRessources;
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

import java.util.*;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL imgURl= getClass().getResource("carte2.png");
        //URL imgURlhypto= getClass().getResource("hyptosis_tile-art-batch-5.tsx");
        //URL imgURltest= getClass().getResource("carteTest.tmx");
        Image carte = new Image(imgURl.toExternalForm());
        ImageView imageView= new ImageView(carte);

        String imgURlP = recuperateurRessources.getRessource("link_epee.png", getClass());
        ProprietesImage img= new ProprietesImage(imgURlP);

        Position position = new Position(0, 0);
        Collision collision = new Collision(position, 30, 26);
        PersonnageJouable pj = new PersonnageJouable(position, imgURlP, collision, 10);

        Decoupeur d = new Decoupeur();
        ArrayList<Image> images;
        images = d.decoupe("C:\\PROJET\\Ressources\\hyptosis_tile-art-batch-3.png",32,32);
        RecuperateurDeCartes recuperateurDeCartes = new RecuperateurDeCartes();
        Carte carte2 = recuperateurDeCartes.recupereCarte("C:\\PROJET\\Ressources\\carteTest.tmx");
        List<JeuDeTuiles> lesJeuxDeTuiles = recuperateurDeCartes.recupereJeuxDeTuiles("C:\\PROJET\\Ressources\\carteTest.tmx");
        List<Tuile> lesTuiles = new ArrayList<Tuile>();
        for (JeuDeTuiles jeuDeTuiles : lesJeuxDeTuiles) {
            for (Tuile tuile : jeuDeTuiles.getListeDeTuiles()) {
                lesTuiles.add(tuile);
            }
        }
        System.out.println(lesTuiles.size());

        Map<Tuile, Image> lesTuilesImagees = new HashMap<Tuile, Image>();
        Iterator<Map.Entry<Tuile, Image>> it = lesTuilesImagees.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Tuile, Image> a = it.next();
            lesTuilesImagees.put(a.getKey(),a.getValue());
        }

        ArrayList<String> input;
        VBox content = new VBox();
        //content.setBackground(new Background(new BackgroundImage(carte,
        //        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        //        BackgroundSize.DEFAULT)));
        Scene scene = new Scene(content);
        stage.setScene(scene);
        scene.setFill(Color.BLACK);

        /*for(String file : recuperateurRessources.getRessourcesString()){
            System.out.println(file);
        }*/

        Boutons b=new Boutons();
        Canvas canvas = new Canvas(800, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        content.getChildren().add( canvas );
        Jeu jeu = new Jeu(gc, images);
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