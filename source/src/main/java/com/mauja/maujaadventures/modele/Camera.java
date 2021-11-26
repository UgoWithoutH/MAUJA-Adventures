package com.mauja.maujaadventures.modele;

import javafx.scene.canvas.GraphicsContext;

public class Camera {
    private double positionCameraX;
    private double positionCameraY;
    private ContexteGraphique c;
    private GraphicsContext gc;

    //private Entite cible;
    /**
     * Constructeur de la classe caméra
     *
     * @param positionCameraX Correspond aux coordonnée X de la caméra
     * @param positionCameraY Correspond aux coordonnée Y de la caméra
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Camera(ContexteGraphique c, double positionCameraX, double positionCameraY){
        this.gc = gc;
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
    public void deplacementCamera(double deplaceX,double deplaceY){

            positionCameraX+=deplaceX;


            positionCameraY+=deplaceY;


    }

    public void centrerSurEntite(Entite e){
        positionCameraX = e.getPosition().getPositionX() - gc.getCanvas().getWidth()/2 + e.getDimensions().getLargeur() / 2;
        positionCameraY = e.getPosition().getPositionY() - gc.getCanvas().getHeight() / 2 + e.getDimensions().getHauteur() / 2;


    }
    /**
     * Getter de la position Camera X
     * @return la valeur de la position X
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getPositionCameraX() {
        return positionCameraX;
    }
    /**
     * Setter de la camera X
     * @param positionCameraX Nouvelle valeur de X
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setPositionCameraX(double positionCameraX) {
        this.positionCameraX = positionCameraX;
    }
    /**
     * Getter de la position Camera Y
     * @return la valeur de la position Y
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getPositionCameraY() {
        return positionCameraY;
    }
    /**
     * Setter de la position Y
     * @param positionCameraY Nouvelle valeur de Y
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setPositionCameraY(double positionCameraY) {
        this.positionCameraY = positionCameraY;
    }
}
