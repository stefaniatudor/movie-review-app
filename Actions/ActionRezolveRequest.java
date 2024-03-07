package org.example.Actions;

import org.example.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ActionRezolveRequest implements IMDBAction, ExperienceStrategy {

    private Request requestToSolve;
    @Override
    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);

        LinkedList<Request> requestsOfUser = new LinkedList<>();
        for(Request r: RequestsHolder.getRequests())
            if(r.getResolverUsername().equals(loggedUser.getUsername())
                    || r.getResolverUsername().equals(loggedUser.getAccountType().toString())) {
                requestsOfUser.add(r);
            }
        for(int i=1;i<requestsOfUser.size();i++){
            System.out.print(i+":");
            requestsOfUser.get(i-1).displayInfo();
        }
        System.out.print("Rezolve request at index:");
        Integer indexOfRequest = Integer.parseInt(scanner.nextLine());
        requestToSolve = requestsOfUser.get(indexOfRequest-1);

        LinkedList<IMDBAction> actionsForSolve = null;
        switch (requestToSolve.getRequestType()){
            case OTHERS:
                actionsForSolve = (LinkedList<IMDBAction>) ActionManager.actionsOfUser.get(loggedUser.getAccountType());
                actionsForSolve.remove(actionsForSolve.size()-1);
                actionsForSolve.add(new ActionConfirmRequest());
                break;
            case ACTOR_ISSUE:
                actionsForSolve = new LinkedList<>(List.of(
                       new ActionAddActor(),
                       new ActionRemoveActor(),
                       new ActionUpdateActorInformation(),
                       new ActionConfirmRequest()
                ));
                break;
            case MOVIE_ISSUE:
                actionsForSolve = new LinkedList<>(List.of(
                        new ActionAddProduction(),
                        new ActionRemoveProduction(),
                        new ActionUpdateProductionInformation(),
                        new ActionConfirmRequest()
                ));
                break;
            case DELETE_ACCOUNT:
                actionsForSolve = new LinkedList<>(List.of(
                        new ActionRemoveUser(),
                        new ActionConfirmRequest()
                ));
        }

        Integer requestDoneValue = actionsForSolve.size();
        Integer option = 0;


        while (!option.equals(requestDoneValue)) {
            System.out.println("Available actions:");
            for (IMDBAction action : actionsForSolve) {
                System.out.println(actionsForSolve.indexOf(action) + 1 + ") " + action.getDisplayableText());
            }
            System.out.print("Choose action:");
            option = Integer.valueOf(scanner.nextLine());

            actionsForSolve.get(option - 1).run(loggedUser);
        }

        requestToSolve.notifyObservers("Cererea pe care ati facut-o a fost rezolvata");

        RequestsHolder.removeRequest(requestToSolve);
    }

    @Override
    public String getDisplayableText() {
        return "Rezolve requests";
    }

    @Override
    public int calculateExperience() {
        if(requestToSolve.getRequestType() == Request.RequestType.ACTOR_ISSUE ||
            requestToSolve.getRequestType() == Request.RequestType.MOVIE_ISSUE)
                return 20;
        return 0;
    }
}
