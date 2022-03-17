package com.mauja.maujaadventures.logique;

import java.util.Objects;

/**
 * Classe de transition entre 2 carte.
 * On vérifie que la position le nom et la collision sont valide et on déplace le joueur sur la 2ème carte
 */
public class TransitionCarte {
    private String nomCarte;
    private Position position;
    private Rectangle collision;

    /**
     * Constructeur de la transition de la Carte
     * @param nom Nom de la carte
     * @param position position x et y de la carte
     * @param collision collision de la carte
     * @throws IllegalArgumentException Argument null
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
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

    /**
     * Récupération du nom de la carte
     * @return chaîne de caractère du nom de la carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public String getNomCarte() {
        return nomCarte;
    }

    /**
     * Récupération de la position de la carte
     * @return X et Y concernant position de la carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Récupération de la collison
     * @return Rectangle de la collision
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Rectangle getCollision() {
        return collision;
    }
    /**
     * Méthode equals
     * @param transitionCarte Velocite que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(TransitionCarte transitionCarte) {
        return (transitionCarte.getNomCarte().equals(nomCarte))
                && (transitionCarte.getPosition().equals(position));
    }
    /**
     * Redéfinition du equals
     * @param o Objet que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransitionCarte transitionCarte = (TransitionCarte) o;
        return equals(transitionCarte);
    }
    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de Transition Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return Objects.hash(nomCarte, position);
    }
    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "[" + nomCarte + " => " + position + " (" + collision + ")]";
    }
}
