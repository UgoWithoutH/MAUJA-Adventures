package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.Projectile;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurEnnemiProjectile extends SolveurCollision{
    private Carte carte;
    /**
     * Constructeur de la classe SolveurEnnemiProjectile
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurEnnemiProjectile(Carte carte) {
        super(carte);
        this.carte = carte;
    }

    /**
     * Résolution de la collision entre un Ennemi et un projectile
     * Lors d'une collision le projectile est détruit et l'ennemi subit des dégâts,
     * si l'ennemi à son nombre de point de vie inférieur ou égale à 0 l'ennemi est supprimer
     * @param e1 Element interactif du première élément à tester la collision de type Ennemi
     * @param e2 Element interactif du second élément à tester de type Projectile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        Ennemi ennemi = (Ennemi) e1;
        Projectile projectile = (Projectile) e2;
        ennemi.setPointsDeVie(ennemi.getPointsDeVie() - projectile.getDegats());
        carte.supprimerEntite(projectile);
        if (ennemi.getPointsDeVie() <= 0 ){
            carte.supprimerEntite(ennemi);
        }
    }
}
