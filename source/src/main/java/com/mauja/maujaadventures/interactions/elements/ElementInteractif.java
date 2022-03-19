package com.mauja.maujaadventures.interactions.elements;

import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.interactions.actions.Action;
import com.mauja.maujaadventures.interactions.conditions.Condition;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.MementoPosition;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class ElementInteractif extends Balise implements Cloneable {
    protected Position position;
    protected Rectangle collision;
    protected Dimension dimension;
    private MementoPosition dernierePosition;
    private Map<Condition, List<Action>> mapConditionAction;

    private Condition derCondition;

    public ElementInteractif(@Param(nom = "position", classe = Position.class) Position position,
                             @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                             @Param(nom = "dimension", classe = Dimension.class) Dimension dimension) {
        verificationParametre(position, "position");
        verificationParametre(dimension, "dimension");
        this.position = position;
        this.collision = collision;
        this.dimension = dimension;
    }

    public void installerMemento(MementoPosition mementoPosition) {
        dernierePosition = new MementoPosition(position);
        setPosition(mementoPosition.getPosition());
    }

    public MementoPosition creerMemento() {
        return new MementoPosition(position);
    }

    public void restorerMemento() {
        setPosition(dernierePosition.getPosition());
    }

    public Position getPosition() {
        return position;
    }

    private void setPosition(Position position) {
        this.position = position;
        Platform.runLater(() ->notifier(this));
    }

    public Rectangle getCollision() {
        return collision;
    }

    public void setCollision(Rectangle collision) {
        this.collision = collision;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setMapConditionAction(Map<Condition, List<Action>> mapConditionAction) {
        this.mapConditionAction = mapConditionAction;
    }

    public Map<Condition, List<Action>> getMapConditionAction() {
        return mapConditionAction;
    }

    /**
     * Mise à jour des classes héritant de ElementInteractif
     */
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
                && elementInteractif.getCollision().equals(collision)
                && elementInteractif.getDimension().equals(dimension);
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
        return Objects.hash(position, collision, dimension);
    }

    @Override
    public String toString() {
        return "[" + this.getClass() + "] : " + position
                + "\nCollision : " + collision.toString()
                + "\nDimension : " + dimension
                + "\nConditions : " + mapConditionAction
                + "\nDernière condition : " + derCondition;
    }

    @Override
    public ElementInteractif clone() {
        try {
            return (ElementInteractif) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
