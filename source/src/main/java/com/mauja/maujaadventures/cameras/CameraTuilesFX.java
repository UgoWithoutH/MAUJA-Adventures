package com.mauja.maujaadventures.cameras;
import com.mauja.maujaadventures.affichages.Carte2DGraphique;
import com.mauja.maujaadventures.affichages.TuileGraphique;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.monde.Carte;

import java.util.List;

/**
 * Mise à jour de la caméra Tuiles et affichages des tuiles
 */
public class CameraTuilesFX extends CameraTuiles {
    private List<TuileGraphique> lesTuilesGraphiques;
    private TuileGraphique[][][] visionGraphique;


    /**
     * Constructeur de la camera
     * @param carte carte ou se trouve la camera
     * @param zoneVisuelle dimension de la vue
     * @throws IllegalArgumentException
     */
    public CameraTuilesFX(Carte carte, Dimension zoneVisuelle, Carte2DGraphique carte2DGraphique)
            throws IllegalArgumentException {
        super(carte, zoneVisuelle);
        if (carte2DGraphique == null) {
            throw new IllegalArgumentException("La carte graphique doit être non nulle pour la création de la caméra.");
        }

        this.lesTuilesGraphiques = carte2DGraphique.getLesTuilesGraphiques();
        visionGraphique = new TuileGraphique[nombreCalques]
    [(int) (zoneVisuelle.getHauteur())][(int) (zoneVisuelle.getLargeur())];
    }


    /**
     * retourne la tuile pour un certain endroit
     * @param x coordonnées X de la tuile
     * @param y coordonnées Y de la tuile
     * @return
     */
    public TuileGraphique getTuileGraphique(int k, int x, int y) {
        return visionGraphique[k][x][y];
    }

    public TuileGraphique[][][] getVisionGraphique() {
        return visionGraphique;
    }

    @Override
    public void centrerSurEntite(Entite entite) {
        super.centrerSurEntite(entite);
        miseAJour();
    }


    /**
     * Methode mettant a jour la camera ( postion etc.. )
     */

    private void miseAJour() {
        double largeurCamera = zoneObservable.getLargeur();
        double hauteurCamera = zoneObservable.getHauteur();
        for (int k = 0; k < nombreCalques; k++) {
            for (int y = 0; y < hauteurCamera; y++) {
                for (int x = 0; x < largeurCamera; x++) {
                    visionGraphique[k][y][x] = lesTuilesGraphiques.get(zoneVisible[k][y][x].getId());
                }
            }
        }
    }
}
