package org.example.Actions;

import org.example.ActorsManager;
import org.example.IMDB;
import org.example.User;

import java.util.Scanner;

public class ActionSearchActor implements IMDBAction {

    private static final String displayableText = "Search actor by name";

    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numele actorului: ");
        String name = scanner.nextLine();
        for (Integer i = 0; i < ActorsManager.getActors().size(); i++) {
            if(name.equals(ActorsManager.getActors().get(i).getName())) {
                ActorsManager.getActors().get(i).displayInfo();
            }
        }
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
