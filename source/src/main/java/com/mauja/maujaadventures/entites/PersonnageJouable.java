package com.mauja.maujaadventures.entites;


import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.logique.*;

public class PersonnageJouable extends Vivant {
    private EtatAction etatAction;

    public PersonnageJouable(@Param(nom = "position", classe = Position.class) Position position,
                             @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                             @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                             @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                             @Param(nom = "attaque", classe = Attaque.class) Attaque attaque,
                             @Param(nom = "pv", classe = int.class) int pointsDeVie) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque, pointsDeVie);
        etatAction = EtatAction.SANS_ACTION;
    }

    public PersonnageJouable(@Param(nom = "position", classe = Position.class) Position position,
                             @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                             @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                             @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                             @Param(nom = "attaque", classe = Attaque.class) Attaque attaque) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque);
        etatAction = EtatAction.SANS_ACTION;
    }

    public EtatAction getEtatAction() {
        return etatAction;
    }

    public void setEtatAction(EtatAction etatAction) {
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
