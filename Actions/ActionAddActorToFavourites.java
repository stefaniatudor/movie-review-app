package org.example.Actions;

import org.example.*;

import java.util.Scanner;

public class ActionAddActorToFavourites implements IMDBAction {

    private static final String displayableText = "Add actor to favourites";

    public void run(User loggedUser) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numele: ");
        String name = scanner.nextLine();

        Actor actor = ActorsManager.getActorByName(name);
        if (actor != null) {
            if(!loggedUser.getFavoriteActors().contains(actor)) {
                loggedUser.addFavorite(name);
                System.out.println("Actorul a fost adaugat");
            }
            else {
                System.out.println("Actorul este deja in lista de favorite");
            }
        } else {
            System.out.println("Nu exista acest actor");

        }
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
