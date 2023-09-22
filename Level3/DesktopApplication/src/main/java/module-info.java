module com.example.desktopapplication {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.desktopapplication to javafx.fxml;
    exports com.example.desktopapplication;
}