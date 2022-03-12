package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.collisionneurs.CollisionneurCarte;
import com.mauja.maujaadventures.collisionneurs.SolveurCollision.SolveurCollision;
import com.mauja.maujaadventures.deplaceurs.DeplaceurEntite;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.interactions.*;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.logique.TransitionCarte;

import java.util.List;
import java.util.Map;

public class EvenementDeplacement extends Evenement {
    private CollisionneurAABB collisionneur;
    private Direction direction;

    public EvenementDeplacement(ElementInteractif elementInteractif, Direction direction) {
        super(elementInteractif);
        this.direction = direction;
        collisionneur = new CollisionneurAABB();
    }

    @Override
    public void traitement(List<Scenario> scenarios, TableauDeJeu tableauDeJeu) {
        if (!(elementInteractif instanceof Entite)) {
            return;
        }

        List<ElementInteractif> elementsInteractifs = tableauDeJeu.getCarteCourante().getLesElementsInteractifs();
        SolveurCollision solveurCollision = new SolveurCollision(tableauDeJeu.getCarteCourante());
        DeplaceurEntite deplaceur = new DeplaceurEntite(tableauDeJeu.getCarteCourante());

        Rectangle collisionElement1 = new Rectangle(
                elementInteractif.getCollision().getPosition().getX() + elementInteractif.getPosition().getX(),
                elementInteractif.getCollision().getPosition().getY() + elementInteractif.getPosition().getY(),
                elementInteractif.getCollision().getDimension());

        deplaceur.deplace((Entite) elementInteractif, 0, direction, true);

        // Pour TOUS les éléments interactifs.
        for (ElementInteractif elementInter : elementsInteractifs) {
            Rectangle collisionElement2 = new Rectangle(
                    elementInter.getCollision().getPosition().getX() + elementInter.getPosition().getX(),
                    elementInter.getCollision().getPosition().getY() + elementInter.getPosition().getY(),
                    tableauDeJeu.getJoueur().getCollision().getDimension());

            if (collisionneur.collisionne(collisionElement1, collisionElement2)) {
                solveurCollision.resoud(elementInteractif, elementInter);
            }
        }

        //Pour l'élément à lui SEUL.
        Map<TransitionCarte, TransitionCarte> transitionsCarte = tableauDeJeu.getTransitionsEntreCartes();
        for (TransitionCarte transition : transitionsCarte.keySet()) {
            if (collisionneur.collisionne(collisionElement1, transition.getCollision())) {
                System.out.println("je transitionne !");
                elementInteractif.setPosition(transition.getCollision().getPosition());
            }
        }
    }
}
