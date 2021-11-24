package com.mauja.maujaadventures.modele;

public class Camera {
    private int positionCameraX;
    private int positionCameraY;

    //private Entite cible;
    /**
     * Constructeur de la classe caméra
     *
     * @param positionCameraX Correspond aux coordonnée X de la caméra
     * @param positionCameraY Correspond aux coordonnée Y de la caméra
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Camera(int positionCameraX,int positionCameraY){
        this.positionCameraX=positionCameraX;
        this.positionCameraY=positionCameraY;
    }
    /**
     * Méthode de déplacement de la caméra
     *
     * @param deplaceX Valeur du déplacement de la caméra en coordonnée X
     * @param deplaceY Valeur du déplacement de la caméra en coordonnée Y
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void deplacementCamera(int deplaceX,int deplaceY){
        positionCameraX+=deplaceX;
        positionCameraY+=deplaceY;
    }
    /**
     * Getter de la position Camera X
     * @return la valeur de la position X
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getPositionCameraX() {
        return positionCameraX;
    }
    /**
     * Setter de la camera X
     * @param positionCameraX Nouvelle valeur de X
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setPositionCameraX(int positionCameraX) {
        this.positionCameraX = positionCameraX;
    }
    /**
     * Getter de la position Camera Y
     * @return la valeur de la position Y
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getPositionCameraY() {
        return positionCameraY;
    }
    /**
     * Setter de la position Y
     * @param positionCameraY Nouvelle valeur de Y
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setPositionCameraY(int positionCameraY) {
        this.positionCameraY = positionCameraY;
    }
}
