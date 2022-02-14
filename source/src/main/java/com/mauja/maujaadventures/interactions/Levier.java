package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.logique.Position;

public class Levier extends ElementInteractif{
    public Position position;
    public boolean active;


    public Levier(Position position) {
        this.position = position;
        this.active = false;
    }

    public Position getPosition() {
        return position;
    }
}
