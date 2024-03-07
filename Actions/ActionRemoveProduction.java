package org.example.Actions;

import org.example.*;

import java.util.Scanner;

public class ActionRemoveProduction implements IMDBAction {

    private static final String displayableText = "Remove production";

    public void run(User loggedUser) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti titlul: ");
        String title = scanner.nextLine();

        ((Staff)loggedUser).removeProductionSystem(title);
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
