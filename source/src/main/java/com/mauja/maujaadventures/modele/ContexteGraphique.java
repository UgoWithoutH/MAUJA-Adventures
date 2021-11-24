package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ProprietesImage;

public interface ContexteGraphique {
    /**
     * Structure de la méthode dessiné
     *
     * @param image Image que l'on souhaite affiché
     * @param position Position de l'image
     * @param dimensions Hauteur et largeur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    void dessiner(ProprietesImage image, Position position, Dimension dimensions);
    /**
     * Structure de la méthode dessiné
     * @param image Image que l'on souhaite affiché
     * @param positionX Position X de l'image
     * @param positionY Position Y de l'image
     * @param dimensions Hauteur et largeur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    void dessiner(ProprietesImage image, int positionX, int positionY, Dimension dimensions);
    /**
     * Structure de la méthode dessiné
     *
     * @param image Image que l'on souhaite affiché
     * @param position Position de l'image
     * @param largeur Largeur de l'image
     * @param hauteur Hauteur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    void dessiner(ProprietesImage image, Position position, int largeur, int hauteur);
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
    void dessiner(ProprietesImage image, int positionX, int positionY, int largeur, int hauteur);
    /**
     * Structure de la méthode effacer
     *
     * @param position Position de l'élément que l'on souhaite effacer
     * @param dimensions Hauteur et largeur de l'élément que l'on souhaite effacer
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void effacer(Position position, Dimension dimensions);
}
