package com.mauja.maujaadventures.modele.action.deplace;

import com.mauja.maujaadventures.modele.Entite;
import javafx.geometry.Rectangle2D;

import java.util.List;

public interface IDeplaceur {

    public void deplaceur(Entite e, int x, int y, List<Rectangle2D> l);
}
