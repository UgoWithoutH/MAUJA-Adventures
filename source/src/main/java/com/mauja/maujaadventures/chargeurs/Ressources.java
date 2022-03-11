package com.mauja.maujaadventures.chargeurs;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ressources {
    private static Ressources ressources;

    private List<String> lesCartes;
    private List<String> lesImagesEntites;

    private String fichierTransitions;

    public Ressources() {
        lesCartes = new ArrayList<>();
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

    private void initialiser() {
        lesCartes.add(new File("ressources/cartes/carteTest3.tmx").getAbsolutePath());

        try {
            lesImagesEntites.add(new File("images/entites/link_epee.png").toURI().toURL().toExternalForm());
            lesImagesEntites.add(new File("images/entites/projectile.png").toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        fichierTransitions = new File("ressources/transitions.txt").getAbsolutePath();
    }
}
