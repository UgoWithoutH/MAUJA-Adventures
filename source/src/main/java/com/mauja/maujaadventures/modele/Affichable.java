package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ProprietesImage;

public abstract class Affichable {

    private String cheminImage;


    public Affichable(String image) {
        this.setCheminImage(image);
    }

    public String getCheminImage() {
        return cheminImage;
    }

    private void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
}
