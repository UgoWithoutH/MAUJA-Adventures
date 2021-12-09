package com.mauja.maujaadventures.modele.action.affiche;

import com.mauja.maujaadventures.modele.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AfficheurFX  {
    public void affiche(Image image, Position pos, GraphicsContext contexteGraphique) {
        contexteGraphique.drawImage(image, pos.getPositionX(), pos.getPositionY());
    }
}
