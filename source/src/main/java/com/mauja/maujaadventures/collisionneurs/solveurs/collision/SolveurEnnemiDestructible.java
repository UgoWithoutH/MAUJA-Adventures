package com.mauja.maujaadventures.collisionneurs.solveurs.collision;

import com.mauja.maujaadventures.entites.Destructible;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

/**
 * Solveur associé à un ennemi et un destructible. Lorsqu'il y a collision l'ennemi prend des dégâts et le projectile est détruit
 */
public class SolveurEnnemiDestructible extends SolveurCollision{
    /**
     * Constructeur de la classe SolveurEnnemiProjectile
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurEnnemiDestructible(Carte carte) {
        super(carte);
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
        Destructible destructible = (Destructible) e2;
        if (!destructible.getEmetteur().getClass().equals(ennemi.getClass())) {
            ennemi.setPointsDeVie(ennemi.getPointsDeVie() - destructible.getDegats());
            if (ennemi.getPointsDeVie() <= 0 ) {
                carteCourante.supprimerElementInteractif(ennemi);
            }
            carteCourante.supprimerElementInteractif(destructible);
        }
    }
}
