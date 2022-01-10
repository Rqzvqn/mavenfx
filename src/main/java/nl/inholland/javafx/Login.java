package nl.inholland.javafx;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends VBox {
    public Login(Stage stage, Database db) {
        createLogin(stage, db);
    }

    private void createLogin(Stage stage, Database db) {
        List<User> users = db.getUsers();

        stage.setTitle("Cinema - Login");
        stage.setMinWidth(430);
        stage.setMinHeight(230);
        this.setPadding(new Insets(10));
        this.setSpacing(10);

        Label userLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");
        TextField usernameInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        Button loginButton = new Button("Log in");
        loginButton.setMinHeight(40);
        loginButton.setMinWidth(65);
        Label errorMessage = new Label("");

        GridPane loginScene = new GridPane();
        loginScene.setPadding(new Insets(15, 15, 15, 15));
        loginScene.setVgap(10);
        loginScene.setHgap(8);
        GridPane.setConstraints(userLabel, 0, 0);
        GridPane.setConstraints(passwordLabel, 0, 1);
        GridPane.setConstraints(usernameInput, 1, 0);
        GridPane.setConstraints(passwordInput, 1, 1);
        GridPane.setConstraints(loginButton, 0, 2);
        loginScene.getChildren().addAll(userLabel, passwordLabel, usernameInput,
                passwordInput, loginButton);

        this.getChildren().addAll(loginScene, errorMessage);

        Scene scene = new Scene(this);
        stage.setScene(scene);
        stage.show();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for (User u :
                        users) {
                    if (u.getUserName().equals(usernameInput.getText()) && u.getPassword().equals(passwordInput.getText())) {
                        stage.close();
                        SellTickets mainWindow = new SellTickets(new Stage(), db, u);
                    } else {
                        errorMessage.setText("Incorrect username or password");
                    }
                }
            }
        });
        StringProperty passwordFieldProperty = passwordInput.textProperty();
        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldValue, String newValue) {
                if (!isValidPassword(passwordInput.getText())) {
                    errorMessage.setText("A valid password has 8 characters, a number and " +
                            "a special character");
                } else {
                    errorMessage.setText("");
                }
            }
        });
    }

    public boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        boolean containsSpecialCharacter = matcher.find();

        return password.matches(".*\\d.*") && password.matches(".*[a-zA-Z]+.*") && containsSpecialCharacter && password.length() >= 8;
    }

}

