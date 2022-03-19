package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.fenetres.Fenetre;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Ressources {
    private static Ressources ressources;
    private ConfigurateurEnvironnement configurateurEnvironnement;
    private final File nomDossierRessourcesProjet = new File("ressources");
    private String cheminIcone;

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
        configurateurEnvironnement = new ConfigurateurEnvironnement(lesFichiers, lesDossiersEtExtensions, nomDossierRessourcesProjet);
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

    public String getCheminIcone() {
        return cheminIcone;
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
        lesFichiers.add(new File(File.separator + "transitions.txt"));
        lesFichiers.add(new File(File.separator + "configurationTouches.txt"));
        lesDossiersEtExtensions.put(new File("cartes" + File.separator), ".tmx");
        lesDossiersEtExtensions.put(new File("scripts" + File.separator), ".xml");
        lesDossiersEtExtensions.put(new File("tilesets" + File.separator), ".tsx");
        lesDossiersEtExtensions.put(new File("images" + File.separator + "tilesets" + File.separator), ".png");

        try {
            cheminIcone = Objects.requireNonNull(Ressources.class.getResource("/images/icone.png")).toExternalForm();
            lesImagesEntites.add(Objects.requireNonNull(new File("images/entites/personnage.png").toURI().toURL().toExternalForm()));
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
