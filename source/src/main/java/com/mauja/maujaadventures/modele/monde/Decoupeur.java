package com.mauja.maujaadventures.modele.monde;

import com.mauja.maujaadventures.modele.personnage.ImageSource;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;

public class Decoupeur {

    public ArrayList<TuileSimple> decoupe(ImageSource tileset){
        ArrayList<TuileSimple> Tuiles.add(tileset);
        TuileSimple t=new TuileSimple(4,"a");
        return t;

        //Image image = new Image("a",20,20,true,false,false);
        WritableImage croppedImage = new WritableImage(image.getPixelReader(), 0, 0, 20, 20);

    }


}
