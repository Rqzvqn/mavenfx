package nl.inholland.javafx.Core.Cinema;

import java.time.LocalDateTime;

public class CinemaMovieShowing {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String title;
    private double price;
    private int availableTickets;
    private int numberOfSeats;
    Movie movie;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public CinemaMovieShowing(LocalDateTime startTime, Movie movie, int availableTickets, Room room) {
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(movie.getDuration().toMinutes());
        this.movie = movie;
        this.title = movie.getTitle();
        this.availableTickets = room.getNumberOfSeats();
        this.price = movie.getPrice();
        this.numberOfSeats = room.getNumberOfSeats();
    }


    public void deductAvailableTickets(int numberOfTickets) {
        availableTickets -= numberOfTickets;
    }
}
