package com.mauja.maujaadventures.modele.entites;

import com.mauja.maujaadventures.modele.logique.Dimension;
import com.mauja.maujaadventures.modele.logique.Position;
import com.mauja.maujaadventures.modele.logique.Rectangle;
import com.mauja.maujaadventures.modele.logique.Velocite;

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
