package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.collisionneurs.CollisionneurCarte;
import com.mauja.maujaadventures.collisionneurs.SolveurCollision.SolveurCollision;
import com.mauja.maujaadventures.deplaceurs.DeplaceurEntite;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.*;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.logique.TransitionCarte;
import com.mauja.maujaadventures.monde.Camera;

import java.util.List;
import java.util.Map;

public class EvenementDeplacement extends Evenement {
    private CollisionneurCarte collisionneurCarte;
    private CollisionneurAABB collisionneur;
    private Direction direction;

    public EvenementDeplacement(ElementInteractif elementInteractif, Direction direction) {
        super(elementInteractif);
        this.direction = direction;
        collisionneurCarte = new CollisionneurCarte();
        collisionneur = new CollisionneurAABB();
    }

    @Override
    public void traitement(List<Scenario> scenarios, TableauDeJeu tableauDeJeu, Camera camera) {
        List<ElementInteractif> elementsInteractifs = tableauDeJeu.getCarteCourante().getLesElementsInteractifs();
        DeplaceurEntite deplaceurEntite = new DeplaceurEntite(tableauDeJeu.getCarteCourante());

        boolean estDeplace = deplaceurEntite.deplace((Entite) elementInteractif, 0, direction, true);
        if (elementInteractif instanceof Ennemi ennemi){
            ennemi.getComportement().miseAJour(estDeplace);
        }

        if (estDeplace) {
            if (elementInteractif instanceof PersonnageJouable) {
                deplacementCamera(tableauDeJeu, camera);
            }

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

                if (collisionneur.collisionne(collisionElement1, collisionElement2) && elementInteractif != elementInter) {
                    new SolveurCollision(tableauDeJeu.getCarteCourante()).resoud(elementInteractif, elementInter, sauvegardeElementInteractif);
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

    private void deplacementCamera(TableauDeJeu tableauDeJeu, Camera camera){
        switch (direction){
            case HAUT -> {
                if (!(camera.getPositionCameraY() <= 0) &&
                        (tableauDeJeu.getJoueur().getPosition().getY() <= tableauDeJeu.getCarteCourante().getDimensionCarte().getHauteur() * camera.getDecalageY() +
                                Camera.getDimensionCameraParDefaut().getHauteur() / 2)) {
                    camera.deplacementCamera(0, -tableauDeJeu.getJoueur().getVelocite().getY());
                }
            }
            case BAS -> {
                if ((tableauDeJeu.getCarteCourante().getDimensionCarte().getLargeur() * tableauDeJeu.getCarteCourante().getDimensionCarte().getLargeur()) - (tableauDeJeu.getJoueur().getPosition().getY()) > tableauDeJeu.getCarteCourante().getDimensionCarte().getHauteur() &&
                        (camera.getPositionCameraY() <= tableauDeJeu.getCarteCourante().getDimensionCarte().getHauteur() * camera.getDecalageY() &&
                                (tableauDeJeu.getJoueur().getPosition().getY() >= Camera.getDimensionCameraParDefaut().getHauteur() / 2))) {
                    camera.deplacementCamera(0, tableauDeJeu.getJoueur().getVelocite().getY());
                }
            }
            case DROITE -> {
                if (tableauDeJeu.getCarteCourante().getDimensionCarte().getLargeur() * camera.getDecalageX() - (tableauDeJeu.getJoueur().getPosition().getX()) > tableauDeJeu.getCarteCourante().getDimensionCarte().getLargeur()) {
                    if (((camera.getPositionCameraX() <= tableauDeJeu.getCarteCourante().getDimensionCarte().getLargeur() * camera.getDecalageX())) &&
                            (tableauDeJeu.getJoueur().getPosition().getX() >= Camera.getDimensionCameraParDefaut().getLargeur() / 2)) {
                        camera.deplacementCamera(tableauDeJeu.getJoueur().getVelocite().getX(), 0);
                    }
                }
            }
            case GAUCHE -> {
                if ( 0 + tableauDeJeu.getJoueur().getPosition().getY() > tableauDeJeu.getCarteCourante().getDimensionCarte().getLargeur()) {
                    if (!(camera.getPositionCameraX() <= 0) &&
                            (tableauDeJeu.getJoueur().getPosition().getX() <= tableauDeJeu.getCarteCourante().getDimensionCarte().getLargeur() * 32 -
                                    Camera.getDimensionCameraParDefaut().getLargeur() / 2)) {
                        camera.deplacementCamera(-tableauDeJeu.getJoueur().getVelocite().getX(), 0);
                    }
                }
            }
        }
    }
}
