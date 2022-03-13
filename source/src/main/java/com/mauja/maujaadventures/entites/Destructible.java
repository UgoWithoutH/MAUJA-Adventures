package com.mauja.maujaadventures.entites;

import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.deplaceurs.DeplaceurDeDestructible;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.interactions.evenements.EvenementDeplacement;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.logique.Velocite;

import java.util.Objects;

public class Destructible extends Entite {
    private DeplaceurDeDestructible deplaceurDeDestructible;
    private ElementInteractif emetteur;
    private int degats;

    public Destructible(@Param(nom = "position", classe = Position.class) Position position,
                        @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                        @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                        @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                        @Param(nom = "degats", classe = int.class) int degats,
                        DeplaceurDeDestructible deplaceurDeDestructible) throws IllegalArgumentException {
        this(position, dimension, collision, velocite, degats, deplaceurDeDestructible, null);
    }

    public Destructible(@Param(nom = "position", classe = Position.class) Position position,
                        @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                        @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                        @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                        @Param(nom = "degats", classe = int.class) int degats,
                        DeplaceurDeDestructible deplaceurDeDestructible,
                        ElementInteractif emetteur) throws IllegalArgumentException {
        super(position, dimension, collision, velocite);
        if (deplaceurDeDestructible == null) {
            throw new IllegalArgumentException("Le deplaceur de destructibles passé en paramètre ne peut pas être null.");
        }
        this.degats = degats < 0 ? 1 : degats;
        this.deplaceurDeDestructible = deplaceurDeDestructible;
        this.emetteur = emetteur;
    }

    public int getDegats() {
        return degats;
    }

    public ElementInteractif getEmetteur() {
        return emetteur;
    }

    @Override
    public void miseAJour() {
        GestionnaireInteractions.getInstance().ajouter(
                new EvenementDeplacement(this, direction, deplaceurDeDestructible));
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(degats);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Destructible destructible = (Destructible) obj;
        return equals(destructible);
    }

    public boolean equals(Destructible destructible) {
        return super.equals(destructible)
                && degats == destructible.getDegats();
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nDegats : " + degats + "\u2665";
    }
}
