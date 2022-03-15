package com.mauja.maujaadventures.monde;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.logique.Dimension;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Carte {
    private final String nom;
    private Dimension dimensionCarte;
    private Dimension dimensionTuiles;
    private List<JeuDeTuiles> lesJeuxDeTuiles;
    private List<Tuile> lesTuiles;
    private Tuile[][][] laCarte;
    private CopyOnWriteArrayList<ElementInteractif> lesElementsInteractifs;

    /**
     * Constructeur de la classe Carte
     *
     * @param nom nom Nom de la carte
     * @param dimensionCarte Longueur et Hauteur de la carte
     * @param laCarte Liste des calques appartenant à la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */

    public Carte(String nom, Dimension dimensionCarte, Tuile[][][] laCarte, List<JeuDeTuiles> lesJeuxDeTuiles,
                 List<ElementInteractif> lesElementsInteractifs) throws IllegalArgumentException {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la carte passé en argument ne peut pas être null ou vide.");
        }
        if (lesJeuxDeTuiles == null || lesJeuxDeTuiles.isEmpty()) {
            throw new IllegalArgumentException("Les jeux de tuiles utilisés par la carte ne peuvent pas être null.");
        }
        verificationDimensionsTuiles(lesJeuxDeTuiles);
        verificationDimensionsTuiles(laCarte, dimensionCarte);

        this.nom = nom;
        this.dimensionCarte = dimensionCarte;
        this.lesJeuxDeTuiles = lesJeuxDeTuiles;
        this.laCarte = laCarte;
        this.lesElementsInteractifs = lesElementsInteractifs == null ? new CopyOnWriteArrayList<>()
                : new CopyOnWriteArrayList<>(lesElementsInteractifs);

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

    public Tuile[][][] getLaCarte() {
        return Arrays.copyOf(laCarte, laCarte.length);
    }

    public List<ElementInteractif> getLesElementsInteractifs() {
        return Collections.unmodifiableList(lesElementsInteractifs);
    }

    public List<JeuDeTuiles> getLesJeuxDeTuiles() {
        return Collections.unmodifiableList(lesJeuxDeTuiles);
    }

    public List<Tuile> getLesTuiles() {
        return Collections.unmodifiableList(lesTuiles);
    }

    public Tuile getTuile(int x, int y, int k) {
        return laCarte[k][y][x];
    }

    public void ajouterElementInteractif(ElementInteractif elementInteractif) {
        if (elementInteractif != null) {
            lesElementsInteractifs.add(elementInteractif);
        }
    }

    public void ajouterElementsInteractifs(List<ElementInteractif> elementsInteractifs) {
        for (ElementInteractif elementInteractif : elementsInteractifs) {
            ajouterElementInteractif(elementInteractif);
        }
    }

    public void supprimerElementInteractif(ElementInteractif elementInteractif) {
        lesElementsInteractifs.remove(elementInteractif);
    }

    public boolean contientElementInteractif(ElementInteractif elementInteractif) {
        return lesElementsInteractifs.contains(elementInteractif);
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
        chaine.append("\nVoici les calques qui composent cette carte :");

        for (int k = 0; k < laCarte.length; k++) {
            chaine.append("\n\nCalque ").append(k + 1).append(" : ");
            for (int y = 0; y < laCarte[k].length; y++) {
                for (int x = 0; x < laCarte[k][y].length; x++) {
                    chaine.append(laCarte[k][y][x].getId()).append(" ");
                }
                chaine.append("\n");
            }
        }

        chaine.append("\n\nVoici les ").append(lesElementsInteractifs.size()).append(" entités qui composent cette carte : \n");
        for (ElementInteractif elementInteractif : lesElementsInteractifs) {
            chaine.append("\n-").append(elementInteractif);
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

    private void verificationDimensionsTuiles(Tuile[][][] grosTableauTuile, Dimension dimensionCarte)
            throws IllegalArgumentException {
        if (grosTableauTuile.length == 0 || grosTableauTuile[0].length == 0
                || grosTableauTuile[0].length < dimensionCarte.getHauteur()
                || grosTableauTuile[0][0].length < dimensionCarte.getLargeur()) {
            throw new IllegalArgumentException("Dimension incompatibles entre le tableau de tuiles et la dimension "
                    + "donnée (" + dimensionCarte + ").");
        }

        for (Tuile[][] moyenTableauTuile : grosTableauTuile) {
            for (Tuile[] petitTableauTuile : moyenTableauTuile) {
                for (Tuile tuile : petitTableauTuile) {
                    if (tuile == null) {
                        throw new IllegalArgumentException("Une tuile du tableau passée en paramètre est nulle, "
                                + "impossible de créer la carte");
                    }
                }
            }
        }
    }

    private void recuperationTuiles(List<JeuDeTuiles> lesJeuxDeTuiles) {
        for (JeuDeTuiles jeuDeTuiles : lesJeuxDeTuiles) {
            lesTuiles.addAll(jeuDeTuiles.getListeDeTuiles());
        }
    }
}