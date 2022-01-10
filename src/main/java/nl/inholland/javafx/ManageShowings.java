package nl.inholland.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ManageShowings extends Window {
    public ManageShowings(Stage stage, Database db, User user) {
        super(stage, db, user);
        createManageShowings(stage, db, user);
    }

    public void createManageShowings(Stage stage, Database db, User user) {
        stage.setTitle("Cinema - Manage showings - Username: " + user.getUserName());

        GridPane manageShowings = new GridPane();
        manageShowings.setPadding(new Insets(10));
        manageShowings.setHgap(30);
        manageShowings.setVgap(5);
        Label selectTitle = new Label("Movie title: ");
        ComboBox<String> titleInput = new ComboBox<>();
        titleInput.getItems().addAll(titleGenerator(movies));
        Label selectRoom = new Label("Room:");
        TextField roomInput = new TextField();
        Label displayPrice = new Label("Price:   ");
        Label selectStart = new Label("Start: ");
        DatePicker startDatePicker = new DatePicker();
        TextField startTimePicker = new TextField();
        Label displayEndDate = new Label("End: ");
        Button addShowing = new Button("Add showing");
        Button clearShowing = new Button("Clear");
        GridPane.setConstraints(selectTitle, 0, 0);
        GridPane.setConstraints(titleInput, 1, 0);
        GridPane.setConstraints(selectRoom, 0, 1);
        GridPane.setConstraints(roomInput, 1, 1);
        GridPane.setConstraints(displayPrice, 0, 2);
        GridPane.setConstraints(selectStart, 2, 0);
        GridPane.setConstraints(startDatePicker, 3, 0);
        GridPane.setConstraints(startTimePicker, 4, 0);
        GridPane.setConstraints(displayEndDate, 2, 1);
        GridPane.setConstraints(addShowing, 5, 0);
        GridPane.setConstraints(clearShowing, 5, 1);

        manageShowings.getChildren().addAll(selectTitle, titleInput, selectRoom, roomInput,
                displayPrice, selectStart, startDatePicker, startTimePicker, displayEndDate, addShowing,
                clearShowing);

        ErrorBox errorBox = new ErrorBox();

        this.getChildren().addAll(manageShowings, errorBox);
        Scene scene = new Scene(this);
        stage.setScene(scene);
        stage.show();

        titleInput.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                movies.stream().filter(movie ->
                        titleInput.getSelectionModel().getSelectedItem()
                                .equals(movie.getTitle())).findAny().ifPresent(selectedMovie -> displayPrice.setText("Price:   " + selectedMovie.getPrice()));

                displayEnd(startTimePicker, startDatePicker, titleInput, displayEndDate);
            }
        });
        startDatePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                displayEnd(startTimePicker, startDatePicker, titleInput, displayEndDate);
            }
        });
        startTimePicker.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                displayEnd(startTimePicker, startDatePicker, titleInput, displayEndDate);
            }
        });
        addShowing.setOnMouseClicked(mouseEvent -> {
            try {
                if (Integer.parseInt(roomInput.getText()) < 1 || Integer.parseInt(roomInput.getText()) > 2) {
                    throw new Exception("Enter a valid room number");
                }
                String timeString = startTimePicker.getText();
                String[] timeStrings = timeString.split(":");
                LocalTime startTime = LocalTime.of(Integer.parseInt(timeStrings[0]), Integer.parseInt(timeStrings[1]));
                LocalDateTime startDateTime = LocalDateTime.of(startDatePicker.getValue(), startTime);

                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String[] endDateTimeString = displayEndDate.getText().split(": ");
                LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString[1], format);

                int roomNumber = Integer.parseInt(roomInput.getText());
                for (Showing existingShowing :
                        showings) {
                    if ((startDateTime.isBefore(existingShowing.getEnd().plus(15, ChronoUnit.MINUTES)) ||
                            (endDateTime.isAfter(existingShowing.getStart().minus(15, ChronoUnit.MINUTES)) &&
                                    endDateTime.isBefore(existingShowing.getEnd().plus(15, ChronoUnit.MINUTES))) &&
                                    existingShowing.getRoom() == roomNumber)) {
                        throw new Exception("The showing overlaps with an existing showing!"); // overlapping / 15 min
                    }
                }

                String[] priceString = displayPrice.getText().split(": ");
                double price = Double.parseDouble(priceString[1]);
                //new showing to db
                Showing newShowing = new Showing(titleInput.getSelectionModel().getSelectedItem(), startDateTime, Duration.between(startDateTime, endDateTime), 200, price, roomNumber);
                db.addShowing(newShowing);

                room1Showings.refresh();
                room2Showings.refresh();
            } catch (Exception e) {
                errorBox.setDisplayErrorMessage(e.getMessage());
            }
        });

    }

    public String[] titleGenerator(List<Movie> movies) {
        String[] titles = new String[movies.size()];
        for (int i = 0; i < titles.length; i++)
            titles[i] = movies.get(i).getTitle();

        return titles;
    }

    public void displayEnd(TextField startTimePicker, DatePicker startDatePicker, ComboBox titleInput, Label displayEndDate) {
        //The method makes a LocalDateTime with the values the user selected...
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        String startTimeString = startTimePicker.getText();
        LocalTime startTime = LocalTime.parse(startTimeString, format);
        LocalDateTime startDateTime = LocalDateTime.of(startDatePicker.getValue(), startTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        movies.stream().filter(movie ->
                titleInput.getSelectionModel().getSelectedItem()
                        .equals(movie.getTitle())).findAny().ifPresent(selectedMovie -> displayEndDate.setText("End: " + startDateTime.plus(selectedMovie.getDuration()).format(formatter)));

    }
}
