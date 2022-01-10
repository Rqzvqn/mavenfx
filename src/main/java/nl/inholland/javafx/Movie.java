package nl.inholland.javafx;

import java.time.Duration;

public class Movie<Private> {
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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    private String title;
    private double price;
    private Duration duration;

    public Movie(String title, double price, Duration duration) {
        this.title = title;
        this.price = price;
        this.duration = duration;
    }

    public String toString() {
        return "Title: " + title + ", Price: " + price + ", Duration: " + duration.toMinutes() + " minutes";
    }
}
