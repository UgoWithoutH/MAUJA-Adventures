package com.mauja.maujaadventures.modele.afficheurs;


import com.mauja.maujaadventures.modele.logique.Dimension;
import com.mauja.maujaadventures.modele.logique.Position;
import javafx.scene.image.Image;

public abstract class ContexteGraphique {
    /**
     * Structure de la méthode dessiné
     *
     * @param image Image que l'on souhaite affiché
     * @param position Position de l'image
     * @param dimensions Hauteur et largeur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */

    public abstract void dessiner(Image image, Position position, Dimension dimensions);

    /**
     * Structure de la méthode dessiné
     * @param image Image que l'on souhaite affiché
     * @param positionX Position X de l'image
     * @param positionY Position Y de l'image
     * @param dimensions Hauteur et largeur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public abstract void dessiner(Image image, double positionX, double positionY, Dimension dimensions);
    /**
     * Structure de la méthode dessiné
     *
     * @param image Image que l'on souhaite affiché
     * @param position Position de l'image
     * @param largeur Largeur de l'image
     * @param hauteur Hauteur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public abstract void dessiner(Image image, Position position, double largeur, double hauteur);
    /**
     * Structure de la méthode dessiné
     *
     * @param image Image que l'on affiche
     * @param positionX Position X de l'image
     * @param positionY Position Y de l'image
     * @param largeur Largeur de l'image
     * @param hauteur Hauteur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public abstract void dessiner(Image image, double positionX, double positionY, double largeur, double hauteur);
    /**
     * Structure de la méthode effacer
     *
     * @param position Position de l'élément que l'on souhaite effacer
     * @param dimensions Hauteur et largeur de l'élément que l'on souhaite effacer
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public abstract void effacer(Position position, Dimension dimensions);





}
