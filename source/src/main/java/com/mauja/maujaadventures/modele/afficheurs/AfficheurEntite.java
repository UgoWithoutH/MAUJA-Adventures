package com.mauja.maujaadventures.modele.afficheurs;

import com.mauja.maujaadventures.modele.entites.Entite;
import com.mauja.maujaadventures.modele.entites.PersonnageJouableFX;
import com.mauja.maujaadventures.modele.jeu.Jeu;
import com.mauja.maujaadventures.modele.logique.Position;


public class AfficheurEntite implements Afficheur {
    /**
     *  Redéfinition de l'interface Afficheur, on rajoute le code permettant l'affichage de l'objet ici une Entite
     *
     * @param obj Correspond à l'objet que l'on souhaite afficher
     * @param pos Correspond à la position X et Y de l'objet
     * @param contexteGraphique Correspond à l'interface ou sera affiché l'objet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void affiche(Object obj, Position pos, ContexteGraphique contexteGraphique, Jeu jeu) {
        if (!(obj instanceof Entite)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas une entité.");
        }
        PersonnageJouableFX e = (PersonnageJouableFX) obj;
        contexteGraphique.dessiner(e.getImage(), new Position(e.getPosition().getX() - jeu.getCamera().getPositionCameraX(),
                e.getPosition().getY() - jeu.getCamera().getPositionCameraY()), e.getDimension());
    }
}