package com.mauja.maujaadventures.interactions.parseurs;

import com.mauja.maujaadventures.interactions.Scenario;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParseurInteraction {
    private List<Scenario> scenarios;

    public ParseurInteraction() {
        scenarios = new ArrayList<>();
    }

    public List<Scenario> getScenarios() {
        return Collections.unmodifiableList(scenarios);
    }

    public void creerActionInteraction(String cheminFichier) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream inputStream = new FileInputStream(cheminFichier);
            SAXParser parseur = factory.newSAXParser();

            InteractionHandler handler = new InteractionHandler();

            parseur.parse(inputStream, handler);
            scenarios = handler.getListeScenarios();
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
