package org.example.Actions;

import org.example.*;

import java.util.Scanner;

public class ActionRemoveActorFromFavourites implements IMDBAction {

    private static final String displayableText = "Remove actor from favourites";

    public void run(User loggedUser) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numele: ");
        String name = scanner.nextLine();

        Actor actor = ActorsManager.getActorByName(name);
        if (actor != null) {
            if(loggedUser.getFavoriteActors().contains(actor)) {
                loggedUser.removeFavorite(name);
                System.out.println("Actorul a fost sters");
            }
            else {
                System.out.println("Actorul nu exista in lista de favorite");
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
