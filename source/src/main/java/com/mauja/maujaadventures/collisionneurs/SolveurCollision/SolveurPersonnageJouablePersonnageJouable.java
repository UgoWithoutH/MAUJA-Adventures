package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class SolveurPersonnageJouablePersonnageJouable implements SolveurCollision{
    private TableauDeJeu tableauDeJeu;
    private CollisionneurAABB collisionneur;

    /**
     * Résolution de la collision entre un PersonnageJouable et un PersonnageJouable
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @return true si collision sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean resoud(ElementInteractif e1, ElementInteractif e2) {
        if (collisionneur.collisionne(((PersonnageJouable)e1).getCollision(), ((PersonnageJouable)e2).getCollision())) {
            ((PersonnageJouable)e1).setPosition(((PersonnageJouable)e1).getPosition());
            ((PersonnageJouable)e2).setPosition(((PersonnageJouable)e2).getPosition());
            return true;
        }
        return false;
    }
}
