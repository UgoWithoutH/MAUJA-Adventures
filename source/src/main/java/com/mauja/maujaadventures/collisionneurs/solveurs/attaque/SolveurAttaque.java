package com.mauja.maujaadventures.collisionneurs.solveurs.attaque;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.Scenario;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Carte;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class SolveurAttaque {
    protected Carte cartecourante;
    protected CollisionneurAABB collisionneur;

    public SolveurAttaque(Carte carte){
        this.cartecourante = carte;
        collisionneur = new CollisionneurAABB();
    }

    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        Class<?> classDefinition = null;
        Constructor<?> constructor = null;
        String nomClasse = "com.mauja.maujaadventures.collisionneurs.solveurs.attaque.Solveur"
                + e1.getClass().getSimpleName() + e2.getClass().getSimpleName();
        try {
            classDefinition = Class.forName(nomClasse);
            Object ob = classDefinition.getDeclaredConstructor(Carte.class).newInstance(cartecourante);
            ((SolveurAttaque) ob).resoud(e1, e2);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
