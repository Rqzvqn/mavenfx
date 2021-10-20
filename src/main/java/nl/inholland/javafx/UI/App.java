package nl.inholland.javafx.UI;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.inholland.javafx.DataBase.DB;

public class App extends Application {

    DB db;

    @Override
    public void start (Stage window) {
        DB db = new DB();
        LoginWindow loginWindow = new LoginWindow(db);
        window.close();
    }
}
