package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.Rectangle;

public abstract class Ennemi extends Entite {

    private int attaque;

    public Ennemi(Position position, Rectangle rectangle, int attaque, int vie) {
        super(position, rectangle, vie);
        this.attaque = attaque;
    }

    public Ennemi(Position position, Rectangle rectangle, Dimension dimension, Velocite velocite, int attaque, int vie) {
        super(position, rectangle, dimension, velocite, vie);
        this.attaque = attaque;
    }

    public int getAttaque() {
        return attaque;
    }

    private void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public boolean attaquer(Personnage personnage){
        personnage.setVie(personnage.getVie() - attaque);
        return personnage.getVie() < 0;
    }
}
