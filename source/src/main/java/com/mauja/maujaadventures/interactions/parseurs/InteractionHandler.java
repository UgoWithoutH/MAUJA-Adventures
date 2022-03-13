package com.mauja.maujaadventures.interactions.parseurs;

import com.mauja.maujaadventures.interactions.Scenario;
import com.mauja.maujaadventures.interactions.createurs.*;
import com.mauja.maujaadventures.interactions.elements.Balise;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import org.xml.sax.Attributes;
import org.xml.sax.ext.Attributes2Impl;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractionHandler extends DefaultHandler {

    private List<Scenario> listeScenarios;
    CreateurDObject createurDObject;
    private Balise baliseCourante;
    private Map<Integer, ElementInteractif> mapIdElemInteractif;
    private Attributes attributesPrimBaliseAcreer;
    private Map<String,Object> attributsNonPrimBaliseACreer;
    private String baliseEncours="";
    private Balise baliseParente;
    public List<Scenario> getListeScenarios() {
        return listeScenarios;
    }

    //cette méthode est appelée lors du début du traitement du document XML
    @Override
    public void startDocument() {
        listeScenarios = new ArrayList<>();
        createurDObject = new CreateurDObject();
        mapIdElemInteractif = new HashMap<>();
        attributsNonPrimBaliseACreer = new HashMap<>();
    }

    //cette méthode est appelée lors de la détection d'un tag de début
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if((baliseParente == null || !(Character.isLowerCase(qName.charAt(0))))){
            baliseParente = baliseCourante;
        }
        if (qName.equalsIgnoreCase("Scenario")) {
            baliseCourante = new Scenario();
            baliseEncours = qName;
            baliseParente = baliseCourante;
        }
        try {
            miseEnPlaceAttributs(qName, attributes);
            miseEnPlaceBaliseCourante(attributes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (baliseCourante != null) {
            baliseCourante.setBaliseParente(baliseParente);
        }

    }

    //cette méthode est appelée lors de la détection d'un tag de fin
    @Override
    public void endElement(String uri, String localName, String qName) {

        if (!Character.isLowerCase(qName.charAt(0))) {
            if (qName.equalsIgnoreCase("Scenario")) {
                listeScenarios.add((Scenario) baliseCourante);
            } else {
                if (!qName.equalsIgnoreCase("Carte")) {
                    baliseCourante.getBaliseParente().ajouter(baliseCourante);
                    baliseCourante = baliseCourante.getBaliseParente();
                }
            }
        }

    }

    //cette méthode est appelée lors de la détection de données entre deux tags
    @Override
    public void characters (char []ch, int start, int length)
    {

    }

    //cette méthode est appelée lors de la fin du traitement du document XML
    @Override
    public void endDocument ()
    {

    }

    private void miseEnPlaceBaliseCourante(Attributes attributes){
        if (attributes.getValue("end").equalsIgnoreCase("oui")){
            if (baliseEncours.equalsIgnoreCase("ElementInteractif")) {
                baliseCourante = (Balise) createurDObject.creation(attributesPrimBaliseAcreer,
                        attributsNonPrimBaliseACreer);
                attributsNonPrimBaliseACreer.clear();
                ((ElementInteractif)baliseCourante).setMapConditionAction(new HashMap<>());
                mapIdElemInteractif.put(Integer.parseInt(attributesPrimBaliseAcreer.getValue("id")),
                        (ElementInteractif)(baliseCourante));
            }
            if(baliseEncours.equalsIgnoreCase("Condition")){
                baliseCourante = (Balise) createurDObject.creation(attributesPrimBaliseAcreer, attributsNonPrimBaliseACreer);
            }
            if (baliseEncours.equalsIgnoreCase("Action")) {
                baliseCourante = (Balise) createurDObject.creation(attributesPrimBaliseAcreer, attributsNonPrimBaliseACreer);
            }
        }
    }

    private void miseEnPlaceAttributs(String qName, Attributes attributes){
        if(Character.isLowerCase(qName.charAt(0))){
            attributsNonPrimBaliseACreer.put(qName, createurDObject.creation(attributes, attributsNonPrimBaliseACreer));
        }
        else {
            if (qName.equalsIgnoreCase("ElementInteractif")) {
                baliseEncours = qName;
                attributesPrimBaliseAcreer = new Attributes2Impl(attributes);
            }
            if (qName.equalsIgnoreCase("Condition")) {
                baliseEncours = qName;
                attributesPrimBaliseAcreer = new Attributes2Impl(attributes);
            }
            if (qName.equalsIgnoreCase("Action")) {
                baliseEncours = qName;
                attributesPrimBaliseAcreer = new Attributes2Impl(attributes);
            }

        }
    }
}
