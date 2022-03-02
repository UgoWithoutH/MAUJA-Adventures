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

public class EvenementDeplacement extends Evenement {

    public EvenementDeplacement(TableauDeJeu tableauDeJeu) {
        super(tableauDeJeu);
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
                                if(personnage.getEtatAction() == EtatAction.ATTAQUE){
                                    Dimension dim = personnage.getCollision().getDimension();
                                    Position pos = personnage.getCollision().getPosition();
                                    Rectangle rectanglePerso = new Rectangle((int) pos.getX(), (int) pos.getY(), (int) dim.getLargeur(), (int) dim.getHauteur());
                                    if (!levier.isActive() &&
                                            rectanglePerso.intersects(levier.getPosition().getX(),levier.getPosition().getY(),Levier.getLargeurDefaut(),Levier.getHauteurDefaut())) {
                                        levier.setActive(true);
                                        for (Action action : a.getValue()) {
                                            action.agit();
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
