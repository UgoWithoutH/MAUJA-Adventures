package com.mauja.maujaadventures.etats;

import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.entrees.GestionnaireDeTouches;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class EtatJoueurImmobile extends EtatJoueur {
    public EtatJoueurImmobile(TableauDeJeu tableauDeJeu, GestionnaireDeTouches gestionnaireDeTouches) throws IllegalArgumentException {
        super(tableauDeJeu, gestionnaireDeTouches);
    }

    @Override
    public EtatJoueur agit(PersonnageJouable joueur, double temps) {
        lesTouchesPressees = gestionnaireDeTouches.detecte();

        if (lesTouchesPressees.contains(Touche.FLECHE_DROITE)
                || lesTouchesPressees.contains(Touche.FLECHE_GAUCHE)
                || lesTouchesPressees.contains(Touche.FLECHE_BAS)
                || lesTouchesPressees.contains(Touche.FLECHE_HAUT)) {
            return new EtatJoueurDeplacement(tableauDeJeu, gestionnaireDeTouches);
        }

        if (lesTouchesPressees.contains(Touche.B)) {
            return new EtatJoueurProtege(tableauDeJeu, gestionnaireDeTouches);
        }

        if (lesTouchesPressees.contains(Touche.ESPACE)) {
            return new EtatJoueurAttaque(tableauDeJeu, gestionnaireDeTouches);
        }

        return null;
    }
}
