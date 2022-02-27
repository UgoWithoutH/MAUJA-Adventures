package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class SolveurEnnemiEnnemi implements SolveurCollision{
    private TableauDeJeu tableauDeJeu;
    private CollisionneurAABB collisionneur;
    @Override
    public boolean resoud(ElementInteractif e1, ElementInteractif e2) {
        if (collisionneur.collisionne(((Ennemi)e1).getCollision(), ((Ennemi)e2).getCollision())) {
            ((Ennemi)e1).setPosition(((Ennemi)e1).getPosition());
            ((Ennemi)e2).setPosition(((Ennemi)e2).getPosition());
            return true;
        }
        return false;
    }
}
