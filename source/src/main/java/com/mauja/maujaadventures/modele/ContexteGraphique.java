package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ProprietesImage;

public interface ContexteGraphique {
    void dessiner(ProprietesImage image, Position position, Dimension dimensions);
    void dessiner(ProprietesImage image, int positionX, int positionY, Dimension dimensions);
    void dessiner(ProprietesImage image, Position position, int largeur, int hauteur);
    void dessiner(ProprietesImage image, int positionX, int positionY, int largeur, int hauteur);
    public void effacer(Position position, Dimension dimensions);
}
