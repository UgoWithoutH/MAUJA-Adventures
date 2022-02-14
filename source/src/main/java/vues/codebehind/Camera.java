package vues.codebehind;

import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;

public abstract class Camera {
    protected Dimension zoneObservable;
    protected Dimension milieu;
    protected Position position;
    public Camera(Dimension zoneObservable) {
        this.zoneObservable = zoneObservable;
        milieu = new Dimension(zoneObservable.getLargeur(), zoneObservable.getHauteur());

    }

    public abstract void centrerSurEntite(Entite entite);

}
