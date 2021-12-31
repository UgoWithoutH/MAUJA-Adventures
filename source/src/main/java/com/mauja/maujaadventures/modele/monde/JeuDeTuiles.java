package com.mauja.maujaadventures.modele.monde;


import com.mauja.maujaadventures.modele.logique.Dimension;

import java.util.ArrayList;
import java.util.List;

public class JeuDeTuiles {
    private int nombreTuiles;
    private Dimension dimension;
    private List<Tuile> listeDeTuiles;
    private final String identifiant;

    /**
     * Constructeur du jeu de tuiles
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public JeuDeTuiles(Dimension dimension, String identifiant, List<Tuile> lesTuiles){
        this.dimension = dimension;
        this.identifiant = identifiant;
        listeDeTuiles = new ArrayList<Tuile>();
        if (lesTuiles != null) {
            listeDeTuiles = lesTuiles;
            nombreTuiles = listeDeTuiles.size();
        }
    }

    /**
     * Getter du nombre de tuile
     * @return Le nombre de Tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getNombreTuiles() {
        return nombreTuiles;
    }


    public List<Tuile> getListeDeTuiles() {
        return listeDeTuiles;
    }


    public Dimension getDimension() {
        return dimension;
    }


    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Redéfinition du HashCode
     * @return Valeurs de l'Hachage des attributs de jeu de tuiles
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return nombreTuiles + 31 * dimension.hashCode()
                + 31 * listeDeTuiles.hashCode()+ 31 * identifiant.hashCode();
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return True si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        JeuDeTuiles autre = (JeuDeTuiles) obj;
        return equals(autre);
    }

    /**
     * Méthode equals
     * @param jt Jeu de Tuile que l'on veut comparer
     * @return True si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(JeuDeTuiles jt) {
        boolean resultat = (listeDeTuiles.equals(jt.getListeDeTuiles())) && (jt.getNombreTuiles()==nombreTuiles)
                && (dimension.equals(jt.getDimension())) && (jt.getIdentifiant()==identifiant);
        return resultat;
    }

    /**
     * Redéfinition du toString
     * @return Chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "JeuDeTuiles{" +
                "nombreTuiles=" + nombreTuiles +
                ", dimension=" + dimension.toString() +
                ", listeDeTuiles=" + listeDeTuiles +
                ", identifiant='" + identifiant + '\'' +
                '}';
    }

}
