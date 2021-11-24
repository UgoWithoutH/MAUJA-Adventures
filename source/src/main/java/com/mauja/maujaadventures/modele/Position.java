package com.mauja.maujaadventures.modele;

public class Position {
    private int positionX;
    private int positionY;
    /**
     * Constructeur de la classe Position
     * @param positionX Position de l'objet en X
     * @param positionY Position de l'obejt en Y
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Position(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
    /**
     * Getter position X
     * @return La position X de l'objet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getPositionX() { return positionX; }
    /**
     * Setter de la position X
     * @param positionX Nouvelle position X de l'objet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setPositionX(int positionX) { this.positionX = positionX; }
    /**
     * Getter de position Y
     * @return Position Y de l'objet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getPositionY() { return positionY; }
    /**
     * Setter de la position Y
     * @param positionY Nouvelle position Y de l'objet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setPositionY(int positionY) { this.positionY = positionY; }
}
