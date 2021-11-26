package com.mauja.maujaadventures.modele.action.affiche;

import com.mauja.maujaadventures.modele.monde.JeuDeTuiles;
import com.mauja.maujaadventures.modele.personnage.ProprietesImage;

public abstract class Affichable {

    private String cheminImage;

    /**
     * Constructeur de la classe abstraite Affichable
     * @param image L'image de l'objet affichable
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Affichable(String image) {
        this.cheminImage = image;
    }
    /**
     * Getter du chemin de l'image
     * @return le chemin absolu de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public String getCheminImage() {
        return cheminImage;
    }
    /**
     * Setter du chemin de l'image
     * @param cheminImage Nouveau chemin de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    @Override
    public int hashCode() { return 31 * cheminImage.hashCode(); }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Affichable autre = (Affichable) obj;
        return equals(autre);
    }

    public boolean equals(Affichable a) {
        boolean resultat=(cheminImage.equals(a.getCheminImage()));
        return resultat;
    }
}
