package com.mauja.maujaadventures.modele.action.affiche;

import com.mauja.maujaadventures.modele.ContexteGraphique;
import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.monde.Calque;
import com.mauja.maujaadventures.modele.monde.Carte;

import java.util.ArrayList;
import java.util.List;

public class AfficheurCarte implements Afficheur {

    AfficheurCalque afficheCalque = new AfficheurCalque();
    /**
     * Redéfinition de l'interface Afficheur, on rajoute le code permettant l'affichage de l'objet ici une carte
     *
     * @param obj Correspond à l'objet que l'on souhaite afficher
     * @param pos Correspond à la position X et Y de l'objet
     * @param contexteGraphique Correspond à l'interface ou sera affiché l'objet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void affiche(Affichable obj, Position pos, ContexteGraphique contexteGraphique) {
        if (!(obj instanceof Carte)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas une carte.");
        }
        Carte carte = (Carte) obj;
        List<Calque> lesCalques = carte.getListeDeCalques();
        for (Calque c : lesCalques) {
            affiche(c, null, contexteGraphique);
        }
    }
}
