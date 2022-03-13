package com.mauja.maujaadventures.affichages;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import javafx.scene.image.Image;

import java.util.Objects;

public class ElementInteractifGraphique {
    private ElementInteractif elementInteractif;
    private Image image;

    public ElementInteractifGraphique(ElementInteractif elementInteractif, Image image)
            throws IllegalArgumentException {
        if (elementInteractif == null) {
            throw new IllegalArgumentException("L'élément interactif passé en paramètre ne peut pas être null.");
        }
        if (image == null) {
            throw new IllegalArgumentException("L'image passée en paramètre ne peut pas être null.");
        }
        this.elementInteractif = elementInteractif;
        this.image = image;
    }

    public ElementInteractif getElementInteractif() {
        return elementInteractif;
    }

    public Image getImage() {
        return image;
    }

    public boolean equals(ElementInteractifGraphique elementInteractifGraphique) {
        return elementInteractifGraphique != null
                && elementInteractifGraphique.getElementInteractif().equals(elementInteractif);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementInteractifGraphique elementInteractif = (ElementInteractifGraphique) o;
        return equals(elementInteractif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementInteractif);
    }
}
