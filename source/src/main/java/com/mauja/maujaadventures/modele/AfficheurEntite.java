package com.mauja.maujaadventures.modele;

import javafx.scene.canvas.GraphicsContext;

public class AfficheurEntite implements IAfficheur{


    @Override
    public void afficheur(Entite e, int positionX, int positionY, GraphicsContext gc) {
        gc.drawImage(e.getImage(),positionX,positionY);
    }
}