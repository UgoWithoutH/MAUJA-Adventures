package com.mauja.maujaadventures.interactions;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractionHandler extends DefaultHandler {

    private List<Scenario> listeScenarios;
    private Scenario scenarioCourant;
    private  ElementInteractif elementInteractifCourant;

    public List<Scenario> getListeScenarios() {
        return listeScenarios;
    }

    //cette méthode est appelée lors du début du traitement du document XML
    @Override
    public void startDocument() {
        listeScenarios = new ArrayList<>();
    }

    //cette méthode est appelée lors de la détection d'un tag de début
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("Scenario")) {

            // nouveau scénario
            scenarioCourant = new Scenario();
            listeScenarios.add(scenarioCourant);

        }
    }

    //cette méthode est appelée lors de la détection d'un tag de fin
    @Override
    public void endElement(String uri, String localName, String qName) {
        /*
        try {
            if (Class.forName(qName) == Class.forName("com.mauja.maujaadventures.interactions.ElementInteractif")) {
                elementInteractifCourant = (ElementInteractif) Class.forName(qName).newInstance();
                Map<Condition, Action> map = new HashMap<>();
                elementInteractifCourant.setMapConditionAction(map);
                //scenarioCourant.ajouterElementInteractif((ElementInteractif)elementInteractif);
            }
            if ()


        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        */

    }

    //cette méthode est appelée lors de la détection de données entre deux tags
    @Override
    public void characters (char ch[], int start, int length)
    {

    }

    //cette méthode est appelée lors de la fin du traitement du document XML
    @Override
    public void endDocument ()
    {

    }
}
