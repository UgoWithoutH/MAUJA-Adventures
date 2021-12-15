package com.mauja.maujaadventures.modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Caneva extends ContexteGraphique {

    private GraphicsContext gc;
    /**
     * Constructeur du Caneva
     * @param gc Correspond à l'interface de l'affichage
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Caneva(GraphicsContext gc) {
        if (gc == null) {
            throw new IllegalArgumentException("Le GraphicsContexte donné est null");
        }
        this.gc = gc;
    }

    public GraphicsContext getGc() { return gc; }

    public void setGc(GraphicsContext gc) { this.gc = gc; }

    /*@Override
    public void dessiner(Image image, Position position, Dimension dimensions) {
        gc.drawImage(image, position.getPositionX(), position.getPositionY(), dimensions.getLargeur(), dimensions.getHauteur());
    }*/

    /**
     * Redéfinition d'une méthode dessiner de contexte graphique
     *
     * @param image Image que l'on souhaite affiché
     * @param position Position de l'image
     * @param dimensions Hauteur et largeur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void dessiner(Image image, Position position, Dimension dimensions) {
        dessiner(image, position.getX(), position.getY(), dimensions.getLargeur(), dimensions.getHauteur());
    }

    /**
     * Redéfinition d'une méthode dessiner de contexte graphique
     *
     * @param image Image que l'on souhaite affiché
     * @param positionX Position X de l'image
     * @param positionY Position Y de l'image
     * @param dimensions Hauteur et largeur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void dessiner(Image image, double positionX, double positionY, Dimension dimensions) {
        verificationDimension(dimensions);
        dessiner(image, positionX, positionY, dimensions.getLargeur(), dimensions.getHauteur());
    }
    /**
     * Redéfinition d'une méthode dessiner de contexte graphique
     *
     * @param image Image que l'on souhaite affiché
     * @param position Position de l'image
     * @param largeur Largeur de l'image
     * @param hauteur Hauteur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void dessiner(Image image, Position position, double largeur, double hauteur) {
        verificationPosition(position);
        dessiner(image, position.getX(), position.getY(), largeur, hauteur);
    }
    /**
     * Redéfinition d'une méthode dessiner de contexte graphique
     *
     * @param image Image que l'on affiche
     * @param positionX Position X de l'image
     * @param positionY Position Y de l'image
     * @param largeur Largeur de l'image
     * @param hauteur Hauteur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void dessiner(Image image, double positionX, double positionY, double largeur, double hauteur) {
        verificationImage(image);
        //System.out.println(positionX + " " + positionY + " " + largeur + " " + hauteur);
        gc.drawImage(image, positionX, positionY, largeur, hauteur);
    }
    /**
     * Redéfiniton de la méthode effacer de contexteGraphique
     *
     * @param position Position de l'élément que l'on souhaite effacer
     * @param dimensions Hauteur et largeur de l'élément que l'on souhaite effacer
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void effacer(Position position, Dimension dimensions) {
        verificationDimension(dimensions);
        verificationPosition(position);
        gc.clearRect(position.getX(), position.getY(), dimensions.getLargeur(), dimensions.getHauteur());
    }
    /**
     * Méthode permettant de vérifier si une image est présente
     *
     * @param image Image que l'on souhaite vérifié
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void verificationImage(Image image) {
        if (image == null) {
            throw new IllegalArgumentException("L'image passée en paramètre qui doit être affichée est nulle.");
        }
    }
    /**
     *Méthode permettant de vérifier si une position est bien présente
     *
     * @param position Position que l'on vérifie
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void verificationPosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("La position passée en paramètre est nulle.");
        }
    }
    /**
     * Méthode permettant de vérifier si la dimension existe
     *
     * @param dimensions Dimension que l'on vérifie
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void verificationDimension(Dimension dimensions) {
        if (dimensions == null) {
            throw new IllegalArgumentException("Les dimensions passées en paramètre sont nulles.");
        }
    }

    /**
     * Redéfintion du hashCode
     * @return Entier de l'hachage des attributs de Caneva
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return 31 * gc.hashCode();
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return true si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Caneva autre = (Caneva) obj;
        return equals(autre);
    }

    /**
     * Méthode equals
     * @param m Caneva que l'on veut comparer
     * @return true si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Caneva m) {
        return gc.equals(m.getGc());
    }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "gc=" + gc.toString();
    }
}
