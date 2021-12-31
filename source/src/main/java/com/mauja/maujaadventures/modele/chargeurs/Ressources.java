package com.mauja.maujaadventures.modele.chargeurs;

import com.mauja.maujaadventures.modele.monde.JeuDeTuiles;
import com.mauja.maujaadventures.modele.utilitaires.Decoupeur;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ressources {
    private static final List<String> lesCartes = new ArrayList<>();
    static {

        lesCartes.add(new File("ressources/cartes/carteTest2.tmx").getAbsolutePath());
        System.out.println(lesCartes.get(0));

    }
    /**/
    private static final List<String> lesJeuxDeTuiles = new ArrayList<>();
    static {
        lesJeuxDeTuiles.add(new File("ressources/tilesets/hyptosis_tile-art-batch-5.tsx").getAbsolutePath());
    }

    private static final List<String> lesImagesJeuxDeTuiles = new ArrayList<>();
    static {
        try {
            lesImagesJeuxDeTuiles.add(new File("ressources/images/tilesets/hyptosis_tile-art-batch-5.png").toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static final List<String> lesImagesEntites = new ArrayList<>();
    static {
        try {
            lesImagesEntites.add(new File("/images/entites/link_epee.png").toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getLesCartes() {
        return lesCartes;
    }

    public static List<String> getLesJeuxDeTuiles() {
        return lesJeuxDeTuiles;
    }

    public static List<String> getLesImagesJeuxDeTuiles() {
        return lesImagesJeuxDeTuiles;
    }

    public static List<String> getLesImagesEntites() {
        return lesImagesEntites;
    }
}
