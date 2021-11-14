package com.mauja.maujaadventures.tests_unitaires;

import com.mauja.maujaadventures.modele.personnage.PersonnageJouable;

public class Test_Personnage {

    public static void main(String[] args) {
        PersonnageJouable pj = new PersonnageJouable(0, 0, 15,null);
        System.out.println(pj);
    }
}
