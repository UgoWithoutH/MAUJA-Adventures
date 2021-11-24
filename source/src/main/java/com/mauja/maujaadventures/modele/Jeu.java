package com.mauja.maujaadventures.modele;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

    private ContexteGraphique contexteGraphique;

    public Jeu(GraphicsContext gc) {
        contexteGraphique = new Caneva(gc);
    }


    public void boucle(int x, int y, ArrayList<String> input, Entite e, List<Rectangle2D> l){
        final long startNanoTime = System.nanoTime();
        DeplaceurEntite de=new DeplaceurEntite();
        new AnimationTimer()
        {

            public void handle(long currentNanoTime)
            {
                contexteGraphique.effacer(new Position(0, 0), new Dimension(x, y));
                if (input.contains("LEFT"))
                    de.deplaceur(e,e.getPosition().getPositionX() - 3, e.getPosition().getPositionY(),l);
                if (input.contains("RIGHT"))
                    de.deplaceur(e, e.getPosition().getPositionX() + 3, e.getPosition().getPositionY(),l);
                if (input.contains("UP"))
                    de.deplaceur(e, e.getPosition().getPositionX(),e.getPosition().getPositionY() - 3,l);
                if (input.contains("DOWN"))
                    de.deplaceur(e, e.getPosition().getPositionX(), e.getPosition().getPositionY() + 3,l);
                //System.out.println(e.toString());
                AfficheurEntite ae = new AfficheurEntite();
                ae.affiche(e, e.getPosition(), contexteGraphique);
                System.out.println("X : " + e.getCollision().getZoneCollision().getMinX() + " Y : " + e.getCollision().getZoneCollision().getMinY());
            }

        }.start();
    };
}
