package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SolveurCollision {
    protected Carte cartecourante;

    /**
     * Constructeur de la classe SolveurCollision
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurCollision(Carte carte) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre ne peut pas être nulle.");
        }
        this.cartecourante = carte;
    }

    /**
     * méthode de la résolution de collision
     * Appel de la bonne méthode de solveur collision par concaténation des noms de la classe
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        String nomClasseBase = "com.mauja.maujaadventures.collisionneurs.SolveurCollision.Solveur";
        String combinaison1 = nomClasseBase + e1.getClass().getSimpleName() + e2.getClass().getSimpleName();
        String combinaison2 = nomClasseBase + e2.getClass().getSimpleName() + e1.getClass().getSimpleName();

        try {
            Class<?> classDefinition = Class.forName(combinaison1);
            Object ob = classDefinition.getDeclaredConstructor(Carte.class).newInstance(cartecourante);
            ((SolveurCollision) ob).resoud(e1, e2);
        }
        catch (ClassNotFoundException e) {
            try {
                Class<?> classDefinition = Class.forName(combinaison2);
                Object ob = classDefinition.getDeclaredConstructor(Carte.class).newInstance(cartecourante);
                ((SolveurCollision) ob).resoud(e2, e1);
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException
                    | NoSuchMethodException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
