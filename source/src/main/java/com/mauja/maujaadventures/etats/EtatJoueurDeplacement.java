package com.mauja.maujaadventures.etats;

import com.mauja.maujaadventures.deplaceurs.DeplaceurEntite;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.entrees.GestionnaireDeTouches;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class EtatJoueurDeplacement extends EtatJoueur {
    private DeplaceurEntite deplaceur;

    public EtatJoueurDeplacement(TableauDeJeu tableauDeJeu, GestionnaireDeTouches gestionnaireDeTouches) {
        super(tableauDeJeu, gestionnaireDeTouches);
        deplaceur = new DeplaceurEntite(tableauDeJeu);
    }

    @Override
    public EtatJoueur agit(PersonnageJouable joueur, double temps) {
        lesTouchesPressees = gestionnaireDeTouches.detecte();

        if (lesTouchesPressees.contains(Touche.ESPACE)) {
            return new EtatJoueurAttaque(tableauDeJeu, gestionnaireDeTouches);
        }

        if (lesTouchesPressees.contains(Touche.B)) {
            return new EtatJoueurProtege(tableauDeJeu, gestionnaireDeTouches);
        }

        if (lesTouchesPressees.isEmpty()) {
            return new EtatJoueurImmobile(tableauDeJeu, gestionnaireDeTouches);
        }

        return null;
    }
}
