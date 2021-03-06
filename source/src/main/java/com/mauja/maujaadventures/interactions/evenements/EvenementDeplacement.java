package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.collisionneurs.solveurs.collision.SolveurCollision;
import com.mauja.maujaadventures.deplaceurs.Deplaceur;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.MementoPosition;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.logique.TransitionCarte;

import java.util.List;
import java.util.Map;

public class EvenementDeplacement extends Evenement {
    private CollisionneurAABB collisionneur;
    private Direction direction;
    private Deplaceur deplaceur;

    public EvenementDeplacement(ElementInteractif elementInteractif, Direction direction, Deplaceur deplaceur) {
        super(elementInteractif);
        if (deplaceur == null) {
            throw new IllegalArgumentException("Le déplaceur passé en paramètre de l'évènement ne peut pas être null.");
        }
        this.direction = direction;
        this.deplaceur = deplaceur;
        collisionneur = new CollisionneurAABB();
    }

    @Override
    public void traitement(TableauDeJeu tableauDeJeu) {
        if (!(elementInteractif instanceof Entite)) {
            return;
        }

        List<ElementInteractif> elementsInteractifs = tableauDeJeu.getCarteCourante().getLesElementsInteractifs();
        SolveurCollision solveurCollision = new SolveurCollision(tableauDeJeu.getCarteCourante());

        boolean resultat = deplaceur.deplace((Entite) elementInteractif, direction, true);

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

        notifier(elementInteractif, resultat, (Object[]) null);

        // Si l'élément se trouve sur la bonne carte et sur un point de transition, on change la carte et on le déplace.
        Map<TransitionCarte, TransitionCarte> transitionsCarte = tableauDeJeu.getTransitionsEntreCartes();
        for (Map.Entry<TransitionCarte, TransitionCarte> transitions : transitionsCarte.entrySet()) {
            if (transitions.getKey() != null && transitions.getValue() != null) {
                if (tableauDeJeu.getCarteCourante().getNom().equals(transitions.getKey().getNomCarte())
                        && collisionneur.collisionne(collisionElement1, transitions.getKey().getCollision())) {
                    if (tableauDeJeu.getJoueur().equals(elementInteractif)) {
                        elementInteractif.installerMemento(
                                new MementoPosition(transitions.getValue().getCollision().getPosition()));
                        tableauDeJeu.changerCarte(transitions.getValue().getNomCarte());
                    }
                }
            }
        }
    }
}
