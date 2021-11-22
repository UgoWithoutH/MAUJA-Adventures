package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.monde.Tuile;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class AfficheurTuile implements Afficheur {

    @Override
    public void affiche(Object obj, Position pos, ContexteGraphique contexteGraphique) {
        if (!(obj instanceof Tuile)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas une tuile.");
        }
        Tuile tuile = (Tuile) obj;
        contexteGraphique.dessiner(tuile.getImage(), null, tuile.getDimensions());
    }
}
