package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.collisionneurs.solveurs.collision.SolveurCollision;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Rectangle;

import java.util.List;

public class EvenementImmobile extends Evenement {
    private CollisionneurAABB collisionneur;

    public EvenementImmobile(ElementInteractif elementInteractif) throws IllegalArgumentException {
        super(elementInteractif);
        collisionneur = new CollisionneurAABB();
    }

    @Override
    public void traitement(TableauDeJeu tableauDeJeu) {
        List<ElementInteractif> elementsInteractifs = tableauDeJeu.getCarteCourante().getLesElementsInteractifs();
        SolveurCollision solveurCollision = new SolveurCollision(tableauDeJeu.getCarteCourante());

        Rectangle collisionElement1 = new Rectangle(
                elementInteractif.getCollision().getPosition().getX() + elementInteractif.getPosition().getX(),
                elementInteractif.getCollision().getPosition().getY() + elementInteractif.getPosition().getY(),
                elementInteractif.getCollision().getDimension());

        // Pour TOUS les éléments interactifs.
        for (ElementInteractif elementInter : elementsInteractifs) {
            // Si ce ne sont pas les mêmes.
            if (!elementInteractif.equals(elementInter)) {
                Rectangle collisionElement2 = new Rectangle(
                        elementInter.getCollision().getPosition().getX() + elementInter.getPosition().getX(),
                        elementInter.getCollision().getPosition().getY() + elementInter.getPosition().getY(),
                        tableauDeJeu.getJoueur().getCollision().getDimension());
                if (collisionneur.collisionne(collisionElement1, collisionElement2)) {
                    solveurCollision.resoud(elementInteractif, elementInter);
                }
            }
        }
    }
}
