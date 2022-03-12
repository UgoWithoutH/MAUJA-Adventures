package com.mauja.maujaadventures.collisionneurs.solveurattaque;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.collisionneurs.SolveurCollision.SolveurCollision;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SolveurAttaque {
    protected Carte cartecourante;
    protected CollisionneurAABB collisionneur;

    /**
     * Constructeur de la classe SolveurCollision
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurAttaque(Carte carte){
        this.cartecourante = carte;
        collisionneur = new CollisionneurAABB();
    }
    /**
     * méthode de la résolution de collision
     * Appel de la bonne méthode de solveur collision par concaténation des noms de la classe
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void resoud(ElementInteractif e1, ElementInteractif e2){
        Class<?> classDefinition = null;
        Constructor<?> constructor = null;
        String nomClasse = "com.mauja.maujaadventures.collisionneurs.solveurattaque.SolveurAtt"
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
