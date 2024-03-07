package org.example.Actions;

import org.example.User;

public interface IMDBAction {
    void run(User loggedUser);
    String getDisplayableText();

}
