package com.mauja.maujaadventures.interactions;

import java.util.List;

public abstract class Action extends Balise{
    protected List<ElementInteractif> listeElementInteractif;

    public abstract void agit();
}
