package com.mauja.maujaadventures.modele.action.affiche;

import com.mauja.maujaadventures.modele.ContexteGraphique;
import com.mauja.maujaadventures.modele.Entite;
import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.personnage.ProprietesImage;

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