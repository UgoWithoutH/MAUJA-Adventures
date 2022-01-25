package vues;

import com.mauja.maujaadventures.jeu.Options;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseMenu {

    @FXML
    private StackPane pausePane;
    private GridPane paramPane;
    private Navigateur navigateur;
    @FXML
    private VBox content;
    @FXML
    private Button reprendre;
    private Options options;

    public PauseMenu(Navigateur navigateur, Options options){
        this.navigateur = navigateur;
        this.options = options;
        Stage myStage = navigateur.getMyStage();

        myStage.widthProperty().addListener((listener) -> {
            content.setPrefSize(myStage.getWidth()*0.70, myStage.getHeight()*0.70);
        });
    }

    @FXML
    public void initialize(){
        Stage myStage = navigateur.getMyStage();
        pausePane.setMaxSize(myStage.getWidth()*0.70, myStage.getHeight()*0.70);
    }

    public Button getReprendre(){return reprendre;}

    @FXML
    public void parametres(){
        if(paramPane == null){
            try {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/Parametres.fxml"));
                fxml.setController(new Parametres(navigateur, options));
                paramPane = fxml.load();
                pausePane.getChildren().add(paramPane);

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(paramPane.isVisible()){
            paramPane.setVisible(false);
        }
        else {
            paramPane.setVisible(true);
        }
    }

    @FXML
    public void reprendre(){
        pausePane.setVisible(false);
    }

    @FXML
    public void sauvegarderQuitter(){
        pausePane.setVisible(false);
        navigateur.naviguerVers("MainMenu.fxml",new MainMenu(navigateur));
    }
}
