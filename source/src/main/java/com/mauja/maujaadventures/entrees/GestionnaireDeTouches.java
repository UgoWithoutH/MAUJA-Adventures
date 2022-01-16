package com.mauja.maujaadventures.entrees;

import java.util.ArrayList;
import java.util.List;

public abstract class GestionnaireDeTouches {
    protected List<Touche> lesTouchesAppuyees = new ArrayList<>();

    public abstract List<Touche> detecte();

    public List<Touche> getLesTouchesAppuyees() {
        return lesTouchesAppuyees;
    }
}
