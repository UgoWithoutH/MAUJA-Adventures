package com.mauja.maujaadventures.modele.monde;

import com.mauja.maujaadventures.modele.personnage.PersonnageJouable;
import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;


public class Decoupeur {



    private ProprietesImage pi;
    private ArrayList<ProprietesImage> listeDeTuiles = new ArrayList<ProprietesImage>();

    public ArrayList<ProprietesImage> decoupe(JeuDeTuiles jdt,int largeur, int hauteur){
        for (int i = 0; i < jdt.getLargeur()/largeur;i++){
            for (int j = 0; j < jdt.getHauteur()/largeur;j++) {
                WritableImage imageTuile = new WritableImage((pi = new ProprietesImage(jdt.getImage())).getImage().getPixelReader(),
                i*largeur, j*hauteur, largeur, hauteur);
                pi.setImage(imageTuile);
                listeDeTuiles.add(pi);




            }
        }

        
        return listeDeTuiles;
    }

    public ProprietesImage getPi() {
        return pi;
    }

    public void setPi(ProprietesImage pi) {
        this.pi = pi;
    }

    public ArrayList<ProprietesImage> getListeDeTuiles() {
        return listeDeTuiles;
    }

    public void setListeDeTuiles(ArrayList<ProprietesImage> listeDeTuiles) {
        this.listeDeTuiles = listeDeTuiles;
    }

    @Override
    public int hashCode() {
        return  31*pi.hashCode()+31*listeDeTuiles.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Decoupeur autre = (Decoupeur) obj;
        return equals(autre);
    }

    public boolean equals(Decoupeur d) {
        boolean resultat=(pi.equals(d.getPi())) && (listeDeTuiles.equals(d.getListeDeTuiles()));
        return resultat;
    }
}
