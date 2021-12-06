package com.mauja.maujaadventures.modele.action.affiche;

import com.mauja.maujaadventures.modele.ContexteGraphique;
import com.mauja.maujaadventures.modele.Jeu;
import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.monde.*;

import java.util.ArrayList;
import java.util.List;

public class AfficheurCalque implements Afficheur {


    AfficheurTuile afficheTuile = new AfficheurTuile();
    /**
     * Redéfinition de l'interface Afficheur, on rajoute le code permettant l'affichage de l'objet ici un calque
     *
     * @param obj Correspond à l'objet que l'on souhaite afficher
     * @param pos Correspond à la position X et Y de l'objet
     * @param contexteGraphique Correspond à l'interface ou sera affiché l'objet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void affiche(Affichable obj, Position pos, ContexteGraphique contexteGraphique, Jeu jeu) {
        Position p;
        if (!(obj instanceof Calque)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas un calque.");
        }
        Calque c = (Calque) obj;
        List<Tuile> lesTuiles = c.getListeDeTuiles();
        for (Tuile t : lesTuiles) {
            // a changer
            p = null;
            afficheTuile.affiche(t, pos, contexteGraphique, jeu);
        }
    }

    /**
     * Redéfinition du toString
     * @return Chaîne de caractère que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "AfficheurCalque{" +
                "afficheTuile=" + afficheTuile.toString() +
                '}';
    }
}
