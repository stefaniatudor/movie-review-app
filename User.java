package org.example;

import com.sun.nio.sctp.Notification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class User implements Comparable<User>, NotificationObserver {
    private Information userInfo;
    private AccountType accountType;
    private String username;
    private int experience = -1;
    private List<String> notifications;
    private List<Production> favoriteProduction;
    private List<Actor> favoriteActors;


    public User(String username, AccountType accountType) {

        this.username = username;
        this.accountType = accountType;
        this.experience = 0;
        this.notifications = new ArrayList<>();
        this.favoriteProduction = new ArrayList<>();
        this.favoriteActors = new ArrayList<>();
    }

    public Information getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Information userInfo) {
        this.userInfo = userInfo;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getUsername() {
        return username;
    }

    public int getExperience() {
        return experience;
    }

    public void addExperience(int experience){
        this.experience += experience;
    }

    public void addNotification(String notification){
        notifications.add(notification);
    }
    public List<String> getNotifications() {
        return notifications;
    }

    public void addFavorite(Object item){
        if(item instanceof Actor){
            favoriteActors.add((Actor) item);
        } else if (item instanceof Production){
            favoriteProduction.add((Production) item);
        }
    }

    public List<Production> getFavoriteProduction() {
        return favoriteProduction;
    }

    public List<Actor> getFavoriteActors() {
        return favoriteActors;
    }

    public void removeFavorite(Object item){
        if(item instanceof Actor){
            favoriteActors.remove((Actor) item);
        } else if (item instanceof Production){
            favoriteProduction.remove((Production) item);
        }
    }

    public void updateExperience(int points) {
        if (accountType != AccountType.ADMIN) {
            experience += points;
        }
    }

    public void logout() {
        // Implementarea pentru delogarea utilizatorului
    }

    @Override
    public int compareTo(User otherUser) {
        // Implementarea pentru sortarea utilizatorilor în funcție de experiență
        return Integer.compare(otherUser.experience, this.experience);
    }

    public void displayInfo(){
        System.out.println("User Information:");
        System.out.println("Username: " + getUsername());
        System.out.println("Account Type: " + getAccountType());
        if(experience == -1)
            System.out.println("Experience: infinite");
        else
            System.out.println("Experience: "+getExperience());

        Information info = getUserInfo();
        if (info != null) {
            System.out.println("Full Name: " + info.getName());
            System.out.println("Country: " + info.getCountry());
            System.out.println("Age: " + info.getAge());
            System.out.println("Gender: " + info.getGender());
        }

        System.out.println("Favorite Productions:");
        for (Production production : getFavoriteProduction()) {
            System.out.println("- " + production.getTitle());
        }

        System.out.println("Favorite Actors:");
        for (Actor actor : getFavoriteActors()) {
            System.out.println("- " + actor.getName());
        }

        System.out.println("Notifications:");
        for (String n : getNotifications()) {
            System.out.println("- " + n);
        }
    };

    // Alte metode specifice clasei User  ??????

    public static class Information {
        private Credentials credentials;
        private String name;
        private String country;
        private Integer age;
        private String gender;
        private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private LocalDate birthDate;

        public Information() {
        }

        public Credentials getCredentials() {
            return credentials;
        }

        public void setCredentials(Credentials credentials) {
            this.credentials = credentials;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = LocalDate.parse(birthDate,DATE_TIME_FORMATTER);
        }

        // Internal InformationBuilder class

        }

}

