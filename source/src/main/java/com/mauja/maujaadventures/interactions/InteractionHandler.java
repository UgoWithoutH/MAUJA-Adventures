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
    private Balise baliseParente;
    private CreateurDElementInteractif createurDElementInteractif;
    private CreateurDAction createurDAction;
    private CreateurDeCondition createurDeCondition;
    private Balise baliseCourante;

    public List<Scenario> getListeScenarios() {
        return listeScenarios;
    }

    //cette méthode est appelée lors du début du traitement du document XML
    @Override
    public void startDocument() {
        listeScenarios = new ArrayList<>();
        createurDElementInteractif = new CreateurDElementInteractif();
        createurDAction = new CreateurDAction();
        createurDeCondition = new CreateurDeCondition();
    }

    //cette méthode est appelée lors de la détection d'un tag de début
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        baliseParente = baliseCourante;

        if (qName.equalsIgnoreCase("Scenario")) {
            baliseCourante = new Scenario();
            baliseParente = baliseCourante;
        }

        try {
            if (qName.equalsIgnoreCase("ElementInteractif")){
                baliseCourante = createurDElementInteractif.creation(attributes);
            }
            if (qName.equalsIgnoreCase("Condition")){
                baliseCourante = createurDeCondition.creation(attributes);
            }
            if (qName.equalsIgnoreCase("Action")){
                baliseCourante = createurDAction.creation(attributes);
            }
            if (baliseCourante != null) {
                baliseCourante.setBaliseParente(baliseParente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //cette méthode est appelée lors de la détection d'un tag de fin
    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equalsIgnoreCase("Scenario")){
            listeScenarios.add((Scenario)baliseCourante);
        }
        else{
            if (!qName.equalsIgnoreCase("Carte")) {
                baliseCourante.getBaliseParente().ajouter(baliseCourante);
                baliseCourante = baliseCourante.getBaliseParente();
            }
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
