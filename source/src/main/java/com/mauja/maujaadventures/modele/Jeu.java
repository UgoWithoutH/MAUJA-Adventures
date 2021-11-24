package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.action.affiche.AfficheurEntite;
import com.mauja.maujaadventures.modele.action.deplace.Deplaceur;
import com.mauja.maujaadventures.modele.action.deplace.DeplaceurEntite;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

    private ContexteGraphique contexteGraphique;
    private Deplaceur deplaceur;
    private Collisionneur collisionneur;
    //private Afficheur afficheur;

    /**
     * Constructeur de la classe Jeu
     * @param gc Contexte graphique à afficher
     */
    public Jeu(GraphicsContext gc) {
        contexteGraphique = new Caneva(gc);
        deplaceur = new DeplaceurEntite();
        collisionneur = new Collisionneur();
    }

    /**
     * Méthode de la boucle de jeu du programme
     * @param x Position X de l'entite
     * @param y Position Y de l'entite
     * @param input Correspond à une liste detouche taper par l'utilisateur
     * @param e Entité que l'on fait bouger
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void boucle(int x, int y, ArrayList<String> input, Entite e, List<Rectangle2D> l){
        //final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            /**
             * Lorsque l'on appuie sur une touche cette méthode est appelé et on le, rajoute dans la liste
             *
             * @param currentNanoTime Correspond au temps passé
             * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
             */
            public void handle(long currentNanoTime)
            {
                contexteGraphique.effacer(new Position(0, 0), new Dimension(x, y));
                if (input.contains("LEFT"))
                    deplaceur.deplace(e,e.getPosition().getPositionX() - 3, e.getPosition().getPositionY(),l);
                if (input.contains("RIGHT"))
                    deplaceur.deplace(e, e.getPosition().getPositionX() + 3, e.getPosition().getPositionY(),l);
                if (input.contains("UP"))
                    deplaceur.deplace(e, e.getPosition().getPositionX(),e.getPosition().getPositionY() - 3,l);
                if (input.contains("DOWN"))
                    deplaceur.deplace(e, e.getPosition().getPositionX(), e.getPosition().getPositionY() + 3,l);
                //System.out.println(e.toString());
                AfficheurEntite ae = new AfficheurEntite();
                ae.affiche(e, e.getPosition(), contexteGraphique);
            }

        }.start();
    };
}
