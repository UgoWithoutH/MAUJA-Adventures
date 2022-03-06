package com.mauja.maujaadventures.monde;

import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Dimension;

import java.util.*;

public class Carte {
    private final String nom;
    private Dimension dimensionCarte;
    private Dimension dimensionTuiles;
    private List<JeuDeTuiles> lesJeuxDeTuiles;
    private List<Tuile> lesTuiles;
    private List<Calque> listeDeCalques;
    private List<Entite> lesEntites;

    /**
     * Constructeur de la classe Carte
     *
     * @param nom nom Nom de la carte
     * @param dimensionCarte Longueur et Hauteur de la carte
     * @param lesCalques Liste des calques appartenant à la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */

    public Carte(String nom, Dimension dimensionCarte, List<Calque> lesCalques, List<JeuDeTuiles> lesJeuxDeTuiles,
                 List<Entite> lesEntites) throws IllegalArgumentException {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la carte passé en argument ne peut pas être null ou vide.");
        }
        if (lesCalques == null || lesCalques.isEmpty()) {
            throw new IllegalArgumentException("La carte doit obligatoirement être composée de calques");
        }
        if (lesJeuxDeTuiles == null || lesJeuxDeTuiles.isEmpty()) {
            throw new IllegalArgumentException("Les jeux de tuiles utilisés par la carte ne peuvent pas être null.");
        }
        verificationDimensionsTuiles(lesJeuxDeTuiles);

        this.nom = nom;
        this.dimensionCarte = dimensionCarte;
        this.lesJeuxDeTuiles = lesJeuxDeTuiles;
        listeDeCalques = lesCalques;
        this.lesEntites = lesEntites == null ? new ArrayList<>() : lesEntites;

        lesTuiles = new ArrayList<>();
        recuperationTuiles(lesJeuxDeTuiles);
    }

    /**
     * Getter du nom de la Carte
     *
     * @return Renvoie le nom de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public String getNom() {
        return nom;
    }

    public Dimension getDimensionTuiles() {
        return dimensionTuiles;
    }

    public Dimension getDimensionCarte() {
        return dimensionCarte;
    }

    /**
     * Getter de la liste de calques
     *
     * @return La liste des calques
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public List<Calque> getListeDeCalques() {
        return Collections.unmodifiableList(listeDeCalques);
    }

    public List<Entite> getLesEntites() {
        return Collections.unmodifiableList(lesEntites);
    }

    public List<JeuDeTuiles> getLesJeuxDeTuiles() {
        return Collections.unmodifiableList(lesJeuxDeTuiles);
    }

    public List<Tuile> getLesTuiles() {
        return Collections.unmodifiableList(lesTuiles);
    }

    public void ajouterEntite(Entite entite) {
        if (entite != null) {
            lesEntites.add(entite);
        }
    }

    public void supprimerEntite(Entite entite) {
        lesEntites.remove(entite);
    }

    /**
     * Ajouter un nouveau calque
     * @param c Calque que l'on veut rajouter
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void ajouterCalque(Calque c) {
        this.listeDeCalques.add(c);
        lesTuiles.addAll(c.getListeDeTuiles());
    }

    /**
     * Redéfinition du Hash Code permet d'avoir une valeurs unique par Carte
     * @return entier de l'Hachage des attributs de Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    /**
     * Redéfinition du Equals
     * @param obj Objet permettant de faire l'égalité
     * @return Un booléen pour savoir si égalité ou non
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Carte autre = (Carte) obj;
        return equals(autre);
    }

    /**
     * Nouvelle fonction equals
     * @param c Correspond à la carte
     * @return Le résultat de l'égalité
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */

    public boolean equals(Carte c) {
        return c.getNom().equals(nom);
    }

    /**
     * Redéfinition du ToString
     * @return la chaîne de caractère que l'on veut afficher dans la console
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder();
        chaine.append("Carte ").append(nom).append(" de dimensions ").append(dimensionCarte).append(" :");
        chaine.append("\nComposée de ").append(lesJeuxDeTuiles.size()).append(" jeux de tuiles : ");
        chaine.append(lesJeuxDeTuiles);
        chaine.append("\nCe qui correspond à ").append(lesTuiles.size()).append(" tuiles différentes");
        chaine.append("\nVoici les ").append(listeDeCalques.size()).append(" calques qui composent cette carte : \n");
        for (Calque calque : listeDeCalques) {
            chaine.append(calque).append("\n\n");
        }
        chaine.append("\nVoici les ").append(lesEntites.size()).append(" entités qui composent cette carte : \n");
        for (Entite entite : lesEntites) {
            chaine.append("\n-").append(entite);
        }

        return chaine.toString();
    }

    private void verificationDimensionsTuiles(List<JeuDeTuiles> lesJeuxDeTuiles) throws IllegalArgumentException {
        dimensionTuiles = lesJeuxDeTuiles.get(0).getDimensionTuiles();

        for (JeuDeTuiles jeuDeTuiles : lesJeuxDeTuiles) {
            if (!jeuDeTuiles.getDimensionTuiles().equals(dimensionTuiles)) {
                throw new IllegalArgumentException("La dimension du jeu de tuiles " + jeuDeTuiles
                        + " n'est pas la même que la dimension d'un autre jeu de tuiles " + dimensionTuiles);
            }
        }
    }

    private void recuperationTuiles(List<JeuDeTuiles> lesJeuxDeTuiles) {
        for (JeuDeTuiles jeuDeTuiles : lesJeuxDeTuiles) {
            lesTuiles.addAll(jeuDeTuiles.getListeDeTuiles());
        }
    }
}