package com.mauja.maujaadventures.utilitaires;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;


public class DecoupeurImage {

    public List<Image> decoupe(Image image, int largeurTuile, int hauteurTuile) {
        List<Image> lesImages = new ArrayList<>();
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

    public List<Image> decoupe(String chemin, int largeurTuile, int hauteurTuile) {
        return decoupe(new Image(chemin), largeurTuile, hauteurTuile);
    }
}
