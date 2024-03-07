package org.example.Actions;

import org.example.*;
import org.graalvm.collections.Pair;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.awt.SystemColor.text;

public class ActionUpdateActorInformation implements IMDBAction {

    private static final String displayableText = "Update actor information";

    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Introduceti numele actorului: ");
            String name = scanner.nextLine();
            Actor actor = ActorsManager.getActorByName(name);

            if(actor != null){
                ((Staff)loggedUser).updateActor(actor);
                return;
            }
            else {
                System.out.println("Actorul nu a fost gasit!");
            }
        }

    }

    @Override
    public String getDisplayableText () {
        return displayableText;
    }
}

