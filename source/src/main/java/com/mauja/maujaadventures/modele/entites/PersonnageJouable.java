package com.mauja.maujaadventures.modele.entites;

import com.mauja.maujaadventures.modele.logique.*;

public class PersonnageJouable extends Vivant {
    private EtatAction etatAction;

    public PersonnageJouable(Position position, Dimension dimension, Rectangle collision, Velocite velocite,
                  Attaque attaque, int pointsDeVie) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque, pointsDeVie);
        etatAction = EtatAction.SANS_ACTION;
    }

    public PersonnageJouable(Position position, Dimension dimension, Rectangle collision, Velocite velocite,
                             Attaque attaque) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque);
        etatAction = EtatAction.SANS_ACTION;
    }

    public EtatAction getEtatAction() {
        return etatAction;
    }

    private void setEtatAction(EtatAction etatAction) {
        this.etatAction = etatAction;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 7 * etatAction.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        PersonnageJouable personnage = (PersonnageJouable) obj;
        return equals(personnage);
    }

    public boolean equals(PersonnageJouable personnage) {
        return super.equals(personnage)
                && etatAction.equals(personnage.getEtatAction());
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nEtat d'action : " + etatAction;
    }
}
