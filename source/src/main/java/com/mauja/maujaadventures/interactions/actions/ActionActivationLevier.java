package com.mauja.maujaadventures.interactions.actions;

import com.mauja.maujaadventures.interactions.elements.Levier;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class ActionActivationLevier extends Action {
    private Levier levier;

    public ActionActivationLevier() throws IllegalArgumentException {
        /*if (levier == null) {
            throw new IllegalArgumentException("Le levier passé en paramètre ne peut pas être null.");
        }*/
    }

    @Override
    public void agit(TableauDeJeu tableauDeJeu) {
        levier.setActive(true);
        tableauDeJeu.getCarteCourante().ajouterElementsInteractifs(lesElementsInteractifs);
    }
}
