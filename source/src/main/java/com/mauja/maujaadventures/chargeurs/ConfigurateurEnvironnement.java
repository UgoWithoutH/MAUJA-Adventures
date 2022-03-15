package com.mauja.maujaadventures.chargeurs;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class ConfigurateurEnvironnement {
    private final String nomDossierConfiguration = ".mauja";
    private Map<File, String> lesDossiersEtExtensions;
    private List<File> lesFichiers;

    private String homeUtilisateur;
    private File dossierConfiguration;

    private Map<String, List<String>> lesFichiersDesDossiers;
    private Map<String, String> lesFichiersDeConfiguration;


    public ConfigurateurEnvironnement(List<File> lesFichiers, Map<File, String> lesDossiersEtExtensions) {
        if (lesFichiers == null) {
            throw new IllegalArgumentException("Les fichiers donnés en paramètre ne peuvent pas être null.");
        }
        if (lesDossiersEtExtensions == null) {
            throw new IllegalArgumentException("Les dossiers spécifiés en paramètre ne peuvent pas être null.");
        }
        this.lesFichiers = lesFichiers;
        this.lesDossiersEtExtensions = lesDossiersEtExtensions;
        lesFichiersDesDossiers = new HashMap<>();
        lesFichiersDeConfiguration = new HashMap<>();
    }

    public Map<String, List<String>> getLesFichiersDesDossiers() {
        return Collections.unmodifiableMap(lesFichiersDesDossiers);
    }

    public Map<String, String> getLesFichiersDeConfiguration() {
        return Collections.unmodifiableMap(lesFichiersDeConfiguration);
    }

    public void configure() {
        homeUtilisateur = System.getProperty("user.home");
        dossierConfiguration = new File(homeUtilisateur + File.separator + nomDossierConfiguration);
        try {
            creationEnvironnement(dossierConfiguration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void creationEnvironnement(File dossierRacineProjet) throws IOException {
        // Création du dossier principal si inexistant.
        creerDossier(dossierRacineProjet, nomDossierConfiguration);

        // Création de tous les fichiers de manière récursive.
        for (File fichier : lesFichiers) {
            if (fichier != null && fichier.exists() && fichier.isFile()) {
                File cheminFichier = new File(dossierRacineProjet.getAbsolutePath() + File.separator
                        + fichier.getName());
                // S'il n'existe pas dans le home de l'utilisateur, on copie celui du projet.
                if (!cheminFichier.exists()) {
                    Files.copy(fichier.toPath(), cheminFichier.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }

                // Dans tous les cas, on l'ajoute à notre liste de données.
                lesFichiersDeConfiguration.put(cheminFichier.getName(), cheminFichier.getAbsolutePath());
            }
        }

        // Parcours de tous les dossiers.
        for (Map.Entry<File, String> paire : lesDossiersEtExtensions.entrySet()) {
            // Création du dossier dans le home de l'utilisateur s'il n'existe pas.
            File cheminDossier = new File(dossierRacineProjet + File.separator + paire.getKey().getName());
            creerDossier(cheminDossier, paire.getKey().getName());

            // Appel d'une méthode pour charger tous les fichiers de chaque dossier qui respectent la bonne extension.
            List<File> lesFichiers = chargeFichiers(cheminDossier, paire.getValue());

            // Si aucun fichier n'a été trouvé dans le dossier, on importe ceux du projet.
            if (lesFichiers != null && lesFichiers.isEmpty()) {
                // On charge les fichiers du projet cette fois-ci.
                List<File> fichiersACopier = chargeFichiers(paire.getKey(), paire.getValue());
                for (File chemin : fichiersACopier) {
                    // On copie chaque fichier dans le home de l'utilisateur.
                    Files.copy(chemin.getAbsoluteFile().toPath(),
                            new File(cheminDossier + File.separator + chemin.getName()).toPath());
                }

                // On recharge le tout depuis son home.
                lesFichiers = chargeFichiers(cheminDossier, paire.getValue());
            }

            // On transforme le tout en string et on les ajoute à une collection.
            List<String> lesFichiersChaine = new ArrayList<>();
            assert lesFichiers != null;
            for (File chemin : lesFichiers) {
                lesFichiersChaine.add(chemin.getAbsolutePath());
            }
            lesFichiersDesDossiers.put(paire.getKey().getName(), lesFichiersChaine);
        }
    }

    private List<File> chargeFichiers(File dossier, String extension) {
        if (dossier == null || !dossier.exists() || !dossier.isDirectory()) {
            return null;
        }
        File[] fichiers = Objects.requireNonNull(dossier.listFiles(
                (dir, name) -> name.toLowerCase(Locale.ROOT).endsWith(extension)));

        return new ArrayList<>(Arrays.asList(fichiers));
    }

    private void creerDossier(File dossierACreer, String nom) throws IOException {
        if (!dossierACreer.exists()) {
            boolean resultat = dossierACreer.mkdirs();
            if (!resultat) {
                throw new FileSystemException("Impossible de créer le dossier " + nom
                        + " dans " + dossierACreer + " , veuillez rééssayer.");
            }
        }
    }

    private void creerFichier(File fichier) throws IOException {
        if (!fichier.exists()) {
            boolean resultat = fichier.createNewFile();
            if (!resultat) {
                throw new FileSystemException("Impossible de créer le fichier " + fichier
                        + ", veuillez rééssayer.");
            }
        }
    }
}
