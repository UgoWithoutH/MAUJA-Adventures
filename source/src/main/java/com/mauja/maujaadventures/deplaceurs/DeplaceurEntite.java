package com.mauja.maujaadventures.deplaceurs;

import com.mauja.maujaadventures.entites.Entite;

public class DeplaceurEntite implements Deplaceur {
    /**
     * Méthode permettant le déplacement de l'entite en la modifiant avec son setter
     * @param e Correspond à l'entite que l'on va déplacer
     * @param x Correspond à la position X de l'entite
     * @param y Correspond à la position Y de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void deplace(Entite e, double x, double y) {
        e.getPosition().setX(x);
        e.getPosition().setY(y);
    }
}
