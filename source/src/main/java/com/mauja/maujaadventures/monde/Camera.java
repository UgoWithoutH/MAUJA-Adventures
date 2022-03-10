package com.mauja.maujaadventures.monde;

import com.mauja.maujaadventures.logique.Dimension;

public class Camera {
    private static final Dimension DIMENSION_CAMERA_PAR_DEFAUT = new Dimension(964, 608);
    private final double decalageX = 28.2;
    private final double decalageY = 24;
    private double positionCameraX;
    private double positionCameraY;

    //private Entite cible;
    /**
     * Constructeur de la classe caméra
     *
     * @param positionCameraX Correspond aux coordonnée X de la caméra
     * @param positionCameraY Correspond aux coordonnée Y de la caméra
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Camera(double positionCameraX, double positionCameraY){
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
/*
    public void centrerSurEntite(Entite e){
        positionCameraX = ((e.getPosition().getPositionX()) - (jeu.getGraphicsContext().getCanvas().getWidth()) / 2) + (e.getDimension().getLargeur() / 2);
        positionCameraY = ((e.getPosition().getPositionY()) - (jeu.getGraphicsContext().getCanvas().getHeight()) / 2) + (e.getDimens
*/

    public double getDecalageX() {
        return decalageX;
    }

    public double getDecalageY() {
        return decalageY;
    }

    public static Dimension getDimensionCameraParDefaut() {
        return DIMENSION_CAMERA_PAR_DEFAUT;
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



    /**
     * Redéfinition du hashCode
     * @return Entier de l'hachage des attributs de Camera
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return (int)(positionCameraX+positionCameraY);
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Camera autre = (Camera) obj;
        return equals(autre);
    }

    /**
     * Méthode equals
     * @param cam Objet que l'on veut comparer
     * @return true si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    /*
    public boolean equals(Camera cam) {
        boolean resultat=(cam.getPositionCameraX() == positionCameraX) && (cam.getPositionCameraY() == positionCameraY) &&
                (c.equals(cam.getC())) && (c.equals(cam.getGc()));
        return resultat;
    }

    /**
     * Redéfinition du toString
     * @return Chaîne de caractère que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    /*
    @Override
    public String toString() {
        return "Camera{" +
                "positionCameraX=" + positionCameraX +
                ", positionCameraY=" + positionCameraY +
                ", c=" + c.toString() +
                ", gc=" + gc.toString() +
                '}';
    }
    */
}
