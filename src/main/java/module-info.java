module comp20050.hexagonalboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens HexOust to javafx.fxml;
    exports HexOust;
    exports GameController.utils;
    opens GameController.utils to javafx.fxml;
}