package com.mauja.maujaadventures.modele;

import javafx.scene.canvas.GraphicsContext;

public interface Afficheur {

    public void affiche(Affichable obj, Position pos, ContexteGraphique contexteGraphique);
}