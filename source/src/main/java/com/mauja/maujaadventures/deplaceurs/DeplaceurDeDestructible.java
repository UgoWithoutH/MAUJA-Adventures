package com.mauja.maujaadventures.deplaceurs;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.interactions.evenements.Evenement;
import com.mauja.maujaadventures.interactions.evenements.EvenementDeplacement;
import com.mauja.maujaadventures.observateurs.ObservateurEvenementiel;
import com.mauja.maujaadventures.monde.Carte;

public class DeplaceurDeDestructible extends Deplaceur implements ObservateurEvenementiel {
    private Deplaceur deplaceur;

    public DeplaceurDeDestructible(Carte carte, Deplaceur deplaceur) {
        super(carte);
        if (deplaceur == null) {
            throw new IllegalArgumentException("Le déplaceur du destructible ne peut pas être null.");
        }
        this.deplaceur = deplaceur;
    }

    @Override
    public boolean deplace(Entite entite, Direction direction, boolean gestionCollisions) {
        Evenement evenement = new EvenementDeplacement(entite, direction, deplaceur);
        evenement.attacher(this);
        GestionnaireInteractions.getInstance().ajouter(evenement);
        return true;
    }

    @Override
    public void miseAJour(ElementInteractif elementInteractif, Boolean resultat, Object... parametres) {
        if (!resultat) {
            carteCourante.supprimerElementInteractif(elementInteractif);
        }
    }
}
