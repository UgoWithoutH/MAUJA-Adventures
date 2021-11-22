package com.mauja.maujaadventures.modele;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class AfficheurCarte implements Afficheur {

    AfficheurCalque afficheCalque = new AfficheurCalque();

    @Override
    public void affiche(Object obj, Position pos, ContexteGraphique contexteGraphique) {
        if (!(obj instanceof Carte)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas une carte.");
        }
        Carte carte = (Carte) obj;
        ArrayList<Calque> lesCalques = carte.getCalques();
        foreach(Calque c : lesCalques) {
            afficheCalque(c, null, contexteGraphique);
        }
    }
}
