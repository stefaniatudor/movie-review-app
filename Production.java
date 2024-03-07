package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Production implements Comparable<Production>,SubjectOfObserver {

    @Override
    public void notifyObservers(String message) {
        observerList.forEach( o -> notifyObservers(message));
    }

    @Override
    public void addObserver(NotificationObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void deleteObserver(NotificationObserver observer) {
        observerList.remove(observer);
    }

    List<NotificationObserver> observerList = new ArrayList<>();
    private String title;
    private List<String> directors = new ArrayList<>();
    private List<String> actors = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    private List<Rating> ratings = new ArrayList<>();
    private String plot;
    private Integer releaseYear;

    public Production() {
    }

    public Production(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void addDirectors(String director) {
        this.directors.add(director);
    }

    public List<String> getActors() {
        return actors;
    }

    public void addActors(String actor) {
        this.actors.add(actor);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    public void removeRating(Rating rating) {
        this.ratings.remove(rating);
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Rating rating : ratings) {
            sum += rating.getValue();
        }

        return sum / ratings.size();
    }

    protected String getRatingsAsString() {
        StringBuilder ratingsString = new StringBuilder("[");
        for (Rating rating : getRatings()) {
            ratingsString.append("{")
                    .append("Username: ").append(rating.getUsername()).append(", ")
                    .append("Rating: ").append(rating.getValue()).append(", ")
                    .append("Comment: ").append(rating.getComments())
                    .append("}, ");
        }
        if (!getRatings().isEmpty()) {
            ratingsString.delete(ratingsString.length() - 2, ratingsString.length());
        }
        ratingsString.append("]");
        return ratingsString.toString();
    }

    protected String getGenresAsString() {
        StringBuilder genresString = new StringBuilder("[");
        for (Genre genre : getGenres()) {
            genresString.append(genre.name()).append(", ");
        }
        if (!getGenres().isEmpty()) {
            genresString.delete(genresString.length() - 2, genresString.length());
        }
        genresString.append("]");
        return genresString.toString();
    }

    public abstract void displayInfo();

}
