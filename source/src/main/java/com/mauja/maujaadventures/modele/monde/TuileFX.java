package com.mauja.maujaadventures.modele.monde;

import javafx.scene.image.Image;

public class TuileFX {
    Image image;
    Tuile tuile;

    public TuileFX(Tuile tuile, Image image) {
        this.tuile = tuile;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Tuile getTuile() {
        return tuile;
    }

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }
}
