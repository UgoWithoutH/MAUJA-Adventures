package com.mauja.maujaadventures.modele.action.affiche;

import com.mauja.maujaadventures.modele.ContexteGraphique;
import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.monde.Tuile;
import com.mauja.maujaadventures.modele.personnage.ProprietesImage;

public class AfficheurTuile implements Afficheur {

    @Override
    public void affiche(Affichable obj, Position pos, ContexteGraphique contexteGraphique) {
        if (!(obj instanceof Tuile)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas une tuile.");
        }
        Tuile tuile = (Tuile) obj;
        ProprietesImage image = new ProprietesImage(tuile.getCheminImage());
        contexteGraphique.dessiner(image, null, tuile.getDimensions());
    }
}
