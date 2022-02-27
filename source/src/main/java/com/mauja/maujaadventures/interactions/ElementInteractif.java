package com.mauja.maujaadventures.interactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ElementInteractif extends Balise{
    private Map<Condition, List<Action>> mapConditionAction;

    private Condition derCondition;
    //public abstract void init();

    //public abstract void initialisation();

    //public abstract void interagit();

    public void setMapConditionAction(Map<Condition, List<Action>> mapConditionAction) {
        this.mapConditionAction = mapConditionAction;
    }

    public Map<Condition, List<Action>> getMapConditionAction() {
        return mapConditionAction;
    }

    @Override
    public String toString() {
        return "ElementInteractif{" +
                "mapConditionAction=" + mapConditionAction +
                '}';
    }

    @Override
    public void ajouter(Balise balise) {
        if (balise instanceof Condition condition){
            derCondition = condition;
            ajouterCondition(derCondition);
        }
        else {
            ajouterAction((Action)balise);
        }
    }

    public void ajouterCondition(Condition condition){
        mapConditionAction.put(condition, new ArrayList<>());
    }

    public void ajouterAction(Action action){
        mapConditionAction.get(derCondition).add(action);
    }
}
