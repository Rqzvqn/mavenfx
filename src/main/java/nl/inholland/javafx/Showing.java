package nl.inholland.javafx;

import java.time.Duration;
import java.time.LocalDateTime;

public class Showing extends Movie {
    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd() {
        this.end = getStart().plusMinutes(getDuration().toMinutes());
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) throws Exception {
        if (seats > this.seats) {
            throw new Exception("Not enough seats available.");
        } else {
            this.seats -= seats;
        }
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    private LocalDateTime start;
    private LocalDateTime end;
    private int seats;
    private int room;

    public Showing(String title, LocalDateTime start, Duration duration, int seats, double price, int room) {
        super(title, price, duration);

        this.start = start;
        setEnd();
        this.seats = seats;
        this.room = room;
    }
}
