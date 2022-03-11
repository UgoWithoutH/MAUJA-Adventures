package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.interactions.createurs.CreateurDAction;
import com.mauja.maujaadventures.interactions.createurs.CreateurDElementInteractif;
import com.mauja.maujaadventures.interactions.createurs.CreateurDeCondition;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractionHandler extends DefaultHandler {

    private List<Scenario> listeScenarios;
    private CreateurDElementInteractif createurDElementInteractif;
    private CreateurDAction createurDAction;
    private CreateurDeCondition createurDeCondition;
    private Balise baliseCourante;
    private Map<Integer, ElementInteractif> mapIdElemInteractif;

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
        mapIdElemInteractif = new HashMap<>();
    }

    //cette méthode est appelée lors de la détection d'un tag de début
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        Balise baliseParente = baliseCourante;

        if (qName.equalsIgnoreCase("Scenario")) {
            baliseCourante = new Scenario();
            baliseParente = baliseCourante;
        }

        try {
            if (qName.equalsIgnoreCase("ElementInteractif")){
                baliseCourante = createurDElementInteractif.creation(attributes);
                mapIdElemInteractif.put(Integer.parseInt(attributes.getValue("id")),(ElementInteractif)(baliseCourante));
            }
            if (qName.equalsIgnoreCase("Condition")){
                baliseCourante = createurDeCondition.creation(attributes);
            }
            if (qName.equalsIgnoreCase("Action")) {
                baliseCourante = createurDAction.creation(attributes);
            }
            if(Character.isLowerCase(qName.charAt(0))){

            }
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
    public void characters (char []ch, int start, int length)
    {

    }

    //cette méthode est appelée lors de la fin du traitement du document XML
    @Override
    public void endDocument ()
    {

    }
}
