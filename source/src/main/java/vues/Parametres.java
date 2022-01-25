package vues;

import com.mauja.maujaadventures.jeu.Options;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Parametres {

    private Navigateur navigateur;
    @FXML
    private GridPane paramPane;
    @FXML
    private TextField zoneText;
    @FXML
    private VBox content;
    private Options options;

    public Parametres(Navigateur navigateur, Options options){
        this.navigateur = navigateur;
        this.options = options;
    }

    @FXML
    public void initialize(){
        Stage stage = navigateur.getMyStage();
        zoneText.textProperty().bindBidirectional(options.paramProperty(),new NumberStringConverter());
    }

    @FXML
    public void retour(){
        paramPane.setVisible(false);
    }
}
