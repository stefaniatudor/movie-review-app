package org.example;

public class Episode {
    private final String title;
    private final Double duration;


    public Episode(String title, Double duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public Double getDuration() {
        return duration;
    }

}
