package vues;

import com.mauja.maujaadventures.jeu.Jeu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseMenu {
    private GridPane paramPane;
    private Navigateur navigateur;
    @FXML
    private VBox content;
    @FXML
    private Button reprendre;
    private Jeu jeu;
    private StackPane stackpane;

    public PauseMenu(Navigateur navigateur, Jeu jeu){
        this.navigateur = navigateur;
        this.jeu = jeu;
        Stage myStage = navigateur.getMyStage();
        myStage.widthProperty().addListener((listener) -> {
            content.setPrefSize(myStage.getWidth()*0.70, myStage.getHeight()*0.70);
        });
    }

    @FXML
    public void initialize(){
        Stage myStage = navigateur.getMyStage();
        content.setMaxSize(myStage.getWidth()*0.70, myStage.getHeight()*0.70);
    }

    public void setPane(StackPane stackPane){this.stackpane = stackPane;}

    public Button getReprendre(){return reprendre;}

    @FXML
    public void parametres(){
        if(paramPane == null){
            try {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/Parametres.fxml"));
                fxml.setController(new Parametres(navigateur, jeu));
                paramPane = fxml.load();
                stackpane.getChildren().add(paramPane);
                jeu.setParamOuvert(true);

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(paramPane.isVisible()){
            paramPane.setVisible(false);
            jeu.setParamOuvert(false);
        }
        else {
            paramPane.setVisible(true);
            jeu.setParamOuvert(true);
        }
    }

    @FXML
    public void reprendre(){
        content.setVisible(false);
        jeu.start();
    }

    @FXML
    public void sauvegarderQuitter(){
        stackpane.setVisible(false);
        jeu.stop();
        navigateur.naviguerVers("MainMenu.fxml",null);
    }
}
