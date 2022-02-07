package vues.codebehind;

import com.mauja.maujaadventures.jeu.Jeu;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import vues.navigation.Navigateur;

public class Parametres {

    private Navigateur navigateur;
    @FXML
    private GridPane paramPane;
    @FXML
    private TextField zoneText;
    private Jeu jeu;

    public Parametres(Navigateur navigateur, Jeu jeu){
        this.navigateur = navigateur;
        this.jeu = jeu;
        Stage myStage = navigateur.getMyStage();
        myStage.widthProperty().addListener((listener) -> {
            paramPane.setPrefSize(myStage.getWidth()*0.70, myStage.getHeight()*0.70);
        });
    }

    @FXML
    public void initialize(){
        Stage myStage = navigateur.getMyStage();
        paramPane.setMaxSize(myStage.getWidth()*0.70, myStage.getHeight()*0.70);
        zoneText.textProperty().bindBidirectional(jeu.getTableauDeJeu().getOptions().paramProperty(),new NumberStringConverter());
    }

    @FXML
    public void retour(){
        paramPane.setVisible(false);
        jeu.setParamOuvert(false);
    }
}
