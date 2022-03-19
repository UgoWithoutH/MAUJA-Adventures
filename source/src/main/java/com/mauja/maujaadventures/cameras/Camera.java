package com.mauja.maujaadventures.cameras;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import jdk.jshell.spi.ExecutionControl;

/**
 * Caméra du jeu abstraite contenant une position et un décalage et une zone observable
 */
public abstract class Camera {
    protected Dimension zoneObservable;
    protected Dimension milieu;
    protected Position position;
    protected Dimension decalageRelatif;
    protected Dimension decalageAbsolu;


    public Camera(Dimension zoneObservable) {
        this.zoneObservable = zoneObservable;
        milieu = new Dimension(zoneObservable.getLargeur() / 2, zoneObservable.getHauteur() / 2);
        position = new Position(0,0);
        decalageRelatif = new Dimension(0, 0);
        decalageAbsolu = new Dimension(0, 0);
    }

    public abstract void centrerSurEntite(ElementInteractif elementInteractif);

    public Position getPosition() {
        return position;
    }

    public Dimension getZoneVisuelle() {
        return zoneObservable;
    }

    public Dimension getDecalageRelatif() {
        return decalageRelatif;
    }

    public Dimension getDecalageAbsolu() {
        return decalageAbsolu;
    }

    public Dimension getZoneObservable() {
        return zoneObservable;
    }

    public abstract void decalage(Direction direction) throws ExecutionControl.NotImplementedException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camera camera = (Camera) o;
        return equals(camera);
    }

    public boolean equals(Camera camera) {
        return position.equals(camera.getPosition())
                && zoneObservable.equals(camera.getZoneVisuelle());
    }

    @Override
    public int hashCode() {
        return 7 * zoneObservable.hashCode() + 3 * position.hashCode();
    }


    @Override
    public String toString() {
        return "Caméra en " + position.toString() + " de dimensions " + zoneObservable.toString();
    }
}
