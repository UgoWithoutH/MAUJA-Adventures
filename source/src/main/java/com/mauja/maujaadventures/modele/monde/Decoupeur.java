package com.mauja.maujaadventures.modele.monde;

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


}
