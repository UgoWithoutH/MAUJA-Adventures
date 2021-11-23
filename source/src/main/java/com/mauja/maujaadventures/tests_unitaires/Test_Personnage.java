package com.mauja.maujaadventures.tests_unitaires;

import com.mauja.maujaadventures.modele.Collision;
import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
import com.mauja.maujaadventures.modele.personnage.PersonnageJouable;
import javafx.geometry.Rectangle2D;

public class Test_Personnage {

    public static void main(String[] args) {
        PersonnageJouable pj = new PersonnageJouable(new Position(0, 0),
                "toto",
                new Collision(new Rectangle2D(1,1,1,1)),
                10);
        System.out.println(pj);
    }
}
