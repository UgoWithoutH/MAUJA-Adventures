package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class SolveurPersonnageJouableProjectile implements SolveurCollision{
    private TableauDeJeu tableauDeJeu;
    private CollisionneurAABB collisionneur;

    /**
     * Résolution de la collision entre un PersonnageJouable et un projectile
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @return true si collision sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean resoud(ElementInteractif e1, ElementInteractif e2) {
        int v;
        if (collisionneur.collisionne(((PersonnageJouable)e1).getCollision(), ((Projectile)e2).getCollision())
                && tableauDeJeu.getJoueur().getEtatAction() != EtatAction.SE_PROTEGE) {
            tableauDeJeu.getJoueur().setPointsDeVie(tableauDeJeu.getJoueur().getPointsDeVie() - ((Projectile)e2).getDegats());
            tableauDeJeu.getCarteCourante().supprimerEntite(((Projectile)e2));
            return true;
        }
        else if (collisionneur.collisionne(((PersonnageJouable)e1).getCollision(), ((Projectile)e2).getCollision()) &&
                tableauDeJeu.getJoueur().getEtatAction() == EtatAction.SE_PROTEGE &&
                (tableauDeJeu.getJoueur().getDirection().getVal() == (v = ((Projectile)e2).getDirection().getVal() + 1) ||
                        (tableauDeJeu.getJoueur().getDirection().getVal() == (v = ((Projectile)e2).getDirection().getVal() - 1)))) {
            ((Projectile)e2).setDirection(Direction.valeurDe((byte) v));
        }
        else if (collisionneur.collisionne(((PersonnageJouable)e1).getCollision(), ((Projectile)e2).getCollision())&&
                tableauDeJeu.getJoueur().getEtatAction() == EtatAction.SE_PROTEGE) {
            tableauDeJeu.getCarteCourante().supprimerEntite(((Projectile)e2));
            tableauDeJeu.getJoueur().setPointsDeVie(tableauDeJeu.getJoueur().getPointsDeVie() /*- e2.getDegats()*/);
        }
            return false;
    }
}
