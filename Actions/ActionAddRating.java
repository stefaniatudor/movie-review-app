package org.example.Actions;

import org.example.*;

import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ActionAddRating implements IMDBAction ,ExperienceStrategy{

    private static final String displayableText = "Add rating";

    @Override
    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduceti titlul productiei: ");
        String title = scanner.nextLine();
        Production production = ProductionsManager.getProductionByName(title);
        if(production == null) {
            System.out.println("Nu exista productia");
        }

        else {

            System.out.println("Introduceti nota: ");
            Integer value = Integer.parseInt(scanner.nextLine());

            System.out.println("Introduceti comentariu: ");
            String comment = scanner.nextLine();


            Rating rating = new Rating(loggedUser.getUsername(), value, comment);
            production.addObserver(loggedUser);
            String type;
            if(production instanceof Movie)
                type =  "Filmul";
            else
                type = "Serialul";
            production.notifyObservers(type + " \"" + production.getTitle() +"\" pe care l-ai adaugat a primit un review de la utilizatorul \"" + loggedUser.getUsername() + "\" -> " + value);
            production.addRating(rating);
        }

    }

    @Override
    public String getDisplayableText () {
        return displayableText;
    }

    @Override
    public int calculateExperience() {
        return 10;
    }
}

