package com.mauja.maujaadventures.modele;

public class Camera {
    private int positionCameraX;
    private int positionCameraY;

    //private Entite cible;

    public Camera(int positionCameraX,int positionCameraY){
        this.positionCameraX=positionCameraX;
        this.positionCameraY=positionCameraY;
    }

    public void deplacementCamera(int deplaceX,int deplaceY){
        positionCameraX+=deplaceX;
        positionCameraY+=deplaceY;
    }

    public int getPositionCameraX() {
        return positionCameraX;
    }

    public void setPositionCameraX(int positionCameraX) {
        this.positionCameraX = positionCameraX;
    }

    public int getPositionCameraY() {
        return positionCameraY;
    }

    public void setPositionCameraY(int positionCameraY) {
        this.positionCameraY = positionCameraY;
    }
}
