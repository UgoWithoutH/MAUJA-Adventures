package com.mauja.maujaadventures.interactions.actions;

import com.mauja.maujaadventures.collisionneurs.CollisionneurCarte;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.MementoPosition;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;

import java.util.Random;

public class ActionTeleportationJoueur extends Action {
    private static final Random ALEATOIRE = new Random();
    private static final int NOMBRE_TENTATIVE_TELEPORTATION = 10;
    private CollisionneurCarte collisionneurCarte;

    public ActionTeleportationJoueur() {
        collisionneurCarte = new CollisionneurCarte();
    }

    @Override
    public void agit(TableauDeJeu tableauDeJeu) {
        PersonnageJouable joueur = tableauDeJeu.getJoueur();
        Dimension dimensionCarte = tableauDeJeu.getCarteCourante().getDimensionCarte();
        Dimension dimensionTuiles = tableauDeJeu.getCarteCourante().getDimensionTuiles();

        int compteur = 0;
        do {
            Position positionFuture = new Position(
                    ALEATOIRE.nextInt((int) (dimensionCarte.getLargeur()) + 1) * dimensionTuiles.getLargeur(),
                    ALEATOIRE.nextInt((int) (dimensionCarte.getHauteur()) + 1) * dimensionTuiles.getHauteur());

            Rectangle zonecollision = new Rectangle(positionFuture, joueur.getDimension());

            if (!collisionneurCarte.collisionne(zonecollision, tableauDeJeu.getCarteCourante())) {
                joueur.installerMemento(new MementoPosition(positionFuture));
                return;
            }
            compteur++;
        }
        while (compteur < NOMBRE_TENTATIVE_TELEPORTATION);
    }
}
