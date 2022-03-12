package com.mauja.maujaadventures.affichages;

import com.mauja.maujaadventures.monde.Tuile;
import javafx.scene.image.Image;

import java.util.Objects;

public class TuileGraphique {
    private Tuile tuile;
    private Image image;

    public TuileGraphique(Tuile tuile, Image image) throws IllegalArgumentException {
        if (tuile == null || image == null) {
            throw new IllegalArgumentException("La tuile ou son image passée en paramètre est nulle. Donné : "
                    + tuile + ", Image : " + image);
        }
        this.tuile = tuile;
        this.image = image;
    }

    public Tuile getTuile() {
        return tuile;
    }

    public Image getImage() {
        return image;
    }

    public boolean equals(TuileGraphique tuileGraphique) {
        return tuileGraphique != null
            && tuileGraphique.getTuile().equals(tuile);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TuileGraphique tuile = (TuileGraphique) o;
        return equals(tuile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tuile);
    }
}
