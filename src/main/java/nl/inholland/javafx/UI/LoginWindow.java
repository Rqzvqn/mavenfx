package nl.inholland.javafx.UI;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import nl.inholland.javafx.DataBase.DB;

import java.awt.event.KeyEvent;

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

        //StringProperty
        StringProperty passwordProperty = thePassword.textProperty();
        loginButton.setVisible(false);
        //add listener to property
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

        public void actionPerformed(ActionEvent ae) {
            String userName = userName_text.getText();
            String password = password_text.getText();
            if (userName.trim().equals("admin") && password.trim().equals("admin")) {
                message.setText(" Hello " + userName + "");
            } else {
                message.setText(" Invalid user.. ");
            }
        }

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                        if (txtUsername.getText().equals(user.getUsername())) {
                            if (thePassword.getText().equals(user.getPassword())) {
                                MainWindow mainWindow = new MainWindow(DB, user);
                                loginWindow.close();
                                return;
                            } else {
                                thePassword.clear();
                                thePassword.requestFocus();
                                throw new Exception("Incorrect password");
                                {
                                    lblErrorMessage.setVisible(true);
                                    lblErrorMessage.setText(rte.getMessage());
                                }
                            }
                        }
                    }

            }
        };

        //blah
         class Controller {
             PasswordField pass;
             TextField name;
             javafx.scene.control.Button login;
             Hyperlink signup;
             Label errormessage;

             private void buttonPressed() {
                checkUser();
            }
            //this is for "ENTER" not fixed"
             //private void ifEnterIsPressed(KeyEvent k) {
                //if (k.getCode() == KeyCode.ENTER)
                   // checkUser();
            }

             private void checkUser() {
                System.out.println(name.getCharacters());
                System.out.println(pass.getCharacters());
                if (name.getCharacters().equals("Marios") && pass.getCharacters().equals("19981998")) {
                    errormessage.setVisible(false);
                    System.out.println("Access granted!");
                }
                else {
                    errormessage.setText("Wrong username or password");
                    System.out.println("Access denied");
                }
            }
            GridPane.setConstraints(lblUsername, 0, 0);
        GridPane.setConstraints(txtUsername, 1, 0);
        GridPane.setConstraints(lblPassword, 0, 1);
        GridPane.setConstraints(thePassword, 1, 1);
        GridPane.setConstraints(loginButton, 0, 2);
        GridPane.setConstraints(lblErrorMessage, 0, 3, 1, 3);

        gridPane.getChildren().addAll(lblUsername, txtUsername, lblPassword, thePassword, loginButton, lblErrorMessage);

    Scene scene = new Scene(gridPane);
        LoginWindow.setScene(scene);
        LoginWindow.show();
}


