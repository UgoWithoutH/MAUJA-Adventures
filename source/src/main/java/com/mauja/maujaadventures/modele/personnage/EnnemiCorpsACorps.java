package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.Rectangle;

public class EnnemiCorpsACorps extends Ennemi{


    public EnnemiCorpsACorps(Position position, Rectangle rectangle, int attaque, int vie) {
        super(position, rectangle, attaque, vie);
    }

    public EnnemiCorpsACorps(Position position, Rectangle rectangle, Dimension dimension, Velocite velocite, int attaque, int vie) {
        super(position, rectangle, dimension, velocite, attaque, vie);
    }

    @Override
    public boolean attaquer(Personnage personnage) {
        return super.attaquer(personnage);
    }
}
