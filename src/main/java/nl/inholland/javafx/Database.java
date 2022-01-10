package nl.inholland.javafx;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Showing> showings;
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie m) {
        movies.add(m);
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public void addShowing(Showing s) {
        showings.add(s);
    }

    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }


    public Database() {
        showings = new ArrayList<>();
        movies = new ArrayList<>();
        users = new ArrayList<>();
        movies.add(new Movie("No time to lie", 12.00, Duration.of(125, ChronoUnit.MINUTES)));
        movies.add(new Movie("The Addams Family 19", 9.00, Duration.of(92, ChronoUnit.MINUTES)));
        showings.add(new Showing("No time to lie", LocalDateTime.of(2021, 10, 9, 20, 0), Duration.of(125, ChronoUnit.MINUTES), 200, 12.00, 1));
        showings.add(new Showing("The Addams Family 19", LocalDateTime.of(2021, 10, 9, 22, 30), Duration.of(92, ChronoUnit.MINUTES), 200, 9.00, 1));
        showings.add(new Showing("No time to lie", LocalDateTime.of(2021, 10, 9, 22, 30), Duration.of(125, ChronoUnit.MINUTES), 200, 12.00, 2));
        showings.add(new Showing("The Addams Family 19", LocalDateTime.of(2021, 10, 9, 23, 50), Duration.of(92, ChronoUnit.MINUTES), 200, 9.00, 2));
        users.add(new User("Razvan", "P@ssword123"));
        users.add(new Admin("Chad", "P@ssword234"));
    }

    public void upDateShowings(Showing s, int index) {
        showings.set(index, s);
    }
}
