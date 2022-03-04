package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.entites.EtatAction;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.*;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EvenementAttaque extends Evenement{

    public EvenementAttaque(TableauDeJeu tableauDeJeu, ElementInteractif elementInteractif) {
        super(tableauDeJeu, elementInteractif);
    }

    @Override
    public void traitement(List<Scenario> scenarios) {
        if(elementInteractif instanceof PersonnageJouable personnage){
            for(Scenario scenario :  scenarios){
                for(ElementInteractif e  : scenario.getListeElemInteractif()){
                    if(e instanceof Levier levier) {
                        Iterator<Map.Entry<Condition, List<Action>>> it = e.getMapConditionAction().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<Condition, List<Action>> a = it.next();
                            if (a.getKey() instanceof ConditionCollision) {
                                if (!levier.isActive()) {
                                    Dimension dim = personnage.getAttaque().getCollision().getDimension();
                                    Position pos = personnage.getAttaque().getCollision().getPosition();
                                    Rectangle rectangleAttaque = new Rectangle((int) pos.getX(), (int) pos.getY(), (int) dim.getLargeur(), (int) dim.getHauteur());
                                    Rectangle rectangleLevier = new Rectangle((int) levier.getPosition().getX(), (int) levier.getPosition().getY(), (int) Levier.getLargeurDefaut(), (int) Levier.getHauteurDefaut());
                                    if (rectangleAttaque.intersects(rectangleLevier)) {
                                        levier.setActive(true);
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
        }
    }
}
