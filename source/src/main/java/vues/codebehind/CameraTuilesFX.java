package vues.codebehind;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.TuileSimple;

import java.util.List;

/**
 * classe permettant de gerer l'affichage de la camera
 */
public class CameraTuilesFX extends CameraTuiles {
    private List<TuileSimple> lesTuilesGraphiques;
    private TuileSimple[][] visionGraphique;

    /**
     * Constructeur de la camera
     * @param carte carte ou se trouve la camera
     * @param zoneVisuelle dimension de la vue
     * @param lesTuilesGraphiques listes de tuiles pour l'affichage
     * @throws IllegalArgumentException
     */
    public CameraTuilesFX(Carte carte, Dimension zoneVisuelle, List<TuileSimple> lesTuilesGraphiques)
            throws IllegalArgumentException {
        super(carte, zoneVisuelle);
        if (lesTuilesGraphiques == null) {
            throw new IllegalArgumentException("La liste de tuiles graphique doit être non nulle pour la création de la caméra.");
        }
        this.lesTuilesGraphiques = lesTuilesGraphiques;
        visionGraphique = new TuileSimple[(int) zoneVisuelle.getHauteur()][(int) zoneVisuelle.getLargeur()];
        //visionGraphique = new TuileFX[(int) zoneVisuelle.getLargeur()][(int) zoneVisuelle.getHauteur()];
    }

    /**
     * retourne la tuile pour un certain endroit
     * @param x coordonnées X de la tuile
     * @param y coordonnées Y de la tuile
     * @return
     */
    public TuileSimple getTuileGraphique(int x, int y) {
        return visionGraphique[x][y];
    }

    /**
     * Methode permettant de centrer ka camera sur l'entite
     * @param entite
     */
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

        for (int x = 0; x < hauteurCamera; x++) {
            for (int y = 0; y < largeurCamera; y++) {
                visionGraphique[x][y] = lesTuilesGraphiques.get(zoneVisible[x][y].getId());
            }
        }
    }
}
