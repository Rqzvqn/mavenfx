package nl.inholland.javafx;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ErrorBox extends HBox {
    Label displayErrorMessage;

    public ErrorBox() {
        displayErrorMessage = new Label("");
        createErrorBox();
    }

    public void createErrorBox() {
        this.setPadding(new Insets(10));
        this.getChildren().add(displayErrorMessage);
    }

    public void setDisplayErrorMessage(String errorMessage) {
        displayErrorMessage.setText("Something went wrong: " + errorMessage);
    }
}