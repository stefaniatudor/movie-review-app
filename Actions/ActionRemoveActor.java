package org.example.Actions;

import org.example.*;

import java.util.Scanner;

public class ActionRemoveActor implements IMDBAction {

    private static final String displayableText = "Remove Actor";

    public void run(User loggedUser) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numele: ");
        String name = scanner.nextLine();

        ((Staff)loggedUser).removeActorSystem(name);
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
