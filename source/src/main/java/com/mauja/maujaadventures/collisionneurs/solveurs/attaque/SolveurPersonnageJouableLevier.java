package com.mauja.maujaadventures.collisionneurs.solveurs.attaque;

import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.*;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SolveurPersonnageJouableLevier extends SolveurAttaque{
    /**
     * Constructeur de la classe SolveurCollision
     *
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurPersonnageJouableLevier(Carte carte) {
        super(carte);
    }

    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2, List<Scenario> scenarios, TableauDeJeu tableauDeJeu) {
        PersonnageJouable pj = (PersonnageJouable) e1;
        Levier levier = (Levier) e2;

        Rectangle rectangleLevier = new Rectangle(levier.getPosition().getX(),levier.getPosition().getY(),
                Levier.getLargeurDefaut(), Levier.getHauteurDefaut());

        Dimension dim = pj.getAttaque().getCollision().getDimension();
        Position pos = pj.getAttaque().getCollision().getPosition();

        Rectangle rectangleAttaque = new Rectangle(pos.getX(), pos.getY(), dim.getLargeur(), dim.getHauteur());
        if (collisionneur.collisionne(rectangleAttaque, rectangleLevier)) {
            levier.setActive(true);
            for(Scenario scenario :  scenarios){
                for(ElementInteractif e  : scenario.getListeElemInteractif()){
                    if(e == levier) {
                        Iterator<Map.Entry<Condition, List<Action>>> it = e.getMapConditionAction().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<Condition, List<Action>> a = it.next();
                            for (Action action : a.getValue()) {
                                action.agit(tableauDeJeu);
                            }
                        }
                    }
                }
            }
        }
    }
}
