package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ImageSource;
import javafx.scene.image.Image;

public abstract class Entite {

    private Position position;
    private ImageSource image;
    private Collision collision;
    private Dimension dimensions;

    public Entite(Position position, ImageSource image, Collision collision){
        this.position = position;
        this.image = image;
        this.collision = collision;
        this.dimensions = new Dimension((int) image.getLongueur(), (int) image.getHauteur());
    }

    public Position getPosition() { return position; }

    public void setPosition(Position position) { this.position = position; }

    public ImageSource getImage() { return image; }

    protected void setImage(ImageSource image) { this.image = image; }

    public Collision getCollision() { return collision; }

    public void setCollision(Collision collision) { this.collision = collision; }

    public Dimension getDimensions() { return dimensions;}

    public void setDimensions(Dimension dimensions) { this.dimensions = dimensions; }
}
