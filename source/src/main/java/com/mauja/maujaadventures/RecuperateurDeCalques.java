package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.monde.Calque;
import com.mauja.maujaadventures.modele.monde.JeuDeTuiles;
import com.mauja.maujaadventures.modele.monde.Tuile;
import javafx.scene.control.ListCell;
import org.tiledreader.*;

import java.util.ArrayList;
import java.util.List;

public class RecuperateurDeCalques {

    private RecuperateurDeTuiles recuperateurDeTuiles;
    private List<JeuDeTuiles> lesJeuxDeTuiles;

    public RecuperateurDeCalques(List<JeuDeTuiles> lesJeuxDeTuiles) {
        this.lesJeuxDeTuiles = lesJeuxDeTuiles;
        recuperateurDeTuiles = new RecuperateurDeTuiles(lesJeuxDeTuiles);
    }

    public List<Calque> recupere(TiledMap chargeurCarte) {
        List<Calque> lesCalques = new ArrayList<>();

        List<TiledLayer> lesCalquesTiled = chargeurCarte.getTopLevelLayers();
        if (lesCalquesTiled == null) {
            return null;
        }

        TiledTileLayer calqueCourant;
        for (int i = 0; i < lesCalquesTiled.size(); i++) {
            /*if ( instanceof HashTileLayer) {
                calqueCourant = (HashTileLayer) lesCalquesTiled.get(i);
            }
            else {
                calqueCourant = (ArrayTileLayer) lesCalquesTiled.get(i);
            }*/

            List<Tuile> lesTuiles = recuperateurDeTuiles.recupere(chargeurCarte, (TiledTileLayer) lesCalquesTiled.get(i));

            if (lesTuiles != null) {
                Dimension dimension = new Dimension(chargeurCarte.getWidth(), chargeurCarte.getHeight());
                Calque calque = new Calque(dimension, lesTuiles);
                lesCalques.add(calque);
            }
        }
        return lesCalques;
    }
}
