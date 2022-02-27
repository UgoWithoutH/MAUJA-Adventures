package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class SolveurEnnemiPersonnageJouable implements SolveurCollision{
    private TableauDeJeu tableauDeJeu;
    private CollisionneurAABB collisionneur;

    /**
     * Résolution de la collision entre un PersonnageJouable et un Ennemi
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @return true si collision sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean resoud(ElementInteractif e1, ElementInteractif e2) {
        if (collisionneur.collisionne(((PersonnageJouable)e2).getCollision(), ((Ennemi)e1).getCollision())) {
            ((PersonnageJouable)e2).setPosition(((PersonnageJouable)e2).getPosition());
            ((Ennemi)e1).setPosition(((Ennemi)e1).getPosition());
            ((PersonnageJouable)e2).setPointsDeVie(((PersonnageJouable) e2).getPointsDeVie() - ((Ennemi) e1).getAttaque().getDegats());
            /*
            if ((PersonnageJouable)e1).getPointDeVie() < 0)
                GameOver
             */
            return true;
        }
        return false;
    }
}
