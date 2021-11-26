package com.mauja.maujaadventures.modele.monde;

import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;


public class Decoupeur {

    private ProprietesImage proprietesImage;
    private ArrayList<Image> listeDeTuiles = new ArrayList<Image>();

    public ArrayList<Image> decoupe(String chemin, int largeurTuile, int hauteurTuile) {
        Image image = new Image(chemin);
        double largeurImage = image.getWidth() / largeurTuile;
        double hauteurImage = image.getHeight() / hauteurTuile;
        for (int i = 0; i < largeurImage; i++){
            for (int j = 0; j < hauteurImage; j++) {
                proprietesImage = new ProprietesImage(image.getUrl());
                WritableImage imageTuile = new WritableImage((proprietesImage).getImage().getPixelReader(),
                j * largeurTuile, i * hauteurTuile, largeurTuile, hauteurTuile);
                //proprietesImage.setImage(imageTuile);
                listeDeTuiles.add(imageTuile);
            }
        }
        return listeDeTuiles;
    }

    public ProprietesImage getProprietesImage() {
        return proprietesImage;
    }

    public void setProprietesImage(ProprietesImage proprietesImage) {
        this.proprietesImage = proprietesImage;
    }

    public ArrayList<Image> getListeDeTuiles() {
        return listeDeTuiles;
    }

    public void setListeDeTuiles(ArrayList<Image> listeDeTuiles) {
        this.listeDeTuiles = listeDeTuiles;
    }

    @Override
    public int hashCode() {
        return  31* proprietesImage.hashCode()+31*listeDeTuiles.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Decoupeur autre = (Decoupeur) obj;
        return equals(autre);
    }

    public boolean equals(Decoupeur d) {
        boolean resultat = (proprietesImage.equals(d.getProprietesImage())) && (listeDeTuiles.equals(d.getListeDeTuiles()));
        return resultat;
    }
}
