package com.mauja.maujaadventures.collisionneurs.solveurs.attaque;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.monde.Carte;

import java.lang.reflect.InvocationTargetException;

public class SolveurAttaque {
    protected Carte cartecourante;
    protected CollisionneurAABB collisionneur;

    public SolveurAttaque(Carte carte){
        this.cartecourante = carte;
        collisionneur = new CollisionneurAABB();
    }

    public void resoud(ElementInteractif e1, Attaque attaque) {
        if (e1 == null || attaque == null) {
            return;
        }
        String nomClasse = "com.mauja.maujaadventures.collisionneurs.solveurs.attaque.SolveurAttaque"
                + e1.getClass().getSimpleName();
        try {
            Class<?> classDefinition = Class.forName(nomClasse);
            Object ob = classDefinition.getDeclaredConstructor(Carte.class).newInstance(cartecourante);
            ((SolveurAttaque) ob).resoud(e1, attaque);
        }
        catch (ClassNotFoundException e) {
            /* Nous ne faisons rien ici car s'il est impossible d'instancier un solveur dans un sens
             * ou dans un autre, alors c'est que l'utilisateur ne veut peut-être pas de collision
             * entre ces deux entités. Si jamais il veut réaliser une quelconque action, il devra alors
             * intégrer le solveur correspondant.
             */
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
