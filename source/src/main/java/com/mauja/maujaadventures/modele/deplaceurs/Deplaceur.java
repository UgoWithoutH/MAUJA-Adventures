package com.mauja.maujaadventures.modele.deplaceurs;

import com.mauja.maujaadventures.modele.entites.Entite;

public interface Deplaceur {
    /**
     * Structure de la méthode de l'interface Deplaceur
     * @param e Correspond à l'entite que l'on va déplacer
     * @param x Correspond à la position X de l'entite
     * @param y Correspond à la position Y de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void deplace(Entite e, double x, double y);
}
