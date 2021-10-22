package nl.inholland.javafx.UI;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    private void start(Stage loginWindow) {
        loginWindow.setHeight(180);
        loginWindow.setWidth(280);
        loginWindow.setTitle("Fabulous Cinema--Login");

        Label lblUsername = new Label("Username");
        TextField txtUsername = new TextField();
        Label lblPassword = new Label("Password");
        PasswordField thePassword = new PasswordField();
        Button loginButton = new Button("Log in");
        Label lblErrorMessage = new Label();

        txtUsername.setPromptText("Enter username");
        thePassword.setPromptText("Enter password");
        lblErrorMessage.setVisible(false);

        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10); // Vertical spacing between grid items
        gridPane.setHgap(8); // Horizontal spacing between grid items


        Label errorLabel = new Label();
        GridPane.setConstraints(errorLabel,1,2);

        //create a StringProperty
        StringProperty passwordProperty = thePassword.textProperty();
        loginButton.setVisible(false);
        //add the listener to this property
        passwordProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String oldValue, String newValue)
            {

                String error = lblErrorMessage.getText();
                String password = thePassword.getText();


                boolean validChars = password.matches(".*[a-zA-Z]+.*");
                boolean validNums = password.matches(".*[0-9]+.*");
                boolean validSymbol = password.matches(".*[!@#$%^&*()-+]");
                loginButton.setVisible(false);
                if(password.length() <= 8)
                {
                    error = "Please enter more than 8 characters";
                    errorLabel.setText(error);
                }
                else if(validChars == false && validNums && validSymbol)
                {
                    error = "You are missing a lower or an upper case letter";
                    errorLabel.setText(error);
                }
                else if (validSymbol == false && validChars && validNums)
                {
                    error = "You are missing a special character";
                    errorLabel.setText(error);
                }
                else if(validNums == false && validChars && validSymbol)
                {
                    error = "Your password must require at least a number character";
                    errorLabel.setText(error);
                }
                else if (validChars && validNums && validSymbol && password.length() >= 8)
                {
                    error = "";
                    errorLabel.setText(error);
                    loginButton.setVisible(true);
                }

            }
        });

        //button to next window

        GridPane.setConstraints(lblUsername, 0, 0);
        GridPane.setConstraints(txtUsername, 1, 0);
        GridPane.setConstraints(lblPassword, 0, 1);
        GridPane.setConstraints(thePassword, 1, 1);
        GridPane.setConstraints(loginButton, 0, 2);
        GridPane.setConstraints(lblErrorMessage, 0, 3, 1, 3);

        gridPane.getChildren().addAll(lblUsername, txtUsername, lblPassword, thePassword, loginButton, lblErrorMessage);

        Scene scene = new Scene(gridPane);
        loginWindow.setScene(scene);
        loginWindow.show();
    }

}


