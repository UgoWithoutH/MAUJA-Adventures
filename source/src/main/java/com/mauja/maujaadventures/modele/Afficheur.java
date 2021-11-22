package com.mauja.maujaadventures.modele;

import javafx.scene.canvas.GraphicsContext;

public interface Afficheur {

    public void affiche(Object obj, Position pos, ContexteGraphique contexteGraphique);
}