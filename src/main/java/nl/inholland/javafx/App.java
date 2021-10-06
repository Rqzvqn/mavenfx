package nl.inholland.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    TextField numerator, divider, outcome;
    Button calculate, reset;
    Label divisionSymbol = new Label ("/");
    Label equalSymbol = new Label ("=");
    Label errorLabel = new Label();
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(150);
        window.setWidth(800);
        window.setTitle("Calculator");

        VBox container = new VBox(10);
        container.setPadding(new Insets(10));
        HBox box = new HBox();
        box.setPadding (new Insets ( 10));
        box.setSpacing(10);

        numerator = new TextField();
        divider = new TextField();
        outcome = new TextField();

        calculate = new Button ("Calculate");
        reset = new Button ("Reset");


        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    int num = Integer.parseInt(numerator.getText());
                    int div = Integer.parseInt(divider.getText());
                    outcome.setText(String.valueOf(num/div));
                } catch (NumberFormatException e) {
                    System.out.println("This exception happened: " + e.getClass().getCanonicalName());
                    errorLabel.setText("Your input was not a number");
                }
            }
        });
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                numerator.clear();
                divider.clear();
                outcome.clear();
                errorLabel.setText("");
            }
        });

        box.getChildren().addAll(numerator,divisionSymbol,divider,equalSymbol,outcome,calculate,reset);
        container.getChildren().add(box);

        Scene scene = new Scene(container);
        window.setScene(scene);
        window.show();
    }
}
