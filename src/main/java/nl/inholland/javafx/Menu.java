package nl.inholland.javafx;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Menu extends HBox {
    public Menu(Stage stage, Database db, User user) {
        CreateMenu(stage, db, user);
    }

    private void CreateMenu(Stage stage, Database db, User user) {
        //for admin
        this.setPadding(new Insets(10));
        this.setSpacing(5);
        MenuItem option1 = new MenuItem("Manage showings");
        MenuItem option2 = new MenuItem("Manage movies");
        MenuItem option3 = new MenuItem("Sell tickets");
        MenuButton admin = new MenuButton("Admin", null, option1, option2, option3);
        Button help = new Button("Help");
        Button logout = new Button("Log out");
        this.getChildren().addAll(admin, help, logout);

        option1.setOnAction(mouseEvent -> {
            if (user instanceof Admin) {
                stage.close();
                ManageShowings manageShowings = new ManageShowings(stage, db, user);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("NotAdminAlert");
                alert.setHeaderText("Admins only!");
                alert.setContentText("This option can only be used by admins");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
            }
        });
        option2.setOnAction(mouseEvent -> {
            if (user instanceof Admin) {
                stage.close();
                ManageMovies manageMovies = new ManageMovies(stage, db, user);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("NotAdminAlert");
                alert.setHeaderText("Admins only!");
                alert.setContentText("This option can only be used by admins");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
            }
        });
        option3.setOnAction(actionEvent -> {
            stage.close();
            SellTickets sellTickets = new SellTickets(stage, db, user);
        });
        logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.close();
                Login login = new Login(new Stage(), db);
            }
        });
    }
}
