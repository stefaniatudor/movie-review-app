package org.example.Actions;

import org.example.User;

public class ActionDisplayNotifications implements IMDBAction {

    private static final String displayableText = "View notifications";

    public void run(User loggedUser) {
        if(loggedUser.getNotifications().isEmpty())
            System.out.println("Nu aveti notificari");
        else {
            System.out.println("Notifications:");
            for (String notificare:loggedUser.getNotifications())
                System.out.println("    " + notificare);
        }
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
