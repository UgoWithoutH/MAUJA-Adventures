import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class VueControlleur implements Initializable {

    @FXML
    private ImageView vueImage;

    private Image image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image = new Image(getClass().getResource("/images/carte2.png").toString());
        vueImage = new ImageView(image);
    }

    public void chargementImage(ActionEvent actionEvent) {
        vueImage.setImage(image);
    }
}
