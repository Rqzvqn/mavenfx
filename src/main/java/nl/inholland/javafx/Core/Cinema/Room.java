package nl.inholland.javafx.Core.Cinema;


import java.time.Duration;
import java.util.ArrayList;

public class Room {
    private static final Duration timeBreak = Duration.ofMinutes(15);

    private int roomNumber;
    private int numberOfSeats;
    private ArrayList<CinemaMovieShowing> showings;

    public int getRoomNumber() {
        return roomNumber;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    public ArrayList<CinemaMovieShowing> getShowings() {
        return showings;
    }
    public void addShowing(CinemaMovieShowing showing) {
        showings.add(showing);
    }
    public Duration getTimeBreak() {
        return timeBreak;
    }

    public Room(int roomNumber, int numberOfSeats) {
        this.roomNumber = roomNumber;
        this.numberOfSeats = numberOfSeats;
        this.showings = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Room %d", this.getRoomNumber());
    }
}