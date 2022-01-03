package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.JeuDeTuiles;
import com.mauja.maujaadventures.monde.Tuile;
import com.mauja.maujaadventures.monde.TuileSimple;
import org.tiledreader.TiledMap;
import org.tiledreader.TiledObject;
import org.tiledreader.TiledTile;
import org.tiledreader.TiledTileset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecuperateurDeJeuDeTuiles {
    /**
     * Méthode de récupération du Jeu de TUile
     * @param chargeurCarte Carte où on veut récupérer le jeu de Tuile
     * @return Liste de Tuile de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public List<JeuDeTuiles> recupere(TiledMap chargeurCarte) {
        List<JeuDeTuiles> lesJeuxDeTuiles = new ArrayList<>();
        List<TiledTileset> lesJeuxDeTuilesTiled = chargeurCarte.getTilesets();

        for (TiledTileset jeuDeTuileTiled : lesJeuxDeTuilesTiled) {
            String nomJeuDeTuile = jeuDeTuileTiled.getName();
            Collection<TiledTile> lesTuilesTiled = jeuDeTuileTiled.getTiles();
            List<Tuile> lesTuiles = new ArrayList<>();

            for (TiledTile tuileTiled : lesTuilesTiled) {
                List<TiledObject> lesCollisions = tuileTiled.getCollisionObjects();
                Rectangle rectangle;
                if (lesCollisions.size() != 0) {
                    //System.out.println(tuileTiled.getID());
                    TiledObject collisionTiled = lesCollisions.get(0);

                    Position position = new Position(collisionTiled.getX(), collisionTiled.getY());
                    rectangle = new Rectangle(position, new Dimension(collisionTiled.getWidth(),
                            collisionTiled.getHeight()));
                }
                else {
                    rectangle = null;
                }
                Tuile tuile = new TuileSimple(tuileTiled.getID(), nomJeuDeTuile, rectangle, new Dimension(32, 32));
                lesTuiles.add(tuile);
            }
            Dimension dimension = new Dimension(jeuDeTuileTiled.getWidth(), jeuDeTuileTiled.getHeight());
            JeuDeTuiles jeuDeTuiles = new JeuDeTuiles(dimension, nomJeuDeTuile, lesTuiles);
            lesJeuxDeTuiles.add(jeuDeTuiles);
        }
        return lesJeuxDeTuiles;
    }
}
