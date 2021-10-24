package nl.inholland.javafx.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.inholland.javafx.Core.AppRoles.RolesEnum;
import nl.inholland.javafx.Core.AppRoles.User;
import nl.inholland.javafx.Core.WindowViews.MovieShowingsView;
import nl.inholland.javafx.Core.WindowViews.MoviesView;
import nl.inholland.javafx.Core.WindowViews.TicketView;
import nl.inholland.javafx.DataBase.DB;

public class MainWindow {

    DB db;
    User currentUser;

    //region Views
    TicketView ticketView;
    MoviesView MoviesView;
    MovieShowingsView manageShowingsView;
    //endregion

    //region Elements
    //Container(s)
    VBox vbxMainContainer;
    VBox vbxTicketView;
    VBox vbxManageMoviesView;
    VBox vbxManageShowingsView;

    //Scenes
    Scene scene;

    //Menu
    MenuBar menuBar;
    Menu userMenu, adminMenu, helpMenu, logoutMenu;
    MenuItem manageShowingsItem, manageMoviesItem, aboutItem, logoutItem, sellTicketsItem;
    //endregion

    public MainWindow(DB db, User loggedUser) {
        this.db = db;
        this.loggedUser = loggedUser;

        Stage window = new Stage();

        ticketView = new TicketView(db, window);
        MoviesView = new MoviesView(db);
        MovieShowingsView = new MovieShowingsView(db);

        loadWindow(window);
    }

    private void loadWindow(Stage window) {
        window.sizeToScene();
        window.setTitle("Fabulous Cinema -- -- Purchase Tickets -- username: " + loggedUser.getUsername());

        topMenu(loggedUser);

        vbxTicketView = ticketView.getView();
        vbxManageShowingsView = MovieShowingsView.getView();
        vbxManageMoviesView = MoviesView.getView();
        hideViews();

        vbxTicketView.setVisible(true);

        vbxMainContainer = new VBox();
        vbxMainContainer.getChildren().addAll(menuBar, vbxTicketView, vbxManageMoviesView, vbxManageShowingsView);

        scene = new Scene(vbxMainContainer);

        setEventHandlers(window);

        window.setScene(scene);
        window.show();
    }

    private void topMenu(User loggedUser) {
        menuBar = new MenuBar();
        sellTicketsItem = new MenuItem("Sell Tickets");

        if (loggedUser.getPermission() == RolesEnum.Admin) {
            adminMenu = new Menu("Admin");

            manageShowingsItem = new MenuItem("Manage Showings");
            manageMoviesItem = new MenuItem("Manage Movies");

            adminMenu.getItems().addAll(sellTicketsItem, manageShowingsItem, manageMoviesItem);
            menuBar.getMenus().add(adminMenu);
        } else {
            userMenu = new Menu("User");
            userMenu.getItems().add(sellTicketsItem);
            menuBar.getMenus().add(userMenu);
        }

        helpMenu = new Menu("Help");
        aboutItem = new MenuItem("About");

        logoutMenu = new Menu("Logout");
        logoutItem = new MenuItem("Logout...");

        helpMenu.getItems().add(aboutItem);
        logoutMenu.getItems().add(logoutItem);
        menuBar.getMenus().addAll(helpMenu, logoutMenu);
    }

    private void hideViews() {
        vbxTicketView.setVisible(false);
        vbxManageMoviesView.setVisible(false);
        vbxManageShowingsView.setVisible(false);
    }

    private void setEventHandlers(Stage window) {
        if (manageShowingsItem != null) {
            manageShowingsItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    hideViews();
                    vbxManageShowingsView = manageShowingsView.getView();
                    vbxManageMoviesView.setVisible(true);
                }
            });
        }

        if (manageShowingsItem != null) {
            manageMoviesItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    hideViews();
                    vbxManageMoviesView = MoviesView.getView();
                    vbxManageMoviesView.setVisible(true);
                }
            });
        }

        sellTicketsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideViews();
                vbxTicketView = ticketView.getView();
                vbxTicketView.setVisible(true);
            }
        });
    }

}