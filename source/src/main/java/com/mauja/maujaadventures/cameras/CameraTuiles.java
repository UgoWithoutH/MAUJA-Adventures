package com.mauja.maujaadventures.cameras;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.Tuile;
import jdk.jshell.spi.ExecutionControl;

public class CameraTuiles extends Camera {
    protected Carte carteCourante;
    protected Tuile[][][] zoneVisible;

    protected double largeurCarte;
    protected double hauteurCarte;
    protected double largeurTuile;
    protected double hauteurTuile;
    protected int nombreCalques;

    public CameraTuiles(Carte carte, Dimension zoneObservable) {
        super(zoneObservable);
        if (carte == null) {
            throw new IllegalArgumentException("La carte donnée à la caméra est nulle.");
        }
        if (carte.getDimensionCarte().getLargeur() < zoneObservable.getLargeur()
                || carte.getDimensionCarte().getHauteur() < zoneObservable.getHauteur()) {
            throw new IllegalArgumentException("La zone visuelle de la caméra (" + zoneObservable + ") ne peut pas "
                    + "être plus grande que les dimensions de la carte : " + carte.getDimensionCarte());
        }

        nombreCalques = carte.getLaCarte().length;
        zoneVisible = new Tuile[nombreCalques]
                [(int) zoneObservable.getHauteur()][(int) zoneObservable.getLargeur()];
        changeCarte(carte);
    }


    @Override
    public void centrerSurEntite(Entite entite) {
        ///double positionEntiteX = ((entite.getPosition().getX() + entite.getDimension().getLargeur() / 2) / largeurTuile);
        //double positionEntiteY = ((entite.getPosition().getY() + entite.getDimension().getHauteur() / 2) / hauteurTuile);

        int positionEntiteX = (int) ((entite.getPosition().getX() + entite.getDimension().getLargeur() / 2) / largeurTuile);
        int positionEntiteY = (int) ((entite.getPosition().getY() + entite.getDimension().getHauteur() / 2) / hauteurTuile);


        double decalageX = ((entite.getPosition().getX() + entite.getDimension().getLargeur() / 2) % largeurTuile);
        double decalageY = ((entite.getPosition().getY() + entite.getDimension().getHauteur() / 2) % hauteurTuile);

        double milieuEcranX = milieu.getLargeur();
        double milieuEcranY = milieu.getHauteur();
        double nouvellePositionX;
        double nouvellePositionY;

        if (positionEntiteX < milieu.getLargeur()) {
            nouvellePositionX = 0;
            decalageX = 0;
        }
        else if (positionEntiteX > (largeurCarte - milieuEcranX)) {
            nouvellePositionX = largeurCarte - zoneObservable.getLargeur();
            decalageX = largeurTuile;
        }
        else {
            nouvellePositionX = positionEntiteX - milieuEcranX;
        }

        if (positionEntiteY < milieuEcranY) {
            nouvellePositionY = 0;
            decalageY = 0;
        }
        else if (positionEntiteY > (hauteurCarte - milieuEcranY)) {
            nouvellePositionY = hauteurCarte - zoneObservable.getHauteur();
            decalageY = hauteurTuile;
        }
        else {
            nouvellePositionY = positionEntiteY - milieuEcranY;
        }

        position = new Position(nouvellePositionX, nouvellePositionY);
        decalageRelatif = new Dimension(decalageX, decalageY);
        actualisation();
    }

    @Override
    public void decalage(Direction direction) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Le système de décalage de la caméra 2D n'est pas implémenté.");
    }

    public void actualisation() throws IndexOutOfBoundsException {
        double largeurCamera = zoneObservable.getLargeur();
        double hauteurCamera = zoneObservable.getHauteur();

        int positionX = (int) position.getX();
        int positionY = (int) position.getY();


        for (int k = 0; k < nombreCalques; k++) {
            for (int y = 0; y < hauteurCamera; y++) {
                for (int x = 0; x < largeurCamera; x++) {
                    zoneVisible[k][y][x] = carteCourante.getTuile(x + positionX, y + positionY, k);
                }
            }
        }
    }

    public void changeCarte(Carte carte) throws IllegalArgumentException {
        if (carte == null || carte.getDimensionCarte().getLargeur() == 0) {
            throw new IllegalArgumentException("La carte passée en paramètre de la caméra ne peut pas être nulle ou vide.");
        }
        carteCourante = carte;

        largeurCarte = carteCourante.getDimensionCarte().getLargeur();
        hauteurCarte = carteCourante.getDimensionCarte().getHauteur();
        largeurTuile = carte.getDimensionTuiles().getLargeur();
        hauteurTuile = carte.getDimensionTuiles().getHauteur();
        actualisation();
    }

    public Carte getCarte() {
        return carteCourante;
    }

    public Tuile[][][] getZoneVisible() {
        return zoneVisible;
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder(super.toString());
        chaine.append("\nCarte courante : ");
        //chaine.append(carteCourante.toString());
        chaine.append("\nVision : \n");

        double largeurCamera = zoneObservable.getLargeur();
        double hauteurCamera = zoneObservable.getHauteur();
        for(int k = 0; k < nombreCalques; k++) {
        for (int x = 0; x < hauteurCamera; x++) {
            for (int y = 0; y < largeurCamera; y++) {
                chaine.append(zoneVisible[k][x][y].getId());
                chaine.append(" ");
            }
            chaine.append("\n");
            }
            chaine.append("\n");
        }

        return chaine.toString();
    }

}
