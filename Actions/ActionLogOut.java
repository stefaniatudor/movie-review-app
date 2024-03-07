package org.example.Actions;

import org.example.User;

public class ActionLogOut implements IMDBAction {

    private static final String displayableText = "Logout";
    @Override
    public String getDisplayableText() {
        return displayableText;
    }

    public void run(User loggedUser) {

    }
}
