import com.mauja.maujaadventures.interactions.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.*;

public class TestParsageInteraction {
    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream inputStream = new FileInputStream("ressources/interactionsTest.xml");
            SAXParser parseur = factory.newSAXParser();

            InteractionHandler handler = new InteractionHandler();

            parseur.parse(inputStream, handler);
            List<Scenario> scenarios = handler.getListeScenarios();
            for(Scenario scenario :  scenarios){
                System.out.println("passage");
                for(ElementInteractif e  : scenario.getListeElemInteractif()){
                    Iterator<Map.Entry<Condition,List<Action>>> it = e.getMapConditionAction().entrySet().iterator();
                    while(it.hasNext()){
                        Map.Entry<Condition,List<Action>> a = it.next();
                        if (a.getKey() instanceof ConditionCollision){
                            System.out.println("oui");
                        }
                    }
                }
            }
            System.out.println(scenarios);
            ElementInteractif elementInteractif = scenarios.get(0).getListeElemInteractif().get(0);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
