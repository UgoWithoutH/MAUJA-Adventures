package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.entites.EtatAction;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

public class GestionnaireInteractions {
    private Queue<ElementInteractif> file;
    private static GestionnaireInteractions gestionnaireInteractions;
    private static  List<Scenario> scenarios;

    private GestionnaireInteractions() {
        file = new LinkedList<>();
        initialisation();
    }

    public static GestionnaireInteractions getInstance(){
        if(gestionnaireInteractions == null){
            return new GestionnaireInteractions();
        }
        else{
            return gestionnaireInteractions;
        }
    }

    private void initialisation(){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream inputStream = new FileInputStream("ressources/interactionsTest.xml");
            SAXParser parseur = factory.newSAXParser();

            InteractionHandler handler = new InteractionHandler();

            parseur.parse(inputStream, handler);
            scenarios = handler.getListeScenarios();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterElement(ElementInteractif elementInteractif) {
        file.add(elementInteractif);
        traitement();
    }

    private void traitement() {
        ElementInteractif element = file.poll();
        if(element instanceof PersonnageJouable personnage){
            for(Scenario scenario :  scenarios){
                for(ElementInteractif e  : scenario.getListeElemInteractif()){
                    if(e instanceof Levier levier) {
                        Iterator<Map.Entry<Condition, List<Action>>> it = e.getMapConditionAction().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry<Condition, List<Action>> a = it.next();
                            if (a.getKey() instanceof ConditionCollision) {
                                if(personnage.getEtatAction() == EtatAction.ATTAQUE){
                                    Dimension dim = personnage.getCollision().getDimension();
                                    Position pos = personnage.getCollision().getPosition();
                                    Rectangle rectanglePerso = new Rectangle((int) pos.getX(), (int) pos.getY(), (int) dim.getLargeur(), (int) dim.getHauteur());
                                    if (!levier.isActive() &&
                                            rectanglePerso.intersects(levier.getPosition().getX(),levier.getPosition().getY(),Levier.getLargeurDefaut(),Levier.getHauteurDefaut())) {
                                        levier.setActive(true);
                                        for (Action action : a.getValue()) {
                                            action.agit();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
