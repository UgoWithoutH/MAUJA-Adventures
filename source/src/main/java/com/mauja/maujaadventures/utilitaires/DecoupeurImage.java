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
        List<Image> lesImages = new ArrayList<>();
        Image image = new Image(chemin);
        PixelReader lecteur = image.getPixelReader();
        WritableImage imageDecoupe;
        double largeurImage = image.getWidth() / largeurTuile;
        double hauteurImage = image.getHeight() / hauteurTuile;

        for (int y = 0; y < hauteurImage; y++){
            for (int x = 0; x < largeurImage; x++) {
                imageDecoupe = new WritableImage(lecteur, x * largeurTuile, y * hauteurTuile, largeurTuile, hauteurTuile);
                lesImages.add(imageDecoupe);
            }
        }

        return lesImages;
    }
}
