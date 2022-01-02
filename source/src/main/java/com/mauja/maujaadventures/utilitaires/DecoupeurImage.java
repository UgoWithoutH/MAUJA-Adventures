package com.mauja.maujaadventures.utilitaires;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;


public class DecoupeurImage {

    /**
     * Fonction de découpage des Tuiles
     * @param chemin Chemin de la Carte du TileSet
     * @param largeurTuile Largeur d'un Tuile (32)
     * @param hauteurTuile Hauteur d'une Tuile (32)
     * @return La liste des Tuiles découpé en format Image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public static List<Image> decoupe(String chemin, int largeurTuile, int hauteurTuile) {
        List<Image> listeDeTuiles = new ArrayList<Image>();
        System.out.println(chemin);
        Image image = new Image(chemin);
        PixelReader lecteur = image.getPixelReader();
        double largeurImage = image.getWidth() / largeurTuile;
        double hauteurImage = image.getHeight() / hauteurTuile;

        for (int i = 0; i < largeurImage; i++){
            for (int j = 0; j < hauteurImage; j++) {
                WritableImage imageTuile = new WritableImage(lecteur,
                j * largeurTuile, i * hauteurTuile, largeurTuile, hauteurTuile);
                listeDeTuiles.add(imageTuile);
            }
        }
        return listeDeTuiles;
    }
}
