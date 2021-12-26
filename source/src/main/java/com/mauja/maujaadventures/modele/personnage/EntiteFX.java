package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.Rectangle;
import javafx.scene.image.Image;

public abstract class EntiteFX extends Entite {
    private Image image;

    public EntiteFX(Position position, Rectangle rectangle, String chemin) throws IllegalArgumentException {
        super(position, rectangle, 20);
        if (chemin == null) {
            throw new IllegalArgumentException("Le chemin donné en paramètre pour l'image est null.");
        }
        image = new Image(chemin);
    }

    public Image getImage() {
        return image;
    }

    private void setImage(Image image) {
        this.image = image;
    }
}
