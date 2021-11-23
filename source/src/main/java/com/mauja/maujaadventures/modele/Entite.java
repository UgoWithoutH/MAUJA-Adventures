package com.mauja.maujaadventures.modele;


public abstract class Entite  extends Affichable{

    private Position position;
    private Collision collision;
    private Dimension dimensions;

    public Entite(Position position, String image, Collision collision){
        super(image);
        this.position = position;
        this.collision = collision;
        //this.dimensions = new Dimension((int) image.getLongueur(), (int) image.getHauteur());
        this.dimensions= new Dimension(32, 32);
    }

    public Position getPosition() { return position; }

    public void setPosition(Position position) { this.position = position; }

    public Collision getCollision() { return collision; }

    public void setCollision(Collision collision) { this.collision = collision; }

    public Dimension getDimensions() { return dimensions;}

    public void setDimensions(Dimension dimensions) { this.dimensions = dimensions; }
}
