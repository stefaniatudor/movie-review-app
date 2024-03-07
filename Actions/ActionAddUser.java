package org.example.Actions;

import org.example.*;

import java.net.SocketTimeoutException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ActionAddUser implements IMDBAction {

    private static final String displayableText = "Add user";

    public void run(User loggedUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduceti numele de utilizator: ");
        String userName = scanner.nextLine();

        System.out.println("Introduceti tipul de user: (REGULAR,ADMIN,CONTRIBUTOR)");
        String userType = scanner.nextLine();
        AccountType type = AccountType.valueOf(userType.toUpperCase());

        User user = UserFactory.createUser(userName,type);

        System.out.println("Introduceti credentialele de autentificare");
        System.out.println("    email:");
        String email = scanner.nextLine();
        System.out.println("    password");
        String password = scanner.nextLine();

        Credentials credentials = new Credentials(email,password);

        System.out.println("Introduceti datele personale:");
        System.out.println("    Nume de familie:");
        String name = scanner.nextLine();
        System.out.println("    Tara:");
        String country = scanner.nextLine();
        System.out.println("    Varsta");
        Integer age = Integer.parseInt(scanner.nextLine());
        System.out.println("    Gen:");
        String gender = scanner.nextLine();
        System.out.println("    Data de nastere:");
        String birthDate = scanner.nextLine();

        InformationBuilder informationBuilder = new InformationBuilder()
                .setName(name)
                .setCountry(country)
                .setAge(age)
                .setGender(gender)
                .setBirthDate(birthDate)
                .setCredentials(credentials);

        user.setUserInfo(informationBuilder.build());

        UsersManager.addUser(user);
    }

    @Override
    public String getDisplayableText () {
        return displayableText;
    }
}

