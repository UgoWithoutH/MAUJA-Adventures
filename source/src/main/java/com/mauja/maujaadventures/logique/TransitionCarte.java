package com.mauja.maujaadventures.logique;

import java.util.Objects;

public class TransitionCarte {
    private String nomCarte;
    private Position position;
    private Rectangle collision;

    public TransitionCarte(String nom, Position position, Rectangle collision) throws IllegalArgumentException {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la carte passé en paramètre ne peut pas être null.");
        }
        if (position == null) {
            throw new IllegalArgumentException("La position passée en paramètre ne peut pas être nulle.");
        }
        if (collision == null) {
            throw new IllegalArgumentException("La collision passée en paramètre ne peut pas être nulle.");
        }
        nomCarte = nom;
        this.position = position;
        this.collision = collision;
    }

    public String getNomCarte() {
        return nomCarte;
    }

    public Position getPosition() {
        return position;
    }

    public Rectangle getCollision() {
        return collision;
    }

    public boolean equals(TransitionCarte transitionCarte) {
        return (transitionCarte.getNomCarte().equals(nomCarte))
                && (transitionCarte.getPosition().equals(position));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransitionCarte transitionCarte = (TransitionCarte) o;
        return equals(transitionCarte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomCarte, position);
    }

    @Override
    public String toString() {
        return "[" + nomCarte + " => " + position + " (" + collision + ")]";
    }
}
