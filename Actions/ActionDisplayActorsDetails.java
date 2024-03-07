package org.example.Actions;

import org.example.Actor;
import org.example.ActorsManager;
import org.example.IMDB;
import org.example.User;

public class ActionDisplayActorsDetails implements IMDBAction {

    private static final String displayableText = "View actors details";

    public void run(User loggedUser) {
        ActorsManager.getActors().forEach(Actor::displayInfo);
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
