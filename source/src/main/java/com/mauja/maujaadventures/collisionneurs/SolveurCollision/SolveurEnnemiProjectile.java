package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.Projectile;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class SolveurEnnemiProjectile implements SolveurCollision{
    private TableauDeJeu tableauDeJeu;
    private CollisionneurAABB collisionneur;

    /**
     * Résolution de la collision entre un Ennemi et un projectile
     * @param e1 Element interactif du première élément à tester la collision de type Ennemi
     * @param e2 Element interactif du second élément à tester de type Projectile
     * @return true si collision sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean resoud(ElementInteractif e1, ElementInteractif e2) {
        if (collisionneur.collisionne(((Ennemi)e1).getCollision(), ((Projectile)e2).getCollision())) {
            ((Ennemi)e1).setPointsDeVie(((Ennemi)e1).getPointsDeVie() - ((Projectile)e2).getDegats());
            tableauDeJeu.getCarteCourante().supprimerEntite(((Projectile)e2));
            if (((Ennemi) e1).getPointsDeVie() < 0 ){
                tableauDeJeu.getCarteCourante().supprimerEntite(((Ennemi)e1));
            }
           return true;
        }
        return false;
    }
}
