package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.collisionneurs.solveurs.attaque.SolveurAttaque;
import com.mauja.maujaadventures.interactions.*;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

import java.util.List;

public class EvenementAttaque extends Evenement {
    private Attaque attaque;
    private CollisionneurAABB collisionneur;

    public EvenementAttaque(ElementInteractif elementInteractif, Attaque attaque)
            throws IllegalArgumentException {
        super(elementInteractif);
        if (attaque == null) {
            throw new IllegalArgumentException("L'attaque passée en paramètre ne peut pas être nulle.");
        }
        this.attaque = attaque;
        collisionneur = new CollisionneurAABB();
    }

    @Override
    public void traitement(List<Scenario> scenarios, TableauDeJeu tableauDeJeu) {
        SolveurAttaque solveurAttaque = new SolveurAttaque(tableauDeJeu.getCarteCourante());

        for (ElementInteractif elementInter : tableauDeJeu.getCarteCourante().getLesElementsInteractifs()) {

            Rectangle collisionElement = new Rectangle(
                    elementInter.getCollision().getPosition().getX() + elementInter.getPosition().getX(),
                    elementInter.getCollision().getPosition().getY() + elementInter.getPosition().getY(),
                    elementInter.getCollision().getDimension());

            if (collisionneur.collisionne(collisionElement, attaque.getCollision())
                    && !elementInter.equals(elementInteractif)) {
                solveurAttaque.resoud(elementInter, attaque);
            }
        }
    }
}
