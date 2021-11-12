module com.mauja.maujaadventures {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mauja.maujaadventures to javafx.fxml;
    exports com.mauja.maujaadventures;
}