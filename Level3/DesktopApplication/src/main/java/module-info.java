module com.example.desktopapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.desktopapplication to javafx.fxml;
    exports com.example.desktopapplication;
}