package nl.inholland.javafx.UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.javafx.DataBase.DB;

public class LoginWindow {
    private DB db;

    public LoginWindow(DB db) {
        this.db = db;
        Stage loginWindow = new Stage();
        start(loginWindow);
    }

    private void start(Stage window){
        window.setHeight(200);
        window.setWidth(250);
        window.setTitle("Login");

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10); // Vertical spacing between grid items
        gridPane.setHgap(8); // Horizontal spacing between grid items

        Label userLabel = new Label ("Username:");
        GridPane.setConstraints(userLabel, 0, 0); // first column, first row

        TextField userInput = new TextField();
        System.out.println(userInput.getText());
        userInput.setPromptText("username");

        Label passwordLabel = new Label("Password:");
        PasswordField pwField = new PasswordField();
        pwField.setPromptText("Enter password");

        String password = pwField.getText();

        Button loginButton = new Button("Log in");

        GridPane.setConstraints(userLabel, 0, 0);
        GridPane.setConstraints(userInput, 1, 0);
        GridPane.setConstraints(passwordLabel, 0, 1);
        GridPane.setConstraints(pwField, 1, 1);
        GridPane.setConstraints(loginButton, 1, 2); // Right align in the grid

        gridPane.getChildren().addAll(userLabel, userInput, passwordLabel, pwField, loginButton);

        Scene scene = new Scene(gridPane);
        window.setScene(scene);
        window.show();
    }

}

