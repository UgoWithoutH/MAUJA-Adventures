package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.monde.Calque;
import com.mauja.maujaadventures.modele.monde.Carte;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class AfficheurCarte implements Afficheur {

    AfficheurCalque afficheCalque = new AfficheurCalque();

    @Override
    public void affiche(Affichable obj, Position pos, ContexteGraphique contexteGraphique) {
        if (!(obj instanceof Carte)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas une carte.");
        }
        Carte carte = (Carte) obj;
        ArrayList<Calque> lesCalques = carte.getListeDeCalques();
        for (Calque c : lesCalques) {
            affiche(c, null, contexteGraphique);
        }
    }
}
