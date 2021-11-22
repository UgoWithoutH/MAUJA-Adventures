package com.mauja.maujaadventures.modele;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class AfficheurCalque implements Afficheur {

    AfficheurTuile afficheTuile = new AfficheurTuile();

    @Override
    public void affiche(Object obj, Position pos, ContexteGraphique contexteGraphique) {
        Position p;
        if (!(o instanceof Calque)) {
            throw new IllegalArgumentException("L'objet " + o.toString() + " passé en paramètre n'est pas un calque.");
        }
        Calque c = (Calque) obj;
        ArrayList<Tuile> lesTuiles = c.getTuiles();
        foreach(Tuile t : lesTuiles) {
            p = new Position(c.get);
            afficheTuile.affiche(t, pos, contexteGraphique);
        }
    }
}
