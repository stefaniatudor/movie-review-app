package org.example.Actions;

import org.example.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ActionCreateRequest implements IMDBAction{
    @Override
    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Request Type:");
        for (int i = 0; i < Request.RequestType.values().length; i++) {
            System.out.println(i + 1 + ". " + Request.RequestType.values()[i]);
        }
        int typeChoice = scanner.nextInt();
        Request.RequestType requestType = Request.RequestType.values()[typeChoice - 1];

        LocalDateTime creationDate = LocalDateTime.now();
        creationDate.format(Request.getFormatter());

        System.out.println("Enter Problem Description:");
        scanner.nextLine(); // Consume the newline character left by nextInt()
        String problemDescription = scanner.nextLine();

        String name = "";
        if(requestType.equals(Request.RequestType.ACTOR_ISSUE) || requestType.equals(Request.RequestType.MOVIE_ISSUE)){
            System.out.println("Enter Name :");
            name = scanner.next();
        }

        String creatorUsername = loggedUser.getUsername();

        // Input for Resolver Username
        System.out.println("Enter Resolver Username:");
        String resolverUsername = scanner.next();

        Request request;
        if (name.isEmpty()) {
            request = new Request(requestType, creationDate, problemDescription, creatorUsername, resolverUsername);
        } else {
            request = new Request(requestType, creationDate, name, problemDescription, creatorUsername, resolverUsername);
        }

        if(loggedUser instanceof RequestsManager) {
            ((RequestsManager) loggedUser).createRequest(request);
            User creatorUser = UsersManager.getUserByUsername(creatorUsername);
            UsersManager.getUserByUsername(resolverUsername).notify("Ati primit o cerere");
            request.addObserver(creatorUser);
        }
    }

    @Override
    public String getDisplayableText() {
        return "Create request";
    }
}
