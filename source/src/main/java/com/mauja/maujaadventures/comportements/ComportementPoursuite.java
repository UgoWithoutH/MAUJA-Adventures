package com.mauja.maujaadventures.comportements;

import com.mauja.maujaadventures.deplaceurs.Deplaceur;
import com.mauja.maujaadventures.deplaceurs.DeplaceurBasique;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.entites.Vivant;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.interactions.evenements.EvenementDeplacement;
import com.mauja.maujaadventures.interactions.evenements.EvenementImmobile;
import com.mauja.maujaadventures.monde.Carte;


public class ComportementPoursuite implements Comportement{
    private PersonnageJouable joueur;

    private Deplaceur deplaceur;
    private GestionnaireInteractions gestionnaireInteractions;

    public ComportementPoursuite(Carte carte, PersonnageJouable joueur) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre du comportement de poursuiveur ne peut pas "
                    + "être nulle.");
        }
        deplaceur = new DeplaceurBasique(carte);
        this.joueur = joueur;
    }

    @Override
    public void agit(Vivant vivant, float temps) {
        gestionnaireInteractions = GestionnaireInteractions.getInstance();
        double positionJoueurX = joueur.getPosition().getX() + joueur.getCollision().getPosition().getX();
        double positionJoueurY = joueur.getPosition().getY() + joueur.getCollision().getPosition().getY();
        double positionVivantX = vivant.getPosition().getX() + vivant.getCollision().getPosition().getX();
        double positionVivantY = vivant.getPosition().getY() + vivant.getCollision().getPosition().getY();
        boolean estDeplace = false;

        if (positionJoueurX > positionVivantX) {
            gestionnaireInteractions.ajouter(new EvenementDeplacement(vivant, Direction.DROITE, deplaceur));
            estDeplace = true;
        }
        if (positionJoueurX < positionVivantX) {
            gestionnaireInteractions.ajouter(new EvenementDeplacement(vivant, Direction.GAUCHE, deplaceur));
            estDeplace = true;
        }
        if (positionJoueurY > positionVivantY) {
            gestionnaireInteractions.ajouter(new EvenementDeplacement(vivant, Direction.BAS, deplaceur));
            estDeplace = true;
        }
        if (positionJoueurY < positionVivantY) {
            gestionnaireInteractions.ajouter(new EvenementDeplacement(vivant, Direction.HAUT, deplaceur));
            estDeplace = true;
        }

        if (!estDeplace) {
            gestionnaireInteractions.ajouter(new EvenementImmobile(vivant));
        }
    }
}
