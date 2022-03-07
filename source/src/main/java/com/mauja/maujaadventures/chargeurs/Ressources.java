package com.mauja.maujaadventures.chargeurs;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ressources {
    private static Ressources ressources;

    private List<String> lesCartes;
    private List<String> lesImagesJeuxDeTuiles;
    private List<String> lesImagesEntites;

    private String fichierTransitions;

    public Ressources() {
        lesCartes = new ArrayList<>();
        lesImagesJeuxDeTuiles = new ArrayList<>();
        lesImagesEntites = new ArrayList<>();
        initialiser();
    }

    public String getFichierTransitions() {
        return fichierTransitions;
    }

    public static Ressources getInstance() {
        if (ressources == null) {
            ressources = new Ressources();
        }
        return ressources;
    }

    public List<String> getLesCartes() {
        return Collections.unmodifiableList(lesCartes);
    }

    public List<String> getLesImagesJeuxDeTuiles() {
        return Collections.unmodifiableList(lesImagesJeuxDeTuiles);
    }

    public List<String> getLesImagesEntites() {
        return Collections.unmodifiableList(lesImagesEntites);
    }

    private void initialiser() {
        lesCartes.add(new File("ressources/cartes/carteTest3.tmx").getAbsolutePath());

        try {
            lesImagesJeuxDeTuiles.add(new File("ressources/images/tilesets/terrain_atlas.png").toURI().toURL().toString());
            lesImagesJeuxDeTuiles.add(new File("ressources/images/tilesets/houses.png").toURI().toURL().toString());

            lesImagesEntites.add(new File("images/entites/link_epee.png").toURI().toURL().toExternalForm());
            lesImagesEntites.add(new File("images/entites/projectile.png").toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        fichierTransitions = new File("ressources/transitions.txt").getAbsolutePath();
    }
}
