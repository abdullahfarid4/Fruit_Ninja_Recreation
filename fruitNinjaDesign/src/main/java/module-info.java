module com.example.fruitninjadesign {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fruitninjadesign to javafx.fxml;
    exports com.example.fruitninjadesign;
}