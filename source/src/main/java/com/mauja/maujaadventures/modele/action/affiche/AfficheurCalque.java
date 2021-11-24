package com.mauja.maujaadventures.modele.action.affiche;

import com.mauja.maujaadventures.modele.ContexteGraphique;
import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.monde.*;

import java.util.ArrayList;

public class AfficheurCalque implements Afficheur {

    AfficheurTuile afficheTuile = new AfficheurTuile();

    @Override
    public void affiche(Affichable obj, Position pos, ContexteGraphique contexteGraphique) {
        Position p;
        if (!(obj instanceof Calque)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas un calque.");
        }
        Calque c = (Calque) obj;
        ArrayList<Tuile> lesTuiles = c.getListeDeTuiles();
        for (Tuile t : lesTuiles) {
            // a changer
            p = null;
            afficheTuile.affiche(t, pos, contexteGraphique);
        }
    }
}
