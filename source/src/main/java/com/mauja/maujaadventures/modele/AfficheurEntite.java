package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ImageSource;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AfficheurEntite implements IAfficheur{


    @Override
    public void afficheur(Entite e, int positionX, int positionY, GraphicsContext gc) {
        gc.drawImage(e.getImage().getImage(),positionX,positionY);
    }
}