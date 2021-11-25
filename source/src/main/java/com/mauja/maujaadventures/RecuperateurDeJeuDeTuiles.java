package com.mauja.maujaadventures;

import com.mauja.maujaadventures.modele.Collision;
import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.monde.JeuDeTuiles;
import com.mauja.maujaadventures.modele.monde.Tuile;
import org.tiledreader.TiledMap;
import org.tiledreader.TiledObject;
import org.tiledreader.TiledTile;
import org.tiledreader.TiledTileset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecuperateurDeJeuDeTuiles {

    public List<JeuDeTuiles> recupere(TiledMap chargeurCarte) {
        List<JeuDeTuiles> lesJeuxDeTuiles = new ArrayList<>();
        List<TiledTileset> lesJeuxDeTuilesTiled = chargeurCarte.getTilesets();

        for (TiledTileset jeuDeTuileTiled : lesJeuxDeTuilesTiled) {
            String nomJeuDeTuile = jeuDeTuileTiled.getName();
            Collection<TiledTile> lesTuilesTiled = jeuDeTuileTiled.getTiles();
            List<Tuile> lesTuiles = new ArrayList<>();

            for (TiledTile tuileTiled : lesTuilesTiled) {
                List<TiledObject> lesCollisions = tuileTiled.getCollisionObjects();
                Collision collision = null;
                if (lesCollisions.size() != 0) {
                    TiledObject collisionTiled = lesCollisions.get(0);

                    Position position = new Position(collisionTiled.getX(), collisionTiled.getY());
                    collision = new Collision(position, collisionTiled.getWidth(),
                            collisionTiled.getHeight());
                }
                Tuile tuile = new Tuile(tuileTiled.getID(), nomJeuDeTuile, collision, null);
                lesTuiles.add(tuile);
            }
            Dimension dimension = new Dimension(jeuDeTuileTiled.getWidth(), jeuDeTuileTiled.getHeight());
            JeuDeTuiles jeuDeTuiles = new JeuDeTuiles(dimension, nomJeuDeTuile, lesTuiles);
            lesJeuxDeTuiles.add(jeuDeTuiles);
        }
        return lesJeuxDeTuiles;
    }
}
