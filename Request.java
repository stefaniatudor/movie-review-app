package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Request implements SubjectOfObserver{
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

    public enum RequestType {
        DELETE_ACCOUNT,
        ACTOR_ISSUE,
        MOVIE_ISSUE,
        OTHERS,
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private RequestType requestType;
    private LocalDateTime creationDate;
    private String name = null;
    private String problemDescription;
    private String creatorUsername;
    private String resolverUsername;

    public Request(RequestType requestType, LocalDateTime creationDate, String problemDescription, String creatorUsername, String resolverUsername) {
        this.requestType = requestType;
        this.creationDate = creationDate;
        this.problemDescription = problemDescription;
        this.creatorUsername = creatorUsername;
        this.resolverUsername = determineResolverUsername(resolverUsername);
    }

    public Request(RequestType requestType, LocalDateTime creationDate, String title, String problemDescription, String creatorUsername, String resolverUsername) {
        this(requestType,creationDate,problemDescription,creatorUsername,resolverUsername);
        this.name = title;
    }

    private String determineResolverUsername(String resolverUsername) {
        if (requestType == RequestType.DELETE_ACCOUNT || requestType == RequestType.OTHERS) {
            return "ADMIN";
        } else {
            return resolverUsername; // Cerere pentru ACTOR_ISSUE sau MOVIE_ISSUE
        }
    }

    public String getResolverUsername() {
        return resolverUsername;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void displayInfo() {
        System.out.println("Request Information:");
        System.out.println("Type: " + requestType);
        System.out.println("Creation Date: " + creationDate.format(formatter));
        if(name != null)
            System.out.println("Name: " + name );
        System.out.println("Problem Description: " + problemDescription);
        System.out.println("Creator Username: " + creatorUsername);
        System.out.println("Resolver Username: " + resolverUsername);
        System.out.println();
    }
}
