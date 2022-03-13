package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.comportements.ComportementTireur;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Velocite;

public class ActionLevierActif extends Action {
    @Override
    public void agit(TableauDeJeu tableauDeJeu) {
        for(ElementInteractif elementInteractif : listeElementInteractif) {
            if (elementInteractif instanceof Ennemi ennemi) {
                ennemi.setComportement(new ComportementTireur(tableauDeJeu.getCarteCourante(), new Velocite(4, 4)));
            }
        }
        tableauDeJeu.getCarteCourante().ajouterElementsInteractifs(listeElementInteractif);
    }
}
