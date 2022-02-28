package vues.codebehind;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.Tuile;
import jdk.jshell.spi.ExecutionControl;

public class CameraTuiles extends Camera {
    protected Carte carteCourante;
    protected Tuile[][] zoneVisible;


    protected double largeurCarte = 32;
    protected double hauteurCarte = 32;
    protected double largeurTuile;
    protected double hauteurTuile;

    public CameraTuiles(Carte carte, Dimension zoneObservable) {
        super(zoneObservable);
        if (carte == null) {
            throw new IllegalArgumentException("La carte donnée à la caméra est nulle.");
        }
        if (carte.getDimension().getLargeur() < zoneObservable.getLargeur()
                || carte.getDimension().getHauteur() < zoneObservable.getHauteur()) {
            throw new IllegalArgumentException("La zone visuelle de la caméra (" + zoneObservable + ") ne peut pas "
                    + "être plus grande que les dimensions de la carte : " + carte.getDimension());
        }
        zoneVisible = new Tuile[(int) zoneObservable.getHauteur()][(int) zoneObservable.getLargeur()];
        changeCarte(carte);    }


    @Override
    public void centrerSurEntite(Entite entite) {
        int positionEntiteX = (int) ((entite.getPosition().getX() + entite.getDimension().getLargeur() / 2) / largeurTuile);
        int positionEntiteY = (int) ((entite.getPosition().getY() + entite.getDimension().getHauteur() / 2) / hauteurTuile);

        double decalageX = (int) ((entite.getPosition().getX() + entite.getDimension().getLargeur() / 2) % largeurTuile);
        double decalageY = (int) ((entite.getPosition().getY() + entite.getDimension().getHauteur() / 2) % hauteurTuile);

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

        for (int x = 0; x < hauteurCamera; x++) {
            for (int y = 0; y < largeurCamera; y++) {
                zoneVisible[x][y] = carteCourante.getListeDeCalques().get(0).getTuile(x + positionX, y + positionY);
            }
        }
    }

    /**
     * Methode permettant de changer la carte actuelle
     * @param carte
     * @throws IllegalArgumentException
     */
    public void changeCarte(Carte carte) throws IllegalArgumentException {
        if (carte == null || carte.getDimension().getLargeur() == 0) {
            throw new IllegalArgumentException("La carte passée en paramètre de la caméra ne peut pas être nulle ou vide.");
        }
        carteCourante = carte;

        largeurCarte = carteCourante.getDimension().getLargeur();
        hauteurCarte = carteCourante.getDimension().getHauteur();
        actualisation();
    }

    public Carte getCarte() {
        return carteCourante;
    }

    public void setCarte(Carte carte) {
        this.carteCourante = carte;
    }

    public Tuile[][] getZoneVisible() {
        return zoneVisible;
    }

    public void setZoneVisible(Tuile[][] zoneVisible) {
        this.zoneVisible = zoneVisible;
    }

}
