package org.example.Actions;

import org.example.User;

public class ActionConfirmRequest implements IMDBAction{
    @Override
    public void run(User loggedUser) {
    }

    @Override
    public String getDisplayableText() {
        return "Confirm Request Solved";
    }
}
