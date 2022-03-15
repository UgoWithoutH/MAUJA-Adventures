package com.mauja.maujaadventures.interactions.actions;

import com.mauja.maujaadventures.comportements.ComportementTireur;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.interactions.actions.Action;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.elements.Levier;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Velocite;

public class ActionActivationLevier extends Action {
    @Override
    public void agit(ElementInteractif elementInteractif, TableauDeJeu tableauDeJeu) {
    Levier levier = (Levier) elementInteractif;

        if (levier.isActive()) {
            return;
        }
        else {
            levier.setActive(true);
        }

        for(ElementInteractif elementInter : lesElementsInteractifs) {
            if (elementInter instanceof Ennemi ennemi) {
                ennemi.setComportement(new ComportementTireur(tableauDeJeu.getCarteCourante(), new Velocite(4, 4)));
            }
        }
        tableauDeJeu.getCarteCourante().ajouterElementsInteractifs(lesElementsInteractifs);
    }
}
