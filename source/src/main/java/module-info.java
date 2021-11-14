module com.mauja.maujaadventures {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.mauja.maujaadventures to javafx.fxml;
    exports com.mauja.maujaadventures;
}