package com.mauja.maujaadventures.collisionneurs.solveurs.attaque;

import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurAttPersonnageJouableEnnemi extends SolveurAttaque{
    /**
     * Constructeur de la classe SolveurCollision
     *
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurAttPersonnageJouableEnnemi(Carte carte) {
        super(carte);
    }

    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        PersonnageJouable pj = (PersonnageJouable) e1;
        Ennemi ennemi = (Ennemi) e2;

        Rectangle collisionEntite = new Rectangle(ennemi.getCollision().getPosition().getX() + ennemi.getPosition().getX(),
                ennemi.getCollision().getPosition().getY() + ennemi.getPosition().getY(),
                ennemi.getCollision().getDimension());

        if (collisionneur.collisionne(pj.getAttaque().getCollision(), collisionEntite)) {
            ennemi.setPointsDeVie(ennemi.getPointsDeVie() - pj.getAttaque().getDegats());
            if (ennemi.getPointsDeVie() <= 0) {
                cartecourante.supprimerElementInteractif(ennemi);
            }
        }
    }
}
