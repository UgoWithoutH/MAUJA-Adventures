package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.*;
import com.mauja.maujaadventures.utilitaires.FormatInvalideException;
import org.tiledreader.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChargeurDeCarteTiledReader implements ChargeurDeCarteTiled {

    @Override
    public Carte charge(String nomCarte) throws FormatInvalideException {
        TiledReader chargeur = new FileSystemTiledReader();
        TiledMap chargeurCarte = chargeur.getMap(nomCarte);

        List<JeuDeTuiles> lesJeuxDeTuiles = chargeJeuxDeTuiles(chargeurCarte);
        Tuile[][][] lesCalques = chargeCalques(chargeurCarte, lesJeuxDeTuiles);
        if (lesCalques == null) {
            throw new FormatInvalideException("La carte " + nomCarte
                    + " ne contient aucun calque, et ne peut pas être créée.");
        }

        Dimension dimensionCarte = new Dimension(chargeurCarte.getWidth(), chargeurCarte.getHeight());
        return new Carte(new File(nomCarte).getName(), dimensionCarte, lesCalques, lesJeuxDeTuiles, null);
    }

    private List<JeuDeTuiles> chargeJeuxDeTuiles(TiledMap chargeurCarte) {
        List<JeuDeTuiles> lesJeuxDeTuiles = new ArrayList<>();
        List<TiledTileset> lesJeuxDeTuilesTiled = chargeurCarte.getTilesets();
        int idTuiles = 0;

        for (TiledTileset jeuDeTuileTiled : lesJeuxDeTuilesTiled) {
            Dimension dimensionTuiles = new Dimension(jeuDeTuileTiled.getTileWidth(), jeuDeTuileTiled.getTileHeight());
            Collection<TiledTile> lesTuilesTiled = jeuDeTuileTiled.getTiles();
            List<Tuile> lesTuiles = new ArrayList<>();

            for (TiledTile tuileTiled : lesTuilesTiled) {
                List<TiledObject> lesCollisions = tuileTiled.getCollisionObjects();
                Rectangle rectangle;

                //Il a une collision, on la récupère et la génère.
                if (lesCollisions.size() != 0) {
                    //On récupère seulement le premier élément pour déterminer qu'il s'agit bien d'une collision.
                    TiledObject collisionTiled = lesCollisions.get(0);
                    Position position = new Position(collisionTiled.getX(), collisionTiled.getY());
                    rectangle = new Rectangle(position, new Dimension(collisionTiled.getWidth(),
                            collisionTiled.getHeight()));
                }
                else {
                    rectangle = null;
                }
                Tuile tuile = new Tuile(tuileTiled.getID() + idTuiles, rectangle, dimensionTuiles);
                lesTuiles.add(tuile);
            }
            /*URL cheminJeuDeTuiles = null;
            try {
                cheminJeuDeTuiles = new URL("file://" + URLEncoder.encode(jeuDeTuileTiled.getImage().getSource(), StandardCharsets.UTF_8));
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }*/

            Dimension dimensionJeuDeTuiles = new Dimension(jeuDeTuileTiled.getWidth(), jeuDeTuileTiled.getHeight());
            String nomJeuDeTuiles = jeuDeTuileTiled.getName();
            JeuDeTuiles jeuDeTuiles = new JeuDeTuiles(dimensionJeuDeTuiles, nomJeuDeTuiles, lesTuiles, jeuDeTuileTiled.getImage().getSource());

            lesJeuxDeTuiles.add(jeuDeTuiles);
            idTuiles += jeuDeTuileTiled.getTiles().size();
        }
        return lesJeuxDeTuiles;
    }

    private Tuile[][][] chargeCalques(TiledMap chargeurCarte, List<JeuDeTuiles> lesJeuxDeTuiles) {
        List<TiledLayer> lesCalquesTiled = chargeurCarte.getTopLevelLayers();
        if (lesCalquesTiled == null) {
            return null;
        }
        Tuile[][][] lesCalques = new Tuile[lesCalquesTiled.size()][chargeurCarte.getHeight()][chargeurCarte.getWidth()];

        for (int i = 0; i < lesCalquesTiled.size(); i++) {
            Tuile[][] lesTuiles = chargeTuiles(chargeurCarte, (TiledTileLayer) lesCalquesTiled.get(i), lesJeuxDeTuiles);
            lesCalques[i] = lesTuiles;
        }
        return lesCalques;
    }

    public Tuile[][] chargeTuiles(TiledMap chargeurCarte, TiledTileLayer calque, List<JeuDeTuiles> lesJeuxDeTuiles) {
        int largeurCarte = chargeurCarte.getWidth();
        int hauteurCarte = chargeurCarte.getHeight();
        Tuile[][] lesTuiles = new Tuile[hauteurCarte][largeurCarte];

        for (int j = 0; j < hauteurCarte; j++) {
            for (int k = 0; k < largeurCarte; k++) {
                TiledTile tuileTiled = calque.getTile(k, j);
                if (tuileTiled == null) {
                    lesTuiles[j][k] = Tuile.TUILE_IGNOREE;
                }
                else {
                    String identifiantJeuDeTuile = tuileTiled.getTileset().getName();
                    for (JeuDeTuiles jeuDeTuile : lesJeuxDeTuiles) {
                        if (jeuDeTuile.getIdentifiant().equals(identifiantJeuDeTuile)) {
                            Tuile tuile = jeuDeTuile.getListeDeTuiles().get(tuileTiled.getID());
                            lesTuiles[j][k] = tuile;
                            break;
                        }
                    }
                }
            }
        }
        return lesTuiles;
    }
}
