package com.mauja.maujaadventures.utilitaires;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecuperateurRessources {

    public Map<String,URL> getRessourcesString(){
        Map<String,URL> map = new HashMap<>();
        File f = new File(System.getProperty("user.dir")+"/ressources/fxml");
        for(var file : Arrays.asList(f.list())){
            try {
                map.put(file,getClass().getResource("/fxml/"+file).toURI().toURL());
            } catch (MalformedURLException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
