package com.mauja.maujaadventures.modele.afficheurs;

import com.mauja.maujaadventures.modele.logique.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AfficheurFX  {
    public void affiche(Image image, Position pos, GraphicsContext contexteGraphique) {
        contexteGraphique.drawImage(image, pos.getX(), pos.getY());
    }
}
