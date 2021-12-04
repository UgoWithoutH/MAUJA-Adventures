package com.mauja.maujaadventures.modele.monde;

import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;


public class Decoupeur {

    private ProprietesImage proprietesImage;
    private ArrayList<Image> listeDeTuiles = new ArrayList<Image>();

    /**
     * Fonction de découpage des Tuiles
     * @param chemin Chemin de la Carte du TileSet
     * @param largeurTuile Largeur d'un Tuile (32)
     * @param hauteurTuile Hauteur d'une Tuile (32)
     * @return La liste des Tuiles découpé en format Image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public ArrayList<Image> decoupe(String chemin, int largeurTuile, int hauteurTuile) {
        Image image = new Image(chemin);
        double largeurImage = image.getWidth() / largeurTuile;
        double hauteurImage = image.getHeight() / hauteurTuile;
        for (int i = 0; i < largeurImage; i++){
            for (int j = 0; j < hauteurImage; j++) {
                proprietesImage = new ProprietesImage(image.getUrl());
                WritableImage imageTuile = new WritableImage((proprietesImage).getImage().getPixelReader(),
                j * largeurTuile, i * hauteurTuile, largeurTuile, hauteurTuile);
                //proprietesImage.setImage(imageTuile);
                listeDeTuiles.add(imageTuile);
            }
        }
        return listeDeTuiles;
    }

    /**
     * Getter de la Propriété de l'image
     * @return les attributs de la propriété de l'image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public ProprietesImage getProprietesImage() {
        return proprietesImage;
    }

    /**
     * Getter Liste de Tuiles
     * @return La liste Des Tuiles
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public ArrayList<Image> getListeDeTuiles() {
        return listeDeTuiles;
    }

    /**
     * Permet de modifier la liste des tuiles
     * @param listeDeTuiles La nouvelle liste de Tuiles
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setListeDeTuiles(ArrayList<Image> listeDeTuiles) {
        this.listeDeTuiles = listeDeTuiles;
    }

    /**
     * Redéfinition du HashCode
     * @return Entier de l'hachage des attributs de Decoupeur
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return  31* proprietesImage.hashCode()+31*listeDeTuiles.hashCode();
    }

    /**
     * Redéfintion du equals
     * @param obj Objet que l'on veut comparé
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Decoupeur autre = (Decoupeur) obj;
        return equals(autre);
    }

    /**
     * Equals
     * @param d découpeur que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Decoupeur d) {
        boolean resultat = (proprietesImage.equals(d.getProprietesImage())) && (listeDeTuiles.equals(d.getListeDeTuiles()));
        return resultat;
    }

    /**
     * Redéfinition du ToString
     * @return chaîne de caractère à afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "Decoupeur{" +
                "proprietesImage=" + proprietesImage.toString() +
                ", listeDeTuiles=" + listeDeTuiles.toString() +
                '}';
    }

}
