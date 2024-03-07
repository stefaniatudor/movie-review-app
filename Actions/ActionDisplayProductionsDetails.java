package org.example.Actions;

import org.example.IMDB;
import org.example.Production;
import org.example.ProductionsManager;
import org.example.User;

public class ActionDisplayProductionsDetails implements IMDBAction {

    private static final String displayableText = "View productions details";

    public void run(User loggedUser) {
        ProductionsManager.getProductions().forEach(Production::displayInfo);
    }

    @Override
    public String getDisplayableText() {
        return displayableText;
    }
}
