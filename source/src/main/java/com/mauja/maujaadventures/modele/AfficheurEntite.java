package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
import javafx.geometry.Rectangle2D;

public class AfficheurEntite implements Afficheur {
    @Override
    public void affiche(Affichable obj, Position pos, ContexteGraphique contexteGraphique) {
        if (!(obj instanceof Entite)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas une entité.");
        }
        Entite e = (Entite) obj;
        ProprietesImage image = new ProprietesImage(e.getCheminImage());
        contexteGraphique.dessiner(image, e.getPosition(), e.getDimensions());
    }
}