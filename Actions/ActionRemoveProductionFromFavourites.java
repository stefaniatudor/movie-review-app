package org.example.Actions;

import org.example.IMDB;
import org.example.Production;
import org.example.ProductionsManager;
import org.example.User;

import java.util.Scanner;

public class ActionRemoveProductionFromFavourites implements IMDBAction {

    private static final String displayableText = "Remove production from favourites";

    public void run(User loggedUser) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti titlul: ");
        String title = scanner.nextLine();

        Production movie = ProductionsManager.getProductionByName(title);
        if (movie != null) {
            if(loggedUser.getFavoriteProduction().contains(movie)) {
                loggedUser.removeFavorite(title);
                System.out.println("Productia a fost stearsa");
            }
            else {
                System.out.println("Productia nu exista in lista de favorite");
            }
        } else {
            System.out.println("Nu exista aceasta productie");

        }
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
