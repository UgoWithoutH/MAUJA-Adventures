package com.mauja.maujaadventures.chargeurs;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Ressources {
    private static Ressources ressources;

    private List<String> lesCartes;
    private List<String> lesImagesEntites;
    private List<String> lesScripts;

    private String fichierTransitions;
    private String fichierTouches;

    public Ressources() {
        lesCartes = new ArrayList<>();
        lesImagesEntites = new ArrayList<>();
        lesScripts = new ArrayList<>();
        initialiser();
    }

    public String getFichierTouches() {
        return fichierTouches;
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

    public List<String> getLesScripts() {
        return Collections.unmodifiableList(lesScripts);
    }

    private void initialiser() {
        lesCartes.add(Objects.requireNonNull(new File("ressources/cartes/carteTest3.tmx").getAbsolutePath()));

        try {
            lesImagesEntites.add(Objects.requireNonNull(new File("images/entites/link_epee.png").toURI().toURL().toExternalForm()));
            lesImagesEntites.add(Objects.requireNonNull(new File("images/entites/projectile.png").toURI().toURL().toString()));

            lesScripts.add(Objects.requireNonNull(new File("ressources/scripts/testInteractions.xml").toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        fichierTransitions = Objects.requireNonNull(new File("ressources/transitions.txt").getAbsolutePath());
        fichierTouches = Objects.requireNonNull(new File("ressources/configurationTouches.txt").getAbsolutePath());
    }
}
