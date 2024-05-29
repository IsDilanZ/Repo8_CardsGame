module com.example.repo8_cardsgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.repo8_cardsgame to javafx.fxml;
    exports com.example.repo8_cardsgame;
}