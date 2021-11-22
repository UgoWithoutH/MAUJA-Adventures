package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ImageSource;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AfficheurEntite implements Afficheur {
    @Override
    public void affiche(Object obj, Position pos, ContexteGraphique contexteGraphique) {
        if (!(obj instanceof Entite)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas une entité.");
        }
        Entite e = (Entite) obj;
        contexteGraphique.dessiner(e.getImage(), e.getPosition(), e.getDimensions());
    }
}