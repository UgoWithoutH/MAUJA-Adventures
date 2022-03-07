package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.collisionneurs.CollisionneurCarte;
import com.mauja.maujaadventures.collisionneurs.SolveurCollision.SolveurCollision;
import com.mauja.maujaadventures.interactions.*;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.logique.TransitionCarte;

import java.util.List;
import java.util.Map;

public class EvenementDeplacement extends Evenement {
    private CollisionneurCarte collisionneurCarte;
    private CollisionneurAABB collisionneur;

    public EvenementDeplacement(ElementInteractif elementInteractif) {
        super(elementInteractif);
        collisionneurCarte = new CollisionneurCarte();
        collisionneur = new CollisionneurAABB();
    }

    @Override
    public void traitement(List<Scenario> scenarios, TableauDeJeu tableauDeJeu) {
        List<ElementInteractif> elementsInteractifs = tableauDeJeu.getCarteCourante().getLesElementsInteractifs();

        Rectangle collisionElement1 = new Rectangle(
                elementInteractif.getCollision().getPosition().getX() + elementInteractif.getPosition().getX(),
                elementInteractif.getCollision().getPosition().getY() + elementInteractif.getPosition().getY(),
                elementInteractif.getCollision().getDimension());

        // Pour TOUS les éléments interactifs.
        for (ElementInteractif elementInter : elementsInteractifs) {
            Rectangle collisionElement2 = new Rectangle(
                    elementInter.getCollision().getPosition().getX() + elementInter.getPosition().getX(),
                    elementInter.getCollision().getPosition().getY() + elementInter.getPosition().getY(),
                    tableauDeJeu.getJoueur().getCollision().getDimension());

            if (collisionneur.collisionne(collisionElement1, collisionElement2)) {
                //new SolveurCollision().resoud(elementInteractif, elementInter);
            }
        }

        //Pour l'élément à lui SEUL.
        Map<TransitionCarte, TransitionCarte> transitionsCarte = tableauDeJeu.getTransitionsEntreCartes();
        for (TransitionCarte transition : transitionsCarte.keySet()) {
            if (collisionneur.collisionne(collisionElement1, transition.getCollision())) {
                elementInteractif.setPosition(transition.getCollision().getPosition());
            }
        }
    }
}
