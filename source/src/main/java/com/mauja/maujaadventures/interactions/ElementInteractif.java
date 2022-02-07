package com.mauja.maujaadventures.interactions;

import java.util.Map;

public abstract class ElementInteractif {
    private Map<Condition, Action> mapConditionAction;

    public abstract void initialisation();

    public abstract void interagit();

    public void setMapConditionAction(Map<Condition, Action> mapConditionAction) {
        this.mapConditionAction = mapConditionAction;
    }
}
