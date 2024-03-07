package org.example.Actions;

import org.example.IMDB;
import org.example.ProductionsManager;
import org.example.User;

import java.util.Scanner;

public class ActionSearchProduction implements IMDBAction {

    private static final String displayableText = "Search production by title";

    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti titlul: ");
        String title = scanner.nextLine();
        for (Integer i = 0; i < ProductionsManager.getProductions().size(); i++) {
            if(title.equals(ProductionsManager.getProductions().get(i).getTitle())) {
                ProductionsManager.getProductions().get(i).displayInfo();
            }
        }
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
