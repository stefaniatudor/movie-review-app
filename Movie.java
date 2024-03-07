package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.List;

    public class Movie extends Production {
        private Double duration;

        public Movie() {
        }

        public Movie(String title) {
            super(title);
        }

        public Double getDuration() {
            return duration;
        }

        public void setDuration(Double duration) {
            this.duration = duration;
        }

        @Override
        public void displayInfo() {
            System.out.println("Movie Information:");
            System.out.println("Title: " + getTitle());
            System.out.println("Directors: " + String.join(", ", getDirectors()));
            System.out.println("Actors: " + String.join(", ", getActors()));
            System.out.println("Genres: " + String.join(", ", getGenresAsString()));
            System.out.println("Ratings: " + getRatingsAsString());
            System.out.println("Plot: " + getPlot());
            System.out.println("Average Rating: " + getAverageRating());
            System.out.println("Release Year: " + getReleaseYear());
            System.out.println("Duration: " + getDuration());
            System.out.println();
        }

        @Override
        public int compareTo(@NotNull Production o) {
            return 0;
        }
    }
