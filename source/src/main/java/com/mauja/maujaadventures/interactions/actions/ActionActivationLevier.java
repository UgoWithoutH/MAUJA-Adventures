package com.mauja.maujaadventures.interactions.actions;

import com.mauja.maujaadventures.comportements.ComportementTireur;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.elements.Levier;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Velocite;

public class ActionActivationLevier extends Action {
    @Override
    public void agit(TableauDeJeu tableauDeJeu) {
        Levier levier = (Levier) getBaliseParente();
        levier.setActive(true);

        for (ElementInteractif elementInter : lesElementsInteractifs) {
            if (elementInter instanceof Ennemi ennemi) {
                ennemi.setComportement(new ComportementTireur(tableauDeJeu.getCarteCourante(),
                        new Velocite(20, 20)));
            }
        }
        tableauDeJeu.getCarteCourante().ajouterElementsInteractifs(lesElementsInteractifs);
    }
}
