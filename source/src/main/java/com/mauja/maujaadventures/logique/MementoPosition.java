package com.mauja.maujaadventures.logique;

public class MementoPosition {
    private Position position;

    public MementoPosition(Position position) throws IllegalArgumentException {
        if (position == null) {
            throw new IllegalArgumentException("La position passée en paramètre ne peut pas être nulle.");
        }
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
