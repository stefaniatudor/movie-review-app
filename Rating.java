package org.example;

import java.util.ArrayList;
import java.util.List;

public class Rating {
    private final String username;
    private final int value;  // Nota oferită, număr întreg din intervalul [1, 10]
    private final String comments;

    private NotificationObserver observer;

    public Rating(String username, int value, String comments) {
        this.username = username;
        this.value = validateRatingValue(value);
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public int getValue() {
        return value;
    }

    public String getComments() {
        return comments;
    }

    private int validateRatingValue(int value) {
        // Asigură că nota se află în intervalul [1, 10]
        if (value < 1 || value > 10) {
            throw new IllegalArgumentException("Rating value must be between 1 and 10");
        }
        return value;
    }

    public String toString() {
        return "Rating{" +
                "username='" + username + '\'' +
                ", value=" + value +
                ", comments='" + comments + '\'' +
                '}';
    }
}
