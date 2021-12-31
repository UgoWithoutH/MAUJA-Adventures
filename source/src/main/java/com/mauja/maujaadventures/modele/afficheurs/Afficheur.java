package com.mauja.maujaadventures.modele.afficheurs;

import com.mauja.maujaadventures.modele.jeu.Jeu;
import com.mauja.maujaadventures.modele.logique.Position;

public interface Afficheur {
    /**
     * Méthode de l'interface afficheur, est composé des paramètres et de la structure de la méthode
     *
     * @param obj Correspond à l'objet que l'on souhaite afficher
     * @param pos Correspond à la position X et Y de l'objet
     * @param contexteGraphique Correspond à l'interface ou sera affiché l'objet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    void affiche(Object obj, Position pos, ContexteGraphique contexteGraphique, Jeu jeu);
}