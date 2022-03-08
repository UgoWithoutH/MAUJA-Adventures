package com.mauja.maujaadventures.interactions;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParseurInteraction {
    private List<Scenario> scenarios;
    private List<ElementInteractif> elementAAjouter;

    public ParseurInteraction() {
        scenarios = new ArrayList<>();
        elementAAjouter = new ArrayList<>();
    }

    public List<ElementInteractif> getElementAAjouter() {
        return elementAAjouter;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void creerActionInteraction(String cheminFichier){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream inputStream = new FileInputStream(cheminFichier);
            SAXParser parseur = factory.newSAXParser();

            InteractionHandler handler = new InteractionHandler();

            parseur.parse(inputStream, handler);
            scenarios = handler.getListeScenarios();

            for(Scenario scenario : scenarios){ //temporaire
                for(ElementInteractif elementInteractif : scenario.getListeElemInteractif()){
                    if(elementInteractif instanceof Levier levier){
                        elementAAjouter.add(levier);
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
