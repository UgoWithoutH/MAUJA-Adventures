package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.interactions.evenements.EvenementDeplacement;
import com.mauja.maujaadventures.logique.MementoPosition;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class ElementInteractif extends Balise implements Cloneable{
    private Position position;
    private Rectangle collision;
    private MementoPosition dernierePosition;
    private Map<Condition, List<Action>> mapConditionAction;

    private Condition derCondition;
    //public abstract void init();

    //public abstract void initialisation();

    //public abstract void interagit();

    public ElementInteractif(@Param(nom = "position", classe = Position.class) Position position,
                             @Param(nom = "collision", classe = Rectangle.class) Rectangle collision) {
        verificationParametre(position, "position");
        this.position = position;
        this.collision = collision;
    }

    public void installerMemento(MementoPosition mementoPosition) {
        dernierePosition = new MementoPosition(position);
        this.position = mementoPosition.getPosition();
    }

    public MementoPosition creerMemento() {
        return new MementoPosition(position);
    }

    public void restorerMemento() {
        position = dernierePosition.getPosition();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Rectangle getCollision() {
        return collision;
    }

    public void setCollision(Rectangle collision) {
        this.collision = collision;
    }

    public void setMapConditionAction(Map<Condition, List<Action>> mapConditionAction) {
        this.mapConditionAction = mapConditionAction;
    }

    public Map<Condition, List<Action>> getMapConditionAction() {
        return mapConditionAction;
    }

    public abstract void miseAJour();

    @Override
    public void ajouter(Balise balise) {
        if (balise instanceof Condition condition){
            derCondition = condition;
            ajouterCondition(derCondition);
        }
        else {
            ajouterAction((Action)balise);
        }
    }

    public void ajouterCondition(Condition condition){
        mapConditionAction.put(condition, new ArrayList<>());
    }

    public void ajouterAction(Action action){
        mapConditionAction.get(derCondition).add(action);
    }

    protected void verificationParametre(Object obj, String nom) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("Le paramètre " + nom + " donnée à l'entité lors de sa création "
                    + "ne peut pas être null.");
        }
    }

    public boolean equals(ElementInteractif elementInteractif) {
        return elementInteractif.getPosition().equals(position)
                && elementInteractif.getCollision().equals(collision);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementInteractif that = (ElementInteractif) o;
        return position.equals(that.position) && Objects.equals(collision, that.collision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, collision);
    }

    @Override
    public String toString() {
        return "[" + this.getClass() + "] : " + position
                + "\nCollision : " + collision.toString()
                + "Conditions : " + mapConditionAction
                + "Dernière condition : " + derCondition;
    }

    @Override
    public ElementInteractif clone() {
        try {
            ElementInteractif clone = (ElementInteractif) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
