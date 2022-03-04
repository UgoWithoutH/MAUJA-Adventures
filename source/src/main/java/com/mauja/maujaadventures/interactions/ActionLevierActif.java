package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.comportements.ComportementOctorockTireur;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class ActionLevierActif extends Action{
    @Override
    public void agit(TableauDeJeu tableauDeJeu) {
        for(ElementInteractif elementInteractif : listeElementInteractif){
            if(elementInteractif instanceof Ennemi ennemi){
                ennemi.setComportement(new ComportementOctorockTireur(tableauDeJeu.getCarteCourante()));
            }
        }
        tableauDeJeu.getCarteCourante().getLesElementsInteractif().addAll(listeElementInteractif);
    }
}
