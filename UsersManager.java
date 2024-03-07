package org.example;

import java.util.List;

public class UsersManager {

    public static List<User> getUsers() {
        return IMDB.getInstance().getUsers();
    }

    public static void addUser(User u){
        IMDB.getInstance().getUsers().add(u);
    }

    public static void removeUser(User u){
        IMDB.getInstance().getUsers().remove(u);
    }

    public static User getUserByCredentials(Credentials credentials){
        for (User u:IMDB.getInstance().getUsers()){
            if(u.getUserInfo().getCredentials().compareTo(credentials) == 0)
                return u;
        }
        return null;
    }

    public static User getUserByUsername(String username){
        for (User u:IMDB.getInstance().getUsers()){
            if(u.getUsername().equals(username))
                return u;
        }
        return null;
    }

}
