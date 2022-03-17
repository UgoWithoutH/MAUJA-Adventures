package com.mauja.maujaadventures.collisionneurs.solveurs.collision;

import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

/**
 * Solveur collision entre PersonnageJouable et destructible
 * Si le personnage se protège il ne prend pas de point de vie et le destructible est envoyé dans l'autre sens
 * Si le personnage ne se protège pas alors il perdera le nombre de point de vie des dégâts du destructible
 */
public class SolveurPersonnageJouableDestructible extends SolveurCollision{

    /**
     * Constructeur de la classe SolveurPersonnageJouableProjectile
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurPersonnageJouableDestructible(Carte carte) {
        super(carte);
    }

    /**
     * Résolution de la collision entre un PersonnageJouable et un projectile
     * si l'ennemi ne se protège pas alors le personnage jouable perd des points de vie et le projectiles est détruit
     * si l'ennemi se protège alors on regarde le sens du projectile et on modifie son sens
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        PersonnageJouable personnageJouable = (PersonnageJouable) e1;
        Destructible destructible = (Destructible) e2;
        int v;
        if (personnageJouable.getEtatAction() != EtatAction.SE_PROTEGE) {
            personnageJouable.setPointsDeVie(personnageJouable.getPointsDeVie() - destructible.getDegats());
            carteCourante.supprimerElementInteractif(destructible);
        }
        else if (personnageJouable.getEtatAction() == EtatAction.SE_PROTEGE &&
                (personnageJouable.getDirection().getVal() == (v = destructible.getDirection().getVal() + 1) ||
                        (personnageJouable.getDirection().getVal() == (v = destructible.getDirection().getVal() - 1)))) {
            destructible.setDirection(Direction.valeurDe((byte) v));
        }
    }
}
