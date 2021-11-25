package com.mauja.maujaadventures.modele.utilitaires;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class recuperateurRessources {

    private static final String SOURCE = "\\source\\src";

    public static ArrayList<String> getRessourcesString(){
        File f = new File(System.getProperty("user.dir")+SOURCE+"\\main\\resources\\com\\mauja\\maujaadventures");

        return new ArrayList<String>(Arrays.asList(f.list()));
    }

}
