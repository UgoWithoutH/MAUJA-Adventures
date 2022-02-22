package vues.codebehind;

import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.Tuile;

public class CameraTuiles extends Camera {
    private Carte carte;
    private Tuile[][] zoneVisible;

    public CameraTuiles(Carte carte, Dimension zoneObservable) {
        super(zoneObservable);
        this.carte = carte;
    }

    @Override
    public void centrerSurEntite(Entite entite) {
        int largeurTuile = 32;
        int hauteurTuile = 32;

        double largeurCarte = carte.getDimension().getLargeur();
        double longueurCarte = carte.getDimension().getHauteur();

        double positionEntiteX = (entite.getPosition().getX() + entite.getDimension().getLargeur() / 2) / largeurTuile;
        double positionEntiteY = (entite.getPosition().getY() + entite.getDimension().getHauteur() / 2) / hauteurTuile;

        double milieuX = milieu.getLargeur();
        double milieuY = milieu.getHauteur();

        double positionActuelleX;
        double positionActuelleY;


        if(positionEntiteX < milieu.getLargeur()) {
            positionActuelleX = 0;
        }
        else if(positionEntiteX > (largeurCarte - milieuX)){
            positionActuelleX = largeurCarte - zoneObservable.getLargeur();
        }
        else{
            positionActuelleX = positionEntiteX - milieuX;
        }
        if(positionEntiteY < milieuY){
            positionActuelleY=0;
        }
        else if(positionEntiteY > (longueurCarte - milieuY)){
            positionActuelleY = longueurCarte - zoneObservable.getHauteur();
        }
        else{
            positionActuelleY = positionEntiteY - milieuY;
        }
        position = new Position(positionActuelleX,positionActuelleY);
        actualisation();


    }

    public void actualisation() {
        double hauteurZone = zoneObservable.getHauteur();
        double largeurZone = zoneObservable.getLargeur();

        double positionX = position.getX();
        double positionY = position.getY();

        for(int x = 0;x < hauteurZone; x++){
            for(int y = 0; y < largeurZone; y++){
                //zoneVisible[x][y] = //récupérer la tuile? après décalage  (x + positionX, y + positionY);
            }
        }
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public Tuile[][] getZoneVisible() {
        return zoneVisible;
    }

    public void setZoneVisible(Tuile[][] zoneVisible) {
        this.zoneVisible = zoneVisible;
    }

}
