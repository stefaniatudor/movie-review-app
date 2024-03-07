package org.example.Actions;

import org.example.User;
import org.example.UsersManager;

import java.util.Scanner;

public class ActionRemoveUser implements IMDBAction{


    @Override
    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Introduceti username-ul utilizatorului:");
            String username = scanner.nextLine();
            for(User u:UsersManager.getUsers()){
                if(u.getUsername().equals(username)){
                    UsersManager.removeUser(u);
                    System.out.println("User-ul a fost sters cu succes");
                    return;
                }
            }
            System.out.print("Nu exista acest user.Doriti sa continuati[Y/N]:");
            String option = scanner.nextLine();
            if(option.equals("Y"))
                return;
        }
    }

    @Override
    public String getDisplayableText() {
        return "Remove user";
    }
}
