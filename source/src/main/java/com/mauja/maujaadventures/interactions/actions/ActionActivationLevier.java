package com.mauja.maujaadventures.interactions.actions;

import com.mauja.maujaadventures.interactions.elements.Levier;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class ActionActivationLevier extends Action {
    private Levier levier;

    public ActionActivationLevier(/*Levier levier*/) throws IllegalArgumentException {
        /*if (levier == null) {
            throw new IllegalArgumentException("Le levier passé en paramètre ne peut pas être null.");
        }
        this.levier = levier;*/
    }

    @Override
    public void agit(TableauDeJeu tableauDeJeu) {
        levier.setActive(true);
    }
}
