package nl.inholland.javafx;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class SellTickets extends Window {
    public SellTickets(Stage stage, Database db, User user) {
        super(stage, db, user);
        createSellTickets(stage, db, user);
    }

    private void createSellTickets(Stage stage, Database db, User user) {
        GridPane sellTickets = new GridPane();
        sellTickets.setPadding(new Insets(10));
        sellTickets.setHgap(30);
        sellTickets.setVgap(5);
        Label displayRoom = new Label("Room: ");
        Label displayStart = new Label("Start: ");
        Label displayEnd = new Label("End: ");
        Label displayTitle = new Label("Title: ");
        Label nmbrOfSeats = new Label("Number of seats: ");
        ComboBox<Integer> cBoxSeats = new ComboBox<Integer>();
        cBoxSeats.getItems().addAll(numberGenerator());
        Label name = new Label("Name: ");
        TextField nameInput = new TextField();
        Button purchaseButton = new Button("Purchase");
        Button clearButton = new Button("Clear");
        GridPane.setConstraints(displayRoom, 0, 1);
        GridPane.setConstraints(displayStart, 0, 2);
        GridPane.setConstraints(displayEnd, 0, 3);
        GridPane.setConstraints(displayTitle, 0, 0);
        GridPane.setConstraints(nmbrOfSeats, 1, 1);
        GridPane.setConstraints(cBoxSeats, 2, 1);
        GridPane.setConstraints(name, 1, 2);
        GridPane.setConstraints(nameInput, 2, 2);
        GridPane.setConstraints(purchaseButton, 3, 1);
        GridPane.setConstraints(clearButton, 3, 2);
        sellTickets.getChildren().addAll(displayRoom, displayStart, displayEnd,
                displayTitle, nmbrOfSeats, cBoxSeats, name, nameInput, purchaseButton,
                clearButton);
        sellTickets.setVisible(false);

        ErrorBox errorBox = new ErrorBox();

        this.getChildren().addAll(sellTickets, errorBox);
        Scene scene = new Scene(this);
        stage.setScene(scene);
        stage.show();

        room1Showings.setOnMouseClicked(mouseEvent -> {
            //These methods fill the sellTickets form with the information from
            //the selected showing
            if (!sellTickets.isVisible()) {
                sellTickets.setVisible(true);
            }
            Showing showing = room1Showings.getSelectionModel().getSelectedItem();
            displayRoom.setText("Room:   Room 1");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");
            displayStart.setText("Start:   " + showing.getStart().format(formatter));
            displayEnd.setText("End:   " + showing.getEnd().format(formatter));
            displayTitle.setText("Title:   " + showing.getTitle());
        });
        room2Showings.setOnMouseClicked(mouseEvent -> {
            if (!sellTickets.isVisible()) {
                sellTickets.setVisible(true);
            }
            Showing showing = room2Showings.getSelectionModel().getSelectedItem();
            displayRoom.setText("Room:   Room 2");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm");
            displayStart.setText("Start:   " + showing.getStart().format(formatter));
            displayEnd.setText("End:   " + showing.getEnd().format(formatter));
            displayTitle.setText("Title:   " + showing.getTitle());
        });

        purchaseButton.setOnAction(actionEvent -> {
            try {
                if (nameInput.getText().equals("") || cBoxSeats.getSelectionModel().getSelectedIndex() == -1)
                    throw new Exception("Please enter your name and the amount of tickets you would like to purchase");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Confirm your purchase? ", ButtonType.OK, ButtonType.CANCEL);
                alert.setTitle("Confirm Purchase");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        confirmPurchase(cBoxSeats, db, errorBox);
                        room1Showings.refresh();
                        room2Showings.refresh();
                        cBoxSeats.getSelectionModel().clearSelection();
                        nameInput.clear();
                        sellTickets.setVisible(false);
                    } else if (rs == ButtonType.CANCEL) {
                    }
                });
            } catch (Exception e) {
                errorBox.setDisplayErrorMessage(e.getMessage());
            }
        });
        clearButton.setOnMouseClicked(mouseEvent -> {
            cBoxSeats.getSelectionModel().clearSelection();
            nameInput.clear();
            room1Showings.getSelectionModel().clearSelection();
            room2Showings.getSelectionModel().clearSelection();
            sellTickets.setVisible(false);
        });
    }

    public Integer[] numberGenerator() {
        Integer[] numbers = new Integer[200];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }

        return numbers;
    }

    public void confirmPurchase(ComboBox<Integer> cBoxSeats, Database db, ErrorBox errBox) {
        try {
            Showing showing = null;     //Retrieve the selected showing from table 1 or 2
            if (room1Showings.getSelectionModel().getSelectedItem() != null)
                showing = room1Showings.getSelectionModel().getSelectedItem();
            else if (room2Showings.getSelectionModel().getSelectedItem() != null)
                showing = room2Showings.getSelectionModel().getSelectedItem();

            int index = showings.indexOf(showing);
            showing.setSeats(cBoxSeats.getSelectionModel().getSelectedItem());
            db.upDateShowings(showing, index);
        } catch (Exception e) {
            errBox.setDisplayErrorMessage("Please choose an amount of seats");
        }
    }
}
