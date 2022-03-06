package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.entites.Projectile;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurProjectileProjectile extends SolveurCollision{
    private TableauDeJeu tableauDeJeu;
    private Carte carte;
    /**
     * Constructeur de la classe SolveurProjectileProjectile
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurProjectileProjectile(Carte carte) {
        super(carte);
    }

    /**
     * Résolution de la collision entre un Projectile et un projectile
     * Les 2 projectiles sont détruit lorsqu'il rentre en collision
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        Projectile projectile1 = (Projectile) e1;
        Projectile projectile2 = (Projectile) e2;
        carte.supprimerEntite(projectile1);
        carte.supprimerEntite(projectile2);
    }
}
