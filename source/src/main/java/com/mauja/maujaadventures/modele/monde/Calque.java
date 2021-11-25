package com.mauja.maujaadventures.modele.monde;

import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.action.affiche.Affichable;

import java.util.ArrayList;
import java.util.List;

public class Calque extends Affichable {

    private Dimension dimension;

    private List<Tuile> listeDeTuiles;
    /**
     * Constructeur de Calque

     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Calque(Dimension dimension, List<Tuile> lesTuiles) {
        super(""); // A modifier
        this.dimension = dimension;
        this.listeDeTuiles = new ArrayList<Tuile>();
        if (lesTuiles != null) {
            listeDeTuiles = lesTuiles;
        }
    }

    public List<Tuile> getListeDeTuiles() {
        return listeDeTuiles;
    }

    /**
     * Méthode permettant d'ajouter une tuile à la liste
     * @param t Tuile que l'on va rajouter à la liste de tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void ajouterTuile(Tuile t){
        this.listeDeTuiles.add(t);
    }

    public Dimension getDimension() {
        return dimension;
    }

    private void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public int hashCode() {
        return dimension.hashCode() + 31 * listeDeTuiles.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Calque autre = (Calque) obj;
        return equals(autre);
    }

    public boolean equals(Calque c) {
        boolean resultat = (dimension.equals(c.getDimension())
                && listeDeTuiles.equals(c.getListeDeTuiles()));
        return resultat;
    }
}
