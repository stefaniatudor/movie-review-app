package org.example.Actions;

import org.example.IMDB;
import org.example.Production;
import org.example.ProductionsManager;
import org.example.User;

import java.util.Scanner;

public class ActionAddProductionToFavourites implements IMDBAction {

    private static final String displayableText = "Add production to favourites";

    public void run(User loggedUser) {

                Scanner scanner = new Scanner(System.in);
                System.out.println("Introduceti titlul: ");
                String title = scanner.nextLine();

                Production production = ProductionsManager.getProductionByName(title);
                if (production != null) {
                    if(!loggedUser.getFavoriteProduction().contains(production)) {
                        loggedUser.addFavorite(title);
                        System.out.println("Productia a fost adaugata");
                    }
                    else {
                        System.out.println("Productia este deja in lista de favorite");
                    }
                } else {
                    System.out.println("Nu exista aceasta productie");

            }

            production.addObserver(loggedUser);
        }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
