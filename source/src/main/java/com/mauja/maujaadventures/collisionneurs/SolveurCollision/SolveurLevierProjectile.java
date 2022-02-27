package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.entites.Projectile;
import com.mauja.maujaadventures.interactions.Condition;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.Levier;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class SolveurLevierProjectile implements SolveurCollision{
    private TableauDeJeu tableauDeJeu;
    private CollisionneurAABB collisionneur;
    private Condition condition;

    /**
     * Résolution de la collision entre un Levier et un projectile
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @return true si collision sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean resoud(ElementInteractif e1, ElementInteractif e2) {
        if (collisionneur.collisionne(((Levier)e1).getCollision(), ((Projectile)e2).getCollision()) && condition.verificationCondition()) {
            ((Levier)e1).setActive(true);
            return true;
        }
        return false;
    }
}
