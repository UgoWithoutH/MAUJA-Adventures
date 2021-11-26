package com.mauja.maujaadventures.modele.personnage;


import com.mauja.maujaadventures.modele.Camera;
import javafx.scene.image.Image;

public class ProprietesImage {
    private String cheminImage;
    private double longueur;
    private double hauteur;
    private Image image;
    /**
     * Constructeur de ProprietesImages
     *
     * @param cheminImage Chemin de l'image que l'on souhaite avoir
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public ProprietesImage(String cheminImage) {
        this.cheminImage = cheminImage;
        try {
            image = new Image(cheminImage);
            hauteur = image.getHeight();
            longueur = image.getWidth();
        }
        catch(IllegalArgumentException i){
            System.out.println("L'image n'a pas été trouvée. Chemin: " + getCheminImage());
        }
    }

    public ProprietesImage(Image image) {
        try {
            this.image = image;
            hauteur = image.getHeight();
            longueur = image.getWidth();
        }
        catch(IllegalArgumentException i){
            System.out.println("L'image n'a pas été trouvée. Chemin: " + getCheminImage());
        }
    }
    /**
     * Getter de l'image permettant de la récupérer plus tard
     * @return Image que l'on souhaite récupérer
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Image getImage() {
        return image;
    }
    /**
     * Setter de l'image
     * @param image Nouvelle image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setImage(Image image) {
        this.image = this.image;
    }
    /**
     * Getter du Chemin de l'image
     * @return Nouveau chemin
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public String getCheminImage() {
        return cheminImage;
    }
    /**
     * Setter du chemin de l'image
     *
     * @param cheminImage Chemin que l'on souhaite modifié de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }
    /**
     * Getter de la longueur
     *
     * @return La longueur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getLongueur() {
        return longueur;
    }
    /**
     * Setter de la longueur de l'image
     *
     * @param longueur longueur de l'image que l'on souhaite modifié
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }
    /**
     * Getter de Hauteur
     *
     * @return L'hauteur de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getHauteur() {
        return hauteur;
    }
    /**
     * Setter de Hauteur
     *
     * @param hauteur de l'image que l'on souhaite modifié
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    @Override
    public int hashCode() {
        return (int)(longueur+hauteur) +31*image.hashCode()+31*cheminImage.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        ProprietesImage autre = (ProprietesImage) obj;
        return equals(autre);
    }

    public boolean equals(ProprietesImage pi) {
        boolean resultat=(pi.getHauteur()==hauteur) && (pi.getLongueur()==longueur) &&
                (image.equals(pi.getImage())) && (cheminImage.equals(pi.getCheminImage()));
        return resultat;
    }
}
