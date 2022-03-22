package com.mauja.maujaadventures.interactions.conditions;

import com.mauja.maujaadventures.entites.EtatAction;
import com.mauja.maujaadventures.interactions.elements.Balise;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.elements.Levier;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.logique.Rectangle;

public class ConditionCollision extends Condition {
    private CollisionneurAABB collisionneur;

    public ConditionCollision() {
        collisionneur = new CollisionneurAABB();
    }

    @Override
    public boolean verificationCondition(ElementInteractif elementInteractif, TableauDeJeu tableauDeJeu) {
        Levier levier = (Levier) getBaliseParente();
        if (levier.isActive()) {
            return false;
        }

        PersonnageJouable joueur = tableauDeJeu.getJoueur();
        if (joueur.getEtatAction() == EtatAction.ATTAQUE) {
            ElementInteractif elementAColisionner = (ElementInteractif) getBaliseParente();

            Rectangle collisionElement = new Rectangle(
                    elementAColisionner.getCollision().getPosition().getX() + elementAColisionner.getPosition().getX(),
                    elementAColisionner.getCollision().getPosition().getY() + elementAColisionner.getPosition().getY(),
                    elementAColisionner.getCollision().getDimension());

            Attaque attaque = joueur.getAttaque();
            return collisionneur.collisionne(collisionElement, attaque.getCollision());
        }
        return false;
    }

    @Override
    public void ajouter(Balise balise) {
        getBaliseParente().ajouter(balise);
    }
}
