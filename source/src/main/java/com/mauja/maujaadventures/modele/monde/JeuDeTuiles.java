package com.mauja.maujaadventures.modele.monde;


import com.mauja.maujaadventures.modele.Dimension;

import java.util.ArrayList;
import java.util.List;

public class JeuDeTuiles {
    private int nombreTuiles;
    private Dimension dimension;
    private List<Tuile> listeDeTuiles;
    private String identifiant;

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

    private void setNombreTuiles(int nombreTuiles) { this.nombreTuiles = nombreTuiles; }

    public List<Tuile> getListeDeTuiles() {
        return listeDeTuiles;
    }

    private void setListeDeTuiles(List<Tuile> listeDeTuiles) {
        this.listeDeTuiles = listeDeTuiles;
        nombreTuiles = listeDeTuiles.size();
    }

    public Dimension getDimension() {
        return dimension;
    }

    private void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    private void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public int hashCode() {
        return nombreTuiles + 32 * dimension.hashCode()
                + 31 * listeDeTuiles.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        JeuDeTuiles autre = (JeuDeTuiles) obj;
        return equals(autre);
    }

    public boolean equals(JeuDeTuiles jt) {
        boolean resultat = listeDeTuiles.equals(jt.getListeDeTuiles());
        return resultat;
    }
}
