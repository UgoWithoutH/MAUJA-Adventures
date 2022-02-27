package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.entites.*;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class SolveurPersonnageJouableProjectile extends SolveurCollision{
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
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        PersonnageJouable personnageJouable = (PersonnageJouable) e1;
        Projectile projectile = (Projectile) e2;
        int v;
        if (personnageJouable.getEtatAction() != EtatAction.SE_PROTEGE) {
            personnageJouable.setPointsDeVie(personnageJouable.getPointsDeVie() - projectile.getDegats());
            tableauDeJeu.getCarteCourante().supprimerEntite(projectile);
        }
        else if (personnageJouable.getEtatAction() == EtatAction.SE_PROTEGE &&
                (personnageJouable.getDirection().getVal() == (v = projectile.getDirection().getVal() + 1) ||
                        (personnageJouable.getDirection().getVal() == (v = projectile.getDirection().getVal() - 1)))) {
            projectile.setDirection(Direction.valeurDe((byte) v));
        }
        else if (personnageJouable.getEtatAction() == EtatAction.SE_PROTEGE) {
            tableauDeJeu.getCarteCourante().supprimerEntite(projectile);
            personnageJouable.setPointsDeVie(tableauDeJeu.getJoueur().getPointsDeVie() - projectile.getDegats());
        }
    }
}
