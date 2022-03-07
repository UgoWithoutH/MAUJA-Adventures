package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.interactions.ElementInteractif;

import java.lang.reflect.InvocationTargetException;

public class SolveurCollision {
    /**
     * Interface de la résolution de collision
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @return true si il y a collision sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        Class classDefinition = null;
        try {
            classDefinition = Class.forName("Solveur" + e1.getClass() + e2.getClass());
            Object ob = classDefinition.getDeclaredConstructor().newInstance();
            ((SolveurCollision) ob).resoud(e1, e2);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
