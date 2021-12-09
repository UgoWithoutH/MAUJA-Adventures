package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.monde.Calque;
import com.mauja.maujaadventures.modele.monde.JeuDeTuiles;
import com.mauja.maujaadventures.modele.monde.Tuile;
import com.mauja.maujaadventures.modele.monde.TuileFX;
import javafx.scene.control.ListCell;
import org.tiledreader.*;

import java.util.ArrayList;
import java.util.List;

public class RecuperateurDeCalques {

    private RecuperateurDeTuiles recuperateurDeTuiles;
    private List<JeuDeTuiles> lesJeuxDeTuiles;

    /**
     * Constructeur du récupérateur de Tuile
     * @param lesJeuxDeTuiles Liste des Tuiles du calques
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public RecuperateurDeCalques(List<JeuDeTuiles> lesJeuxDeTuiles) {
        this.lesJeuxDeTuiles = lesJeuxDeTuiles;
        recuperateurDeTuiles = new RecuperateurDeTuiles(lesJeuxDeTuiles);
    }

    /**
     * Méthode de récupération des Tuiles à mettre dans le calque
     * @param chargeurCarte Carte que l'on veut charger en calque
     * @return la liste de calque ou null si il n'y a pas de calques
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public List<Calque> recupere(TiledMap chargeurCarte) {
        List<Calque> lesCalques = new ArrayList<>();

        List<TiledLayer> lesCalquesTiled = chargeurCarte.getTopLevelLayers();
        if (lesCalquesTiled == null) {
            return null;
        }

        TiledTileLayer calqueCourant;
        for (int i = 0; i < lesCalquesTiled.size(); i++) {
            List<Tuile> lesTuiles = recuperateurDeTuiles.recupere(chargeurCarte, (TiledTileLayer) lesCalquesTiled.get(i));

            if (lesTuiles != null) {
                Dimension dimension = new Dimension(chargeurCarte.getWidth(), chargeurCarte.getHeight());
                Calque calque = new Calque(dimension, lesTuiles);
                lesCalques.add(calque);
            }
        }
        return lesCalques;
    }

    /**
     * Redéfinition du toString
     * @return chaine de caractère à afficher dans la console
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "RecuperateurDeCalques{" +
                "recuperateurDeTuiles=" + recuperateurDeTuiles.toString() +
                ", lesJeuxDeTuiles=" + lesJeuxDeTuiles.toString() +
                '}';
    }
}
