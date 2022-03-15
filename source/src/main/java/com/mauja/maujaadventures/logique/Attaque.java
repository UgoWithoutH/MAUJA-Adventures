package com.mauja.maujaadventures.logique;

import java.util.Objects;

public class Attaque {
    private static final int DEGATS_PAR_DEFAUT = 3;
    private final float dureeOriginelle;
    private Rectangle collision;
    private int degats;
    private float duree;

    /**
     * Constructeur de l'attaque
     * @param degats nombre de dégât que va infliger l'attaque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Attaque(Rectangle collision, float duree, int degats) {
        this.collision = collision;
        if (degats < 0) {
            degats = DEGATS_PAR_DEFAUT;
        }
        this.degats = degats;
        this.duree = duree;
        dureeOriginelle = duree;
    }

    /**
     * Constructeur de l'attaque
     * @param collision collision de l'attaque
     * @param duree entier correspondant à la durée d'une attaque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Attaque(Rectangle collision, float duree) {
        this(collision, duree, DEGATS_PAR_DEFAUT);
    }

    /**
     * Récupération du rectangle de collision
     * @return collision de l'attaque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Rectangle getCollision() {
        return collision;
    }

    /**
     * Modification de la collision
     * @param collision nouvelle collision de l'attaque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setCollision(Rectangle collision) {
        this.collision = collision;
    }

    /**
     * Récupération du nombre de dégât d'une attaque
     * @return nombre de dégâts de l'attaque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getDegats() {
        return degats;
    }

    /**
     * Récupération de la durée de l'attaque
     * @return durée de l'attaque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public float getDuree() {
        return duree;
    }

    /**
     * Récupération de la durée originelle de l'attaque
     * @return Durée originelle de l'attaque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public float getDureeOriginelle() {
        return dureeOriginelle;
    }

    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de Attaque
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
