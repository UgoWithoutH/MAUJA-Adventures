package com.mauja.maujaadventures.collisionneurs.solveurs.collision;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

import java.lang.reflect.InvocationTargetException;

public class SolveurCollision {
    protected Carte carteCourante;

    /**
     * Constructeur de la classe SolveurCollision
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurCollision(Carte carte) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre ne peut pas être nulle.");
        }
        this.carteCourante = carte;
    }

    /**
     * méthode de la résolution de collision
     * Appel de la bonne méthode de solveur collision par concaténation des noms de la classe
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        if (e1 == null || e2 == null) {
            return;
        }
        String nomClasseBase = "com.mauja.maujaadventures.collisionneurs.solveurs.collision.Solveur";
        String combinaison1 = nomClasseBase + e1.getClass().getSimpleName() + e2.getClass().getSimpleName();
        String combinaison2 = nomClasseBase + e2.getClass().getSimpleName() + e1.getClass().getSimpleName();

        try {
            Class<?> classDefinition = Class.forName(combinaison1);
            Object ob = classDefinition.getDeclaredConstructor(Carte.class).newInstance(carteCourante);
            ((SolveurCollision) ob).resoud(e1, e2);
        }
        catch (ClassNotFoundException e) {
            try {
                Class<?> classDefinition = Class.forName(combinaison2);
                Object ob = classDefinition.getDeclaredConstructor(Carte.class).newInstance(carteCourante);
                ((SolveurCollision) ob).resoud(e2, e1);
            }
            catch (ClassNotFoundException ex) {
                /* Nous ne faisons rien ici car s'il est impossible d'instancier un solveur dans un sens
                * ou dans un autre, alors c'est que l'utilisateur ne veut peut-être pas de collision
                * entre ces deux entités. Si jamais il veut réaliser une quelconque action, il devra alors
                * intégrer le solveur correspondant.
                 */
            }
            catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException
                    | InstantiationException ex) {
                ex.printStackTrace();
            }
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
