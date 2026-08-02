module com.example.return_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.return_3.main to javafx.fxml;
    exports com.example.return_3.main;
    exports com.example.return_3.test;
    exports com.example.return_3.Controllers;
    exports com.example.return_3.Controllers.puzzGame;
    exports com.example.return_3.Controllers.tictactoe;
    opens com.example.return_3.Controllers to javafx.fxml;
    opens com.example.return_3.Controllers.puzzGame to javafx.fxml;
    exports com.example.return_3.ui;
    opens com.example.return_3.ui to javafx.fxml;
}