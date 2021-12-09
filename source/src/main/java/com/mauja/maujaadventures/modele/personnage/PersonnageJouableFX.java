package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.Rectangle;
import javafx.scene.image.Image;

public class PersonnageJouableFX extends PersonnageJouable {
    private Image image;

    public PersonnageJouableFX(Position position, Rectangle rectangle, int attaque, String chemin) throws IllegalArgumentException {
        super(position, rectangle, attaque);
        this.image = new Image(chemin);
    }

    public Image getImage() {
        return image;
    }

    private void setImage(Image image) {
        this.image = image;
    }
}
