package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.fenetres.Fenetre;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Ressources {
    private static Ressources ressources;
    private ConfigurateurEnvironnement configurateurEnvironnement;

    private List<File> lesFichiers;
    private Map<File, String> lesDossiersEtExtensions;

    private List<String> lesCartes;
    private List<String> lesScripts;
    private List<String> lesImagesEntites;
    private Map<Fenetre, URL> lesVues;

    public Ressources() {
        lesImagesEntites = new ArrayList<>();
        lesVues = new HashMap<>();
        lesFichiers = new ArrayList<>();
        lesDossiersEtExtensions = new HashMap<>();
        initialiser();
        configurateurEnvironnement = new ConfigurateurEnvironnement(lesFichiers, lesDossiersEtExtensions);
        configurateurEnvironnement.configure();
        lesCartes = configurateurEnvironnement.getLesFichiersDesDossiers().get("cartes");
        lesScripts = configurateurEnvironnement.getLesFichiersDesDossiers().get("scripts");
    }

    public Map<Fenetre, URL> getLesVues() {
        return Collections.unmodifiableMap(lesVues);
    }

    public String getCheminTouches() {
        return configurateurEnvironnement.getLesFichiersDeConfiguration().get("configurationTouches.txt");
    }

    public String getCheminTransitions() {
        return configurateurEnvironnement.getLesFichiersDeConfiguration().get("transitions.txt");
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
        File fichierTransitions = new File("ressources" + File.separator + "transitions.txt");
        File fichierTouches = new File("ressources" + File.separator + "configurationTouches.txt");
        lesFichiers.add(fichierTransitions);
        lesFichiers.add(fichierTouches);

        File dossierCartes = new File("ressources" + File.separator + "cartes" + File.separator);
        File dossierScripts = new File("ressources" + File.separator + "scripts" + File.separator);
        lesDossiersEtExtensions.put(dossierCartes, ".tmx");
        lesDossiersEtExtensions.put(dossierScripts, ".xml");

        try {
            lesImagesEntites.add(Objects.requireNonNull(new File("images/entites/link_epee.png").toURI().toURL().toExternalForm()));
            lesImagesEntites.add(Objects.requireNonNull(new File("images/entites/projectile.png").toURI().toURL().toString()));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        lesVues.put(Fenetre.MENU_PRINCIPAL, Objects.requireNonNull(Ressources.class.getResource("/fxml/MenuPrincipal.fxml")));
        lesVues.put(Fenetre.PARAMETRES, Objects.requireNonNull(Ressources.class.getResource("/fxml/Parametres.fxml")));
        lesVues.put(Fenetre.MENU_PAUSE, Objects.requireNonNull(Ressources.class.getResource("/fxml/MenuPause.fxml")));
    }
}
