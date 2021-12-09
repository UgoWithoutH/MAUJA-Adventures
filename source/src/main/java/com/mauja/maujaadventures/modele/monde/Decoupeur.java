package com.mauja.maujaadventures.modele.monde;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;


public class Decoupeur {
    private List<Image> listeDeTuiles = new ArrayList<Image>();

    /**
     * Fonction de découpage des Tuiles
     * @param chemin Chemin de la Carte du TileSet
     * @param largeurTuile Largeur d'un Tuile (32)
     * @param hauteurTuile Hauteur d'une Tuile (32)
     * @return La liste des Tuiles découpé en format Image
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public List<Image> decoupe(String chemin, int largeurTuile, int hauteurTuile) {
        Image image = new Image(chemin);
        PixelReader lecteur = image.getPixelReader();
        double largeurImage = image.getWidth() / largeurTuile;
        double hauteurImage = image.getHeight() / hauteurTuile;

        for (int i = 0; i < largeurImage; i++){
            for (int j = 0; j < hauteurImage; j++) {
                System.out.println("toto");
                WritableImage imageTuile = new WritableImage(lecteur,
                j * largeurTuile, i * hauteurTuile, largeurTuile, hauteurTuile);
                listeDeTuiles.add(imageTuile);
            }
        }
        return listeDeTuiles;
    }

    /**
     * Getter Liste de Tuiles
     * @return La liste Des Tuiles
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public List<Image> getListeDeTuiles() {
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
        return 31 * listeDeTuiles.hashCode();
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
        return listeDeTuiles.equals(d.getListeDeTuiles());
    }

    /**
     * Redéfinition du ToString
     * @return chaîne de caractère à afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "Decoupeur{" +
                ", listeDeTuiles=" + listeDeTuiles.toString() +
                '}';
    }
}
