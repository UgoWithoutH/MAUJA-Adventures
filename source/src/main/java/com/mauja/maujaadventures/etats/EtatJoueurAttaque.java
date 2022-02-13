package com.mauja.maujaadventures.etats;

import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.entrees.GestionnaireDeTouches;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class EtatJoueurAttaque extends EtatJoueur {
    private double dureeAttaque;

    public EtatJoueurAttaque(TableauDeJeu tableauDeJeu, GestionnaireDeTouches gestionnaireDeTouches) throws IllegalArgumentException {
        super(tableauDeJeu, gestionnaireDeTouches);
        dureeAttaque = 0;
    }

    @Override
    public EtatJoueur agit(PersonnageJouable joueur, double temps) {
        lesTouchesPressees = gestionnaireDeTouches.detecte();

        if (dureeAttaque >= joueur.getAttaque().getDuree()) {
            joueur.getAttaque().setDuree(0);
            return new EtatJoueurImmobile(tableauDeJeu, gestionnaireDeTouches);
        }
        else {
            dureeAttaque += temps;
            joueur.getAttaque().setDuree(dureeAttaque);
        }

        //Gestion collision

        return null;
    }
}
