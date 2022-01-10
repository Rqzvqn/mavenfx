package nl.inholland.javafx;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ManageMovies extends VBox {
    public ManageMovies(Stage stage, Database db, User user) {
        createManageMovies(stage, db, user);
    }

    public void createManageMovies(Stage stage, Database db, User user) {
        List<Movie> movies = db.getMovies();
        stage.setTitle("Cinema - Manage movies - Username: " + user.getUserName());

        Menu menu = new Menu(new Stage(), db, user);
        ErrorBox errorBox = new ErrorBox();

        Label manageMoviesLabel = new Label("Manage movies");
        ListView<Movie> movieList = new ListView<>();
        for (Movie m :
                movies) {
            movieList.getItems().add(m);
        }

        this.setPadding(new Insets(10));

        HBox addWindowForm = new HBox();
        addWindowForm.setPadding(new Insets(10));
        addWindowForm.setSpacing(10);
        Label movieTitle = new Label("Title: ");
        TextField movieTitleInput = new TextField();
        Label movieDuration = new Label("Duration in minutes: ");
        TextField movieDurationInput = new TextField();
        Label moviePrice = new Label("Price: ");
        TextField moviePriceInput = new TextField();
        Button addMovie = new Button("Add movie");
        Button clear = new Button("Clear");

        addWindowForm.getChildren().addAll(movieTitle, movieTitleInput, movieDuration, movieDurationInput,
                moviePrice, moviePriceInput, addMovie, clear);
        this.getChildren().addAll(menu, manageMoviesLabel, movieList, addWindowForm, errorBox);

        Scene scene = new Scene(this);
        stage.setScene(scene);
        stage.show();

        addMovie.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String title = movieTitleInput.getText();
                    double price = Double.parseDouble(moviePriceInput.getText());
                    Duration duration = Duration.of(Long.parseLong(movieDurationInput.getText()), ChronoUnit.MINUTES);

                    Movie newMovie = new Movie(title, price, duration);
                    db.addMovie(newMovie);

                    movieList.refresh();
                } catch (NumberFormatException e) {
                    errorBox.setDisplayErrorMessage("Please enter a valid duration and price");
                }
            }
        });
        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                movieTitleInput.clear();
                movieDurationInput.clear();
                moviePriceInput.clear();
            }
        });
    }
}
