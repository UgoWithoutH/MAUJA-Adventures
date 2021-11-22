package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ImageSource;

import java.awt.*;

public interface ContexteGraphique {
    void dessiner(ImageSource image, Position position, Dimension dimensions);
    void dessiner(ImageSource image, int positionX, int positionY, Dimension dimensions);
    void dessiner(ImageSource image, Position position, int largeur, int hauteur);
    void dessiner(ImageSource image, int positionX, int positionY, int largeur, int hauteur);
    public void effacer(Position position, Dimension dimensions);
}
