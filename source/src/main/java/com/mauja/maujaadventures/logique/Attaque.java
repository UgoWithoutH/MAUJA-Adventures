package com.mauja.maujaadventures.logique;

import java.util.Objects;

public class Attaque {
    private static final int DEGATS_PAR_DEFAUT = 3;
    private final float dureeOriginelle;
    private Rectangle collision;
    private int degats;
    private float duree;

    public Attaque(Rectangle collision, float duree, int degats) {
        this.collision = collision;
        if (degats < 0) {
            degats = DEGATS_PAR_DEFAUT;
        }
        this.degats = degats;
        this.duree = duree;
        dureeOriginelle = duree;
    }

    public Attaque(Rectangle collision, float duree) {
        this(collision, duree, DEGATS_PAR_DEFAUT);
    }

    public Rectangle getCollision() {
        return collision;
    }

    public void setCollision(Rectangle collision) {
        this.collision = collision;
    }

    public int getDegats() {
        return degats;
    }

    private void setDegats(int degats) {
        this.degats = degats;
    }

    public float getDuree() {
        return duree;
    }

    private void setDuree(float duree) {
        this.duree = duree;
    }

    public float getDureeOriginelle() {
        return dureeOriginelle;
    }

    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de dimension
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return 7 * (collision.hashCode() + Objects.hash(duree, degats));
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
        Attaque attaque = (Attaque) obj;
        return equals(attaque);
    }

    /**
     * Méthode equals
     * @param attaque Dimension que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Attaque attaque) {
        return collision.equals(attaque.getCollision())
                && degats == attaque.getDegats()
                && duree == attaque.getDuree();
    }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return collision + " : "
                + duree + "ns "
                + degats + "\u2665 degats";
    }
}
