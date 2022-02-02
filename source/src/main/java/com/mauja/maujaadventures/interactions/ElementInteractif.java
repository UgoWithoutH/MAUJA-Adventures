package com.mauja.maujaadventures.interactions;

import java.util.Map;

public abstract class ElementInteractif {
    private Map<Condition, Action> mapConditionAction;

    public abstract void init();

    public abstract void interagit();
}
