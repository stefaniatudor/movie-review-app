package org.example;


import org.example.Actions.ActionManager;
import org.example.Actions.IMDBAction;
import org.example.Visual.LoginWindow;
import org.example.Visual.MenuWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IMDB {

    public static User loggedUser = null;

    private static List<Actor> actors = new ArrayList<>();

    private static List<Production> productions = new ArrayList<>();


    private static List<User> users = new ArrayList<>();

    public static List<Actor> getActors() {
        return actors;
    }

    public static List<Production> getProductions() {
        return productions;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void main(String[] args) {
        IMDB.getInstance().run();
    }


    public List<Request> requests;

    public MenuWindow mainWindow;
    public LoginWindow loginWindow;

    private static RunType runType;

    private enum RunType {
        TERMINAL,
        VISUAL
    }

    private static final IMDB instance = new IMDB();

    private IMDB() {
    }

    public static IMDB getInstance() {
        return instance;
    }

    public void run() {
        // Încărcare date parsate din fișierele JSON
        try {
            loadDataFromJSON();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getRunType();

        // Autentificare utilizator
        authenticateUser();

        // Pornire flow aplicatiei in functie de rolul utilizatorului
        startApplicationFlow();
    }

    private void loadDataFromJSON() throws IOException {
        ProductionLoader.loadEntriesFromJSONFile(Utils.FileType.PRODUCTION);
        UserLoader.loadEntriesFromJSONFile(Utils.FileType.ACCOUNTS);
        ActorLoader.loadEntriesFromJSONFile(Utils.FileType.ACTORS);
        RequestLoader.loadEntriesFromJSONFile(Utils.FileType.REQUESTS);
    }

    public void authenticateUser() {
        if (runType == RunType.TERMINAL) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome back! Enter your credentials");
            while (loggedUser == null) {
                System.out.println();
                System.out.print("  email:");
                String email = scanner.nextLine();
                System.out.print("  password:");
                String password = scanner.nextLine();

                Credentials credentials = new Credentials(email, password);
                loggedUser = UsersManager.getUserByCredentials(credentials);
                if (loggedUser == null) {
                    System.out.print("Wrong Credentials. Enter your credentials again");
                } else {
                    System.out.println("Welcome back user " + loggedUser.getUsername());
                    System.out.println("User experience:" + loggedUser.getExperience());
                    System.out.println();
                }
            }
        } else if (runType.equals(RunType.VISUAL)) {
            loginWindow.revalidate();
            loginWindow.repaint();
            loginWindow.setVisible(true);

            // Wait for the login window to be disposed
            while (loginWindow.isVisible()) {
                try {
                    Thread.sleep(100); // Sleep for 100 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void reauthentificateUser() {
        if(runType.equals(RunType.TERMINAL)){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Do you want to re-authentificate?[Y/N]:");
            String option = scanner.nextLine();
            if (option.equals("Y")) {
                loggedUser = null;
                authenticateUser();
                startApplicationFlow();
            }
        } else if(runType.equals(RunType.VISUAL)){
            loggedUser = null;
            authenticateUser();
            startApplicationFlow();
        }

    }

    private void startApplicationFlow() {
        if (runType.equals(RunType.TERMINAL)) {
            List<IMDBAction> availableActions = ActionManager.actionsOfUser.get(loggedUser.getAccountType());
            Integer logoutValue = availableActions.size();
            Integer option = 0;

            Scanner scanner = new Scanner(System.in);

            while (!option.equals(logoutValue)) {
                System.out.println("Available actions:");
                for (IMDBAction action : availableActions) {
                    System.out.println(availableActions.indexOf(action) + 1 + ") " + action.getDisplayableText());
                }
                System.out.print("Choose action:");
                option = Integer.valueOf(scanner.nextLine());

                availableActions.get(option - 1).run(loggedUser);
                if(availableActions instanceof ExperienceStrategy)
                    loggedUser.addExperience(((ExperienceStrategy) availableActions).calculateExperience());
            }
            reauthentificateUser();
        } else {
            mainWindow = new MenuWindow();
            mainWindow.setVisible(true);
        }
    }

    private void getRunType() {
        System.out.print("In ce mod doriti sa rulati aplicatia Terminal sau Vizual [T\\V]:");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if (option.equals("T"))
            runType = RunType.TERMINAL;
        else if (option.equals("V")) {
            runType = RunType.VISUAL;
            loginWindow = new LoginWindow();
        }else {
            System.out.println("Valoare incorecta!");
            getRunType();
        }
    }
}
