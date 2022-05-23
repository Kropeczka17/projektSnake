module com.example.snake2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snake2 to javafx.fxml;
    exports com.example.snake2;
}