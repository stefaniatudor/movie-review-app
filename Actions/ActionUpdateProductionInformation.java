package org.example.Actions;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.*;
import org.graalvm.collections.Pair;

import java.util.*;

public class ActionUpdateProductionInformation implements IMDBAction {

    private static final String displayableText = "Update production information";

    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Introduceti numele productiei: ");
            String name = scanner.nextLine();
            Production production = ProductionsManager.getProductionByName(name);

            if (production != null) {
                ((Staff)loggedUser).updateProduction(production);
                return;
            }else {
                System.out.println("Productia nu a fost gasita");
            }
        }
    }

    @Override
    public String getDisplayableText () {
        return displayableText;
    }
}


