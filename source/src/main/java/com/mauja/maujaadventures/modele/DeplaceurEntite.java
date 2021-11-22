package com.mauja.maujaadventures.modele;

public class DeplaceurEntite implements IDeplaceur {
    @Override
    public void deplaceur(Entite e, int x, int y) {
        e.getPosition().setPositionX(x);
        e.getPosition().setPositionY(y);
    }
}
