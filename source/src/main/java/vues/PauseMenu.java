package vues;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PauseMenu {

    @FXML
    private Pane pausePane;
    private Navigateur navigateur;

    public PauseMenu(Navigateur navigateur){
        this.navigateur = navigateur;
        Stage myStage = navigateur.getMyStage();

        myStage.widthProperty().addListener((listener) -> {
            pausePane.setPrefSize(myStage.getWidth()*0.70, myStage.getHeight()*0.70);
        });
    }

    @FXML
    public void initialize(){
    }
}
