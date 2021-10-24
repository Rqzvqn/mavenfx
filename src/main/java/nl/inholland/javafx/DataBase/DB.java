package nl.inholland.javafx.DataBase;

import nl.inholland.javafx.Core.AppRoles.Admin;
import nl.inholland.javafx.Core.AppRoles.RolesEnum;
import nl.inholland.javafx.Core.AppRoles.User;
import nl.inholland.javafx.Core.Cinema.CinemaMovieShowing;
import nl.inholland.javafx.Core.Cinema.Movie;
import nl.inholland.javafx.Core.Cinema.Room;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public List<Movie> movies;
    public List<User> users;
    public Room room1;
    public Room room2;

    public Room getRoom1() {
        return room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        //create users
        User user1 = new User("Razvan", "P@ssword123", RolesEnum.Normal);
        User user2 = new User("SnoopDog", "P@ssword234",RolesEnum.Normal);
        Admin admin = new Admin("Chad", "P@ssword345", RolesEnum.Admin);

        //add users to the list
        users.add(user1);
        users.add(user2);
        users.add(admin);

        //return the list
        return users;
    }

/*
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        Movie noTimeToLie = new Movie("No time to lie", 12.00, Duration.ofMinutes(Long.parseLong("125")));
        movies.add(noTimeToLie);
        Movie theAddamsFamily19 = new Movie("The Addams Family 19", 9.00, Duration.ofMinutes(Long.parseLong("92")));
        movies.add(theAddamsFamily19);

        movies.add(noTimeToLie);
        movies.add(theAddamsFamily19);

        return movies;
    }
 */

    public  DB {

    movies = new ArrayList<>();
    users = new ArrayList<>();
    movies.addAll(getMovies());
    users.addAll(getUsers());
        movies = getMovies();
    room1 = new Room(1, 200);
    room2 = new Room(2, 100);
        addMovie(new Movie("No time to lie", 12.00, Duration.ofMinutes(Long.parseLong("125"))));
        addMovie(new Movie("The Addams Family 19", 9.00, Duration.ofMinutes(Long.parseLong("92"))));
    addfirstShowings();

        public List<Movie> getMovies() {
            return readMovies();
        }

        public void addMovie(Movie movie) {
            movies.add(movie);
        }
    /*
      Showing noTimeToLie_Room1_Start09102021_2000_End09102021_2205 = new Showing(1, LocalDateTime.of(2021, 10, 9, 20, 0), LocalDateTime.of(2021, 10, 9, 22, 5), room1, noTimeToLie, 200);
       showings.add(noTimeToLie_Room1_Start09102021_2000_End09102021_2205);
      Showing theAddamsFamily19_Room1_Start09102021_2230_End10102021_0002 = new Showing(2, LocalDateTime.of(2021, 10, 9, 22, 30), LocalDateTime.of(2021, 10, 10, 0, 2), room1, theAddamsFamily19, 200);
       showings.add(theAddamsFamily19_Room1_Start09102021_2230_End10102021_0002);
       Showing theAddamsFamily19_Room2_Start09102021_2000_End09102021_2132 = new Showing(3, LocalDateTime.of(2021, 10, 9, 20, 0), LocalDateTime.of(2021, 10, 9, 21, 32), room2, theAddamsFamily19, 100);
        showings.add(theAddamsFamily19_Room2_Start09102021_2000_End09102021_2132);
       Showing noTimeToLie_Room2_Start09102021_2200_End10102021_0005 = new Showing(4, LocalDateTime.of(2021, 10, 9, 22, 0), LocalDateTime.of(2021, 10, 10, 0, 5), room2, noTimeToLie, 100);
        showings.add(noTimeToLie_Room2_Start09102021_2200_End10102021_0005);
      */

}


    private void addMovie(Movie no_time_to_lie) {
    }

    private void addfirstShowings() {
        //Room1
        room1.addShowing(
                new CinemaMovieShowing(
                        LocalDateTime.parse("09-10-2021 20:00", dateTimeFormatter),
                        movies.get(0),
                        room1.getNumberOfSeats(),
                        room1
                )
        );
        room1.addShowing(
                new CinemaMovieShowing(
                        LocalDateTime.parse("10-10-2021 22:30", dateTimeFormatter),
                        movies.get(1),
                        room1.getNumberOfSeats(),
                        room1
                )
        );

        //Room2
        room2.addShowing(
                new CinemaMovieShowing(
                        LocalDateTime.parse("09-10-2021 20:00", dateTimeFormatter),
                        movies.get(1),
                        room1.getNumberOfSeats(),
                        room2
                )
        );
        room2.addShowing(
                new CinemaMovieShowing(
                        LocalDateTime.parse("09-10-2021 22:00", dateTimeFormatter),
                        movies.get(0),
                        room1.getNumberOfSeats(),
                        room2
                )
        );
    }


}
