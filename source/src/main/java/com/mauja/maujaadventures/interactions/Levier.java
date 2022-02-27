package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.annotations.ConstructDef;
import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.logique.Position;

public class Levier extends ElementInteractif{
    public Position position;
    public boolean active;

    @ConstructDef
    public Levier(@Param(nom = "x") Double x, @Param(nom = "y") Double y,
                  @Param(nom = "active", classe = Boolean.class) boolean active) {
        this.position = new Position(x,y);
        this.active = active;
    }

    public Position getPosition() {
        return position;
    }
}
