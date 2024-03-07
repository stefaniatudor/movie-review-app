package org.example.Actions;

import org.example.*;

import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ActionAddActor implements IMDBAction,ExperienceStrategy {

    private static final String displayableText = "Adaugati actor";

    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);
        Actor actor = new Actor();

        System.out.println("Introduceti numele actorului: ");
        String name = scanner.nextLine();

        Actor actorLista = ActorsManager.getActorByName(name);
        if (actorLista != null) {
            System.out.println("Actorul a fost deja adaugat");
        } else {
            if (!name.equals(null)) {
                actor.setName(name);
            }

            System.out.println("Introduceti biografia: ");
            String bio = scanner.nextLine();
            if (!bio.equals(null)) {
                actor.setBio(bio);
            }

            System.out.println("Doriti sa adugati productii? [Y\\N]: ");
            String option = scanner.nextLine();

            if (option.equals("Y")) {
                System.out.println("Introduceti numele productiei: ");
                String productionTitle = scanner.nextLine();

                Production production = ProductionsManager.getProductionByName(productionTitle);
                if (production != null) {
                    actor.addPerformances(production);
                } else {
                    System.out.println("Introduceti tipul productiei: ");
                    String type = scanner.nextLine();
                    if (type.equals("Movie")) {
                        actor.addPerformances(new Movie(productionTitle));
                    } else {
                        actor.addPerformances(new Series(productionTitle));
                    }
                }
                System.out.println("Doriti sa adaugati alta productie? [Y\\N]: ");
                String answer = scanner.nextLine();
                while (answer.equals("Y")) {
                    System.out.println("Introduceti numele productiei: ");
                    String nextProductionTitle = scanner.nextLine();

                    Production nextProduction = ProductionsManager.getProductionByName(productionTitle);
                    if (nextProduction != null) {
                        actor.addPerformances(nextProduction);
                    } else {
                        System.out.println("Introduceti tipul productiei: ");
                        String type = scanner.nextLine();
                        if (type.equals("Movie")) {
                            actor.addPerformances(new Movie(nextProductionTitle));
                        } else {
                            actor.addPerformances(new Series(nextProductionTitle));
                        }
                    }
                    System.out.println("Doriti sa adaugati alta productie? [Y\\N]: ");
                    answer = scanner.nextLine();
                }
            }

            ((Staff)loggedUser).addActorSystem(actor);
        }

        }

        @Override
        public String getDisplayableText () {
            return displayableText;
        }

    @Override
    public int calculateExperience() {
        return 15;
    }
}

