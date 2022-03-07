package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;
public class SolveurPersonnageJouableProjectile extends SolveurCollision{

    /**
     * Constructeur de la classe SolveurPersonnageJouableProjectile
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurPersonnageJouableProjectile(Carte carte) {
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
        Projectile projectile = (Projectile) e2;
        int v;
        if (personnageJouable.getEtatAction() != EtatAction.SE_PROTEGE) {
            personnageJouable.setPointsDeVie(personnageJouable.getPointsDeVie() - projectile.getDegats());
            cartecourante.supprimerEntite(projectile);
        }
        else if (personnageJouable.getEtatAction() == EtatAction.SE_PROTEGE &&
                (personnageJouable.getDirection().getVal() == (v = projectile.getDirection().getVal() + 1) ||
                        (personnageJouable.getDirection().getVal() == (v = projectile.getDirection().getVal() - 1)))) {
            projectile.setDirection(Direction.valeurDe((byte) v));
        }
        else if (personnageJouable.getEtatAction() == EtatAction.SE_PROTEGE) {
            cartecourante.supprimerEntite(projectile);
            personnageJouable.setPointsDeVie(personnageJouable.getPointsDeVie() - projectile.getDegats());
        }
    }
}
