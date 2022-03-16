package com.mauja.maujaadventures.logique;

import java.util.Objects;

public class Velocite {
    private double x;
    private double y;
    public static final double VELOCITE_PAR_DEFAUT = 3;

    /**
     * Constructeur de la vélocité
     * @param x Valeur de la modification en x
     * @param y Valeur de la modification en y
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Velocite(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur de la vélocité
     * Met valeur par défaut à la vitesse
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Velocite() {
        this(VELOCITE_PAR_DEFAUT, VELOCITE_PAR_DEFAUT);
    }

    /**
     * Récupération de la valeur de X
     * @return valeur de X
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getX() {
        return x;
    }

    private void setX(double x) {
        this.x = x;
    }

    /**
     * Récupération de la valeur de Y
     * @return valeur de Y
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getY() {
        return y;
    }

    private void setY(double y) {
        this.y = y;
    }
    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de Velocité
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Velocite velocite = (Velocite) obj;
        return equals(velocite);
    }
    /**
     * Méthode equals
     * @param velocite Velocite que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Velocite velocite) {
        return x == velocite.getX()
                && y == velocite.getY();
    }
    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "[" + x + "v, " + y + "]";
    }
}
