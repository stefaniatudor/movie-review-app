package org.example;


public class UserFactory {

    public static User createUser(String username, AccountType accountType) {
        switch (accountType) {
            case REGULAR:
                return new Regular(username);
            case CONTRIBUTOR:
                return new Contributor(username);
            case ADMIN:
                return new Admin(username);
            default:
                throw new IllegalArgumentException("Tip de utilizator necunoscut: " + accountType);
        }

    }


}
