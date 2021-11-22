package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ImageSource;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Caneva implements ContexteGraphique {

    private GraphicsContext gc;

    public Caneva(GraphicsContext gc) {
        if (gc == null) {
            throw new IllegalArgumentException("Le GraphicsContexte donné est null");
        }
        this.gc = gc;
    }

    @Override
    public void dessiner(ImageSource image, Position position, Dimension dimensions) {
        dessiner(image, position.getPositionX(), position.getPositionY(), dimensions.getLargeur(), dimensions.getHauteur());
    }

    @Override
    public void dessiner(ImageSource image, int positionX, int positionY, Dimension dimensions) {
        verificationDimension(dimensions);
        dessiner(image, positionX, positionY, dimensions.getLargeur(), dimensions.getHauteur());
    }

    @Override
    public void dessiner(ImageSource image, Position position, int largeur, int hauteur) {
        verificationPosition(position);
        dessiner(image, position.getPositionX(), position.getPositionY(), largeur, hauteur);
    }

    @Override
    public void dessiner(ImageSource image, int positionX, int positionY, int largeur, int hauteur) {
        verificationImage(image);
        gc.drawImage(image.getImage(), positionX, positionY, largeur, hauteur);
    }

    @Override
    public void effacer(Position position, Dimension dimensions) {
        verificationDimension(dimensions);
        verificationPosition(position);
        gc.clearRect(position.getPositionX(), position.getPositionY(), dimensions.getLargeur(), dimensions.getHauteur());
    }

    private void verificationImage(ImageSource image) {
        if (image == null) {
            throw new IllegalArgumentException("L'image passée en paramètre qui doit être affichée est nulle.");
        }
    }

    private void verificationPosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("La position passée en paramètre est nulle.");
        }
    }

    private void verificationDimension(Dimension dimensions) {
        if (dimensions == null) {
            throw new IllegalArgumentException("Les dimensions passées en paramètre sont nulles.");
        }
    }
}
