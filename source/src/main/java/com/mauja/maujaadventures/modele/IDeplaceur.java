package com.mauja.maujaadventures.modele;

import javafx.geometry.Rectangle2D;

import java.util.List;

public interface IDeplaceur {

    public void deplaceur(Entite e, int x, int y, List<Rectangle2D> l);
}
