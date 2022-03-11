package com.mauja.maujaadventures.comportements;

import com.mauja.maujaadventures.deplaceurs.DeplaceurEntite;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Projectile;
import com.mauja.maujaadventures.entites.Vivant;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.interactions.evenements.EvenementDeplacement;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComportementOctorockTireur implements Comportement {
    private static final Random ALEATOIRE = new Random();
    private static final List<Direction> DIRECTIONS_POSSIBLES = Arrays.asList(Direction.values());
    private static final int NOMBRE_DIRECTIONS = DIRECTIONS_POSSIBLES.size();
    private static final float INTERVALLE_DEPLACEMENT = 20;
    private static final float INTERVALLE_TIR = 600;
    private static final int NOMBRE_MAXIMUM_TENTATIVES_DEPLACEMENT = 6;

    private DeplaceurEntite deplaceur;
    private Carte carteCourante;
    private int iterations = 0;
    private Direction derniereDirection;
    private int temps;
    private Vivant vivant;
    private int nombreTentatives = 0;

    public ComportementOctorockTireur(Carte carte) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre du comportement de tireur ne peut pas "
                    + "être nulle.");
        }
        carteCourante = carte;
        deplaceur = new DeplaceurEntite(carte);
    }

    @Override
    public void agit(Vivant vivant, float temps) {
        this.vivant = vivant;
        this.temps++;

        if (this.temps > INTERVALLE_DEPLACEMENT || iterations != 0) {
            this.temps = 0;
            if (iterations == 0) {
                derniereDirection = DIRECTIONS_POSSIBLES.get(ALEATOIRE.nextInt(NOMBRE_DIRECTIONS));
            }
            //resultatDeplacement = deplaceur.deplace(vivant, temps, derniereDirection, true);
            GestionnaireInteractions.getInstance().ajouter(new EvenementDeplacement(vivant, derniereDirection));
            nombreTentatives++;
        }

        /*if (this.temps > INTERVALLE_DEPLACEMENT || iterations != 0) {
            this.temps = 0;
            do {
                if (iterations == 0) {
                    derniereDirection = DIRECTIONS_POSSIBLES.get(ALEATOIRE.nextInt(NOMBRE_DIRECTIONS));
                }
                resultatDeplacement = deplaceur.deplace(vivant, temps, derniereDirection, true);
                nombreTentatives++;
            }
            while (!resultatDeplacement && nombreTentatives < NOMBRE_MAXIMUM_TENTATIVES_DEPLACEMENT);
            iterations++;
            if (iterations == 10) {
                Projectile projectile = new Projectile(vivant.getPosition(), new Dimension(20, 20),
                        new Rectangle(0, 0, 20, 20), null, 3);
                projectile.setDirection(vivant.getDirection());
                carteCourante.ajouterElementInteractif(projectile);
                iterations = 0;
            }
        }*/
    }

    private void action(){
        iterations++;
        if (iterations == 10) {
            Projectile projectile = new Projectile(vivant.getPosition(), new Dimension(20, 20),
                    new Rectangle(0, 0, 20, 20), null, 3);
            projectile.setDirection(vivant.getDirection());
            carteCourante.ajouterElementInteractif(projectile);
            iterations = 0;
        }
    }

    @Override
    public void miseAJour(boolean resultatDeplacement) {
        if (this.temps > INTERVALLE_DEPLACEMENT || iterations != 0) {
            if (!resultatDeplacement && nombreTentatives < NOMBRE_MAXIMUM_TENTATIVES_DEPLACEMENT) {
                this.temps = 0;
                if (iterations == 0) {
                    derniereDirection = DIRECTIONS_POSSIBLES.get(ALEATOIRE.nextInt(NOMBRE_DIRECTIONS));
                }
                GestionnaireInteractions.getInstance().ajouter(new EvenementDeplacement(vivant, derniereDirection));
                nombreTentatives++;
            }
        }
        else{
            action();
        }
    }
}
