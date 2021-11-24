package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
import javafx.scene.canvas.GraphicsContext;

public class Caneva implements ContexteGraphique {

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
    /**
     * Redéfinition d'une méthode dessiner de contexte graphique
     *
     * @param image Image que l'on souhaite affiché
     * @param position Position de l'image
     * @param dimensions Hauteur et largeur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void dessiner(ProprietesImage image, Position position, Dimension dimensions) {
        dessiner(image, position.getPositionX(), position.getPositionY(), dimensions.getLargeur(), dimensions.getHauteur());
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
    public void dessiner(ProprietesImage image, int positionX, int positionY, Dimension dimensions) {
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
    public void dessiner(ProprietesImage image, Position position, int largeur, int hauteur) {
        verificationPosition(position);
        dessiner(image, position.getPositionX(), position.getPositionY(), largeur, hauteur);
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
    public void dessiner(ProprietesImage image, int positionX, int positionY, int largeur, int hauteur) {
        verificationImage(image);
        gc.drawImage(image.getImage(), positionX, positionY, largeur, hauteur);
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
        gc.clearRect(position.getPositionX(), position.getPositionY(), dimensions.getLargeur(), dimensions.getHauteur());
    }
    /**
     * Méthode permettant de vérifier si une image est présente
     *
     * @param image Image que l'on souhaite vérifié
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void verificationImage(ProprietesImage image) {
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
}
