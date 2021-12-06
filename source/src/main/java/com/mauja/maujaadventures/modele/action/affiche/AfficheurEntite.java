package com.mauja.maujaadventures.modele.action.affiche;

import com.mauja.maujaadventures.modele.*;
import com.mauja.maujaadventures.modele.personnage.ProprietesImage;


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
    public void affiche(Affichable obj, Position pos, ContexteGraphique contexteGraphique, Jeu jeu) {
        if (!(obj instanceof Entite)) {
            throw new IllegalArgumentException("L'objet " + obj.toString() + " passé en paramètre n'est pas une entité.");
        }
        Entite e = (Entite) obj;
        ProprietesImage image = new ProprietesImage(e.getCheminImage());
        contexteGraphique.dessiner(image, new Position(e.getPosition().getPositionX() - jeu.getCamera().getPositionCameraX(),
                e.getPosition().getPositionY() - jeu.getCamera().getPositionCameraY()), e.getDimensions());
    }
}