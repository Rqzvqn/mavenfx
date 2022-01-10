package nl.inholland.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Window extends VBox {
    protected List<Movie> movies;
    protected List<Showing> showings;

    protected TableView<Showing> room1Showings;
    protected TableView<Showing> room2Showings;

    public Window(Stage stage, Database db, User user) {
        showings = db.getShowings();
        movies = db.getMovies();
        openWindow(stage, db, user);
    }

    public void openWindow(Stage stage, Database db, User user) {
        stage.setTitle("Cinema - Purchase tickets - Username: " + user.getUserName());
        stage.setWidth(980);
        Menu menu = new Menu(stage, db, user);
        this.setPadding(new Insets(10));

        Label purchase = new Label("Purchase tickets");
        GridPane tableContainer = new GridPane();
        tableContainer.setPadding(new Insets(15));
        tableContainer.setHgap(10);
        Label room1 = new Label("Room1");
        Label room2 = new Label("Room2");
        room1Showings = createTables(showings, 1);
        room2Showings = createTables(showings, 2);
        tableContainer.getChildren().addAll(room1, room2, room1Showings, room2Showings);
        GridPane.setConstraints(room1, 0, 0);
        GridPane.setConstraints(room2, 1, 0);
        GridPane.setConstraints(room1Showings, 0, 1);
        GridPane.setConstraints(room2Showings, 1, 1);

        this.getChildren().addAll(menu, purchase, tableContainer);
    }

    public TableView<Showing> createTables(List<Showing> showings, int roomNumber) {
        TableView<Showing> table = new TableView<>();

        TableColumn<Showing, String> startColumn = new TableColumn<>("Start");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        TableColumn<Showing, String> endColumn = new TableColumn<>("End");
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        TableColumn<Showing, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Showing, String> seatsColumn = new TableColumn<>("Seats");
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
        TableColumn<Showing, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().addAll(startColumn, endColumn, titleColumn, seatsColumn, priceColumn);

        List<Showing> thisRoomsShowings = new ArrayList<>();
        for (Showing s :
                showings) {
            if (s.getRoom() == roomNumber) thisRoomsShowings.add(s);
        }
        ObservableList<Showing> roomShowings = FXCollections.observableArrayList(thisRoomsShowings);
        table.setItems(roomShowings);
        table.setMinWidth(457);

        return table;
    }
}
