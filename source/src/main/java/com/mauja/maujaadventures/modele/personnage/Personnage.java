package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Collision;
import com.mauja.maujaadventures.modele.Entite;
import javafx.scene.image.Image;

public abstract class Personnage extends Entite { //pour éventuellement des PNJ

<<<<<<< HEAD
    public Personnage(int x, int y, ImageSource image){
        super(x,y,image);
=======
    public Personnage(int x, int y, Image image, Collision collision){
        super(x,y,image,collision);
>>>>>>> 02edb04c2e8cf920a9ed0983ea45a59fc418cfa3
    }

}
