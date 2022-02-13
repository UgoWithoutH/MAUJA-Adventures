package com.mauja.maujaadventures.etats;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.entrees.GestionnaireDeTouches;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class EtatJoueurProtege extends EtatJoueur {
    private Direction directionProtection;

    public EtatJoueurProtege(TableauDeJeu tableauDeJeu, GestionnaireDeTouches gestionnaireDeTouches) throws IllegalArgumentException {
        super(tableauDeJeu, gestionnaireDeTouches);
        directionProtection = tableauDeJeu.getJoueur().getDirection();
    }

    @Override
    public EtatJoueur agit(PersonnageJouable joueur, double temps) {
        lesTouchesPressees = gestionnaireDeTouches.detecte();

        if (lesTouchesPressees.contains(Touche.ESPACE)) {
            return new EtatJoueurAttaque(tableauDeJeu, gestionnaireDeTouches);
        }

        //Gérer le bouclier et la protection

        if (lesTouchesPressees.isEmpty()) {
            return new EtatJoueurImmobile(tableauDeJeu, gestionnaireDeTouches);
        }

        return null;
    }
}
