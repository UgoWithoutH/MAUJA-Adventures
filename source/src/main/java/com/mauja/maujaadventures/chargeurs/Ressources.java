package com.mauja.maujaadventures.chargeurs;

import vues.navigation.Fenetre;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Ressources {
    private static Ressources ressources;

    private List<String> lesCartes;
    private List<String> lesImagesEntites;
    private List<String> lesScripts;
    private Map<Fenetre, URL> lesVues;

    private String fichierTransitions;
    private String fichierTouches;

    public Ressources() {
        lesCartes = new ArrayList<>();
        lesImagesEntites = new ArrayList<>();
        lesScripts = new ArrayList<>();
        lesVues = new HashMap<>();
        initialiser();
    }

    public Map<Fenetre, URL> getLesVues() {
        return Collections.unmodifiableMap(lesVues);
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
        lesCartes.add(Objects.requireNonNull(new File("ressources/cartes/carteTest4.tmx").getAbsolutePath()));

        try {
            lesImagesEntites.add(Objects.requireNonNull(new File("images/entites/link_epee.png").toURI().toURL().toExternalForm()));
            lesImagesEntites.add(Objects.requireNonNull(new File("images/entites/projectile.png").toURI().toURL().toString()));

            lesScripts.add(Objects.requireNonNull(new File("ressources/scripts/testInteractions.xml").toString()));

            lesVues.put(Fenetre.MENU_PRINCIPAL, Objects.requireNonNull(Ressources.class.getResource("/fxml/MenuPrincipal.fxml")));
            lesVues.put(Fenetre.PARAMETRES, Objects.requireNonNull(Ressources.class.getResource("/fxml/Parametres.fxml")));
            lesVues.put(Fenetre.JEU, Objects.requireNonNull(Ressources.class.getResource("/fxml/Partie.fxml")));
            lesVues.put(Fenetre.MENU_PAUSE, Objects.requireNonNull(Ressources.class.getResource("/fxml/MenuPause.fxml")));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        fichierTransitions = Objects.requireNonNull(new File("ressources/transitions.txt").getAbsolutePath());
        fichierTouches = Objects.requireNonNull(new File("ressources/configurationTouches.txt").getAbsolutePath());
    }
}
