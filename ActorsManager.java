package org.example;

import java.util.List;

public class ActorsManager {

    public static List<Actor> getActors() {
        return IMDB.getInstance().getActors();
    }

    public static void addActors(Actor a){
        IMDB.getInstance().getActors().add(a);
    }

    public static void removeActors(Actor a){
        IMDB.getInstance().getActors().remove(a);
    }

    public static Actor getActorByName(String name){
        for(Actor a:IMDB.getInstance().getActors()){
            if(a.getName().equals(name))
                return a;
        }

        return null;
    }
}
