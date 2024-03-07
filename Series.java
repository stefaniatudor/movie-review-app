package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Series extends Production {
    private int numberOfSeasons;
    private Map<String, List<Episode>> seasons = new LinkedHashMap<>();  // Mapă cu numele sezonului și lista de episoade asociată

    public Series() {
    }

    public Series(String title) {
        super(title);
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public Map<String, List<Episode>> getSeasons() {
        return seasons;
    }

    public void setSeasons(Map<String, List<Episode>> seasons) {
        this.seasons = seasons;
    }

    public void addSeasons(String seasonName, List<Episode> episodes){
        seasons.put(seasonName,episodes);
    }

    @Override
    public void displayInfo() {
        System.out.println("Series Information:");
        System.out.println("Title: " + getTitle());
        System.out.println("Directors: " + String.join(", ", getDirectors()));
        System.out.println("Actors: " + String.join(", ", getActors()));
        System.out.println("Genres: " + String.join(", ", getGenresAsString()));
        System.out.println("Ratings: " + getRatingsAsString());
        System.out.println("Plot: " + getPlot());
        System.out.println("Average Rating: " + getAverageRating());
        System.out.println("Release Year: " + getReleaseYear());
        System.out.println("Number of Seasons: " + getNumberOfSeasons());

        System.out.println("Seasons:");
        for (Map.Entry<String, List<Episode>> entry : seasons.entrySet()) {
            System.out.println("  " + entry.getKey() + ":");
            for (Episode episode : entry.getValue()) {
                System.out.print("    Episode: " + episode.getTitle());
                System.out.println("    Duration: " + episode.getDuration());
            }
        }

        System.out.println();
    }

    @Override
    public int compareTo(@NotNull Production o) {
        return 0;
    }
}
