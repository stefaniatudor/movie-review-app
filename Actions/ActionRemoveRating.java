package org.example.Actions;

import org.example.*;

import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ActionRemoveRating implements IMDBAction{

    private static final String displayableText = "Remove rating";

    @Override
    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Introduceti titlul productiei: ");
            String title = scanner.nextLine();
            Production production = ProductionsManager.getProductionByName(title);
            if (production == null) {
                System.out.println("Nu exista productia");
            } else {
                for (Rating r : production.getRatings())
                    if (r.getUsername().equals(loggedUser.getUsername())) {
                        production.removeRating(r);
                        System.out.println("Rating-ul a fost sters cu succes");
                        return;
                    }
                System.out.print("Nu ati acordat un rating acestui film.Doriti sa continuati[Y/N]:");
                String option = scanner.nextLine();
                if(option.equals("Y"))
                    return;
            }
        }

    }

    @Override
    public String getDisplayableText () {
        return displayableText;
    }
}

