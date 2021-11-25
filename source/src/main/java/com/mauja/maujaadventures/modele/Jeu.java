package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.action.affiche.AfficheurEntite;
import com.mauja.maujaadventures.modele.action.deplace.Deplaceur;
import com.mauja.maujaadventures.modele.action.deplace.DeplaceurEntite;
import com.mauja.maujaadventures.modele.monde.Tuile;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

    private ContexteGraphique contexteGraphique;
    private Deplaceur deplaceur;
    private Collisionneur collisionneur;
    private List<Image> lesImages;
    private Camera camera;
    //private Afficheur afficheur;

    /**
     * Constructeur de la classe Jeu
     * @param gc Contexte graphique à afficher
     */
    public Jeu(GraphicsContext gc, List<Image> lesImages) {
        contexteGraphique = new Caneva(gc);
        deplaceur = new DeplaceurEntite();
        collisionneur = new Collisionneur();
        this.lesImages = lesImages;
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
        Camera camera = new Camera(this.contexteGraphique ,e.getPosition().getPositionX(),e.getPosition().getPositionY());

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
                for (int i = 0; i < 40; i++) {
                    for (int j = 0; j < 40; j++) {
                        contexteGraphique.dessiner(lesImages.get(400 -(i * 2 + j * 2)),
                                new Position(i * 32 - camera.getPositionCameraX(),
                                        j * 32 - camera.getPositionCameraY()),
                                new Dimension(32, 32));
                    }
                }

                System.out.println(camera.getPositionCameraX());
                if (input.contains("LEFT")) {
                    deplaceur.deplace(e, e.getPosition().getPositionX() - 3, e.getPosition().getPositionY(), l);
                    camera.deplacementCamera(-3, 0);
                }
                if (input.contains("RIGHT")) {
                    deplaceur.deplace(e, e.getPosition().getPositionX() + 3, e.getPosition().getPositionY(), l);
                    camera.deplacementCamera(3, 0);
                }
                if (input.contains("UP")) {
                    deplaceur.deplace(e, e.getPosition().getPositionX(), e.getPosition().getPositionY() - 3, l);
                    camera.deplacementCamera(0, -3);
                }
                if (input.contains("DOWN")) {
                    deplaceur.deplace(e, e.getPosition().getPositionX(), e.getPosition().getPositionY() + 3, l);
                    camera.deplacementCamera(0, 3);
                }
                //System.out.println(e.toString());
                AfficheurEntite ae = new AfficheurEntite();
                ae.affiche(e, e.getPosition(), contexteGraphique);
            }

        }.start();
    };
    public Collisionneur getCollisionneur() {
        return collisionneur;
    }

    public void setCollisionneur(Collisionneur collisionneur) {
        this.collisionneur = collisionneur;
    }

    public Deplaceur getDeplaceur() {
        return deplaceur;
    }

    public void setDeplaceur(Deplaceur deplaceur) {
        this.deplaceur = deplaceur;
    }
    public ContexteGraphique getContexteGraphique() {
        return contexteGraphique;
    }

    public void setContexteGraphique(ContexteGraphique contexteGraphique) {
        this.contexteGraphique = contexteGraphique;
    }
    @Override
    public int hashCode() {
        return 31*contexteGraphique.hashCode()+31*deplaceur.hashCode()+31*collisionneur.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Jeu autre = (Jeu) obj;
        return equals(autre);
    }

    public boolean equals(Jeu j) {
        boolean resultat = (contexteGraphique.equals(j.getContexteGraphique())) && (deplaceur.equals(j.getDeplaceur())) &&
                (collisionneur.equals(j.getCollisionneur()));
        return resultat;
    }
}
