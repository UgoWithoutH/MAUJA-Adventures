package com.mauja.maujaadventures.entrees;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class GestionnaireDeTouches {
    protected List<Touche> lesTouchesAppuyees = new ArrayList<>();

    public abstract List<Touche> detecte();

    public List<Touche> getLesTouchesAppuyees() {
        return lesTouchesAppuyees;
    }
}
