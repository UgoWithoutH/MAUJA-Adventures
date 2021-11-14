package com.mauja.maujaadventures.modele;

import javafx.scene.canvas.GraphicsContext;

public interface IAfficheur {

    public void afficheur(Entite e, int x, int y, GraphicsContext gc);
}