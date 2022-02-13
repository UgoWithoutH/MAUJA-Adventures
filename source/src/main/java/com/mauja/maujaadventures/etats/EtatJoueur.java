package com.mauja.maujaadventures.etats;

import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.entrees.GestionnaireDeTouches;
import com.mauja.maujaadventures.entrees.Touche;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

import java.util.List;

public abstract class EtatJoueur {
    protected TableauDeJeu tableauDeJeu;
    protected GestionnaireDeTouches gestionnaireDeTouches;
    protected List<Touche> lesTouchesPressees;

    public EtatJoueur(TableauDeJeu tableauDeJeu, GestionnaireDeTouches gestionnaireDeTouches)
            throws IllegalArgumentException {
        if (tableauDeJeu == null || gestionnaireDeTouches == null) {
            throw new IllegalArgumentException("Le tableau de jeu ou le gestionnaire de touches passés en paramètre "
                    + "ne peuvent pas être null.");
        }
        this.tableauDeJeu = tableauDeJeu;
        this.gestionnaireDeTouches = gestionnaireDeTouches;
    }

    public abstract EtatJoueur agit(PersonnageJouable joueur, double temps);
}
