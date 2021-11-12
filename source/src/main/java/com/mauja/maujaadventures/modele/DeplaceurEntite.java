package com.mauja.maujaadventures.modele;

public class DeplaceurEntite implements IDeplaceur {
    @Override
    public void deplaceur(Entite e, int x, int y) {
        if(x < 0)
            e.setX(0);
        else
            e.setX(x);

        if(y < 0)
            e.setY(0);
        else
            e.setY(y);
    }
}
