package org.example.Actions;

import org.example.*;

import java.util.LinkedList;
import java.util.Scanner;

public class ActionRemoveRequest implements IMDBAction{
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
            System.out.print(i);
            requestsOfUser.get(i-1).displayInfo();
        }
        System.out.print("Remove request at index:");
        Integer indexOfRequest = Integer.parseInt(scanner.nextLine());
        if(loggedUser instanceof RequestsManager)
            ((RequestsManager) loggedUser).removeRequest(requestsOfUser.get(indexOfRequest));
    }

    @Override
    public String getDisplayableText() {
        return "Remove Requests";
    }
}
