module com.example.newapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens com.example.newapplication to javafx.fxml;
    exports com.example.newapplication;
}