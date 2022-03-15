package com.mauja.maujaadventures.entites;

import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.deplaceurs.DeplaceurDeDestructible;
import com.mauja.maujaadventures.interactions.ElementInteractif;
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

    /**
     * Constructeur des annotations du destructible
     * @param position position x et y du destructible
     * @param dimension hauteur et largeur du destructible
     * @param collision collision du destructible
     * @param velocite vitesse du destructible
     * @param degats nombre de dégâts du destructible
     * @param deplaceurDeDestructible classe de destruction du destructible
     * @throws IllegalArgumentException Argument non voulu
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Destructible(@Param(nom = "position", classe = Position.class) Position position,
                        @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                        @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                        @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                        @Param(nom = "degats", classe = int.class) int degats,
                        DeplaceurDeDestructible deplaceurDeDestructible) throws IllegalArgumentException {
        this(position, dimension, collision, velocite, degats, deplaceurDeDestructible, null);
    }

    /**
     * Constructeur du destructible
     * @param position position x et y du destructible
     * @param dimension hauteur et largeur du destructible
     * @param collision collision du destructible
     * @param velocite vitesse du destructible
     * @param degats nombre de dégâts du destructible
     * @param deplaceurDeDestructible classe de destruction du destructible
     * @param emetteur Element Interactif que émet le destructible
     * @throws IllegalArgumentException Argument non voulu
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
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

    /**
     * Récupération des dégâts
     * @return nombre de dégât réalisé par le destructible
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getDegats() {
        return degats;
    }

    /**
     * Récupération de l'emmeteur du destrucible
     * @return Personnage ayant envoyé le projectile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public ElementInteractif getEmetteur() {
        return emetteur;
    }

    /**
     * Mise A jour de l'instance du destructible
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void miseAJour() {
        GestionnaireInteractions.getInstance().ajouter(
                new EvenementDeplacement(this, direction, deplaceurDeDestructible));
    }

    /**
     * Table de hachage
     * @return entier correspondant à la table de hachage de l'ennemi et ses attributs
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(degats);
    }

    /**
     * Redéfinition de la comparaison de l'ennemi avec un autre objet
     * @param obj Objet à comparer
     * @return true si l'ennemi est égal a l'objet sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Destructible destructible = (Destructible) obj;
        return equals(destructible);
    }
    /**
     * Comparaison du destructible
     * @param destructible destructible que l'on souhaite comparée
     * @return true si égal sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Destructible destructible) {
        return super.equals(destructible)
                && degats == destructible.getDegats();
    }
    /**
     * Redéfinition de l'affichage console du destructible
     * @return chaîne de caractère de son affichage
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nDegats : " + degats + "\u2665";
    }
}
