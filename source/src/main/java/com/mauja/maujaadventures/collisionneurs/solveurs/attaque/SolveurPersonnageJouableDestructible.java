package com.mauja.maujaadventures.collisionneurs.solveurs.attaque;

import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.Scenario;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Carte;

import java.util.List;

public class SolveurPersonnageJouableDestructible extends SolveurAttaque{
    /**
     * Constructeur de la classe SolveurCollision
     *
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurPersonnageJouableDestructible(Carte carte) {
        super(carte);
    }

    public void resoud(ElementInteractif e1, ElementInteractif e2, List<Scenario> scenarios, TableauDeJeu tableauDeJeu) {
        //Ne fait rien
    }
}
