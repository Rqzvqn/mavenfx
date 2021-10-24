package nl.inholland.javafx.Core.Cinema;

import java.time.LocalDateTime;

public class Ticket {
    private int NRroom;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String title;
    private String name;

    // to display
    //LocalTime time = LocalTime.now();
    //System.out.println(time);

    public int getRoom() {
        return NRroom;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public String getName() {
        return name;
    }


    public Ticket(int NRroom, LocalDateTime startTime, LocalDateTime endTime, String title, String name) {
        this.NRroom = NRroom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.name = name;
    }
}
