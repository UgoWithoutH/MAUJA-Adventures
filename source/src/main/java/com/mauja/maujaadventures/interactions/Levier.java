package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.logique.Position;

public class Levier extends ElementInteractif{
    public Position position;
    public boolean active;


    public Levier(Double x, Double y) {
        this.position = new Position(x,y);
        this.active = false;
    }

    public Position getPosition() {
        return position;
    }
}
