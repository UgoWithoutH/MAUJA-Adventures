package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.entites.Ennemi;

import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractionHandler extends DefaultHandler {

    private List<Scenario> listeScenarios;
    private Scenario scenarioCourant;
    private  ElementInteractif elementInteractifCourant;
    private Balise baliseParente;
    private Balise baliseCourante;

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
            baliseCourante = new Scenario();
            baliseParente = baliseCourante;
        }

        try {
            if (qName.equalsIgnoreCase("ElementInteractif")){
                Constructor[] constructors = Class.forName(attributes.getValue("type")).getConstructors();
                Map<Condition, Action> map = new HashMap<>();
                baliseParente = baliseCourante;
                baliseCourante = (ElementInteractif)constructors[0].newInstance(new Position(
                                Double.parseDouble(attributes.getValue("x")),
                                Double.parseDouble(attributes.getValue("y"))),
                        new Dimension(Double.parseDouble(attributes.getValue("largeur")),
                                Double.parseDouble(attributes.getValue("hauteur"))),
                        new Rectangle(10, 10, 10, 10), null, null, null,
                        Integer.parseInt(attributes.getValue("ptsVie")));
                ((ElementInteractif)baliseCourante).setMapConditionAction(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //cette méthode est appelée lors de la détection d'un tag de fin
    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equalsIgnoreCase("Scenario")){
            listeScenarios.add((Scenario)baliseParente);
        }
        if (qName.equalsIgnoreCase("ElementInteractif")) {
            baliseParente.ajouter(baliseCourante);
            baliseCourante = baliseParente;
        }



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
