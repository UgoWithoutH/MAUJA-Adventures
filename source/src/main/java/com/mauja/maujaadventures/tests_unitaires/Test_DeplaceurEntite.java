package com.mauja.maujaadventures.tests_unitaires;

import com.mauja.maujaadventures.modele.DeplaceurEntite;
import com.mauja.maujaadventures.modele.personnage.PersonnageJouable;

public class Test_DeplaceurEntite {

    public static void main(String[] args){
        PersonnageJouable pj = new PersonnageJouable(0,0,15);
        DeplaceurEntite dp = new DeplaceurEntite();

        System.out.println(pj);

        //cas normale
        dp.deplaceur(pj,10,1);
        System.out.println(pj);

        //cas position négative X
        dp.deplaceur(pj,-10,1);
        System.out.println(pj);

        //cas position négative Y
        dp.deplaceur(pj,10,-1);
        System.out.println(pj);
    }
}