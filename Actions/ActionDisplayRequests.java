package org.example.Actions;

import org.example.Request;
import org.example.RequestsHolder;
import org.example.User;

public class ActionDisplayRequests implements IMDBAction {

    private static final String displayableText = "View requests";

    public void run(User loggedUser) {
        for(Request r:RequestsHolder.getRequests())
            if(r.getResolverUsername().equals(loggedUser.getUsername())
                    || r.getResolverUsername().equals(loggedUser.getAccountType().toString()))
                r.displayInfo();
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
