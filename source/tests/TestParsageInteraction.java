import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.InteractionHandler;
import com.mauja.maujaadventures.interactions.Scenario;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.List;
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
            System.out.println(scenarios);
            ElementInteractif elementInteractif = scenarios.get(0).getListeElemInteractif().get(0);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
